package socket;


import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ByteUtil;
import com.leansoft.bigqueue.BigQueueImpl;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadShunxu {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D:\\result.txt"), true)));
        BigQueueImpl bigQueue = new BigQueueImpl("D:\\queue", "1");
        for (int i = 1; i <= 99; i++) {
            bigQueue.enqueue(ByteUtil.intToBytes(i));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<Integer>> taskList = new ArrayList<>(5);
        List<Integer> data = new ArrayList<>(5);
        try{
            while (true){
                int length = 5; //步长
                for (int i = 0; i < length; i++) {
                    if(bigQueue.isEmpty()){
                        if(i==0){
                            executorService.shutdown();
                            return;
                        }
                        length=i-1;
                        break;
                    }
                    byte[] dequeue = bigQueue.dequeue();
                    int i1 = ByteUtil.bytesToInt(dequeue);
                    System.out.println("获取到数据:"+i1);
                    data.add(i1);
                }
                for (int i = 0; i < length; i++) {
                    taskList.add(executorService.submit(new jiexi(data.get(i))));
                }
                for (Future<Integer> future : taskList) {
                    Integer integer = future.get();
                    System.out.println("写入数据:"+integer);
                    writer.write(integer+",");
                    writer.flush();
                }
                taskList.clear();
                data.clear();
            }
        }finally {
            writer.close();
        }

    }
}

class jiexi implements Callable<Integer> {
    private Integer bytes;

    public jiexi(Integer bytes) {
        System.out.println("初始化数据:"+bytes);
        this.bytes = bytes;
    }

    @Override
    public Integer call() throws Exception {
        ThreadUtil.sleep(1000);
        return bytes*2;
    }
}
