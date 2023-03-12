package test.study4;

import java.util.ArrayList;
import java.util.List;

public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        List<String> list=new ArrayList<>();
        new Thread(()->{
           synchronized (Test5.class){
               try {
                   Test5.class.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (list){
                   list.add("123");
                   list.notify();
               }

           }

        }).start();

        new Thread(()->{
            synchronized (list){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Test5.class){
                    list.add("456");
                    Test5.class.notify();
                }
            }
        }).start();
        Thread.sleep(1000);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
