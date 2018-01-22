package cn.milo.io;


import java.io.Closeable;
import java.io.IOException;
/******************************************************
 ****** @ClassName   : TryCatchResources.java                                            
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 15 17:34     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class TryCatchResources  implements Closeable {

    /*
    1.如果是普通的try catch finally 中 try和finally都抛出异常,最终是finally抑制try中的异常,这个体现在main方法抛出的异常上,一个方法只能抛出一个异常   参照demo4
    2.如果是try catch resources 中 try 和try catch resources 都抛出异常 , 最终try catch resources 中的异常被抑制   参照main2
    3.如果以例【2】为基础添加finally的异常,最终inllay中的异常都会抛出   参照demo3 (和demo4中情况一样)
     */


    private final boolean throwInClose;
    private final String name;

    public TryCatchResources(boolean throwInConstruction, boolean throwInClose, String name) throws IOException {
        this.throwInClose = throwInClose;
        this.name = name;
        if (throwInConstruction) {
            throw new IOException("throwing in construction");
        }
    }

    @Override
    public void close() throws IOException {
        if (throwInClose) {
            throw new IOException("throwing in close");
        }
        System.out.println(name + " is closing...");
    }

    public static void main(String[] args)throws Exception {
        try (TryCatchResources d1 = new TryCatchResources(false, false, "a"); //构造方法 > try内 > try cathc resources内(close中的)
             TryCatchResources d2 = new TryCatchResources(true, false, "b");) {
            throw new IOException("in main1");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        System.out.println("----end1----");

        //demo2
        try (TryCatchResources d1 = new TryCatchResources(false, false, "a");
             TryCatchResources d2 = new TryCatchResources(false, true, "b");) {
            throw new IOException("in main2");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        System.out.println("----end2----");
        //cn.milo.simpledataformat.demo 3
        try (TryCatchResources d2 = new TryCatchResources(false, true, "b");) {
            int k = 1/0;
        } finally {
            int k = 1/0;
        }

        System.out.println("----end3----");
        //cn.milo.simpledataformat.demo 4
        try {
            throw new IOException("in main4");
        } finally {
            int k = 1/0;
        }
    }

}
