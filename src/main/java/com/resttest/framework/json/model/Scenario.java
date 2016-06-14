package com.resttest.framework.json.model;

public class Scenario {
	
	String id;
	String command;
	String expected;
	String validate;
	String result;
	private String method;
	private String error;
	private String scenario;
	
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
	
	public void setResult(String result){
		this.result=result;
	}
	public String getResult(){
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
	
}
