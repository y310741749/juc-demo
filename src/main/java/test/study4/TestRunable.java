package test.study4;

import java.util.concurrent.atomic.AtomicInteger;

public class TestRunable implements Runnable {
    private AtomicInteger
            count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            count.getAndIncrement();
        }
        System.out.println(count);
    }
}
