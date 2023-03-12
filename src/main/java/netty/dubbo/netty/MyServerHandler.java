package netty.dubbo.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import netty.dubbo.publicentity.MessageEntity;
import netty.dubbo.publicentity.RequestEntity;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class MyServerHandler extends SimpleChannelInboundHandler<MessageEntity> {
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端" + ctx.channel().remoteAddress() + "上线");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageEntity msg) throws Exception {
        //接收到数据，并处理
        int len = msg.getLength();
        byte[] content = msg.getContent();
        System.out.println("长度：" + len);
        System.out.println("内容：" + new String(content, CharsetUtil.UTF_8));
        String s = new String(content, CharsetUtil.UTF_8);
        RequestEntity requestEntity = JSON.parseObject(s, RequestEntity.class);
        List<Object[]> methodParams = getMethodParams(requestEntity.getParams());
        Object bean = Class.forName(requestEntity.getClassName()).newInstance();
        Method method = bean.getClass().getDeclaredMethod(requestEntity.getMethod(), getMethodParamsType(methodParams));
        String invoke = (String)method.invoke(bean, getMethodParamsValue(methodParams));
        byte[] response = invoke.getBytes(CharsetUtil.UTF_8);
        MessageEntity entity=new MessageEntity();
        entity.setLength(response.length);
        entity.setContent(response);
        ctx.writeAndFlush(entity);
    }

    private List<Object[]> getMethodParams(String[] methodParams) {
        List<Object[]> classs = new LinkedList<>();
        for (int i = 0; i < methodParams.length; i++) {
            classs.add(new Object[]{methodParams[i], String.class});
        }
        return classs;
    }

    public static Class<?>[] getMethodParamsType(List<Object[]> methodParams) {
        Class<?>[] classs = new Class<?>[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams) {
            classs[index] = (Class<?>) os[1];
            index++;
        }
        return classs;
    }

    /**
     * 获取参数值
     *
     * @param methodParams 参数相关列表
     * @return 参数值列表
     */
    public static Object[] getMethodParamsValue(List<Object[]> methodParams)
    {
        Object[] classs = new Object[methodParams.size()];
        int index = 0;
        for (Object[] os : methodParams)
        {
            classs[index] = (Object) os[0];
            index++;
        }
        return classs;
    }
}
