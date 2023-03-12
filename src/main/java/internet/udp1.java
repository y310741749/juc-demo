package internet;

import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class udp1 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket(8080);
        ThreadUtil.execute(() -> {
            try {
                while (true) {
                    byte[] bytes = new byte[1024];
                    DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
                    socket.receive(datagramPacket);
                    System.out.println(datagramPacket.getSocketAddress() + ":" + new String(bytes));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        while (true) {
            if (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                byte[] bytes = s.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getLocalHost(), 8080);
                socket.send(datagramPacket);
            }
        }

    }
}
