package com.resttest.framework;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;

public class TestAPI {
	
	private String testcase;
	private String url;
	private Response response;
	private int statuscode;
	private String contenttype;
	private String CONTENTTYPE="Content-Type";
	
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
//System.out.println("Inside TestAPI Statuscod : "+statuscode);
		Config.writehtml(Integer.toString(statuscode));
		return statuscode;
	}
	
	public int getStatus(){
		statuscode=response.getStatusCode();
		//System.out.println(statuscode);
		Config.writehtml(Integer.toString(statuscode));
		return statuscode;
	}

	public String getContentType(){
		contenttype = response.getHeader(CONTENTTYPE);
		return contenttype;
	}
	
	public Headers getAPIHeaders(){
		/*
		Headers allHeaders = response.getHeaders();
		for (Header header1 : allHeaders){
			System.out.println(header1.getName()+" : "+ header1.getValue());
		}*/
		return response.getHeaders();
	}
	
	public JsonPath getResponseJsonPath(){
		return response.getBody().jsonPath();
	}
	
}
