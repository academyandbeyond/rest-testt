package com.resttest.framework.json.model;

public enum ResultEnum {

	PASS ("PASS"),
	FAIL("FAIL"),
	NE("NOT EXECUTED");
	
	private final String result;
	
	private ResultEnum(String result) {
        this.result = result;
    }
	
	public String getResult(){
		return result;
	}
	
}
