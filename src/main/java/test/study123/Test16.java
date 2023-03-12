package test.study123;

import cn.hutool.core.thread.ThreadUtil;

import java.util.HashMap;
import java.util.Map;

public class Test16 {
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        Map<String,Thread> map=new HashMap<>();
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("洗水壶");
                Thread.sleep(15000);
                System.out.println("烧开水");
                Thread t1 = map.get("t1");
                t1.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(4000);
                System.out.println("洗茶壶，洗茶杯，拿茶叶");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        map.put("t1",t1);
        map.put("t2",t2);
        t1.start();
        t2.start();
        t1.join();
        long l1 = System.currentTimeMillis();
        long time = l1 - l;
        System.out.println("完成操作，耗时:" + time);
    }
}
