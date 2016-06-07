package com.resttest.framework.json.model;

public class Scenario {
	
	String id;
	String command;
	String expected;
	String validator;
	String result;
	
	public void setID(String id){
		this.id=id;
	}
	
	public String getID(){
		return id;
	}
	
	public void setValidate(String validator){
		this.validator=validator;
	}
	
	
	public String getValidate(){
		return validator;
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
}
