package test.study123;

import java.io.*;
import java.util.concurrent.*;

public class Test4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newSingleThreadExecutor();

        Runnable ab = () -> {
            int i = 0;
            FileInputStream is = null;
            try {
                is = new FileInputStream(new File("D:\\下载\\centos7.4镜像\\CentOS-7-x86_64-DVD-1708.iso"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileOutputStream os = null;
            try {
                os = new FileOutputStream("D:\\下载\\centos7.4镜像\\CentOS-7-x86_64-DVD-1708.iso2");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] buf = new byte[10240];
            int bytesRead = 0;
            while (true) {
                try {
                    if (!((bytesRead = is.read(buf)) != -1)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Thread.currentThread().isInterrupted()) {
                    throw new RuntimeException("线程被中断");
                }
                i += 1;
                System.out.println("读取第" + i + "次");
                try {
                    os.write(buf, 0, bytesRead);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Future<Object> submit = (Future<Object>) executorService.submit(ab);
        Thread.sleep(1000);
        boolean cancel = submit.cancel(true);
        System.out.println("任务取消结果:"+cancel);
        System.out.println("任务取消是否成功:"+submit.isCancelled());
        submit.get();
    }
}
