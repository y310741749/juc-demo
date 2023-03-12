package internet;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class client2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(FileUtil.readBytes(new File("D://证件照.jpg")));
    }
}
