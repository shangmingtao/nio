package cn.milo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by mac on 2018/1/23.
 */
public class SocketChannelDemo {
    public static void main(String[] args)throws Exception {
        //创建socketChannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1" , 8888));

        //输入流,但是对于buffer是写入    用户 <- (buffer <- channel)
        ByteBuffer readBuffer = ByteBuffer.allocate(32);
        int bytesRead = socketChannel.read(readBuffer);

        //输出流,但是对于buffer是读取    用户 -> (buffer -> channel)
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

    private static void tcpClient()throws Exception{
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
