package cn.milo.niodemo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by mac on 2018/1/23.
 */
public class SocketChannelDemo {

    public static void main(String[] args)throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        SocketChannel socketChannel = null;

        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1" , 8888));//该方法可能在连接建立之前就返回了

        if(socketChannel.finishConnect()){
            int i = 0;
            while (true){
                TimeUnit.SECONDS.sleep(1);
                String info = "I`m "+ i++ + "th information from client\r\n";
                byteBuffer.clear();
                byteBuffer.put(info.getBytes());
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.println(byteBuffer);
                    socketChannel.write(byteBuffer);
                }
            }
        }
    }
}
