package netty.dubbo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.dubbo.publicentity.MessageEntity;
import netty.dubbo.publicentity.RequestEntity;
import netty.inandoutboundhandler.MyByteToLongEncoder;
import netty.inandoutboundhandler.MyReplayingDecoder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClient {
    private static ExecutorService executor = Executors.newFixedThreadPool(5);
    private MessageEntity messageEntity;
    private RequestEntity requestEntity;
    private MyClientHandler myClientHandler;


    public MyClient(MessageEntity messageEntity, RequestEntity requestEntity) {
        this.messageEntity = messageEntity;
        this.requestEntity = requestEntity;
        myClientHandler = new MyClientHandler(messageEntity);
    }

    NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();

    public Object getResult() throws InterruptedException, ExecutionException {
        start();
        return executor.submit(myClientHandler).get();
    }

    public void start() throws InterruptedException {
        bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new MyByteToEntityEncoder());
                pipeline.addLast(new MyByteToEntityDecoder());
                pipeline.addLast(myClientHandler);
            }
        });

        bootstrap.connect(requestEntity.getAddress(), requestEntity.getPort()).sync();
    }

}
