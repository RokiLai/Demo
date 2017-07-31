package myutils;

public class MyAvlTree<AnyType extends Comparable<? super AnyType>> {

    private static class MyAvlNode<AnyType> {

        MyAvlNode<AnyType> leftNode, rightNode;
        AnyType element;
        int height;

        MyAvlNode(AnyType theElement) {
            element = theElement;
        }

        MyAvlNode(AnyType theElement, MyAvlNode<AnyType> leftNode, MyAvlNode<AnyType> rightNode) {
            element = theElement;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

    }

    private int getHight(MyAvlNode<AnyType> node) {
        return node == null ? -1 : node.height;
    }
}
