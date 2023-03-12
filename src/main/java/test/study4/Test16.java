package test.study4;

import cn.hutool.core.thread.ThreadUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Test16 {
    static Map<Integer,Boolean> flag = new HashMap<>();
    static {
        flag.put(1,true);
    }
    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                if(!flag.get(1)){
                    break;
                }
            }
        }).start();
        ThreadUtil.sleep(1000);
        flag.put(1,false);
    }
}
