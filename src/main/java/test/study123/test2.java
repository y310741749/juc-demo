package test.study123;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class test2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> {
            log.info("开始沉睡");
            Thread.sleep(2000);
            log.info("结束沉睡");
            return 100;
        });
        Thread t2 = new Thread(integerFutureTask, "t2");
        Thread t3 = new Thread(integerFutureTask, "t2");
        log.info("{}",t2.getId());
        log.info("{}",t3.getId());
        t2.start();
        t3.start();

        System.out.println("===开始获取结果===");
        Integer integer = integerFutureTask.get();
        System.out.println("===获取结果完成===");
        log.info("获得结果{}", integer);
    }
}
