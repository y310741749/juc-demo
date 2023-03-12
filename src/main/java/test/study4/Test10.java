package test.study4;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test10 {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock){
                System.out.println("1111");
                ThreadUtil.sleep(2000);
                lock.notifyAll();
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println("t2收到通知，执行逻辑");
//                    ThreadUtil.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println("t3收到通知，执行逻辑");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t3.start();
        ThreadUtil.sleep(100);
        t1.start();
        System.out.println("t1:"+t1.getState());
        System.out.println("t2:"+t2.getState());
        System.out.println("t3:"+t3.getState());
        ThreadUtil.sleep(3000);

        System.out.println("t1:"+t1.getState());
        System.out.println("t2:"+t2.getState());
        System.out.println("t3:"+t3.getState());
    }
}
