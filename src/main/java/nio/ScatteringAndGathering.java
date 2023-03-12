package nio;




import java.nio.ByteBuffer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.stream.Stream;

public class ScatteringAndGathering {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(7000));
        SocketChannel accept = serverSocketChannel.accept();
        ByteBuffer[] byteBuffers=new ByteBuffer[2];
        byteBuffers[0]=ByteBuffer.allocate(5);
        byteBuffers[1]=ByteBuffer.allocate(3);
        int byteRead=0;
        while (accept.read(byteBuffers)!=-1&&(byteRead+=accept.read(byteBuffers))<8){
            System.out.println(byteRead += accept.read(byteBuffers));
            Stream.of(byteBuffers).map(buffer->"posttion:"+buffer.position()+",limit"+buffer.limit()).forEach(System.out::println);
        }
    }
}
