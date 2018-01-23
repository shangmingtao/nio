package cn.milo.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

/******************************************************
 ****** @ClassName   : BufferInputStreamDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 16 13:52     
 ****** @version     : v1.0.x                      
 *******************************************************/

/*
你这里，通过BufferedInputStream读取用的时间比通过InputStream读取用时时间长，是消耗在你从缓冲区里读取数据的时间。用了BufferedInputStream后你每次读取都是从缓冲区里拷贝数据，在后你再读，缓冲区没东西了就调IO从数据源读到缓冲区，然后你再从缓冲区读。为什么会这样呢，因为你自己建立的数组大小和缓冲区大小一样，根本就没起到缓冲作用。
当你程序的数组小于缓冲区的大小的时候才会起到缓冲作用。比如是byte[] b=new byte[2048];，你要读的数据是1G，那么你要调512次IO，假设一次1s，就512s，但如果用BufferedInputStream，你每从缓冲区读取4（8192/2048=4）次才调用一次IO（假设访问内存一次0.1s），总共要128次，就128s，加上总的从缓冲区拷贝数据的时间（512*0.1=51.2s），128+51.2=179.2。
这里用0.1s和1s来体现IO很耗时

http://www.genshuixue.com/i-cxy/p/12199465
 */
public class BufferInputStreamDemo {
    public static void main(String[] args)throws Exception {
        BufferedInputStream input = new BufferedInputStream(new FileInputStream("D:\\PROGRAM_INSTALL\\androidstudio_2.3.3.0.exe"),64*1024);
//        InputStream input = new FileInputStream("D:\\PROGRAM_INSTALL\\androidstudio_2.3.3.0.exe");
        long time1 = new Date().getTime();
        byte[] bytes = new byte[1024]; //1024*1024
        int bytesRead = input.read(bytes);
        while(bytesRead != -1) {
            for (byte b: bytes) {
            }
            bytesRead = input.read(bytes);
        }
        long time2 = new Date().getTime();
        System.out.println(time2 - time1);

    }
     //3144  //3040   1024       1024
     //803  //3000    128*1024   1024
     //836  //3000    64*1024   1024
     //1006  //3000   256*1024   1024
     //1114  //3000   8*1024     1024

    //http://bbs.csdn.net/topics/390517474/

    //BufferedInputStream没有readline方法是因为字节流没法识别"/n" 因为"/n" 是一个换行符,是一个字符

}
