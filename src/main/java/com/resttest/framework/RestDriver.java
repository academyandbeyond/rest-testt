package com.resttest.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.resttest.framework.json.model.*;
import org.apache.log4j.Logger;

public class RestDriver {

	String[] files;
	Capabilities capabilities;
	final Logger logger = Logger.getLogger(RestDriver.class);
	String folderpath;

	
	public RestDriver(){
		logger.info("Inside RestDriver constructor");
		capabilities = new Capabilities();

		//capabilities.setEnvironment(capabilitiesmap.get("Environment").toString());
		//this.folderpath=folderpath;
	}
	
	
	/*Gather all the test cases and divide by command
	 * 
	 */
	public void executeScripts(Map capabilitiesmap) throws IOException{

		/*  1. Read all the components
		 *  2. Assign all the components to the variables
		 *  3. Execute the command based on the value
		 */
		// TODO verify capabilities are not null
		try{
			folderpath=capabilitiesmap.get(JsonConstants.FPATH.getConstant()).toString();
			capabilities.setFolderPath(folderpath);

			File dir = new File(folderpath);
			if (dir.exists()){
				logger.info("Folder exists");

			} else
			{
				logger.error("Exiting ... Data folder path provided is invalid. Folder-Path: "+folderpath);
				return;
			}

		}catch (Exception e){
			logger.error("Exiting ... Data folder path provided is either null or invalid. Folder-Path: "+folderpath+ " \nError : "+e);
			return;
		}


		JsonTCParser jsontcparser = new JsonTCParser(capabilities);
		Map<String,String>jsonData = new HashMap<String,String>();
		jsonData=getJsonDataMap(capabilities.getFolderPath());

		if (jsonData.isEmpty()){
			logger.info("Exiting .... TestCase folder is empty");
			return;
		}

		ArrayList<TestCase> allTestCases = jsontcparser.parsejson(jsonData);
		// TD ... Raise exception if test case is null
		//int totalTestCasesCount = allTestCases.size();
		logger.info("Inside executeScripts. Total TestCase size : "+ allTestCases.size());
		Executor executor = new Executor(allTestCases,capabilities);
		executor.executeAll();

	}
	
	
	private Map<String, String> getJsonDataMap(String testDataDirectory) throws IOException{
		 
		File directory = new File(testDataDirectory);
		File[] fileslist = directory.listFiles();
		logger.info("Number of files found to parse : "+ fileslist.length);
		
		Map <String, String> datamap = new HashMap<String, String>();
		
		//http://stackoverflow.com/questions/7463414/what-s-the-best-way-to-load-a-jsonobject-from-a-json-text-file
		InputStream is;
        String jsonTxt ;

		for (int i=0; i< fileslist.length; i++) {
			if (fileslist[i].isFile()){
				logger.info(fileslist[i]);

				if (FilenameUtils.isExtension(String.valueOf(fileslist[i].getName()),"json")){
					is = new FileInputStream(fileslist[i]);
					jsonTxt = IOUtils.toString(is);
					datamap.put(fileslist[i].getName(), jsonTxt);
					logger.info(fileslist[i].getName() +" : "+ jsonTxt);

				} else {
					logger.warn("Data folder should not have any file other than json");
				}

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
	
	
	/*
	
	
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
	}*/
	/*
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
	
	*/
	
}
