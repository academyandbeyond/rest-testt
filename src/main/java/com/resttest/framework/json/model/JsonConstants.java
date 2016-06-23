package com.resttest.framework.json.model;

public enum JsonConstants {
	GET("GET"),
	GETD ("GET-D");
	
	
	private final String constant;
	
	private JsonConstants(String constant) {
        this.constant = constant;
    }
	
	public String getConstant(){
		return constant;
	}
}
