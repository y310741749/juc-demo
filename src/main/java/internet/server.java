package internet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = null;
        try{
            while (true) {
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                System.out.println(socket.toString()+"上线");
                byte[] buffer = new byte[1024];
                while ((inputStream.read(buffer)) != -1) {
                    System.out.println("收到客户端消息:" + new String(buffer));
                }
            }
        }catch (SocketException e){
            assert socket != null;
            System.out.println(socket.toString()+"下线");
        }


    }
}
