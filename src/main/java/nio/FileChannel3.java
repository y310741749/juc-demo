package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannel3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel channel = fileInputStream.getChannel();
        FileChannel channel1 = fileOutputStream.getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        while(channel.read(buffer)!=-1){
            buffer.flip();
            channel1.write(buffer);
            buffer.clear();
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
