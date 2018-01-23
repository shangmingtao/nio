package cn.milo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by mac on 2018/1/23.
 */
public class SocketChannelDemo {
    public static void main(String[] args)throws Exception {
        //创建socketChannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1" , 8888));

        //读取
        ByteBuffer readBuffer = ByteBuffer.allocate(32);
        int bytesRead = socketChannel.read(readBuffer);

        //写入
        String newData = "New String to write to socket";
        System.currentTimeMillis();

        ByteBuffer writeBuffer = ByteBuffer.allocate(32);
        writeBuffer.clear();
        writeBuffer.put(newData.getBytes());

        writeBuffer.flip();
        while (writeBuffer.hasRemaining()){
            socketChannel.write(writeBuffer);
        }


        socketChannel.close();

    }
}
