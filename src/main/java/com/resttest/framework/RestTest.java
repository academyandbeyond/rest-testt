package com.resttest.framework;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sreepadbhagwat on 9/10/16.
 */
public class RestTest {

    RestDriver driver;
    final Logger logger = Logger.getLogger(RestTest.class);

public RestTest() {
    logger.info("Inside RestTest constructor");
     driver = new RestDriver();

}

    
    public void ExecuteJsonScripts(Map<String, String> capabilities){
        try {
            logger.info("Inside ExecuteJsonScripts method");
            driver.executeScripts(capabilities);

        } catch (IOException e) {
            logger.error("Inside ExecuteJsonScripts catch block"+e);
        }

    }


}
