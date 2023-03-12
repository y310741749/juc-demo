package test.study4;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.locks.ReentrantLock;

public class Test12 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("执行t1线程逻辑");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
//            try {
//                ThreadUtil.sleep(1000);
//
//            } finally {
//
//            }

        }, "t1");
        lock.lock();
        t1.start();
        ThreadUtil.sleep(1000);
        System.out.println("开始中断");
        System.out.println(t1.getState());
        t1.interrupt();
        ThreadUtil.sleep(1000);
        System.out.println(t1.getState());
        Thread t2 = new Thread(() -> {
            if(lock.tryLock()){
                System.out.println("t2获取到锁");
            }else {
                System.out.println("t2没有获取到锁");
            }
        }, "t2");
        t2.start();
    }
}
