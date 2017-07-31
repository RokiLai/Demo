import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rokilai on 2017/7/20.
 * 通过删除list中的偶数来比较list的remove和add性能
 */
public class MyList {


    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 50000000; i++) {
            arrayList.add(i);
        }
        Date start = new Date();
        List<Integer> resultList = removeEven2(arrayList);
        Date end = new Date();
//        for (Integer resule : resultList
//                ) {
//            System.out.println(resule);
//
//        }
        System.out.println(end.getTime()-start.getTime());


    }

    public static List<Integer> removeEven1(List<Integer> list) {
        Iterator<Integer> itList = list.iterator();
//        System.out.println(list.size());
        while (itList.hasNext()) {
            if (itList.next() % 2 == 0)
                itList.remove();
        }
        return list;
    }

    public static List<Integer> removeEven2(List<Integer> list){
        List<Integer> newList = new ArrayList<Integer>();
//        System.out.println(list.size());
        for (Integer i:list
             ) {
            if (i % 2 != 0)
                newList.add(i);
        }
        return newList;
    }

}

