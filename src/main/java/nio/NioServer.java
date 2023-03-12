package nio;


import java.net.Socket;
import java.nio.ByteBuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        Selector selector = Selector.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while(true){
            int select = selector.select(3000);
            if(select==0){
//                System.out.println("无关注事件。。。");
                continue;
            }else {
                System.out.println("有事件产生..."+select);
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
                    SocketChannel socketChannel=null;
                    try{
                    socketChannel = serverSocketChannel.accept();
                        System.out.println(socketChannel.getRemoteAddress()+" 上线了");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));}
                    catch (Exception e){
                        iterator.remove();
                    }
                }
                if(key.isReadable()){
                    SocketChannel channel = null;
                    try {
                        System.out.println("产生读事件");
                        channel = (SocketChannel)key.channel();
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
                        channel.read(buffer);
                        System.out.println("读到客户端发来的数据:"+new String(buffer.array()));
                        //获得此selector注册的所有channel
                        Set<SelectionKey> keys = selector.keys();
                        for (SelectionKey p : keys) {
                            SelectableChannel c = p.channel();
                            if(c instanceof SocketChannel && c!=channel){
                                //转发消息
                                ByteBuffer wrap = ByteBuffer.wrap((channel.getRemoteAddress()+"发来消息:"+new String(buffer.array())).getBytes());
                                ((SocketChannel) c).write(wrap);
                            }
                        }
                        buffer.clear();
                    }catch (Exception e){
                        System.out.println(channel.getRemoteAddress()+"下线");
                    }finally {
                        //把当前key从集合删除，防止重复操作
                        iterator.remove();
                    }

                }

            }
        }
    }
}
