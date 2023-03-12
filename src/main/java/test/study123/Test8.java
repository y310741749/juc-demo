package test.study123;

import sun.net.ftp.FtpClient;

import java.io.*;

public class Test8 {
    public static void main(String[] args) throws InterruptedException, IOException {
        Thread t1 = new Thread(() -> {
            while (true){
            }
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        System.out.println("打断标记:"+t1.isInterrupted());
        t1.interrupt();
        System.out.println("被打断");
        System.out.println("打断标记:"+t1.isInterrupted());

        BufferedOutputStream bf=new BufferedOutputStream(new FileOutputStream(new File("")));
//        FileOutputStream fileOutputStream=new FileOutputStream()
//        FileOutputStream fileOutputStream;
//        fileOutputStream.getChannel()
        FtpClient ftpClient;





    }
}
