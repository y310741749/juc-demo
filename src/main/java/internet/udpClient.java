package internet;

import java.io.IOException;
import java.net.*;

public class udpClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8080);
        InetAddress localhost = InetAddress.getByName("localhost");
        datagramSocket.send(new DatagramPacket("你好,udp".getBytes(), 0, "你好,udp".getBytes().length, localhost, 9090));
        System.out.println("发送成功");
    }
}
