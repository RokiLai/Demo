package myarithmetic;

import java.util.Date;

/**
 * Created by rokilai on 2017/7/21.
 */
public class Price {
    public static void main(String[] args) throws InterruptedException {
        Date start = new Date();
        System.out.println(getPrice(100000000));
        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());
    }

    /**
     * 查找2到num之间有多少素数
     *
     */
    public static int getPrice(int num) {
        int[] list = new int[num + 1];

        for (int i = 2; i < Math.sqrt(list.length - 1); i++) {
            if (list[i] == 0) {
                for (int j = i * i; j < list.length; j += i) {
                    if (list[j] == 0) {
                        list[j] = 1;
                        num--;
                    }
                }
            }
        }
        return num - 1;
    }
}
