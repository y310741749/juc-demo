package test.study7;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Test1 {
    public static void main(String[] args) {
        BlockQueue<Runnable> stringBlockQueue = new BlockQueue<>(1, 3);
        ThreadPool threadPool = new ThreadPool(stringBlockQueue, 1, (queue, task) -> queue.put(task));
        log.info("线程池创建完毕...");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            log.info("读取到控制台输入:{}", s);
            threadPool.execue(() -> {
                log.info("开始执行任务......");
                log.info("收到控制台输入数据:" + s);
                ThreadUtil.sleep(100000);
                log.info("任务执行完成");
            });
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
    }
}

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockQueue<T> queue, T task);
}

@Slf4j
class BlockQueue<T> {
    private Deque<T> deque = new ArrayDeque<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition fullCondition = lock.newCondition();
    private Condition kongCondition = lock.newCondition();
    private int size;
    private long timeOut;

    BlockQueue(int size) {
        this.size = size;
    }

    BlockQueue(int size, long timeOut) {
        this.size = size;
        this.timeOut = timeOut;
    }

    //生产者
    public void put(T t) {
        try {
            lock.lock();
            boolean await = true;
            if (getSize() == size) {
                log.info("队列已满，等待...");
                try {
                    await = fullCondition.await(timeOut, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!await) {
                log.error("等待超时，返回...");
                return;
            }
            deque.addLast(t);
            log.info("放入元素");
            kongCondition.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        try {
            lock.lock();
            if (getSize() == size) {
                rejectPolicy.reject(this, task);
            } else {
                deque.addLast(task);
                log.info("放入元素");
                kongCondition.signal();
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    //消费者
    public T take() {
        try {
            lock.lock();
            if (getSize() == 0) {
                log.info("队列为空,等待...");
                try {
                    kongCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            log.info("消费元素");
            fullCondition.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //获取大小
    public int getSize() {
        return deque.size();
    }

}

//线程池
class ThreadPool {
    private BlockQueue<Runnable> blockQueue;
    private int coreSize;
    private Set<Workers> workersSet = new HashSet<>();
    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(BlockQueue<Runnable> blockQueue, int coreSize, RejectPolicy<Runnable> rejectPolicy) {
        this.blockQueue = blockQueue;
        this.coreSize = coreSize;
        this.rejectPolicy = rejectPolicy;
    }

    //提交任务
    public void execue(Runnable task) {
        synchronized (this) {
            System.out.println("进入同步代码块");
            if (workersSet.size() < coreSize) {
                Workers workers = new Workers(task, blockQueue);
                workersSet.add(workers);
                workers.start();
            } else {
                blockQueue.tryPut(rejectPolicy, task);
            }
        }
    }
}

class Workers extends Thread {
    private Runnable runnable;
    private BlockQueue<Runnable> blockQueue;

    public Workers(Runnable runnable, BlockQueue<Runnable> blockQueue) {
        this.runnable = runnable;
        this.blockQueue = blockQueue;
    }

    @Override
    public void run() {
        while (true) {
            if (runnable != null) {
                try {
                    runnable.run();
                } finally {
                    runnable = null;
                }
            } else {
                blockQueue.take().run();
            }
        }
    }


}



