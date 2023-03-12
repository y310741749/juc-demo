package netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ByteBuf01 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,杨彭回到宿舍电话和的撒客户端卡萨丁", CharsetUtil.UTF_8);
        byteBuf.capacity();
        if(byteBuf.hasArray()){
            byte[] array = byteBuf.array();
            log.info("等待");
        }
    }
}
