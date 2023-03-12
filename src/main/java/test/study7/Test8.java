package test.study7;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * countDownLanch
 */
@Slf4j
public class Test8 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(()->{
            try{
                ThreadUtil.sleep(1000);
                log.debug("1...");
            }finally {
                countDownLatch.countDown();
            }
        });
        executorService.execute(()->{
            try{
                ThreadUtil.sleep(2000);
                log.debug("2...");
            }finally {
                countDownLatch.countDown();
            }
        });
        executorService.execute(()->{
            try{
                ThreadUtil.sleep(1500);
                log.debug("3...");
            }finally {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        System.out.println("执行结束");
    }
}
