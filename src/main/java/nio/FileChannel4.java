package nio;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class FileChannel4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel channel = fileInputStream.getChannel();
        FileChannel channel1 = fileOutputStream.getChannel();
        channel1.transferFrom(channel,0,channel.size());
//        channel.transferTo()
        OutputStream os;
//        WritableByteChannel writableByteChannel = Channels.newChannel(fileOutputStream);
//        channel.tr

    }
}
