package test.study4;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test14 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();


            Thread aThread = new Thread(() -> {
                for(int i=0;i<5;i++){
                lock.lock();
                try {
                    a.await();
                    log.info("a");
                    b.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }}
            },"aThread");


            Thread bThread = new Thread(() -> {
                for(int i=0;i<5;i++){
                lock.lock();
                try {
                    b.await();
                    log.info("b");
                    c.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }}
            },"bThread");



            Thread cThread = new Thread(() -> {
                for(int i=0;i<5;i++){
                lock.lock();
                try {
                    c.await();
                    log.info("c");
                    a.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }}
            },"cThread");

        aThread.start();
        bThread.start();
        cThread.start();
        ThreadUtil.sleep(1000);
        lock.lock();
        try{
            a.signal();
        }finally {
            lock.unlock();
        }

    }
}
