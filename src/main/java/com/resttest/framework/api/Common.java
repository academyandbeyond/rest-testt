package com.resttest.framework.api;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by sreepadb on 7/10/2016.
 */
public class Common {

    public static String getStackTrace(Throwable e){
        StringWriter writer = new StringWriter();
        PrintWriter printwriter = new PrintWriter(writer);
        e.printStackTrace(printwriter);
        return writer.toString();
    }

    public static boolean stringCompare(String expected, String actual){

        System.out.println("Inside stringCompre : Expected("+expected+")  Actual("+actual+")");
        if(expected.contains(actual)){
            return true;
        }
        return false;
    }
}

