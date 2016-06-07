package com.resttest.framework.json.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class TestCaseDeserialize implements JsonDeserializer<TestSuite> {
	
	
	public TestSuite deserialize(final JsonElement json, final Type 
			typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		
		
		
		/*TODO
		 *  !userDomainName.isEmpty() .. jsonobject
		 *  any exceptions
		 *  Raise exception if the set is empty
		 */ 
		
		//Step1 :Deserialize : Convert JsonElement in to JsonObject
		
		
		
		// new code
		
		
		
		// end new code
		
		
		
		//System.out.println("Inside TestCaseDeserializer");
		final JsonObject jsonobject = json.getAsJsonObject();
		
		
		/*
		 * Get all the Ids in to the list
		 * 
		 * 
		
		 */

	

		
		TestCase tcs;
		TestSuite testsuite = new TestSuite();
		 JsonElement jsonTest;
		 JsonElement jsonurl;
		 JsonElement jsonMethod;
		 JsonElement jsonstatusCode;
		 String jsonid;
		Set<Map.Entry<String, JsonElement>> entrySet = jsonobject.entrySet();

		 for(Map.Entry<String, JsonElement> entry : entrySet) {
			 //System.out.println(idList.get(i));
			 if (!entry.getKey().isEmpty()){
				 tcs = new TestCase();
		jsonid=entry.getKey().toString();
	//	System.out.println("Json ID : "+jsonid);
		
		if (jsonobject.getAsJsonObject(jsonid).has("TestCase")){
			jsonTest=jsonobject.getAsJsonObject(jsonid).get("TestCase");
			tcs.addTCID(Integer.parseInt(jsonid));
		    tcs.addTestCase(jsonTest.getAsString());
		} else {
			// add exception to add tests
		}
		
		if (jsonobject.getAsJsonObject(jsonid).has("statuscode")){
			jsonstatusCode=jsonobject.getAsJsonObject(jsonid).get("statuscode");
			tcs.addStatusCode(jsonstatusCode.getAsInt());
		} else {
			// add exception to add tests
		}
		
	    
	    
		jsonurl=jsonobject.getAsJsonObject(jsonid).get("url");
		tcs.addUrl(jsonurl.getAsString());

		 jsonMethod=jsonobject.getAsJsonObject(jsonid).get("method");
		tcs.addMethod(jsonMethod.getAsString());
		
	//	JsonElement jsonsce = jsonobject.getAsJsonObject(jsonid).get("scenarios");
		//System.out.println(sce.);
		// TD ... check for null for scenarios and others
		Gson scenarioGson = new Gson();
		Type scenariosType = new TypeToken<List<Scenario>>(){}.getType();
		//System.out.println(scenariosType.getTypeName());
		List<Scenario> scenarioList = scenarioGson.fromJson(jsonobject.getAsJsonObject(jsonid).get("scenarios"), scenariosType);
	    //System.out.println("printing scenarios - "+scenarioList.get(0).getCommand());
	    //System.out.println("printing scenarios - "+scenarioList.get(1).getCommand());

		tcs.addScenarios(scenarioList);
		
	   // ArrayList<Scenario> scenarios = context.deserialize(jsonobject.get("scenarios"), Scenario.class);
	   // Scenario[] scenarios = context.deserialize(jsonobject.get("scenarios"), Scenario[].class);

	   // System.out.println("printing scenarios - "+scenarioList);
	    //tcs.addScenarios(scenarios);
		
	    /* JsonArray<Scenario> jsonScenarios = jsonobject.get("scenarios").getAsJsonArray();

	    String[] scenarios = new String[jsonScenarios.size()];
	    for (int i = 0; i < scenarios.length; i++) {
	        final JsonElement jsonAuthor = jsonScenarios.get(i);
	        scenarios[i] = jsonAuthor.getAsString();
	        System.out.println(scenarios[i]);
	      }
*/
		
		/* jsonExptdStatus = jsonobject.getAsJsonObject(jsonid).get("ExpectedStatus");
		tcs.addExpectedStatus(jsonExptdStatus.getAsInt());*/
		
		testsuite.addTestCase(tcs);
		}
		 }
		//System.out.println("size of the test cases : "+testsuite.getTestCases().size());
		return testsuite;
		/*
		 * 
		
		
		final JsonArray jsonTagsArray = jsonobject.get("tags").getAsJsonArray();
		final String[] tags = new String[jsonTagsArray.size()];
		
		for (int i=0; i<tags.length; i++){
		//	final JsonElement jsonTag = jsonTagsArray.get(i);
			//tags[i]=jsonTag.getAsString();
		}
		
	*/
		
	//	final TestCase tc= new TestCase();
		//tc.setTestCase(testCase);
	//	tc.setUrl(url);
		//tc.setResponse(response);
		//tc.setStatus(status);
	//	tc.setTags(tags);
		
		
		//return tc;
		
	}

}
