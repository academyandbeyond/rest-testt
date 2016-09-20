package com.resttest.framework.json.model;

public enum JsonConstants {
	GET("GET"),
	GETD ("GET-D"),
	POST("POST"),
	POSTD("POST-D"),

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
