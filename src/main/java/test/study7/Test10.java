package test.study7;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

public class Test10 {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> map = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Integer> map1 = new ConcurrentHashMap<>();
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
//                LongAdder a = map.computeIfAbsent("a", (key) -> new LongAdder());
//                a.increment();
                map1.merge("a", 1, (a, b) -> a + b);
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(map1.get("a"));


    }
}
