package nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel1 {
    public static void main(String[] args) throws IOException {
        String str = "hello,netty,杨鹏";
        FileOutputStream fileOutputStream = new FileOutputStream("D://1.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();
        channel.write(buffer);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
