package netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import netty.protobuf.StudentPOJO;

import java.util.Scanner;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 通道准备就绪后执行
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StudentPOJO.Student student = StudentPOJO.Student.newBuilder().setId(1).setName("杨鹏").build();
        ctx.writeAndFlush(student);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务端" + ctx.channel().remoteAddress() + "发来消息:" + buf.toString(CharsetUtil.UTF_8));
    }

}
