package netty.dubbo.provider;

import netty.dubbo.publicinterface.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        System.out.println("你好,客户端:" + msg);
        return "你好,客户端:" + msg;
    }
}
