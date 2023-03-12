package socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class BioServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket=new ServerSocket(6666);
        while (true){
            Socket accept = serverSocket.accept();
            log.info("获取到连接");
            executorService.execute(()->getMessage(accept));
        }

    }

    public static void getMessage(Socket accept){
        try {
            log.info("读数据..........");
            InputStream inputStream = accept.getInputStream();
            System.out.println("读完数据......");
            byte[] bytes = new byte[1024];
            System.out.println("111111......");
            int len = 0;
            System.out.println("222222......");
            while ((len=inputStream.read(bytes))!=-1){
                log.info(new String(bytes,0,bytes.length));
                System.out.println("333333......");
            }
            System.out.println("4444444......");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
