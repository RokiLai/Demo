package myutils;

import java.util.Iterator;

public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private AnyType[] theItems;

    public MyArrayList() {
        clear();
    }


    /**
     * 清空list 这里我是声明了一个新的地址默认大小的数组，原因是这样更快
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        theItems = (AnyType[]) new Object[DEFAULT_CAPACITY];
        theSize = 0;
    }

    /**
     * 获取当前list的size
     *
     * @return 当前list中实际数组的大小
     */
    public int size() {
        return theSize;
    }

    /**
     * 判断当前的lise是否为空
     *
     * @return true or flase
     */
    public boolean isEmpty() {
        return theSize == 0;
    }

    /**
     * 删除list容量中比size多的部分 节省内存
     */
    public void trimToSize() {
        enSureCapacity(theSize);
    }

    /**
     * 返回当前list中位置为index的元素
     *
     * @param index 位置
     * @return 数组中下标为index的元素
     */
    public AnyType get(int index) {
        check(index);
        return theItems[index];
    }

    /**
     * 返回list中位置的为index的元素然后修改为x
     *
     * @param index 修改的位置
     * @param x     修改的元素
     * @return 被修改的元素
     */
    public AnyType set(int index, AnyType x) {
        check(index);
        AnyType oldItem = theItems[index];
        theItems[index] = x;
        return oldItem;
    }

    /**
     * list的扩容方法
     * 当新的容量小于size时不做任何操作 否则扩充为原来两倍
     *
     * @param newCapacity 新容量
     */
    @SuppressWarnings("unchecked")
    public void enSureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;
        //将oldItems指向theItems中的内容
        //由于只是修改了引用，所以效率很高
        AnyType[] oldItems = theItems;

        //gettheItems赋值一块新的地址空间
        theItems = (AnyType[]) new Object[newCapacity];

        //将原来数组中的内容拷贝到新的地址空间 O(N)
        System.arraycopy(oldItems, 0, theItems, 0, theSize);


    }

    /**
     * 在list末尾添加元素
     *
     * @param x 需要添加的元素
     * @return 添加成功返回true
     */
    public boolean add(AnyType x) {
        if (theSize == theItems.length)
            enSureCapacity(2 * theItems.length);
        theItems[theSize] = x;
        theSize++;
        return true;
    }

    /**
     * 添加元素x到list的指定位置index上
     * 时间复杂度 O(N)
     *
     * @param index 添加的位置
     * @param x     添加的元素
     */
    public void add(int index, AnyType x) {

        if (index == theSize) {
            add(x);
        }
        check(index);
        if (theSize + 1 == theItems.length)
            enSureCapacity(theItems.length * 2);
        for (int i = theSize; i < index; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[index] = x;
        theSize++;
    }

    /**
     * 删除list中位置为index的元素
     *
     * @param index 删除的位置
     * @return 被删除的元素
     */
    public AnyType remove(int index) {
        check(index);
        AnyType oldItem = theItems[index];

        //调用了系统的数组拷贝 速度更快
        System.arraycopy(theItems, index + 1, theItems, index, theSize - index);
        theSize--;
        return oldItem;
    }

    /**
     * 检查位置参数index是否越界 越界则抛出异常
     *
     * @param index 位置
     */
    private void check(int index) {
        if (index < 0 || index >= theSize)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));

    }


    /**
     * 异常抛出的信息
     *
     * @param index 异常出现的位置
     * @return 信息字符串
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + "Size:= " + theSize;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new MyIterator();
    }


    class MyIterator implements Iterator<AnyType> {

        private int current = 0;

        /**
         * 迭代器中是否还有下一个元素
         *
         * @return true or false
         */
        @Override
        public boolean hasNext() {
            return current < theSize;
        }

        /**
         * next前注意要检查边界
         *
         * @return 返回当前的值
         */
        @Override
        public AnyType next() {
            check(current);
            return theItems[current++];
        }


        /**
         * 迭代器调用了list本身的删除方法
         * 时间复杂度O(N)
         */
        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }


}
