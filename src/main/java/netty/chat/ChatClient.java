package netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("decoder",new StringDecoder());
                        pipeline.addLast("encoder",new StringEncoder());
                        ch.pipeline().addLast(new ChatClientHandler());
                    }
                });
        ChannelFuture localhost = bootstrap.connect("127.0.0.1", 8080).sync();
        Channel channel = localhost.channel();
        System.out.println("客户端准备就绪"+channel.remoteAddress());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            channel.writeAndFlush(scanner.nextLine());
        }
        localhost.channel().closeFuture().sync();
    }
}
