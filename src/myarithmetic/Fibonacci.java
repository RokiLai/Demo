package myarithmetic;

/**
 * Created by rokilai on 2017/7/19.
 */
public class Fibonacci {
    public static Long getNumber(int num) {
        Long n1 = 1L, n2 = 1L;
        Long temp;
        while (num > 0) {
//            System.out.println(n1);
            temp = n1 + n2;
            n1 = n2;
            n2 = temp;
            num--;
        }
        return n1;
    }

    public static void main(String[] args) {
        Long fb = Fibonacci.getNumber(9);
        System.out.println(fb);
    }
}
