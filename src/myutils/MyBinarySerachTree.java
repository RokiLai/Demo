package myutils;

public class MyBinarySerachTree<AnyType extends Comparable<? super AnyType>> {

    private MyTreeNode<AnyType> root;


    /**
     * 二叉树节点
     *
     * @param <AnyType> 泛型
     */
    private static class MyTreeNode<AnyType> {

        AnyType element;
        MyTreeNode<AnyType> leftChild, rightChild;

        MyTreeNode(AnyType element) {
            this.element = element;
        }

        MyTreeNode(AnyType element, MyTreeNode<AnyType> leftChild, MyTreeNode<AnyType> rightChild) {
            this.element = element;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

    }

    public MyBinarySerachTree() {
        clear();
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }


    private boolean contains(AnyType x, MyTreeNode<AnyType> node) {
        if (node == null) return false;

        int compareResult = node.element.compareTo(x);

        if (compareResult > 0) {
            return contains(x, node.leftChild);
        } else
            return compareResult == 0 || contains(x, node.rightChild);

    }

    private MyTreeNode<AnyType> findMin(MyTreeNode<AnyType> node) {
        if (node == null) {
            return null;
        } else if (node.leftChild == null) {
            return node;
        } else {
            return findMin(node.leftChild);
        }
    }

    private MyTreeNode<AnyType> findMax(MyTreeNode<AnyType> node) {

        if (node != null)
            while (node.rightChild != null) {
                node = node.rightChild;
            }

        return node;
    }

    private MyTreeNode<AnyType> insert(AnyType x, MyTreeNode<AnyType> node) {
        if (node == null) return new MyTreeNode<>(x);

        MyTreeNode<AnyType> tempNode = node;

        while (true) {
            int compareResult = x.compareTo(tempNode.element);

            MyTreeNode<AnyType> newNode = new MyTreeNode<>(x);

            if (compareResult < 0) {
                if (tempNode.leftChild == null) {
                    tempNode.leftChild = newNode;
                    return node;
                } else tempNode = tempNode.leftChild;
            } else if (compareResult > 0) {
                if (tempNode.rightChild == null) {
                    tempNode.rightChild = newNode;
                    return node;
                } else {
                    tempNode = tempNode.rightChild;
                }
            } else
                return node;
        }
    }

    private MyTreeNode<AnyType> remove(AnyType x, MyTreeNode<AnyType> node) {

        MyTreeNode<AnyType> currentNode = node;
        MyTreeNode<AnyType> preNode = currentNode;
        while (currentNode != null) {
            int compareResult = x.compareTo(currentNode.element);

            if (compareResult < 0) {
                if (currentNode.leftChild == null) {
                    return node;
                } else {
                    preNode = currentNode;
                    currentNode = currentNode.leftChild;
                }
            } else if (compareResult > 0) {
                if (currentNode.rightChild == null) {
                    return node;
                } else {
                    preNode = currentNode;
                    currentNode = currentNode.rightChild;
                }
            } else if (currentNode.leftChild != null && currentNode.rightChild != null) {
                currentNode.element = findMin(currentNode.rightChild).element;
                x = currentNode.element;
                preNode = currentNode;
                currentNode = currentNode.rightChild;
            } else {
                if (currentNode == node) {
                    currentNode = (currentNode.leftChild != null) ? currentNode.leftChild : currentNode.rightChild;
                    node = currentNode;
                } else {
                    if (preNode.leftChild == currentNode) {
                        preNode.leftChild = (currentNode.leftChild != null) ? currentNode.leftChild : currentNode.rightChild;
                    } else {
                        preNode.rightChild = (currentNode.leftChild != null) ? currentNode.leftChild : currentNode.rightChild;
                    }
                    break;
                }
            }
        }

        return node;
    }

    /**
     * 将后缀表达式生成一颗二叉树
     *
     * @param backExp 后缀表达式
     * @return 一颗二叉树
     */
    public static MyBinarySerachTree<Character> buildTree(String backExp) {
        MyBinarySerachTree<Character> tree = new MyBinarySerachTree<>();
        MyStack<MyTreeNode<Character>> stack = new MyStack<>();
        MyTreeNode<Character> tempNode;
        for (int i = 0; i < backExp.length(); i++) {
            char temp = backExp.charAt(i);
            if (!isOpt(temp)) {
                stack.push(new MyTreeNode<>(temp));
            } else {
                MyTreeNode<Character> right = stack.pop();
                MyTreeNode<Character> left = stack.pop();
                stack.push(new MyTreeNode<>(temp, left, right));
            }
        }
        tree.root = stack.pop();
        return tree;
    }

    /**
     * 判断一个字符是否为操作符
     *
     * @param c 被判断的字符
     * @return true or false
     */
    private static boolean isOpt(char c) {
        Character[] opt = {'+', '-', '*', '/', '(', ')'};
        for (Character anOpt : opt) {
            if (c == anOpt)
                return true;
        }
        return false;

    }

    public void print() {
        MyQueue<MyTreeNode<AnyType>> queue = new MyQueue<>();
        MyTreeNode<AnyType> node = root;
        queue.enqueue(node);
        while (!queue.isEmpty()) {
            node = queue.dequeue();
            System.out.print(node.element + " ");
            if (node.leftChild != null) {
                queue.enqueue(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.enqueue(node.rightChild);
            }
        }

    }

    public static void main(String[] args) {
        MyBinarySerachTree<Integer> a = new MyBinarySerachTree<>();
        a.insert(6);
        a.insert(2);
        a.insert(8);
        a.insert(1);
        a.insert(5);
        a.insert(3);
        a.insert(4);
//        a.remove(2);
        a.print();

//        System.out.println();
    }
}
