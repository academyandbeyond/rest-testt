package com.resttest.framework;

import java.io.InputStream;
import java.util.List;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.matcher.RestAssuredMatchers.*;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.params.CoreConnectionPNames;

import static com.jayway.restassured.config.HttpClientConfig.httpClientConfig;


public class Sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssuredConfig config = RestAssured.config().httpClient(httpClientConfig().setParam(CoreConnectionPNames.SO_TIMEOUT, 1));
		//CoreConnectionPNames.SO_TIMEOUT
		//RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(1000).build();

		//setConnectionRequestTimeout
		Response response=null;
		try {
			 response = RestAssured.with().config(config).get("http://jsonplaceholder.typicode.com/posts?userId=1");
			String json = response.asString();
		}catch(Exception e){}
	//	InputStream stream = RestAssured.get("http://jsonplaceholder.typicode.com").asInputStream(); // Don't forget to close this one when you're done
		//byte[] byteArray = RestAssured.get("http://jsonplaceholder.typicode.com").asByteArray();

		/*given().config(RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().httpClientFactory(() -> {
					DefaultHttpClient httpClient = new DefaultHttpClient();
					httpClient.setRedirectStrategy(..);
					return httpClient;
				})
*/

		//RequestSpecification rs= RestAssured.given().spec(arg0)
		//System.out.println(json);
		/*
		//extracting response after validation
		JsonPath jsonPath = new JsonPath(json).setRoot("");
		System.out.println(jsonPath.getString("title"));
		int userId = jsonPath.getInt("userId");
		System.out.println("USERID : "+userId);
		
		//List<Integer> winnerIds = jsonPath.get("userId");
		// *** Get single path
		int userId1 = response.path("userId");
		System.out.println(userId1);
		*/

	// GET HEADERNAME
		String header = response.getHeader("Content-Type");
		System.out.println("Content-Type : "+header);
	
		System.out.println("PRINTING ALL HEADERS");
		Headers allHeaders = response.getHeaders();
			for (Header header1 : allHeaders){
				System.out.println(header1.getName()+" : "+ header1.getValue());
			}
		
	// //	Headers, cookies, status etc
		//String body = response.body().asString();
		JsonPath jsonPath1 = response.getBody().jsonPath();
		
		System.out.println(jsonPath1.getString("id[1]"));
		
		// for payload
		//http://artoftesting.com/automationTesting/restAPIAutomationPostRequest.html
		//http://www.swtestacademy.com/api-testing-with-rest-assured/
		
			TestAPI test = new TestAPI();
//			test.createTest("asdasdf").get("").getStatus();
			
			
	}

}
