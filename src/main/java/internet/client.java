package internet;

import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream outputStream = socket.getOutputStream();
//        while (true){
//            outputStream.write("你好".getBytes());
//            ThreadUtil.sleep(300000);
//        }

        Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                outputStream.write(s.getBytes());
            }
    }
}
