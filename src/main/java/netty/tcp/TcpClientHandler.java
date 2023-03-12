package netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class TcpClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int m = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ByteBuf byteBuf = msg.readBytes(1);
        ByteBuffer buffer = byteBuf.nioBuffer();
        int i = msg.readInt();
        m += i;
        if(m>20){
            System.out.println("m:"+m);
        }

    }


}
