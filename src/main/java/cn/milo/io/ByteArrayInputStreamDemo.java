package cn.milo.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/******************************************************
 ****** @ClassName   : ByteArrayInputStreamDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 04 12:06     
 ****** @version     : v1.0.x                      
 *******************************************************/

/*
以内存中的byte数组作为输入源
 */
public class ByteArrayInputStreamDemo {

    public static void main(String[] args) throws Exception {

        byte[] bytes = new byte[1024];
        bytes[2] = (byte)'m';
        bytes[4] = (byte)'i';
        bytes[6] = (byte)'l';
        bytes[8] = (byte)'o';

        InputStream input = new ByteArrayInputStream(bytes);
        int data = input.read();
        while(data != -1) {
            System.out.println((char)data);
            data = input.read();
        }
    }
}
