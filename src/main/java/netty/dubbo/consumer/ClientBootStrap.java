package netty.dubbo.consumer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.CharsetUtil;
import netty.dubbo.netty.MyClient;
import netty.dubbo.publicentity.MessageEntity;
import netty.dubbo.publicentity.RequestEntity;

import java.util.concurrent.ExecutionException;

public class ClientBootStrap {
    //模拟浏览器发送请求
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setAddress("localhost");
        requestEntity.setPort(6668);
        requestEntity.setClassName("netty.dubbo.provider.HelloServiceImpl");
        requestEntity.setMethod("hello");
        String[] params = {"hahahashsdjhjd"};
        requestEntity.setParams(params);
        String message = JSON.toJSONString(requestEntity);
        byte[] content = message.getBytes(CharsetUtil.UTF_8);
        MessageEntity entity = new MessageEntity();
        entity.setLength(content.length);
        entity.setContent(content);
        MyClient myClient = new MyClient(entity, requestEntity);
        Object result = myClient.getResult();
        System.out.println("返回结果:" + result);
    }
}
