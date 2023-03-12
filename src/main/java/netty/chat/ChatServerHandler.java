package netty.chat;


import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler {

    public static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 上线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端"+channel.remoteAddress()+"上线");
        channelGroup.writeAndFlush("【客户端】" + channel.remoteAddress() + "上线了\n");
        channelGroup.add(channel);
    }

    /**
     * 离线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("客户端"+channel.remoteAddress()+"离线");
        channelGroup.writeAndFlush("【客户端】" + channel.remoteAddress() + "离线了\n");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String buf = (String) msg;
        System.out.println("客户" + ctx.channel().remoteAddress() + "发来消息:" + buf);
        channelGroup.forEach(p -> {
            if (p != ctx.channel()) {

                p.writeAndFlush("客户" + ctx.channel().remoteAddress() + "发来消息:" + buf);
            }
        });
    }
}
