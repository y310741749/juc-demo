package test.study4;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class comsumer {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            Message take = Provider.messageQueue.take();
            log.info("获得消息{}",take.toString());
        }
//        System.out.println("结束");
    }
}
