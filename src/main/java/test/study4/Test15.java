package test.study4;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;


@Slf4j
public class Test15 {
    static Thread a1;
    static Thread b1;
    static Thread c1;
    public static void main(String[] args) {
        a1 = new Thread(() -> {
            for (int i=0;i<5;i++){
                LockSupport.park();
                System.out.print("a");
                LockSupport.unpark(b1);
            }
        });

        b1 = new Thread(() -> {
            for (int i=0;i<5;i++){
                LockSupport.park();
                System.out.print("b");
                LockSupport.unpark(c1);
            }
        });

        c1 = new Thread(() -> {
            for (int i=0;i<5;i++){
                LockSupport.park();
                System.out.print("c");
                LockSupport.unpark(a1);
            }
        });
        a1.start();
        b1.start();
        c1.start();
        ThreadUtil.sleep(1000);
        LockSupport.unpark(a1);
    }
}
