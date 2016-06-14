package com.resttest.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.resttest.framework.json.model.Scenario;
import com.resttest.framework.json.model.TestCase;
import com.resttest.framework.json.model.TestSuite;

public class Executor {
	
	ArrayList<TestCase> allTestCases;
	TestCase currentTestCase;
	TestAPI test;
	private String currentUrl;
	private String currentTestCaseName;
	
	private String currentExpected;
	private Integer actualStatusCode;
	private String testcaseresult;
	private String currentMethod;
	private String currentcommand;
	private Scenario currentscenario;
	private TestSuite testReport;
	private String currentvalidate;
	private Capabilities capabilities;
	
	public Executor(ArrayList<TestCase> allTestCases, Capabilities capabilities){
		this.allTestCases=allTestCases;
		this.capabilities=capabilities;
		test=new TestAPI();
		testReport=new TestSuite();
	}
	
public void executeAll(){
	int totaltestcases=allTestCases.size();
	for(int i=0; i<totaltestcases;i++)
		{
			testcaseresult="Not Executed";
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
		
		currentUrl=currentTestCase.getUrl();
		currentTestCaseName=currentTestCase.getTestCase();

				
		for(int i=0; i<scenarios; i++){
			
			currentscenario=currentTestCase.getScenarios().get(i);
			currentscenario.setResult("Not Executed");
			currentcommand=currentscenario.getCommand().toString();
			currentExpected=currentscenario.getExpected();
			currentMethod=currentscenario.getMethod();
			currentvalidate=currentscenario.getValidate();
			
			
			if (currentMethod==null || currentvalidate==null || currentcommand==null){
				currentscenario.setError("One of the values is either null or not assigned");
				continue;
			}
			
			
			
			if (currentMethod.equalsIgnoreCase("GET")){
			
			try {	
				if (currentvalidate.equalsIgnoreCase("status")){
					actualStatusCode=getActualStatusCode();
					
					if (currentcommand.equalsIgnoreCase("compare"))
						{
							if (intCompare(Integer.parseInt(currentExpected),actualStatusCode))
							{
								passScenario();
								currentscenario.setError("Expected : "+currentExpected+" - Actual: "+actualStatusCode);
							} else {
								failScenario();
								currentscenario.setError("Expected : "+currentExpected+" - Actual: "+actualStatusCode);
							}
						} else {
							currentscenario.setError("Compare command should be used to verify status code with GET method");
						}
					
				}
			}catch(Exception e){currentscenario.setError(getStackTrace(e));}
				
			}
			
			
			
		}
		
		
	}
	
	private void passScenario(){
		currentscenario.setResult("Pass");
	}
	
	private void failScenario(){
		currentscenario.setResult("Fail");
	}
	
	private void executeScenario(TestCase testcase){
		
		
	}
	
	private boolean stringCompare(String expected, String actual){
		//test.createTest(currentTestCase.getTestCase()).getStatus(currentTestCase.getUrl());
		//System.out.println("Inside executeCompare");
		if(expected.contains(actual)){
			return true;
		}
		return false;
	}

	private void executeEqual(){
		//System.out.println("Inside executeEqual");

	}
	
	private boolean intCompare(int expected, int actual){
		
		if (expected==actual){
			return true;
		}
		return false;
		
	}
	
	
	private int getActualStatusCode(){
		return test.createTest(currentTestCaseName).getStatus(currentUrl);
	}
	
	
	
	private void createReport(TestCase currenttestcase){
		testReport.addTestCase(currenttestcase);
		
		int scenarios;
		List<Scenario> scenario;
		String currenttestcaseresult="Pass";
		
		
		
		//for(TestCase testcase : testReport.getTestCases()){
			
			scenario = currenttestcase.getScenarios();
			scenarios=scenario.size();
			
			System.out.println(currenttestcase.getFileName()+" : ");
			System.out.print(currenttestcase.getTCID()+" : ");
			System.out.print(currenttestcase.getTestCase()+" : ");
			System.out.println(currenttestcase.getUrl()+" : ");
				
			for(int i=0; i<scenarios; i++){
				System.out.print(scenario.get(i).getID()+" : ");
				System.out.print(scenario.get(i).getResult()+" : ");	
				System.out.println(scenario.get(i).getError()+" : ");		
			}
			
			currenttestcase.addTCResult(currenttestcaseresult);
			
			System.out.println(currenttestcase.getTCResult()+" : ");
			currenttestcaseresult="Pass";
			scenario=null;
			scenarios=0;
		
		//}
	}
	
	private static String getStackTrace(Throwable e){
		StringWriter writer = new StringWriter();
		PrintWriter printwriter = new PrintWriter(writer);
		e.printStackTrace(printwriter);
		return writer.toString();
	}

}
