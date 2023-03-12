package nio;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D://1.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        System.out.println(new String(buffer.array()));
        fileInputStream.close();
    }
}
