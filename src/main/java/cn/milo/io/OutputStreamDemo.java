package cn.milo.io;

import java.io.FileOutputStream;
import java.io.OutputStream;

/******************************************************
 ****** @ClassName   : OutputStreamDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 03 18:09     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class OutputStreamDemo {
    public static void main(String[] args) throws  Exception {

        //字节写入
        String str = "hello \rworld";
        OutputStream outputStream = new FileOutputStream("D:\\MILO\\GIT\\nio\\io\\FileOutputStreamDemo");
        char[] chars = str.toCharArray();
        for (char c: chars) {
//            System.out.println(c);
            outputStream.write(c);
        }
        outputStream.close();



        System.out.println();
        System.out.print("---------------------------------------------------------");
        System.out.println();


        //字节数组写入
        String str2 = "hello \rworld2";
        OutputStream outputStream2 = new FileOutputStream("D:\\MILO\\GIT\\nio\\io\\FileOutputStreamDemo");
        byte[] chars2 = str2.getBytes("UTF-8");
        outputStream2.write(chars2);
        outputStream2.close();


        System.out.println();
        System.out.print("---------------------------------------------------------");
        System.out.println();


        //字节数组写入指定长度    offset:数组开始位置    length:数组长度
        String str3 = "hello \rworld2";
        OutputStream outputStream3 = new FileOutputStream("D:\\MILO\\GIT\\nio\\io\\FileOutputStreamDemo");
        byte[] chars3 = str3.getBytes("UTF-8");
        outputStream3.write(chars3 , 3 , 2);
        outputStream3.close();

    }
}
