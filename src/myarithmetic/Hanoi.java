package myarithmetic;

/**
 * Created by rokilai on 2017/7/19.
 */
public class Hanoi {
    static char A='A',B='B',C='C';
    public static void printHanoi(char from,char to,char temp,int num){
        if(num == 1){
            System.out.println("Disk 1"+" move " + from + " to " + to);
        }else{
            printHanoi(from,temp,to,num-1);
            System.out.println("Disk "+num+" move " + from + " to " + to);
            printHanoi(temp,to,from,num-1);

        }

    }

    public static void main(String[] args) {
        printHanoi(A,C,B,3);
    }
}
