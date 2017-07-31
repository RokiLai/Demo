/**
 * Created by rokilai on 2017/7/19.
 */
public class TestThread {
    public static void main(String[] args) {
        Thread t1 = new Demo();
        t1.start();
        int index = 0;
        while(index <= 5){
            System.out.println(index);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index++;
        }

    }


}

class Demo extends Thread{

    @Override
    public void run() {
        int index = 0;
        while(index <= 5){
            System.out.println("thread1"+index);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index++;
        }
    }
}