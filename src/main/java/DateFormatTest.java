import java.text.SimpleDateFormat;
import java.util.*;

/******************************************************
 ****** @ClassName   : DateFormatTest.java
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 04 21:41     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class DateFormatTest {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
    private static String date[] = { "01-Jan-1999", "01-Jan-2000", "01-Jan-2001" };

    static Set<SimpleDateFormat> set = new HashSet<SimpleDateFormat>();
    

    private static SimpleDateFormat get(){
        SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

//        System.out.println(sdf);
//        set.add(sdf);
//        return sdf;


        System.out.println(sd);
        set.add(sd);
        return sd;
    }

    public static void main(String[] args) {
        for (int i = 0; i < date.length; i++) {
            final int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j <1000 ; j++) {
                            String str1 = date[temp];
                            String str2 = get().format(get().parse(str1));
                            System.out.println(Thread.currentThread().getName() + ", " + str1 + "," + str2);
                            if(!str1.equals(str2)){
                                throw new RuntimeException(Thread.currentThread().getName()
                                        + ", Expected " + str1 + " but got " + str2);
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("parse failed", e);
                    }
                    jisuan();
                }
            }).start();
        }


    }


    public static void jisuan(){
        for (SimpleDateFormat SimpleDateFormat:set) {
            for (SimpleDateFormat SimpleDateFormat2:set) {
                if(SimpleDateFormat != SimpleDateFormat2){
                    System.out.println("不一样");
                }
            }
        }

        System.out.println("一样" + set.size());
    }
}