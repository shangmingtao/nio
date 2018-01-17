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
