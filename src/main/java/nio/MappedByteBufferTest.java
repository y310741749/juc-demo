package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile rw = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = rw.getChannel();

        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 10);
        map.put(0,(byte)'A');
        map.put(5,(byte)'B');
        map.put(9,(byte)'C');
        channel.close();
    }
}
