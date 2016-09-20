package com.resttest.framework.json.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;

public class Scenario {
	
	private String id;
	private String command;
	private String expected;
	private String validate;
	private ResultEnum result;
	private String method;
	private String error;
	private String scenario;
	private String actual;
	private String[] dependent;
	private String[] tags;


	private JsonElement responseasjp;
	private Headers headers;
	private String primary;

	private JsonObject header;
	private JsonObject payload;
	private String responseattribute;
	private String actualresponse;
	private String url;
	private long responsetime;
	private String responseheader;

	
	public void setID(String id){
		this.id=id;
	}
	
	public String getID(){
		return id;
	}
	
	public void setValidate(String validate){
		this.validate=validate;
	}
	
	
	public String getValidate(){
		return validate;
	}
	
	public void setCommand(String command){
		this.command=command;
	}
	
	public String getCommand(){
		return command;
	}
	
	public void setExpected(String expected){
		this.expected=expected;
	}
	
	public String getExpected(){
		return expected;
	}
	
	public void setResult(ResultEnum result){
		this.result=result;
	}
	public ResultEnum getResult(){
		return result;
	}
	
	public void setMethod(String method){
		this.method=method;
	}
	
	public String getMethod(){
		return method;
	}
	
	public void setError(String error){
		this.error=error;
	}
	
	public String getError(){
		return error;
	}
	
	public void setScenario(String scenario){
		this.scenario=scenario;
	}
	
	public String getScenario(){
		return scenario;
	}
	
	public void setActual(String actual){
		this.actual=actual;
	}
	
	public String getActual(){
		return actual;
	}

	
	public void setTags(String[] tags){
		this.tags=tags;
	}
	
	public String[] getTags(){
		return tags;
	}
	
	public void setHeaders(Headers headers){
		this.headers=headers;
		
	}
	
	public Headers getHeaders(){
		return headers;
	}

	
	public void setJsonPath(JsonElement responseasjp){
		this.responseasjp=responseasjp;
	}
	
	public JsonElement getJsonPath(){
		return responseasjp;
	}
	
	public void setPrimary(String primary){
		this.primary=primary;
	}
	
	public String getPrimary(){
		return primary;
	}


	public void setDependent(String[] dependent){
		this.dependent=dependent;

	}

	public String[] getDependent(){
		return dependent;
	}

	// new ones


	public void setResponseHeader(String responseheader){
		this.responseheader=responseheader;
	}


	public String getResponseHeader(){
		return responseheader;
	}

	public void setHeader(JsonObject header){
		this.header = header;
	}

	public JsonObject getHeader(){
		return header;
	}

	public void setPayload(JsonObject payload){
		this.payload=payload;
	}

	public JsonObject getPayload(){
		return payload;
	}

	public void setResponseAttribute(String responseattribute){
		this.responseattribute=responseattribute;
	}

	public String getResponseAttribute(){
		return responseattribute;
	}

	public void setActualResponse(String actualresponse){
		this.actualresponse=actualresponse;
	}

	public String getActualResponse(){
		return actualresponse;
	}

	public void setUrl(String url){
		this.responseattribute=responseattribute;
	}

	public String getUrl(){
		return url;
	}

	public void setResponseTime(long responsetime){
		this.responsetime=responsetime;
	}

	public long getResponsetime(){
		return responsetime;
	}

}
