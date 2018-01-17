package cn.milo.io;

import java.io.*;

/**
 * Created by mac on 2018/1/17.
 */
public class InputStreamReaderDemo {
    public static void main(String[] args)throws Exception {
        InputStream inputStream = new FileInputStream("/Users/mac/Desktop/IDEA Project/nio/io/InputStreamReaderDemo");
        int data1 = inputStream.read();
        while(data1 != -1){
            char theChar = (char) data1;
            System.out.println(theChar);
            data1 = inputStream.read();
        }


        Reader reader = new InputStreamReader(inputStream);
        int data = reader.read();
        while(data != -1){
            char theChar = (char) data;
            System.out.println(theChar);
            data = reader.read();
        }
        reader.close();


        OutputStream outputStream = new FileOutputStream("/Users/mac/Desktop/IDEA Project/nio/io/InputStreamReaderDemo");
        Writer writer = new OutputStreamWriter(outputStream);
        writer.write("你好");
        writer.close();
    }
}
