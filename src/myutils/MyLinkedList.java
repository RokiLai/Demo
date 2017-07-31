package myutils;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by rokilai on 2017/7/22.
 * 实现一个双向链表
 */


public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private static class MyNode<AnyType> implements Cloneable {
        AnyType val;
        MyNode<AnyType> pre;
        MyNode<AnyType> next;

        MyNode(AnyType val, MyNode<AnyType> pre, MyNode<AnyType> next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }

        /**
         * 重写LiseNode的clone()方法实现浅拷贝
         *
         * @return 返回节点的一个拷贝 pre和next指向的是同一个地址 但本身不是一个地址
         * @throws CloneNotSupportedException 不支持克隆的异常
         */
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        MyNode(AnyType val) {
            this.val = val;
        }

    }

    private MyNode<AnyType> headNode, endNode;
    private int modCount = 0;
    private int theSize;

    public MyLinkedList() {
        clear();
    }

    /**
     * 清空当前链表
     */
    public void clear() {
        headNode = null;
        endNode = null;

        theSize = 0;

    }

    /**
     * 获取当前链表长度
     *
     * @return 当前list的size
     */
    public int size() {
        return theSize;
    }

    /**
     * 询问当前链表是否为空
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return theSize == 0;
    }

    /**
     * 获取当前链表中位置为index的元素
     *
     * @param index 位置
     * @return 节点中的元素
     */
    public AnyType get(int index) {
        check(index);
        return getNode(index).val;

    }

    /**
     * 返回链表中位置为index的元素并修改为newVal
     *
     * @param index  位置
     * @param newVal 需要修改的元素
     * @return 被修改的元素
     */
    public AnyType set(int index, AnyType newVal) {

        MyNode<AnyType> node = getNode(index);
        AnyType oldVal = node.val;
        node.val = newVal;
        return oldVal;


    }

    /**
     * 在链表末端添加一个元素x
     *
     * @param x 节点的元素
     * @return 添加成功返回true
     */
    public boolean add(AnyType x) {
        addEnd(x);
        return true;
    }

    /**
     * 将元素为x添加到链表的index位置上
     *
     * @param index 位置
     * @param x     添加的元素
     */
    public void add(int index, AnyType x) {

        if (index == theSize)
            addEnd(x);
        else {
            check(index);
            addBefore(getNode(index), x);
        }
    }

    /**
     * 删除链表中的元素x
     *
     * @param index 位置
     * @return 被删除的元素
     */
    public AnyType remove(int index) {
        return removeNode(getNode(index));
    }

    private void addEnd(AnyType x) {
        MyNode<AnyType> node = new MyNode<>(x, endNode, null);
        if (endNode != null) {
            endNode.next = node;
        } else {
            headNode = node;
        }
        endNode = node;
        theSize++;
        modCount++;
    }

    /**
     * 在节点node前面添加一个元素为x的节点
     *
     * @param node 需要被添加的元素节点的后驱
     * @param x    被添加的节点的元素
     */
    private void addBefore(MyNode<AnyType> node, AnyType x) {
        MyNode<AnyType> preNode = node.pre;
        MyNode<AnyType> tempNode = new MyNode<>(x, preNode, node);
        node.pre = tempNode;

        if (preNode == null)
            headNode = tempNode;
        else
            preNode.next = tempNode;

        theSize++;
        modCount++;
    }

    /**
     * 删除节点node
     *
     * @param node 被删除的节点
     * @return 被删除的节点所含的元素
     */
    private AnyType removeNode(MyNode<AnyType> node) {
        AnyType oldVal = node.val;
        MyNode<AnyType> preNode = node.pre;
        MyNode<AnyType> nextNode = node.next;

        if (preNode == null) {
            headNode = node.next;
        } else {
            preNode.next = nextNode;
            node.pre = null;
        }
        if (nextNode == null) {
            endNode = preNode;
        } else {
            nextNode.pre = preNode;
            node.next = null;
        }
        node.val = null;

        theSize--;
        modCount++;

        return oldVal;
    }


    /**
     * 获取位置为index的节点
     *
     * @param index 位置
     * @return 节点
     */
    private MyNode<AnyType> getNode(int index) {
        check(index);

        int mid = theSize / 2;
        MyNode<AnyType> node;

        if (index <= mid) {
            node = headNode;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            //尾节点是可以被返回的
            node = endNode;
            for (int i = theSize - 1; i > index; i--) {
                node = node.pre;
            }
        }
        return node;
    }

    /**
     * 检查位置参数index是否越界
     *
     * @param index 位置
     */
    private void check(int index) {
        if (index < 0 || index >= theSize)
            throw new IndexOutOfBoundsException(OutOfBoundMsg(index));
    }

    private String OutOfBoundMsg(int index) {
        return "index: " + index + "size: " + theSize;
    }

    public Iterator<AnyType> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<AnyType> {

        //迭代器第一次next后才是头节点,因此先令当前节点为一个next指向头节点的节点
        MyNode<AnyType> current = new MyNode<>(null, null, headNode);
        int exceptedModCount = modCount;
        boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endNode;
        }

        @Override
        public AnyType next() {
            checkModCount();
            if (!hasNext())
                throw new IndexOutOfBoundsException(OutOfBoundMsg(theSize));
            current = current.next;
            okToRemove = true;
            return current.val;

        }

        @Override
        public void remove() {
            checkModCount();
            if (!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.removeNode(current);
            okToRemove = false;
            exceptedModCount++;
            theSize--;
        }

        private void checkModCount() {
            if (modCount != exceptedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * 将数组转化为链表
     *
     * @param ints      泛型数组
     * @param <AnyType> 泛型
     * @return 泛型链表
     */
    public static <AnyType> MyLinkedList<AnyType> ValueOf(AnyType[] ints) {
        MyLinkedList<AnyType> myLinkedList = new MyLinkedList<>();
        for (AnyType anInt : ints) {
            myLinkedList.add(anInt);
        }
        return myLinkedList;
    }

    /**
     * 将链表转化为数组
     *
     * @param myLinkedList 泛型链表
     * @param <AnyType>    泛型
     * @return 泛型数组
     */
    @SuppressWarnings("unchecked")
    public static <AnyType> AnyType[] toArray(MyLinkedList<AnyType> myLinkedList) {
        int length = myLinkedList.size();
        AnyType[] array = (AnyType[]) new Object[length];
        for (int i = 0; i < length; i++) {
            array[i] = myLinkedList.get(i);
        }
        return array;
    }


    /**
     * 利用循环添加子节点
     * 优点:内存消耗少
     **/
    public static MyNode<Integer> addTwoNumbers(MyNode<Integer> l1, MyNode<Integer> l2) {
        MyNode<Integer> ResNode = null;
        MyNode<Integer> tempNode, currentNode = null;
        int tempCarry = 0;

        int count = 0;
        while (l1 != null || l2 != null) {
            if (l1 == null) l1 = new MyNode<>(0);
            if (l2 == null) l2 = new MyNode<>(0);
            tempNode = new MyNode<>((l1.val + l2.val + tempCarry) % 10);
            tempCarry = (l1.val + l2.val + tempCarry) / 10;
            if (count == 0) {
                ResNode = tempNode;
                currentNode = ResNode;
                count++;
            } else {
                currentNode.next = tempNode;
                currentNode = currentNode.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (tempCarry > 0) {
            currentNode.next = new MyNode<>(1);
        }
        return ResNode;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Integer[] a = {1, 2, 3};
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(0, 1);
//        MyLinkedList<Integer> list = MyLinkedList.ValueOf(a);
//        Iterator<Integer> itr = list.iterator();
//        while (itr.hasNext()){
//            System.out.println(itr.next());
//        }
        System.out.println();
    }
}
