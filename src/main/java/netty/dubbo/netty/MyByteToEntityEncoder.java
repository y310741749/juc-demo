package netty.dubbo.netty;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.dubbo.publicentity.MessageEntity;
import netty.dubbo.publicentity.RequestEntity;

public class MyByteToEntityEncoder extends MessageToByteEncoder<MessageEntity> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageEntity msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
