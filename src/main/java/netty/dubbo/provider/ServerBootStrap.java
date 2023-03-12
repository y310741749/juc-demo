package netty.dubbo.provider;

import netty.dubbo.netty.MyServer;

public class ServerBootStrap {
    public static void main(String[] args) throws InterruptedException {
        MyServer myServer = new MyServer();
        myServer.start();
    }
}
