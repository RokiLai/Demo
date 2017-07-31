package myutils;

import myexception.BinaryFormatException;

/**
 * 该类主要实现二进制数的基本运算操作以及格式化
 * Created by rokilai on 2017/7/24.
 */
public class MyBinaryNumber {

    /**
     * 将二进制补码扩展为32位二进制补码
     *
     * @param num [1,32】长度的二进制补码字符串
     * @return 32位二进制补码字符串
     */
    public static String expand32Binary(String num) {
        if (!num.matches("[01]+"))
            throw BinaryFormatException.forInputString(num);
        boolean[] boolNum = new boolean[32];

        for (int i = 0; i < 32; i++) {
            if (i < num.length()) {
                boolNum[31 - i] = num.charAt(num.length() - 1 - i) == '1';
            } else {
                boolNum[31 - i] = num.charAt(0) == '1';
            }
        }
        StringBuilder resBinary = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            resBinary.append(boolNum[i] ? 1 : 0);
        }
        return resBinary.toString();
    }

    /**
     * 取相反数
     *
     * @param num [1,32]长度的二进制补码字符串
     * @return 32位长度的二进制补码字符串
     */
    public static String getOpposite(String num) {
        if (!num.matches("[01]+"))
            throw BinaryFormatException.forInputString(num);

        boolean[] boolNum = new boolean[32];

        for (int i = 0; i < 32; i++) {
            if (i < num.length()) {
                boolNum[31 - i] = num.charAt(num.length() - 1 - i) == '1';
            } else {
                boolNum[31 - i] = num.charAt(0) == '1';
            }
        }

        //取反操作
        for (int i = 0; i < 32; i++) {
            boolNum[i] = !boolNum[i];
        }

        //加一操作
        boolean carry = true;
        int index = 31;
        while (carry && index >= 0) {
            boolNum[index] = !boolNum[index];
            if (boolNum[index]) {
                carry = false;
            }
            index--;
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            res.append(boolNum[i] ? 1 : 0);
        }
        return res.toString();
    }


    /**
     * 将int转化为2进制字符串
     *
     * @param num int类型的任意值
     * @return 长度32位的二进制补码字符串
     */
    public static String ValueOf(int num) {
        int tempnNum = num;
        if (tempnNum == Integer.MIN_VALUE) return "10000000000000000000000000000000";

        boolean[] tempArray = new boolean[32];
        boolean flag = false;


        //若num为负数，则先取绝对值
        //并将flag位标记为true
        if (tempnNum < 0) {
            tempnNum = -tempnNum;
            flag = true;
        }

        //转化为二进制
        int index = 0;
        while (tempnNum > 0) {
            tempArray[31 - index] = tempnNum % 2 == 1;
            tempnNum = tempnNum >> 1;
            index++;
        }


        StringBuilder resStr = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            resStr.append((tempArray[i]) ? 1 : 0);
        }
//        System.out.println(num);
//        System.out.println(Integer.toBinaryString(num));
//        System.out.println(resStr);
        if (flag) {
            resStr = new StringBuilder(getOpposite(resStr.toString()));
        }

        return resStr.toString();
    }

    /**
     * [1,32]长度的二进制补码字符串转化为int型整数
     * @param binary [1,32]长度的二进制字符串
     * @return int整数
     */
    public static int toInteger(String binary) {
        boolean flag = binary.charAt(0) == '1';
        String tempBinary = flag ? getOpposite(binary) : expand32Binary(binary);
        int resNum = 0;
        int index = 31;
        while (index > 0) {
            int tempNum = tempBinary.charAt(index) - '0';
            resNum += tempNum * Math.pow(2, 31 - index);
            index--;
        }
        if (flag)
            resNum = -resNum;

        return resNum;

    }


    /**
     * 将两个2进制字符串相加返回二进制结果
     *
     * @param num1 只包含0和1的长度[1~32]的二进制补码字符串
     * @param num2 只包含0和1的长度[1~32]的二进制补码字符串
     * @return 长度32位的二进制补码字符串
     */
    public static String add(String num1, String num2) {

        //正则表达式检验输入的二进制字符串是否合法
        if (!num1.matches("[01]+"))
            throw BinaryFormatException.forInputString(num1);
        if (!num2.matches("[01]+"))
            throw BinaryFormatException.forInputString(num2);

        boolean[] boolNum1 = new boolean[32];
        boolean[] boolNum2 = new boolean[32];

        //将短的二进制补码放到长的二进制补码中要注意符号位的扩展
        for (int i = 0; i < 32; i++) {
            if (i < num1.length()) {
                boolNum1[31 - i] = num1.charAt(num1.length() - 1 - i) == '1';
            } else {
                boolNum1[31 - i] = num1.charAt(0) == '1';
            }
        }
        for (int i = 0; i < 32; i++) {
            if (i < num2.length()) {
                boolNum2[31 - i] = num2.charAt(num2.length() - 1 - i) == '1';
            } else {
                boolNum2[31 - i] = num2.charAt(0) == '1';
            }
        }

        //二进制加法运算
        int index = 31;
        boolean carry = false;
        while (index >= 0) {
            boolean tempBoolNum1 = boolNum1[index];
            boolNum1[index] = boolNum1[index] ^ boolNum2[index] ^ carry;

            //carry和temoBoolNum1和boolNum2[index]中 只要有两个为真下一个carry就为真
            carry = tempBoolNum1 & boolNum2[index] || tempBoolNum1 & carry || boolNum2[index] & carry;
            index--;
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            res.append((boolNum1[i]) ? 1 : 0);
        }

        return res.toString();

    }

    /**
     * @param num1 被减数  只包含0和1的长度[1~32]的二进制补码字符串
     * @param num2 减数   只包含0和1的长度[1~32]的二进制补码字符串
     * @return 长度32位的二进制补码字符串
     */
    public static String sub(String num1, String num2) {

        String oppositeNum2 = getOpposite(num2);
        return add(num1, oppositeNum2);

    }


    public static void main(String[] args) {



    }
}
