package test.study123;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class test1 {
    public static void test1(String[] args) {
        new Thread(() -> {
            log.info("heheheh");
        }, "t1").start();
        log.info("heiheihei");
    }


}
