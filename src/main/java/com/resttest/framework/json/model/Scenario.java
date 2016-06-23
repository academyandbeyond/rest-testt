package com.resttest.framework.json.model;

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
	private String[] sendheader;
	private JsonPath responseasjp;
	private Headers headers;
	private String primary;
	
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
	
	public void setDependent(String[] dependent){
		this.dependent=dependent;
	}
	
	public String[] getDependent(){
		return dependent;
	}
	
	public void setTags(String[] tags){
		this.tags=tags;
	}
	
	public String[] getTags(){
		return tags;
	}
	
	public void setHeader(Headers headers){
		this.headers=headers;
		
	}
	
	public Headers getHeader(){
		return headers;
	}
	
	public void setSendHeader(String[] sendheader){
		this.sendheader=sendheader;
	}
	
	public String[] getSendHeader(){
		return sendheader;
	}
	
	public void setJsonPath(JsonPath responseasjp){
		this.responseasjp=responseasjp;
	}
	
	public JsonPath getJsonPath(){
		return responseasjp;
	}
	
	public void setPrimary(String primary){
		this.primary=primary;
	}
	
	public String getPrimary(){
		return primary;
	}
}
