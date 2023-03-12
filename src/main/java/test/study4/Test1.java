package test.study4;

import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {
    static int
            counter = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (Test1.class) {
                        counter++;
                    }
                }
            }
        }
        );
        Thread t2 = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (Test1.class) {
                        counter--;
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);
    }
}
