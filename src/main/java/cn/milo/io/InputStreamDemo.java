package cn.milo.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/******************************************************
 ****** @ClassName   : InputStreamDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 03 17:29     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class InputStreamDemo {
    public static void main(String[] args) throws Exception {


        //单个读取
        InputStream inputStream = new FileInputStream("D:\\MILO\\IdeaProject\\nio\\io\\FileInputStreamDemo");
        int data = inputStream.read();
        while(data != -1){
            System.out.print((char)data);
            data =  inputStream.read();
        }
        inputStream.close();

        System.out.println();
        System.out.print("---------------------------------------------------------");
        System.out.println();

        //数组读取
        InputStream inputstream = new FileInputStream("D:\\MILO\\IdeaProject\\nio\\io\\FileInputStreamDemo");
        byte[] dataByte = new byte[1024];
        int bytesRead = inputstream.read(dataByte);
        while(bytesRead != -1) {
            for (byte b: dataByte) {
                System.out.print((char)b);
            }
            bytesRead = inputstream.read(dataByte);
        }
        inputstream.close();

        System.out.println();
        System.out.print("---------------------------------------------------------");
        System.out.println();

        //数组读取 指定长度offset:开始位置    length:长度
        InputStream inputstream2 = new FileInputStream("D:\\MILO\\IdeaProject\\nio\\io\\FileInputStreamDemo");
        byte[] dataByte2 = new byte[5];
        int bytesRead2 = inputstream2.read(dataByte2 , 3 , 2);
        while(bytesRead2 != -1) {
            for (int i = 0; i <dataByte2.length ; i++) {
                System.out.println((char)dataByte2[i]);
            }
            bytesRead2 = inputstream2.read(dataByte2, 3 , 2);
        }
        inputstream2.close();
    }
}
