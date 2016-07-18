package com.resttest.framework;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.resttest.framework.exceptions.Errors;
import com.resttest.framework.exceptions.RestAPIException;


public class TestAPI {
	
	public String testcase;
	private String url;
	private Response response;
	private int statuscode;
	private String contenttype;
	private String CONTENTTYPE="Content-Type";
	private String error;
	
	public TestAPI(){
		
	}
	
	public TestAPI createTest(String testcase){
		this.testcase=testcase;
		return this;
	}
	
	public TestAPI get(String url){
		this.url=url;
		response=RestAssured.get(url);
		return this;
	}

	public String getError(){
		return this.error;
	}

	public TestAPI post(String url){
		this.url=url;
		response=RestAssured.post(url);
		return this;
	}

	public int postStatus(String url){
		statuscode=RestAssured.post(url).getStatusCode();
		return statuscode;
	}

	public int postStatus(){
		statuscode=response.getStatusCode();
		return statuscode;
	}

	public int getStatus(String url){
		this.url=url;
		statuscode=RestAssured.get(url).getStatusCode();
		Config.writehtml(Integer.toString(statuscode));
		return statuscode;
	}
	
	public int getStatus(){
		statuscode=response.getStatusCode();
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
	
	public JsonPath getResponseJsonPath()  {
		if (testcase!=null & testcase!="") {
			return response.getBody().jsonPath();
		} else {
			this.error = Errors.ERROR_TC.getErrorMessage();
			return null;
		}
	}

}
