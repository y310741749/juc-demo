package netty.dubbo.netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import netty.dubbo.publicentity.MessageEntity;
import netty.dubbo.publicentity.RequestEntity;

import java.util.concurrent.Callable;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageEntity> implements Callable {
    private MessageEntity messageEntity;
    private ChannelHandlerContext channelHandlerContext;
    private String result;

    public MyClientHandler(MessageEntity messageEntity) {
        this.messageEntity = messageEntity;
    }

    @Override
    protected synchronized void channelRead0(ChannelHandlerContext ctx, MessageEntity msg) throws Exception {
        result = new String(msg.getContent(), CharsetUtil.UTF_8);
        notify();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelHandlerContext = ctx;
    }

    @Override
    public synchronized Object call() throws Exception {
        channelHandlerContext.writeAndFlush(messageEntity);
        wait();
        return result;
    }
}
