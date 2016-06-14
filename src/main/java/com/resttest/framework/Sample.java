package com.resttest.framework;

import java.io.InputStream;
import java.util.List;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;


public class Sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Response response = RestAssured.get("http://jsonplaceholder.typicode.com/posts/1");
		
	//	InputStream stream = RestAssured.get("http://jsonplaceholder.typicode.com").asInputStream(); // Don't forget to close this one when you're done
		//byte[] byteArray = RestAssured.get("http://jsonplaceholder.typicode.com").asByteArray();
		String json = response.asString();
		//System.out.println(json);
		
		//extracting response after validation
		JsonPath jsonPath = new JsonPath(json).setRoot("");
		System.out.println(jsonPath.getString("title"));
		int userId = jsonPath.getInt("userId");
		System.out.println("USERID : "+userId);
		
		//List<Integer> winnerIds = jsonPath.get("userId");
		// *** Get single path
		int userId1 = response.path("userId");
		System.out.println(userId1);

	// GET HEADERNAME
		String header = response.getHeader("headerName");
		System.out.println("Header : "+header);
		Headers allHeaders = response.getHeaders();
			for (Header header1 : allHeaders){
				System.out.println(header1.getName()+" : "+ header1.getValue());
			}
		
	// //	Headers, cookies, status etc
		String body = response.body().asString();
		
		System.out.println(body);
		
		// for payload
		//http://artoftesting.com/automationTesting/restAPIAutomationPostRequest.html
		//http://www.swtestacademy.com/api-testing-with-rest-assured/
		

	}

}
