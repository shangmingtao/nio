package cn.milo.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

/******************************************************
 ****** @ClassName   : io_nio_mmap.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 30 12:02     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class io_nio_mmap {

    private static String filePath = "/Users/mac/Documents/安装程序/eclipse-standard-kepler-SR1-win32.zip";

    /*
    normal io
     */
    public static void inputStreamDemo()throws Exception{
        long start = System.currentTimeMillis();
        InputStream input = new FileInputStream(filePath);
        byte[] bytes = new byte[1024]; //1024*1024

        int byteCount = 0;
        byte b1 ;

        int bytesRead = input.read(bytes);
        while(bytesRead != -1) {
            for (byte b: bytes) {
                b1 = b;
                byteCount ++;
            }
            bytesRead = input.read(bytes);
        }
        long end = System.currentTimeMillis();
        System.out.println("inputString : " + (end - start) + " , byteCount = " + byteCount);
        input.close();
    }

    /*
    BufferInputStreamDemo
     */
    public static void bufferInputStreamDemo()throws Exception{
        long start = System.currentTimeMillis();
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(filePath),8*1024);
        byte[] bytes = new byte[1024]; //1024*1024

        byte b1;
        int byteCount = 0;

        int bytesRead = input.read(bytes);
        while(bytesRead != -1) {
            for (byte b: bytes) {
                b1 = b;
                byteCount ++;
            }
            bytesRead = input.read(bytes);
        }
        long end = System.currentTimeMillis();
        System.out.println("BufferedInputStream : " + (end - start) + " , byteCount = " + byteCount);
        input.close();
    }

    /*
  allocate
   */
    public static void allocate()throws Exception{
        long start = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath , "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)randomAccessFile.length());

        int byteCount = 0;
        int bytesRead = channel.read(byteBuffer);

        while(bytesRead != -1){
            byteBuffer.flip();
            while(byteBuffer.hasRemaining()){
                byteBuffer.get();
                byteCount ++;
            }
            byteBuffer.clear();//清空缓存区  clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据
            bytesRead = channel.read(byteBuffer);
        }
        long end = System.currentTimeMillis();
        System.out.println("allocate : " + (end - start) + " , byteCount = " + byteCount);
        channel.close();
    }

    /*
    allocateDirect
     */
    public static void allocateDirect()throws Exception{
        long start = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath , "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int)randomAccessFile.length());

        int byteCount = 0;
        int bytesRead = channel.read(byteBuffer);

        while(bytesRead != -1){
            byteBuffer.flip();
            while(byteBuffer.hasRemaining()){
                byteBuffer.get();
                byteCount ++;
            }
            byteBuffer.clear();//清空缓存区  clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据
            bytesRead = channel.read(byteBuffer);
        }
        long end = System.currentTimeMillis();
        System.out.println("allocateDirect : " + (end - start) + " , byteCount = " + byteCount);
        channel.close();
    }

    /*
    mmap
     */
    public static void mmap()throws Exception {
        long start = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");
        FileChannel channel = randomAccessFile.getChannel();

        int byteCount = 0;
        int len = (int) randomAccessFile.length();

        MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0, randomAccessFile.length());
        for (int offset = 0; offset < len; offset ++) {
            if (offset < len) {
                buff.get(offset);
//                System.out.println(buff.get(offset));
                byteCount ++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("mmap : " + (end - start) + " , byteCount = " + byteCount);
        channel.close();
    }

    public static void main(String[] args)throws Exception {
//        allocate();
        allocateDirect();
        mmap();
        inputStreamDemo();
        bufferInputStreamDemo();
    }

}
