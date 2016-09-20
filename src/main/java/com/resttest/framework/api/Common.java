package com.resttest.framework.api;

import com.resttest.framework.json.model.ResultEnum;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by sreepadb on 7/10/2016.
 */
public class Common {

    final static Logger logger = Logger.getLogger(Common.class);

    public static String getStackTrace(Throwable e){
        StringWriter writer = new StringWriter();
        PrintWriter printwriter = new PrintWriter(writer);
        e.printStackTrace(printwriter);
        return writer.toString();
    }

    public static boolean stringCompare(String expected, String actual){

        logger.info("Inside stringCompre : Expected("+expected+")  Actual("+actual+")");
        if(expected.contains(actual)){
            return true;
        }
        return false;
    }


    public static ResultEnum setResult(boolean value){
        if(value==true){
            return ResultEnum.PASS;
        }else if(value==false){
            return ResultEnum.FAIL;
        } else{
            return ResultEnum.NE;
        }
    }
}

