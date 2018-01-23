package cn.milo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by mac on 2018/1/23.
 */
public class DatagramChannelDemo {

    public static void main(String[] args)throws Exception {
        //company
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress("127.0.0.1",8888));

        //receive
        ByteBuffer receiveBuffer = ByteBuffer.allocate(32);
        receiveBuffer.clear();
        channel.receive(receiveBuffer);

        //send
        String newData = "New String to send by udp";
        System.currentTimeMillis();

        ByteBuffer sendBuffer = ByteBuffer.allocate(32);
        sendBuffer.clear();
        sendBuffer.put(newData.getBytes());
        sendBuffer.flip();

        int byteSend = channel.send(sendBuffer,new InetSocketAddress("127.0.0.1" , 8888));
    }
}
