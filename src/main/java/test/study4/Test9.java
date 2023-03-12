package test.study4;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.locks.LockSupport;

public class Test9 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("等待。。。");
            LockSupport.park();
            System.out.println("结束等待");
            LockSupport.park();
            System.out.println("结束二次等待");
        });
        thread.start();
        ThreadUtil.sleep(1000);
        LockSupport.unpark(thread);
        ThreadUtil.sleep(1000);
        LockSupport.unpark(thread);

    }
}
