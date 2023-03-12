package internet;

import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class udpServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9090);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
        datagramSocket.receive(datagramPacket);
        System.out.println("收到数据:" + new String(bytes));
        System.out.println(datagramPacket.getSocketAddress());
        System.out.println(datagramPacket.getAddress());
        ThreadUtil.sleep(Integer.MAX_VALUE);
    }
}
