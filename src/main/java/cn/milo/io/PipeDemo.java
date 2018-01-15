package cn.milo.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/******************************************************
 ****** @ClassName   : PipeDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 04 11:38     
 ****** @version     : v1.0.x                      
 *******************************************************/

/*
请记得，当使用两个相关联的管道流时，务必将它们分配给不同的线程。read()方法和write()方法调用时会导致流阻塞，这意味着如果你尝试在一个线程中同时进行读和写，可能会导致线程死锁。
 */

/*
问题:为什么read方法在读取流到最后时返回-1 , 源码没有看懂还
 */
public class PipeDemo {

    public static void main(String[] args) throws Exception {
        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream inputStream = new PipedInputStream(outputStream);

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    outputStream.write("hello world , pipe!".getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    int data = inputStream.read();
                    while (data != -1){
                        System.out.println((char)data);
                        data = inputStream.read();
                    }
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        thread1.start();
        thread2.start();


    }

}
