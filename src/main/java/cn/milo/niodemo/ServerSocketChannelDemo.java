package cn.milo.niodemo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by mac on 2018/1/23.
 */
public class ServerSocketChannelDemo {

    public static  void handleAccept(SelectionKey key)throws Exception{
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(64));
    }

    public static void handleRead(SelectionKey key)throws Exception{
        SocketChannel socketChannel = (SocketChannel)key.channel();
        ByteBuffer byteBuffer = (ByteBuffer)key.attachment();
        long bytesRead = socketChannel.read(byteBuffer);
        while (bytesRead > 0){
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get());
            }
            System.out.println();
            byteBuffer.clear();
            bytesRead = socketChannel.read(byteBuffer);
        }
        if (bytesRead == -1){
            socketChannel.close();
        }
    }

    public static void selector() throws Exception{
        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;

        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",8888));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector , SelectionKey.OP_ACCEPT);

        while (true){
            if(selector.select(3000) == 0){
                System.out.println("==");
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isAcceptable()){
                    handleAccept(key);
                }
                if (key.isReadable()){
                    handleRead(key);
                }
                if (key.isWritable() && key.isValid()){
                    System.out.printf("write");

                }
                if (key.isConnectable()){
                    System.out.printf("connect");
                }
                iterator.remove();
            }
        }

    }

    public static void main(String[] args) throws Exception{
        selector();
    }
}
