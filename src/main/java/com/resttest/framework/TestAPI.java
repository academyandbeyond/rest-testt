package com.resttest.framework;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class TestAPI {
	
	private String testcase;
	private String url;
	private Response response;
	private int statuscode;
	
	public TestAPI(){
		
	}
	
	public TestAPI createTest(String testcase){
		this.testcase=testcase;
		Config.writehtml("<br>");
		Config.writehtml(testcase);
		return this;
	}
	
	public TestAPI get(String url){
		this.url=url;
		response=RestAssured.get(url);
		
		Config.writehtml(url);
		return this;
	}
	
	public int getStatus(String url){
		this.url=url;
		statuscode=RestAssured.get(url).getStatusCode();
System.out.println("Inside TestAPI Statuscod : "+statuscode);
		Config.writehtml(Integer.toString(statuscode));
		return statuscode;
	}
	
	public int getStatus(){
		statuscode=response.getStatusCode();
		//System.out.println(statuscode);
		Config.writehtml(Integer.toString(statuscode));
		return statuscode;
	}

	
	
	
	
}
