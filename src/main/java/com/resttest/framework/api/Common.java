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
}
