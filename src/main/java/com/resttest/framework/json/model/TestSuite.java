package com.resttest.framework.json.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class TestSuite {
	private ArrayList<TestCase> testSuite = new ArrayList<TestCase>();
	
	
	public void addTestSuite(String filename,ArrayList<TestCase> newTestSuite){
		for (TestCase tc : newTestSuite){
			tc.addFileName(filename);
		}
		
		testSuite.addAll(newTestSuite);
	}
	
	 public void addTestCase(TestCase testCase) {
		    testSuite.add(testCase);
		  }
	
	 public ArrayList<TestCase> getTestCases() {
		    return testSuite;
		  }
	 
	 /*
	 @Override
	  public String toString() {
	    StringBuilder formattedString = new StringBuilder();
	   
	      for (TestCase tc : getTestCases()) {
	        formattedString.append("  ").append(tc).append("\n");
	      }
	  
	

	    return formattedString.toString();
	  }*/
	 
}
