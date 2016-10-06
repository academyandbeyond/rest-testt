package com.resttest.framework.json.model;

public enum JsonConstants {


	// From json file
	GET("GET"),
	GETD ("GET-D"),
	POST("POST"),
	POSTD("POST-D"),
	HEADER("header"),
	PAYLOAD("payload"),
	SCENARIOREF("~Scenario"),


	FPATH("folderpath"), // folderpath for json data
	ENV("environment");

	private final String constant;
	
	private JsonConstants(String constant) {
        this.constant = constant;
    }
	
	public String getConstant(){
		return constant;
	}
}
