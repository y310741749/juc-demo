package test.study7;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(() -> {
            log.debug("1...start");
            ThreadUtil.sleep(3000);
            log.debug("1...finish");
        });
        service.execute(() -> {
            log.debug("2...start");
            ThreadUtil.sleep(2000);
            log.debug("2...finish");
        });
        service.execute(() -> {
            log.debug("3...start");
            ThreadUtil.sleep(1000);
            log.debug("3...finish");
        });
        service.shutdown();

        while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
            log.debug("service.isShutdown():{}", service.isShutdown());
            log.debug("service.isTerminated():{}", service.isTerminated());
            log.info("还有任务在执行");
        }
        log.debug("==========================");
        log.info("service.isShutdown():{}", service.isShutdown());
        log.info("service.isTerminated():{}", service.isTerminated());
        log.info("所有线程执行完毕");
    }
}
