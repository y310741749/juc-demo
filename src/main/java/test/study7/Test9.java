package test.study7;

import cn.hutool.core.thread.ThreadUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class Test9 {
    public static void main(String[] args) throws InterruptedException {
        String[] jindu = new String[10];
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Random r = new Random();
        CountDownLatch latch=new CountDownLatch(10);
        executorService.execute(new Task(0,r,jindu,latch));
        executorService.execute(new Task(1,r,jindu,latch));
        executorService.execute(new Task(2,r,jindu,latch));
        executorService.execute(new Task(3,r,jindu,latch));
        executorService.execute(new Task(4,r,jindu,latch));
        executorService.execute(new Task(5,r,jindu,latch));
        executorService.execute(new Task(6,r,jindu,latch));
        executorService.execute(new Task(7,r,jindu,latch));
        executorService.execute(new Task(8,r,jindu,latch));
        executorService.execute(new Task(9,r,jindu,latch));

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.print("\r"+ Arrays.toString(jindu));
        },0,1, TimeUnit.SECONDS);
        latch.await();
        executorService.shutdown();
        scheduledExecutorService.shutdown();
        System.out.println("\n游戏开始");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    }
}

@AllArgsConstructor
class Task implements Runnable {
    private int index;
    private Random r;
    private String[] jindu;
    private CountDownLatch latch;

    @Override
    public void run() {
        try{
            for (int i = 0; i <= 100; i++) {
                ThreadUtil.sleep(r.nextInt(100));
                jindu[index] = i + "%";
            }
        }finally {
            latch.countDown();
        }

    }
}
