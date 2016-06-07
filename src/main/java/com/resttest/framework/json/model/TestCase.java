package com.resttest.framework.json.model;

import java.util.ArrayList;
import java.util.List;

public class TestCase {
	
	private int tcid;
	private String testCase;
	private String url;
	private String method;
	private int statuscode;
	private List<Scenario> scenarios;
	private String tcresult;
	private String filename;
	private String comments;


	/*
	 * Implement below
	 * 			'headers': {'Content-Type' : 'application/json'},
			'tags':['smoke','regression']
	 */
	
	public void addTCID(int tcid){
		this.tcid=tcid;
	}
	
	public int getTCID(){
		return this.tcid;
	}
	
	public void addStatusCode(int statuscode){
		this.statuscode=statuscode;
	}

	public int getStatusCode(){
		return statuscode;
	}
	  public void addTestCase(String testcase) {
		    this.testCase=testcase;
		  }
	  
	  public String getTestCase(){
		  return this.testCase;
	  }
	  
	  public void addUrl(String url){
		  this.url=url;
	  }
	  
	  public String getUrl(){
		  return this.url;
	  }
	  public void addMethod(String method){
		  this.method=method;
	  }
	  
	  public String getMethod(){
		  return this.method;
	  }

	 
	 
	  public void addScenarios(List<Scenario> scenarioList){
		  this.scenarios=scenarioList;
	  }
	  
	  public List<Scenario> getScenarios(){
		  return scenarios;
	  }
	 
	
	  public void addTCResult(String tcresult){
		  this.tcresult=tcresult;
	  }
	  
	  public String getTCResult(){
		  return tcresult;
	  }
	
	  public void addFileName(String filename){
		  this.filename=filename;
	  }
	  
	  public String getFileName(){
		  return filename;
	  }
	  
	  
	  public void setComments(String comments){
		  this.comments=comments;
	  }
	  
	  public String getComments(){
		  return comments;
	  }



}


