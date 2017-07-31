package myarithmetic;

import java.util.Arrays;

public class Sort {

    /**
     * 冒泡排序 O(N²)
     *
     * @param list 未排序的数组
     */
    public static void bubble(int[] list) {

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }

            }
        }
    }

    /**
     * 插入排序 O(N²)
     *
     * @param list 未排序的数组
     */
    public static void insertion(int[] list) {

        int j;
        for (int i = 1; i < list.length; i++) {

            int temp1 = list[i];

            for (j = i - 1; j >= 0; j--) {
                if (temp1 > list[j])
                    break;

                list[j + 1] = list[j];
            }

            list[j + 1] = temp1;

        }
    }

    /**
     * 希尔排序
     *
     * @param list 未排序的数组
     */
    public static void shell(int[] list) {

        int j;

        for (int k = list.length / 2; k > 0; k /= 2) {

            for (int i = k; i < list.length; i++) {

                int temp1 = list[i];

                for (j = i - k; j >= 0; j -= k) {
                    if (temp1 > list[j])
                        break;

                    list[j + k] = list[j];
                }

                list[j + k] = temp1;

            }

        }
    }

    /**
     * 并归排序递归例程
     *
     * @param list 未排序的数组
     */

    public  static void merge(int[] list){
        int length = list.length;
        if (length>2){
            int[] tempList1 = new int[length/2];
            System.arraycopy(list, 0, tempList1, 0, tempList1.length);
            int[] tempList2 = new int[length-length/2];
            System.arraycopy(list,tempList1.length,tempList2,0,tempList2.length);
            merge(tempList1);
            System.arraycopy(tempList1, 0, list, 0, tempList1.length);
            merge(tempList2);
            System.arraycopy(tempList2,0,list,tempList1.length,tempList2.length);
        }
        mergeA(list);
    }

    /**
     * 并归排序具体实现
     * @param list 数组中分开的两个数组已排序
     */
    private static void mergeA(int[] list) {
        int[] tempList = new int[list.length];

        int left = 0;
        int center = list.length / 2;
        int right = center;

        int i = 0;
        while (left < center && right < list.length) {
            if (list[left] <= list[right]) {
                tempList[i++] = list[left++];
            } else {
                tempList[i++] = list[right++];
            }
        }

        while (left < center) {
            tempList[i++] = list[left++];
        }

        while (right < list.length) {
            tempList[i++] = list[right++];
        }

        System.arraycopy(tempList, 0, list, 0, list.length);

    }


    public static void main(String[] args) {
//        int[] a = {1,3,2};
//        int[] a = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
        int[] a = {34,8,64,51,32,21};
        merge(a);
        System.out.println(Arrays.toString(a));
    }
}
