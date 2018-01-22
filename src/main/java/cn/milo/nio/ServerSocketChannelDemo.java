package cn.milo.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by mac on 2018/1/23.
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args)throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",8888));
        serverSocketChannel.configureBlocking(false);
//        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
//        }

        serverSocketChannel.close();

    }
}
