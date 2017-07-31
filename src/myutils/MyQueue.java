package myutils;

import java.util.EmptyStackException;

public class MyQueue<AnyType> {
    private static class MyNode<AnyType> {
        AnyType val;
        MyNode<AnyType> next;

        MyNode(AnyType val) {
            this.val = val;
        }
    }

    private MyNode<AnyType> head;
    private MyNode<AnyType> end;
    private int size;

    public MyQueue() {
        clear();
    }

    public void clear() {
        head = null;
        end = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(AnyType x) {
        MyNode<AnyType> tempNode = new MyNode<>(x);
        if (size == 0) {
            head = tempNode;
            end = tempNode;
        }
        end.next = tempNode;
        end = tempNode;
        size++;
    }

    public AnyType dequeue() {
        if (size == 0)
            throw new EmptyStackException();
        MyNode<AnyType> tempNode = head;
        head = head.next;
        size--;
        return tempNode.val;
    }
}
