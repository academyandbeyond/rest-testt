package com.resttest.framework.json.model;

import com.google.gson.JsonElement;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Headers;

public class PrimaryData {
	
	private String TCID;
	private String TSID;
	private JsonElement responseasjp;
	private Headers headers;
	
	
	public void setTCID(String TCID){
		this.TCID=TCID;
	}
	public String getTCID(){
		return this.TCID;
	}
	public void setTSID(String TSID){
		this.TSID=TSID;
	}
	public String getTSID(){
		return this.TSID;
	}
	public void setJsonPath(JsonElement responseasjp){
		this.responseasjp=responseasjp;
	}
	
	public JsonElement getJsonPath(){
		return responseasjp;
	}

	public void setHeader(Headers headers){
		this.headers=headers;
		
	}
	
	public Headers getHeader(){
		return headers;
	}
}
