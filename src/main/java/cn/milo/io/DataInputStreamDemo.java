package cn.milo.io;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/******************************************************
 ****** @ClassName   : DataInputStreamDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 16 16:35     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class DataInputStreamDemo {
    public static void main(String[] args)throws Exception {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("D:\\MILO\\GIT\\nio\\io\\DataInputStreamDemo"));
        DataOutputStream output = new DataOutputStream(new FileOutputStream("D:\\MILO\\GIT\\nio\\io\\DataInputStreamDemo"));
        output.writeInt(10);
        output.writeBytes("aaa");
        output.close();

        int value = dataInputStream.readInt();
        System.out.println(value);

    }
}
