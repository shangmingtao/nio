package cn.milo.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/******************************************************
 ****** @ClassName   : WriterDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 04 12:16     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class WriterDemo {

    public static void main(String[] args) throws Exception {
        Writer writer = new FileWriter("D:\\MILO\\IdeaProject\\nio\\io\\WriterDemo");
        writer.write("你好 milo");
        writer.close();
    }
}
