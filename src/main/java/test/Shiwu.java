package test;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Shiwu {
    private int i = 0;
    private int j = 1;

    public static void main(String[] args) {
        Shiwu s=new Shiwu();
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            log.info("i:{}",s.getI());
        },1,1, TimeUnit.SECONDS);
        s.rollback();

    }

    @Transactional(rollbackFor = Exception.class)
    public void rollback() {
        ThreadUtil.sleep(2000);
        setI(10);
        System.out.println(getI()/0);
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
