package cn.milo.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by mac on 2018/1/22.
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("/Users/mac/Desktop/IDEA Project/nio/nio/FileChannelDemo","rw");
        FileChannel fileChannel = accessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(3);//创建一个3长度的缓存区
        int bytesRead = fileChannel.read(byteBuffer); //读进buffer

        while(bytesRead != -1){
            System.out.println("Read : " + bytesRead);
            byteBuffer.flip(); //是缓存区由写变为读

            while(byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get());//Relative get method. Reads the byte at this buffer's current position, and then increments the position.
            }

            System.out.println(); //清空缓存区  clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据

            byteBuffer.clear();
            bytesRead = fileChannel.read(byteBuffer);
        }

        accessFile.close();

        //capacity 容量
        //position 位置
        //limit    限制
    }
}
