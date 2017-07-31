package myarithmetic;

import java.util.Arrays;

public class Bubble {

    public static int[] bubble(int[] list){

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length-1-i; j++) {
                if (list[j]>list[j+1]){
                    int temp =list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }

            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] a = {3,2,1};
        System.out.println(Arrays.toString(bubble(a)));
    }
}
