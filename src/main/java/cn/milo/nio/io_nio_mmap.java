package cn.milo.nio;

import java.io.*;
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

//    private static String filePath = "/Users/mac/Documents/安装程序/eclipse-standard-kepler-SR1-win32.zip";
    private static String filePath = "C:\\Users\\admin\\Desktop\\笔记\\redis学习笔记\\redis项目.rar";
//    private static String filePath = "D:\\PROGRAM_INSTALL\\Visio2010-xdowns.rar";

    //readBytes(byte b[], int off, int len)

    /*
    normal io
     */
    public static void inputStreamDemo()throws Exception{
        long start = System.currentTimeMillis();
        InputStream input = new FileInputStream(filePath);
        byte[] bytes = new byte[67980260]; //1024*1024

        int byteCount = 0;
        byte b1 ;

        int bytesRead = input.read(bytes);
        while(bytesRead != -1) {
//            for (byte b: bytes) {
//                b1 = b;
//                byteCount ++;
//            }
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
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(filePath),67980260);
        byte[] bytes = new byte[67980260]; //1024*1024

        byte b1;
        int byteCount = 0;

        int bytesRead = input.read(bytes);
        while(bytesRead != -1) {
//            for (byte b: bytes) {
//                b1 = b;
//                byteCount ++;
//            }
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
//        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath , "rw");
//        FileChannel channel = randomAccessFile.getChannel();
//        ByteBuffer byteBuffer = ByteBuffer.allocate((int)randomAccessFile.length());
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(67980260);

        int byteCount = 0;
        int bytesRead = channel.read(byteBuffer);

        while(bytesRead != -1){
            byteBuffer.flip();
//            while(byteBuffer.hasRemaining()){
//                byteBuffer.get();
//                byteCount ++;
//            }
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
//        ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int)randomAccessFile.length());
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int)randomAccessFile.length());

        int byteCount = 0;
        int bytesRead = channel.read(byteBuffer);

        while(bytesRead != -1){
            byteBuffer.flip();
//            while(byteBuffer.hasRemaining()){
//                byteBuffer.get();
//                byteCount ++;
//            }
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
        byte[] b = new byte[1024*4];
        int len = (int) randomAccessFile.length();

        MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0, randomAccessFile.length());
        for (int offset = 0; offset < len; offset += 1024*4) {

            if (len - offset > 1024*4) {
                buff.get(b);
            } else {
                buff.get(new byte[len - offset]);
            }
            byteCount ++;
        }
        long end = System.currentTimeMillis();
        System.out.println("mmap : " + (end - start) + " , byteCount = " + byteCount);
        channel.close();
    }

    public static void main(String[] args)throws Exception {
        inputStreamDemo();
        bufferInputStreamDemo();
        allocate();
        allocateDirect();
        mmap();
        //http://blog.csdn.net/fcbayernmunchen/article/details/8635427
    }

}
