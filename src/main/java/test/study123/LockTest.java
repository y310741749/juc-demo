package test.study123;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        Lock l = new ReentrantLock();
        try{
            l.lock();
            try{
                ThreadUtil.execute(()->{
                    l.lock();
                    try{
                        System.out.println("我是子线程");
                    }finally {
                        l.unlock();
                    }

                });
                System.out.println("我是主线程");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                l.unlock();
            }
        }finally {
            System.out.println("hahahas");
        }

    }
}
