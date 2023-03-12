package internet;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class server2 {
    public static void main(String[] args) throws IOException {
        Socket accept = null;
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D://证件照copy.jpg"), true);
            byte[] bytes = new byte[1024];
            while (inputStream.read(bytes) != -1) {
                fileOutputStream.write(bytes);
            }
        } catch (SocketException e) {
            assert accept != null;
            System.out.println(accept.toString() + "下线");
        }

    }
}
