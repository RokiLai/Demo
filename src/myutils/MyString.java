package myutils;

import java.util.*;

/**
 * Created by rokilai on 2017/7/19.
 * 利用栈实现字符串的反转
 */
public class MyString {

    public static int reverse(int x) {
        String string = String.valueOf(Math.abs(x));
        StringBuffer stringBuffer = new StringBuffer(string);
        String string1 = String.valueOf(stringBuffer.reverse());
        Long tempNum = Long.valueOf(string1);
        if (x < 0) {
            if (-tempNum < Integer.MIN_VALUE) return 0;
            else
                return -Integer.valueOf(string1);
        } else {
            if (tempNum > Integer.MAX_VALUE) return 0;
            else
                return -Integer.valueOf(string1);
        }

    }


    /**
     * 利用StringBuffer的reverse()实现字符串反转
     **/
    public static String reverse1(String string) {

        StringBuffer stringBuffer = new StringBuffer(string);

        return String.valueOf(stringBuffer.reverse());

    }

    /**
     * 利用栈实现字符串反转
     **/
    public static String reverse2(String string) {
        Stack<Character> c = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            c.push(string.charAt(i));
        }
        StringBuilder result = new StringBuilder();
        while (!c.isEmpty()) {
            result.append(c.pop());
        }
        return result.toString();
    }

    /**
     * 找到最长不含重复字符的子字符串
     * 利用Hashset实现(set查询效率高于list)
     * set中元素不重复
     */
    public static int lengthOfLongestSubstring(String s) {
        int longest = 0;
        Set<Character> characterSet = new HashSet<>();
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (characterSet.contains(s.charAt(i))) {
                if (characterSet.size() > longest)
                    longest = characterSet.size();
                i = index;
                index++;
                characterSet = new HashSet<>();
            } else {
                characterSet.add(s.charAt(i));
            }
            if (i == s.length() - 1) {
                if (characterSet.size() > longest)
                    longest = characterSet.size();
            }
        }

        return longest;
    }

    /**
     * 判断一个字符串是否是回文字符串
     */
    public static boolean isPalindromicStr(String string) {
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) != string.charAt(string.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 找到字符串中的最大回文子字符串
     * 利用中心扩展法
     * 时间复杂度O(n²) 空间复杂度O(1)
     */
    public static String longestPalindrome(String s) {
        if (s.length() == 1)
            return s;
        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1))
                return s;
            return s.substring(0, 1);
        }
        String string = "";

        for (int i = 0; i < s.length(); i++) {
            String tempStr1, tempStr2;

            tempStr1 = getSubPalindrome(s, i, i);

            if (tempStr1.length() == s.length()) return s;

            /**
             * 当子字符串为偶数时
             */
            tempStr2 = getSubPalindrome(s, i, i + 1);

            String tempStr3 = (tempStr1.length() >= tempStr2.length()) ? tempStr1 : tempStr2;
            string = (string.length() > tempStr3.length()) ? string : tempStr3;

        }
        return string;
    }

    private static String getSubPalindrome(String s, int pre, int back) {
        while (pre >= 0 && back <= s.length() - 1) {
            if (s.charAt(pre) == s.charAt(back)) {
                pre--;
                back++;
            } else {
                break;
            }
        }
        return s.substring(pre + 1, back);
    }


    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
    }
}
