package myutils;

import java.util.*;

/**
 * Created by rokilai on 2017/7/21.
 */
public class MyStack<AnyType> {
    private AnyType[] theArray;
    private static final int DEFAULT_SIZE = 10;
    private int topOfStack;


    public MyStack() {
        clear();
    }

    /**
     * 清空栈 栈指针指向-1
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        topOfStack = -1;
        theArray = (AnyType[]) new Object[DEFAULT_SIZE];
    }


    public void push(AnyType anyType) {
        if (topOfStack + 1 == theArray.length)
            ensureCapacity(2 * theArray.length);
        theArray[++topOfStack] = anyType;
    }

    public AnyType pop() {
        if (!isEmpty())
            return theArray[topOfStack--];
        throw new EmptyStackException();
    }

    public boolean isEmpty() {
        return topOfStack == -1;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int newCapacity) {
        if (newCapacity <= topOfStack + 1)
            return;

        AnyType[] newArray = (AnyType[]) new Object[newCapacity];
        System.arraycopy(theArray, 0, newArray, 0, topOfStack + 1);
        theArray = newArray;

    }

    /**
     * 将中缀表达式转化为后缀表达式
     * @param midExp 中缀表达式
     * @return 后缀表达式
     */
    public static String midToBack(String midExp) {
        char[] list = midExp.toCharArray();
        MyStack<Character> optStack = new MyStack<>();
        StringBuilder backExp = new StringBuilder();
        char tempOpt;
        for (int i = 0; i < midExp.length(); i++) {
            char temp = midExp.charAt(i);
            if (!isOpt(temp)) {
                backExp.append(temp);
            } else {
                if (optStack.isEmpty()) {
                    optStack.push(temp);
                } else {
                    if (temp == ')') {
                        tempOpt = optStack.pop();
                        while (tempOpt != '(') {
                            backExp.append(tempOpt);
                            tempOpt = optStack.pop();
                        }
                    } else {
                        int priority = getPriority(temp);
                        tempOpt = optStack.pop();
                        while (!(priority > getPriority(tempOpt)) && tempOpt != '(') {
                            backExp.append(tempOpt);
                            if (optStack.isEmpty()) {
                                break;
                            } else {
                                tempOpt = optStack.pop();
                            }
                        }
                        if (priority > getPriority(tempOpt) || tempOpt == '(')
                            optStack.push(tempOpt);
                        optStack.push(temp);
                    }

                }
            }
        }
        while (!optStack.isEmpty()) {
            backExp.append(optStack.pop());
        }
        return String.valueOf(backExp);
    }

    /**
     * 根据表达式计算结果
     * @param midExp 中缀表达式
     * @return 计算int型结果
     */
    public static int calculate(String midExp) {
        String backExp = midToBack(midExp);
        MyStack<Integer> numStack = new MyStack<>();
        for (int i = 0; i < backExp.length(); i++) {
            char temp = backExp.charAt(i);
            if (!isOpt(temp)) {
                numStack.push(temp - '0');
            } else {
                int num1 = numStack.pop();
                int num2 = numStack.pop();
                int result = getCalRes(num1, num2, temp);
                numStack.push(result);
            }
        }
        return numStack.pop();
    }

    /**
     * 根据操作数和操作符返回计算结果
     * @param num1 操作数1
     * @param num2 操作数2
     * @param opt 操作符
     * @return 计算结果
     */
    private static int getCalRes(int num1, int num2, char opt) {
        switch (opt) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     *  判断一个字符是否为操作符
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

    /**
     * 返回操作符的优先级
     * @param opt 操作符
     * @return int越大优先级越高
     */
    private static int getPriority(char opt) {
        switch (opt) {
            case '+':
                return 0;
            case '-':
                return 0;
            case '*':
                return 1;
            case '/':
                return 1;
            case '(':
                return 2;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        int a = calculate("1+3*(1+3)");
        System.out.println(a);
    }


}
