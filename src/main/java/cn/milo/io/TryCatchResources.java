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

        public static void main(String[] args) {
            try (TryCatchResources d1 = new TryCatchResources(false, false, "a");
                 TryCatchResources d2 = new TryCatchResources(true, false, "b");) {
                throw new IOException("in main1");
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }

            System.out.println("----end1----");

            try (TryCatchResources d1 = new TryCatchResources(false, false, "a");
                 TryCatchResources d2 = new TryCatchResources(false, true, "b");) {
                throw new IOException("in main2");
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }

            System.out.println("----end2----");
        }

}
