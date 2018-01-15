package cn.milo.io;

import java.io.RandomAccessFile;

/**
 * Created by mac on 2018/1/16.
 */
public class RandomAccessFileDemo {

    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("D:\\MILO\\IdeaProject\\nio\\io\\RandomAccessFileDemo", "rw");
        file.seek(200);
//        int aByte = file.read();
//        while (aByte != -1){
//            System.out.println((char) aByte);
//            aByte = file.read();
//        }

//        long pointer = file.getFilePointer();
//        System.out.println(pointer); //当前位置
        file.write("你好".getBytes());
//        file.close();
    }
}
