package com.resttest.framework;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sreepadbhagwat on 9/10/16.
 */
public class RestTest {

    RestDriver driver;

public RestTest(Map<String, String> capabilities) {

   // String folderpath = "/Users/sreepadbhagwat/OneStop/production/resttest/rest-test/src/main/java/com/resttest/framework/data";
   // Map<String, String> capabilities = new HashMap<String, String>();

   // capabilities.put("folderpath", folderpath);
    //capabilities.put("environment", "stg");

     driver = new RestDriver(capabilities);



}

    
    public void ExecuteJsonScripts(){
        try {
            driver.executeScripts();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
