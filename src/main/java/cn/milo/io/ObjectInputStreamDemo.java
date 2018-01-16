package cn.milo.io;

import java.io.*;

/******************************************************
 ****** @ClassName   : ObjectInputStreamDemo.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 16 16:48     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class ObjectInputStreamDemo {

    public static void main(String[] args)throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\MILO\\GIT\\nio\\io\\ObjectInputStreamDemo"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\MILO\\GIT\\nio\\io\\ObjectInputStreamDemo"));
        demo demo = new ObjectInputStreamDemo.demo("milo",18);
        objectOutputStream.writeObject(demo);
        objectOutputStream.close();

        demo demoOfRead = (demo) objectInputStream.readObject();
        demoOfRead.print();
        objectInputStream.close();
    }

    static class demo implements Serializable{

        public demo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String name ;
        private int age ;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void print(){
            System.out.println("name = " + name + " , age = " + age );
        }
    }
}
