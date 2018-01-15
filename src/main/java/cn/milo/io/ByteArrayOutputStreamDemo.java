package cn.milo.io;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/******************************************************
 ****** @ClassName   : ByteArrayOutputStreamDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 04 12:09     
 ****** @version     : v1.0.x                      
 *******************************************************/

/*
将字节写入到output中,ByteArrayOutputStream不是真实的物理文件或者网络,而是内存.
 */
public class ByteArrayOutputStreamDemo {

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write("This text is converted to bytes".getBytes("UTF-8"));
        byte[] bytes = output.toByteArray();
        System.out.println(Arrays.toString(bytes));
    }
}
