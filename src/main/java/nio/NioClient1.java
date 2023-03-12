package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioClient1 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接中");
            }
        }
        //再开一个线程监听接收到的数据
        new Thread(()->{
            System.out.println("开始监听....");
            while (true){
                ByteBuffer allocate = ByteBuffer.allocate(1024);
                int read = 0;
                try {
                    read = socketChannel.read(allocate);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(read>0){
                    System.out.println("接收到数据:"+new String(allocate.array()));
                }
            }
        }).start();
        while (true){
            System.out.println("请输入:");
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            if (line != null && !line.equals("")) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(line.getBytes());
                socketChannel.write(byteBuffer);
            }

        }

    }
}
