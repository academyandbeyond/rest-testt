package com.resttest.framework;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
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
	
	public JsonElement getResponseJsonPath()  {
		if (testcase!=null & testcase!="") {
			return JsonUtil.getBodyAsElement(response.getBody());
		} else {
			this.error = Errors.ERROR_TC.getErrorMessage();
			return null;
		}
	}

	//nn

	public TestAPI post(String url, String header, String payload){
		response=JsonUtil.getResponseBodyWhenContent(header,payload,url,"post");
		return this;
	}

	public String getResponsebody(){
		return response.body().asString();
	}

	public JsonPath getResponsebodyasJsonPath() {
		return response.body().jsonPath();
	}

	public String getResponseAttribute(String attribute){

		String responsebodystring="";
		String responsecontent="";
		ResponseBody responsebody =response.body();

		if (responsebody!=null){
			JsonElement jsonelementbody=JsonUtil.getBodyAsElement(responsebody);

			if(attribute!="" & attribute !=null){
				JsonElement jsonelement = JsonUtil.getJsonElementValue(jsonelementbody,attribute);
				if(jsonelement==null){
					return responsecontent;
				}
				responsebodystring=jsonelement.toString();

			}else {
				Gson gson = new Gson();
				responsebodystring=gson.toJson(jsonelementbody);
			}
		}

		return responsebodystring;
	}


	public long responseTime(){
		return response.getTime();
	}


}
