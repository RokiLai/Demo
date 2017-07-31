package myarithmetic;

import java.util.Arrays;

public class Sort {

    /**
     * 冒泡排序 O(N²)
     *
     * @param list 未排序的数组
     * @return 排序后的数组
     */
    public static int[] bubble(int[] list) {

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }

            }
        }
        return list;
    }

    /**
     * 插入排序 O(N²)
     *
     * @param list 未排序的数组
     * @return 排序后的数组
     */
    public static int[] insertion(int[] list) {

        int j;
        for (int i = 1; i < list.length; i++) {

            int temp1 = list[i];

            for (j = i - 1; j >= 0; j--) {
                if (temp1 > list[j])
                    break;

                list[j+1] = list[j];
            }

            list[j+1] = temp1;

        }
        return list;
    }

    public static int[]

    public static void main(String[] args) {
        int[] a = {34, 8, 64, 51, 32, 21};
        System.out.println(Arrays.toString(insertion(a)));
//        System.out.println(Arrays.toString(bubble(a)));

    }
}
