package com.resttest.framework.json.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.resttest.framework.Capabilities;


/* TO DO
 1. Check if json file exists and raise exception
 */


/* This class has methods related to json parser
 * 
 */


public class JsonTCParser {
	
	private Capabilities capabilities;
	
	public JsonTCParser(Capabilities capabilities){
		this.capabilities=capabilities;
	}
	
	/*
	 * This method reads the json files and stores the json test cases into and arraylist of TestCase object
	 */
	
	public  ArrayList<TestCase> parsejson(Map<String,String> files) throws UnsupportedEncodingException{
		
		
			
				//Configure Gson
				
				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.registerTypeAdapter(TestSuite.class, new TestCaseDeserialize());
			    Gson gson = gsonBuilder.create();
			    
				// Get Json Data
			   // Reader reader;
			    
			    TestSuite testSuiteAll = new TestSuite();  // Variable to store all the test cases from multiple json files
			    TestSuite testSuiteCurrent;  // Variable to store the test cases of current file in the loop
		

			    String filename;
			    String jsontext;
			    for (Map.Entry<String,String> entry : files.entrySet()) {
			    	   filename = entry.getKey();
			    	   jsontext = entry.getValue();
			    	  // do stuff
			    	testSuiteCurrent = gson.fromJson(jsontext, TestSuite.class);
				    testSuiteAll.addTestSuite(filename,testSuiteCurrent.getTestCases());
			    	
			    
			   // for(int i=0; i<files.size(); i++){ 
			    	//System.out.println("Inside JsonTCParcer loop to gather all test cases - Reading file : "+files.get(i));
			    	//reader = new InputStreamReader(JsonTCParser.class.getResourceAsStream(folderpath+files[i]),"UTF-8");
			    	//System.out.println(files.get(i).toString());
			    	
			    	//filename=        files.get("four.json");
			    	//jsontext=files.get(filename);
			    	//System.out.println("Inside JsonTCParcer : Filename : "+filename);
			    	//System.out.println("Inside JsonTCParcer : data : "+value);

			    	//testSuiteCurrent = gson.fromJson(files.get(i), TestSuite.class);
			    	//testSuiteAll.addTestSuite(files.get(i).toString(),testSuiteCurrent.getTestCases());
			    	testSuiteCurrent=null;	
			    }
			    
			    
			    
			   /* store the test cases from all the json files in to allTestCases variable an arraylist of TestCase Object
			    * 
			    */
			 	ArrayList<TestCase> allTestCases = testSuiteAll.getTestCases();
			 	
			 //	System.out.println("Inside JsonTCParcer - TC Count"+allTestCases.size());
			 //	System.out.println("Inside JsonTCParcer - First TC - "+allTestCases.get(0).getTCID()+" :"+ allTestCases.get(0).getTestCase());
			 //	System.out.println("Inside JsonTCParcer - First TC - "+allTestCases.get(0).getTCID()+" :"+ allTestCases.get(0).getScenarios().get(0).getCommand());

			 	return allTestCases;
	}

	/*
	
	public void readTestSuite(ArrayList<TestCase> allTestCases) throws IOException{
		Config1.before();
		TestAPI test = new TestAPI();
		//ArrayList<TestCase> allTestCases =readdatafromjson();
	 	int totalTCs = allTestCases.size();
	     
	 	System.out.println("Total number of test cases : "+ totalTCs );
	     for (int i = 0; i < totalTCs; i++) {
				System.out.println(allTestCases.get(i).getTCID()+" : "+ allTestCases.get(i).getTestCase());
			
			
				int exptd = 0;//allTestCases.get(i).getExpectedStatus();
				
				int actual = test.createTest(allTestCases.get(i).getTestCase()).getStatus(allTestCases.get(i).getUrl());
				
				if (actual == exptd){
					Config1.writehtml("Pass");
				
				} else {
					Config1.writehtml("Fail" + " : ");
					Config1.writehtml("Expected : "+exptd+"  Actual : "+actual);
				}

				
				
			}
	   
	 	Config1.after();

	}
	*/
	
}
