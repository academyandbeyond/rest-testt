package com.resttest.framework;

import com.resttest.framework.json.model.JsonConstants;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.lang.*;

public class gsonsample {

	public static void main(String[] args) throws Exception {

		final Logger logger = Logger.getLogger(gsonsample.class);

		//String folderpath = "/Users/sreepadbhagwat/OneStop/production/resttest/rest-test/src/main/java/com/resttest/framework/data";

		String folderpath = "/Users/sreepadbhagwat/OneStop/production/resttest/rest-test/src/main/java/com/resttest/framework/data";
		Map<String, String> capabilities = new HashMap<String, String>();

		logger.info("folderpath : "+folderpath);
		logger.warn("folderpath : "+folderpath);

		capabilities.put(JsonConstants.FPATH.getConstant(), folderpath);
		capabilities.put(JsonConstants.ENV.toString(), "stg");

		//RestDriver driver = new RestDriver();
		
		//driver.executeScripts(capabilities);


		RestTest test = new RestTest();
		test.ExecuteJsonScripts(capabilities);
/*
		long startTime = System.currentTimeMillis();
		long endTime = startTime + 6*1000;
		while (System.currentTimeMillis() < endTime)
		{
			Thread.sleep(7000);
			System.out.println("adf");
			// your code
		}

*/

	}
	

	
	
}

