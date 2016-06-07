package com.resttest.framework;

import java.io.IOException;
import java.util.ArrayList;

import com.resttest.framework.json.model.Scenario;
import com.resttest.framework.json.model.TestCase;
import com.resttest.framework.json.model.TestSuite;

public class Executor {
	
	ArrayList<TestCase> allTestCases;
	TestCase currentTestCase;
	TestAPI test;
	private String currentUrl;
	private String currentTestCaseName;
	
	private String currentCommand;
	private String currentExpected;
	private String currentActual;
	private Integer currentStatusCode;
	private Integer actualStatusCode;
	private String testcaseresult;
	private String scenarioresult;
	private String statuscoderesult;
	private String currentMethod;
	private Scenario currentscenario;
	private TestSuite testReport;

	
	public Executor(ArrayList<TestCase> allTestCases){
		this.allTestCases=allTestCases;
		test=new TestAPI();
		testReport=new TestSuite();
	}
	
public void executeAll(){
		
		// Start Html here
		//System.out.println(allTestCases.size());
		int totaltestcases=allTestCases.size();
		
		for(int i=0; i<totaltestcases;i++){
			testcaseresult="Not Executed";
			statuscoderesult="Not Executed";
			//System.out.println(allTestCases.get(i));
			//System.out.println(allTestCases.get(i).getScenarios().size());
			currentTestCase=allTestCases.get(i);
			//Think ... you can get separate method names and call specific method here
			executeTest();
			currentTestCase.addTCResult(testcaseresult);
			createReport(currentTestCase);

			currentTestCase=null;

		}
		
		
	}
	
	
	private void executeTest(){
	
		int scenarios;
		if (currentTestCase.getScenarios()!=null){
			scenarios=currentTestCase.getScenarios().size();
		}else {
			scenarios=0;
		}
		
		//System.out.println("Status Code Result" +statuscoderesult);
		currentUrl=currentTestCase.getUrl();
		currentTestCaseName=currentTestCase.getTestCase();
		currentStatusCode=currentTestCase.getStatusCode();
		currentMethod=currentTestCase.getMethod();
		
		if (currentStatusCode==null){
			//System.out.println("It is null");
			
		} else if(currentMethod.equalsIgnoreCase("GET"))
		{
			//System.out.println("It is NOOOOOOT NULL : "+currentStatusCode);
			statuscoderesult=verifyStatusCode();
			if(statuscoderesult.equalsIgnoreCase("Pass")){
				testcaseresult="Pass";
			}else if(statuscoderesult.equalsIgnoreCase("Fail")){
				testcaseresult="Fail";
			}
		}
		
		//System.out.println(scenarios);
				
		for(int i=0; i<scenarios; i++){
			currentscenario=currentTestCase.getScenarios().get(i);
			scenarioresult="Not Executed";
			currentCommand=currentscenario.getCommand().toString();
			currentExpected=currentscenario.getExpected();
	
			// TODO add case statements
			//System.out.println(currentCommand);
			if (currentCommand.equalsIgnoreCase("compare")){
				executeCompare();
				//verifyStatusCode();
			} 
			else if(currentCommand.equalsIgnoreCase("equals")){
				executeEqual();
			}
			
			currentscenario.setResult(scenarioresult);
						
		}
		
		
	}
	
	
	private void executeScenario(TestCase testcase){
		
		
	}
	
	private void executeCompare(){
		test.createTest(currentTestCase.getTestCase()).getStatus(currentTestCase.getUrl());
		//System.out.println("Inside executeCompare");
	}

	private void executeEqual(){
		//System.out.println("Inside executeEqual");

	}
	
	private String verifyStatusCode(){
		System.out.println(currentTestCaseName);
		System.out.println(currentUrl);
		actualStatusCode = test.createTest(currentTestCaseName).getStatus(currentUrl);
		
		System.out.println(currentStatusCode);
		System.out.println(actualStatusCode);
		
		if (currentStatusCode.intValue()==actualStatusCode.intValue()){
			System.out.println("PASS");
			return "Pass";
		
		}else {System.out.println("fail"); return "Fail";}
		
		//System.out.println(currentExpected);
		//System.out.println(currentStatusCode);
	}
	
	private void createReport(TestCase currenttestcase){
		testReport.addTestCase(currenttestcase);
		for(TestCase testcase : testReport.getTestCases()){
			System.out.print(testcase.getFileName());
			System.out.print(testcase.getTestCase());
			System.out.print(testcase.getUrl());
		
				
		System.out.print(testcase.getMethod());
		System.out.print(testcase.getStatusCode());
		System.out.print(testcase.getTCID());
		System.out.print(testcase.getTCResult());
		System.out.print(testcase.getComments());
		
		}
	}

}
