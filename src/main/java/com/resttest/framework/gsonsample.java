package com.resttest.framework;

import java.util.HashMap;
import java.util.Map;

public class gsonsample {

	public static void main(String[] args) throws Exception {
		
		//Users/sreepadbhagwat/OneStop/Projects/sumana/resttest/rest-test/src/main/java/com/resttest/framework/data
		//String folderpath="/Users/sreepadbhagwat/OneStop/Projects/Rest-Assured/com.resttest.framework/src/main/java/com/resttest/framework/data/";
		String folderpath="C:/Users/sreepadb/onestop/resttest/rest-test/src/main/java/com/resttest/framework/data/";
		Map<String,String> capabilities = new HashMap<String,String>();

		capabilities.put("folderpath", folderpath);
		capabilities.put("environment", "stg");
		
		RestDriver driver = new RestDriver(capabilities);
		
		driver.executeScripts();
			
	 	
	}
	
	

	
	
}

