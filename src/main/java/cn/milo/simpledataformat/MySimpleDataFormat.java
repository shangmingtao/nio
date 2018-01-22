package cn.milo.simpledataformat;

import java.text.SimpleDateFormat;
import java.util.Random;

/******************************************************
 ****** @ClassName   : cn.milo.simpledataformat.MySimpleDataFormat.java
 ****** @author      : milo ^ ^                     
 ****** @date        : 2018 01 05 0:03     
 ****** @version     : v1.0.x                      
 *******************************************************/
public class MySimpleDataFormat extends SimpleDateFormat {

    @Override
    public int hashCode(){
        return new Random().nextInt(10);
    }
}
