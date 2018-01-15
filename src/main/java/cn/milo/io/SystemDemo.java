package cn.milo.io;

import java.io.*;

/******************************************************
 ****** @ClassName   : SystemDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 04 11:56     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class SystemDemo {

    public static void main(String[] args) throws FileNotFoundException {


        OutputStream output = new FileOutputStream("D:\\MILO\\IdeaProject\\nio\\io\\SystemOut");
        PrintStream printOut = new PrintStream(output);
        System.setOut(printOut);
        System.setErr(printOut);


        try {
            System.out.println("File opened...");
            InputStream input = new FileInputStream("c:\\data\\...");
        } catch (IOException e) {
            System.err.println("File opening failed:");
            e.printStackTrace();
        }


    }
}
