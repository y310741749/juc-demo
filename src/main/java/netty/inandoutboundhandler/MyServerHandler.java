package netty.inandoutboundhandler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

public class MyServerHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端"+ctx.channel().remoteAddress()+"上线");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("客户端发来的数据:" + msg);
        ctx.writeAndFlush(654321L);
    }
}
