package netty.dubbo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import netty.inandoutboundhandler.MyByteToLongEncoder;
import netty.inandoutboundhandler.MyReplayingDecoder;

public class MyServer {
    NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    NioEventLoopGroup workGroup = new NioEventLoopGroup();
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    public void start() throws InterruptedException {
        serverBootstrap.group(bossGroup, workGroup) //设置两个线程组
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast(new MyByteToEntityDecoder());
                                pipeline.addLast(new MyByteToEntityEncoder());
                                pipeline.addLast(new MyServerHandler());
                            }
                        }
                );
        ChannelFuture sync = serverBootstrap.bind(6668).sync();
        ChannelFuture sync1 = sync.channel().closeFuture().sync();
    }

}
