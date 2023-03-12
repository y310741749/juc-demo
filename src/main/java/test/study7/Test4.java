package test.study7;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class Test4 {
    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
////        scheduledExecutorService.schedule(()->{
////            log.debug("start");
////            int i=1/0;
////            log.debug("end");
////        },1, TimeUnit.SECONDS);
//        scheduledExecutorService.execute(() -> {
//            log.debug("start");
//            int i = 1 / 0;
//            log.debug("end");
//        });
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            log.debug("start");
            int i = 1 / 0;
        });
    }
}
