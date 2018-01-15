package cn.milo.io;

import java.io.FileReader;
import java.io.Reader;

/******************************************************
 ****** @ClassName   : ReaderDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 04 12:20     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class ReaderDemo {

    public static void main(String[] args)throws Exception {
        Reader reader = new FileReader("D:\\MILO\\IdeaProject\\nio\\io\\ReaderDemo");
        int data = reader.read();
        while(data != -1){
            char dataChar = (char) data;
            System.out.println(dataChar);
            data = reader.read();
        }
    }
}
