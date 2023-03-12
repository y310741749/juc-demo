package test.study7;


import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自己实现锁
 */
@Slf4j
public class Test6 {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(()->{
            lock.lock();
            try {
                log.debug("locking...");
                ThreadUtil.sleep(3000);
            }finally {
                log.debug("unLocking");
                lock.unlock();
            }
        },"t1").start();
        ThreadUtil.sleep(1000);
        new Thread(()->{

            log.debug("尝试加锁结果{}",lock.tryLock());
            lock.lock();
            log.debug("加锁成功");
            try {
                log.debug("locking...");
            }finally {
                log.debug("unLocking");
                lock.unlock();
            }
        },"t2").start();
    }
}

class MyLock implements Lock {
    Sync sync=new Sync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

//同步器类
class Sync extends AbstractQueuedSynchronizer{
    @Override
    protected boolean tryAcquire(int arg) {
        if(compareAndSetState(0,arg)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(arg);
        return true;
    }


//    @Override
//    protected boolean tryRelease(int arg) {
//        return super.tryRelease(arg);
//    }

    @Override
    protected boolean isHeldExclusively() {
        return getState()==1;
    }

    public Condition newCondition(){
        return new ConditionObject();
    }

}
