/**
 * Created by rokilai on 2017/7/21.
 */
public class Main {

    static class Person {
        int sex = 1;
    }

    public static void changeSex(Person person) {
        Person person1 = new Person();
        person1.sex = 0;
        person.sex = person1.sex;
    }

    public static void main(String args[]) {
        Person a = new Person();
        changeSex(a);
        System.out.println(a.sex);
    }
}