package cn.milo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by mac on 2018/1/22.
 */
public class ChannelTransferToAndFromDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("/Users/mac/Desktop/IDEA Project/nio/nio/FileChannelDemo","rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size() + 10;

        toChannel.transferFrom(fromChannel,position,count);

//        transferFrom
//        long position = 0;
//        long count = toChannel.size();
//        toChannel.transferFrom(position, count, fromChannel);


        ByteBuffer byteBuffer = ByteBuffer.allocate(3);//创建一个3长度的缓存区
        int bytesRead = toChannel.read(byteBuffer); //读进buffer

        while(bytesRead != -1){
            System.out.println("Read : " + bytesRead);
            byteBuffer.flip(); //是缓存区由写变为读

            while(byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get());//Relative get method. Reads the byte at this buffer's current position, and then increments the position.
            }

            System.out.println(); //清空缓存区  clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据

            byteBuffer.clear();
            bytesRead = toChannel.read(byteBuffer);
        }

        fromFile.close();
        toFile.close();

        //capacity 容量
        //position 位置
        //limit    限制
    }
}
