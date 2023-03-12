package netty.dubbo.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;
import netty.dubbo.publicentity.MessageEntity;
import netty.dubbo.publicentity.RequestEntity;

import java.util.List;

public class MyByteToEntityDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int i = in.readInt();
        byte[] content = new byte[i];
        in.readBytes(content);
        MessageEntity entity=new MessageEntity();
        entity.setLength(i);
        entity.setContent(content);
        out.add(entity);
    }
}
