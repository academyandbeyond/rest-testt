package com.resttest.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;

import com.resttest.framework.json.model.*;
public class RestDriver {

	String[] files;
	Capabilities capabilities;

	
	public RestDriver(Map capabilitiesmap){
		capabilities = new Capabilities();
		System.out.println(capabilitiesmap.get("folderpath").toString());
		capabilities.setFolderPath(capabilitiesmap.get("folderpath").toString());
		capabilities.setEnvironment(capabilitiesmap.get("environment").toString());
		//capabilities.setEnvironment(capabilitiesmap.get("Environment").toString());
		//this.folderpath=folderpath;
	}
	
	
	/*Gather all the test cases and divide by command
	 * 
	 */
	public void executeScripts() throws IOException{

		/*  1. Read all the components
		 *  2. Assign all the components to the variables
		 *  3. Execute the command based on the value
		 */
		// TODO verify capabilities are not null

		JsonTCParser jsontcparser = new JsonTCParser(capabilities);
		//ArrayList<String> jsonData = new ArrayList<String>();
		Map<String,String>jsonData = new HashMap<String,String>();
		jsonData=getJsonDataMap(capabilities.getFolderPath());
		// TD ... Raise exception here if the count is null
		// TD ... Check if file it ends with .json
		ArrayList<TestCase> allTestCases = jsontcparser.parsejson(jsonData);
		// TD ... Raise exception if test case is null
		//int totalTestCasesCount = allTestCases.size();
		//System.out.println("Inside RestDriver.executeScripts - TC Count : "+ totalTestCasesCount );
		Executor executor = new Executor(allTestCases,capabilities);
		executor.executeAll(); 
	}
	
	
	private Map<String, String> getJsonDataMap(String testDataDirectory) throws IOException{
		 
		File directory = new File(testDataDirectory);
		
		File[] fileslist = directory.listFiles();
		//System.out.println(fileslist.length);

		
		
		Map <String, String> datamap = new HashMap<String, String>();
		
		//http://stackoverflow.com/questions/7463414/what-s-the-best-way-to-load-a-jsonobject-from-a-json-text-file
		
		//File file = new File(folderPath);
		//List<String> fileswithpath = new ArrayList<String>();
		//File[] files = new File(folderPath).listFiles();
		InputStream is;
        String jsonTxt ;
      //  System.out.println(jsonTxt);
	
		//ArrayList<String> fileliststring=new ArrayList();
		
		for (int i=0; i< fileslist.length; i++) {
			if (fileslist[i].isFile()){
				//System.out.println(fileslist[i]);
				is = new FileInputStream(fileslist[i]);
				jsonTxt = IOUtils.toString(is);
				
				datamap.put(fileslist[i].getName(), jsonTxt);
				is=null;
				jsonTxt=null;
			}
		}
	 
		return datamap;
	}
	 
	
	public void executeParallel(){
		
	}
	
	
	/*
	 * BELOW METHODS ARE DEPRECATED
	 * 
	 */
	
	
	
	
	
	private ArrayList<String> getJsonData(String testDataDirectory) throws IOException{
	 
		
		File directory = new File(testDataDirectory);
		
		File[] fileslist = directory.listFiles();
		//System.out.println(fileslist.length);
		ArrayList<String> jsonData = new ArrayList();
		
		InputStream is;
        String jsonText ;
	
		
		for (int i=0; i< fileslist.length; i++) {
			if (fileslist[i].isFile()){
				//System.out.println(fileslist[i]);
				is = new FileInputStream(fileslist[i]);
				jsonText = IOUtils.toString(is);
				jsonData.add(jsonText);
				is=null;
				jsonText=null;
			}
		}
	 
		return jsonData;
	}
	private void readTestSuite(ArrayList<TestCase> allTestCases) throws IOException{
		Config.before();
		TestAPI test = new TestAPI();
	 	int totalTCs = allTestCases.size();
	     
	 	System.out.println("Total number of test cases : "+ totalTCs );
	     for (int i = 0; i < totalTCs; i++) {
				//System.out.println(allTestCases.get(i).getTCID()+" : "+ allTestCases.get(i).getTestCase());
			
			
				int exptd = 0;//allTestCases.get(i).getExpectedStatus();
				
				int actual = test.createTest(allTestCases.get(i).getTestCase()).getStatus(allTestCases.get(i).getUrl());
				
				if (actual == exptd){
					Config.writehtml("Pass");
				
				} else {
					Config.writehtml("Fail" + " : ");
					Config.writehtml("Expected : "+exptd+"  Actual : "+actual);
				}

				
				
			}
	   
	 	Config.after();

	}
	
	
	
}
