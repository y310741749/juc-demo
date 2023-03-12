package test.study4;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test13 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            log.info("其他代码");
        });
        t1.start();
        ThreadUtil.sleep(100);
        new Thread(() -> {
            lock.lock();
                try {
                    log.info("进入等待...");
                    Thread.sleep(2000);
                    log.info("结束等待...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    c2.signal();
                    lock.unlock();
                }
        }).start();
    }
}
