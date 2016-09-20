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
import org.apache.log4j.Logger;

public class TestCaseDeserialize implements JsonDeserializer<TestSuite> {

	final Logger logger = Logger.getLogger(TestCaseDeserialize.class);

	public TestSuite deserialize(final JsonElement json, final Type 
			typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		
		
		
		/*TODO
		 *  !userDomainName.isEmpty() .. jsonobject
		 *  any exceptions
		 *  Raise exception if the set is empty
		 */ 
		
		//Step1 :Deserialize : Convert JsonElement in to JsonObject

		
		
		logger.info("Inside TestCaseDeserializer");
		final JsonObject jsonobject = json.getAsJsonObject();

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

				if (jsonobject.getAsJsonObject(jsonid).has("TestCase")){
					jsonTest=jsonobject.getAsJsonObject(jsonid).get("TestCase");
					tcs.addTCID(jsonid);
		    		tcs.addTestCase(jsonTest.getAsString());
				} else {
				// add exception to add tests
				}

				/*
				if (jsonobject.getAsJsonObject(jsonid).has("statuscode")){
					jsonstatusCode=jsonobject.getAsJsonObject(jsonid).get("statuscode");
					tcs.addStatusCode(jsonstatusCode.getAsInt());
				} else {
				// add exception to add tests
				}*/

			// TD ... check for null for scenarios and others
			Gson scenarioGson = new Gson();
			Type scenariosType = new TypeToken<List<Scenario>>(){}.getType();
			List<Scenario> scenarioList = scenarioGson.fromJson(jsonobject.getAsJsonObject(jsonid).get("scenarios"), scenariosType);


			tcs.addScenarios(scenarioList);
		

		
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
