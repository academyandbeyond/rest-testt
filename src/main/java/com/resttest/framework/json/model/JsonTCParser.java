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
import org.apache.log4j.Logger;


/* TO DO
 1. Check if json file exists and raise exception
 */


/* This class has methods related to json parser
 * 
 */


public class JsonTCParser {
	
	private Capabilities capabilities;
	final Logger logger = Logger.getLogger(JsonTCParser.class);

	public JsonTCParser(Capabilities capabilities){
		this.capabilities=capabilities;
		logger.info("Inside JsonTcParser constructor");
	}
	
	/*
	 * This method reads the json files and stores the json test cases into and arraylist of TestCase object
	 */
	
	public  ArrayList<TestCase> parsejson(Map<String,String> files) throws UnsupportedEncodingException{

				//Configure Gson
		logger.info("Inside parsejson");
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(TestSuite.class, new TestCaseDeserialize());
		Gson gson = gsonBuilder.create();
			    
		// Get Json Data
		// Reader reader;
		String jsontext;

		TestSuite testSuiteAll = new TestSuite();  // Variable to store all the test cases from multiple json files
		TestSuite testSuiteCurrent;  // Variable to store the test cases of current file in the loop
		//testSuiteCurrent = gson.fromJson(jsontext, TestSuite.class);

		String filename;
		for (Map.Entry<String,String> entry : files.entrySet()) {
			filename = entry.getKey();
			jsontext = entry.getValue();
			//logger.info(files);
			    	  // do stuff
			logger.info("About to read from json");
			testSuiteCurrent = gson.fromJson(jsontext, TestSuite.class);
			testSuiteAll.addTestSuite(filename,testSuiteCurrent.getTestCases());
			testSuiteCurrent=null;
		}
			    
			    
			    
			   /* store the test cases from all the json files in to allTestCases variable an arraylist of TestCase Object
			    * 
			    */
		ArrayList<TestCase> allTestCases = testSuiteAll.getTestCases();
		logger.info("TestCase Count : "+allTestCases.size());
		//	logger.info("Inside JsonTCParcer - First TC - "+allTestCases.get(0).getTCID()+" :"+ allTestCases.get(0).getTestCase());
		//	logger.info("Inside JsonTCParcer - First TC - "+allTestCases.get(0).getTCID()+" :"+ allTestCases.get(0).getScenarios().get(0).getCommand());
		return allTestCases;
	}
	
}
