package com.resttest.framework;

import java.util.HashMap;
import java.util.Map;

public class gsonsample {

	public static void main(String[] args) throws Exception {
		
		String folderpath="/Users/sreepadbhagwat/OneStop/Projects/Rest-Assured/com.resttest.framework/src/main/java/com/resttest/framework/data/";
		Map<String,String> capabilities = new HashMap<String,String>();
		capabilities.put("folderpath", folderpath);
		capabilities.put("Environment", "");
		
		RestDriver driver = new RestDriver(capabilities);
		driver.executeScripts();
			
	 	
	}
	
	

	
	
}

