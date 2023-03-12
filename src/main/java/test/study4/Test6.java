package test.study4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test6 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("其他代码");
            }
        });
        t1.start();
        t1.join();

        new Thread(()->{
            synchronized (lock){
                try {
                    log.info("进入等待...");
                    Thread.sleep(10000);
                    log.info("结束等待...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
