package com.resttest.framework;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Headers;
import com.resttest.framework.json.model.JsonConstants;
import com.resttest.framework.json.model.PrimaryData;
import com.resttest.framework.json.model.ResultEnum;
import com.resttest.framework.json.model.Scenario;
import com.resttest.framework.json.model.TestCase;
import com.resttest.framework.json.model.TestSuite;

public class Executor {
	
	ArrayList<TestCase> allTestCases;
	TestCase currentTestCase;
	TestAPI test;
	private String currentUrl;
	private String currentTestCaseName;
	private String testcaseresult;
	private String currentMethod;
	private TestSuite testReport;
	private Capabilities capabilities;
	private ArrayList<PrimaryData> primarydata;
	private String PASS = ResultEnum.PASS.getResult();
	private String FAIL = ResultEnum.PASS.getResult();
	private String NE = ResultEnum.NE.getResult();
	
	public Executor(ArrayList<TestCase> allTestCases, Capabilities capabilities){
		this.allTestCases=allTestCases;
		this.capabilities=capabilities;
		test=new TestAPI();
		testReport=new TestSuite();
		primarydata= new ArrayList<PrimaryData>();
	}
	
	public void executeAll(){
		int totaltestcases=allTestCases.size();
		for(int i=0; i<totaltestcases;i++)
			{
				testcaseresult="Not Executed";
				currentTestCase=allTestCases.get(i);
				//System.out.println("Inside executeAll : "+currentTestCase.getTCID());
				//Think ... you can get separate method names and call specific method here
				executeTest();
				currentTestCase.addTCResult(testcaseresult);
				createReport(currentTestCase);
				currentTestCase=null;
			}
	}
	
	
	private void executeTest(){
		
			 Scenario currentscenario;
			 String currentvalidate;
			 String currentcommand;
			 String currentExpected;
			 Headers currentheader;
			 JsonPath currentresponse;
			 PrimaryData currentprimarydata=new PrimaryData();
			 
			 
				//System.out.println("Inside executeTest : "+currentTestCase.getTCID());


		int scenarios;
		if (currentTestCase.getScenarios()!=null){
			scenarios=currentTestCase.getScenarios().size();
		}else {
			scenarios=0;
			return;
		}
		
		currentUrl=currentTestCase.getUrl();
		currentTestCaseName=currentTestCase.getTestCase();

				
		for(int i=0; i<scenarios; i++){
			
			test.createTest(currentTestCase.getTestCase());
			currentscenario=currentTestCase.getScenarios().get(i);
			currentscenario.setResult(ResultEnum.NE);
			currentcommand=currentscenario.getCommand().toString();
			currentExpected=currentscenario.getExpected();
			currentMethod=currentscenario.getMethod();
			currentvalidate=currentscenario.getValidate();
			
			if (currentMethod==null || currentvalidate==null || currentcommand==null || currentExpected==null){
				//System.out.println("1111 "+currentscenario.getID() );
				currentscenario.setError("Exiting execution as one of these is null. { 'method','validate','command','expected'}");
				continue;
			}
			
			//System.out.println(currentMethod+ "  "+ JsonConstants.GETD.getConstant());
			if (currentMethod.equalsIgnoreCase(JsonConstants.GET.toString())){
				//test=new TestAPI().createTest(currentTestCase.getTestCase()).get(currentUrl);
				test = test.get(currentUrl);
				currentheader=test.getAPIHeaders();
				currentscenario.setHeader(currentheader);
				currentscenario = executeGet(currentscenario);	
				
			} else if(currentMethod.equalsIgnoreCase(JsonConstants.GETD.getConstant())){
				System.out.println("Coming here");
				test = test.get(currentUrl);
				currentheader=test.getAPIHeaders();
				currentscenario.setHeader(currentheader);	
				currentscenario = executeGetD(currentscenario,primarydata);	
			}
			
			//System.out.println("Inside executeAll : "+currentTestCase.getTCID());

			String currentTCID=currentTestCase.getTCID();
			
			if(currentscenario.getPrimary()!=null){
				if(currentscenario.getPrimary().equalsIgnoreCase("Yes")){
					currentprimarydata.setTCID(currentTCID);
					
					currentprimarydata.setTSID(currentscenario.getID());
					currentprimarydata.setJsonPath(currentscenario.getJsonPath());
					currentprimarydata.setHeader(currentscenario.getHeader());
					primarydata.add(currentprimarydata);
					//primarytestcases.add(currentTestCase);
				}
			}
			
			currentscenario=null;
			currentcommand=null;
			currentExpected=null;
			currentMethod=null;
			currentvalidate=null;	
			currentresponse=null;
		}	
	}
	
	
	private Scenario executeGet(Scenario currentscenario){
		Scenario resultscenario=null;
		String headervalue;
		String validator=currentscenario.getValidate();
		
		try {	
			switch(validator){
			case "status":
				currentscenario=executeStatus(currentscenario);
				break;
			case "content-type":
				currentscenario=executeHeaderCT(currentscenario);
				break;
			}
			
			/*if (currentscenario.getValidate().equalsIgnoreCase("status")){
				currentscenario=executeStatus(currentscenario);
				//return currentscenario;
			}*/
		}catch(Exception e){currentscenario.setError(getStackTrace(e));return currentscenario;}
		return currentscenario;
	}
	

	
	private Scenario executeGetD(Scenario currentscenario,ArrayList<PrimaryData> primarydata){
		Scenario resultscenario=null;
		String headervalue;
		String validator=currentscenario.getValidate();
		String dependencyvalue;
		
		//System.out.println("Inside GetD : 1");
		// Get the dependency info
		String[] primaryscenario= currentscenario.getDependent();
		
		//System.out.println("Inside executeGetD - Length - "+primaryscenario.length +" "+primaryscenario[0] );
		// TODO if GET-D is null
		
		// 1. get dependency value
		// 2. pass it as parameter
		
		for (PrimaryData pd : primarydata) {
			System.out.println(pd.getTCID().concat(pd.getTSID()));
			if(  ( pd.getTCID().concat(pd.getTSID()).equalsIgnoreCase(primaryscenario[0]) )   ) {
				System.out.println("Inside GetD : 2");

				//System.out.println(pd.getJsonPath().toString());
			}
		 }
		
		try {	
			switch(validator){
			case "status":
				currentscenario=executeStatus(currentscenario);
				break;
			case "content-type":
				currentscenario=executeHeaderCT(currentscenario);
				break;
			}
			
			/*if (currentscenario.getValidate().equalsIgnoreCase("status")){
				currentscenario=executeStatus(currentscenario);
				//return currentscenario;
			}*/
		}catch(Exception e){currentscenario.setError(getStackTrace(e));return currentscenario;}
		return currentscenario;
	}
	
	
	
	private Scenario executeHeaderCT(Scenario currentscenario) {
		
		String expectedcontenttype = currentscenario.getExpected() ;
		String actualcontenttype=getActualContentType();
		String currentcommand = currentscenario.getCommand();
		
		if (currentcommand.equalsIgnoreCase("compare"))
		{
			if (stringCompare(expectedcontenttype,actualcontenttype))
			{
				currentscenario.setJsonPath(test.getResponseJsonPath());
				currentscenario.setResult(ResultEnum.PASS);
				currentscenario.setError("Expected : "+expectedcontenttype+" - Actual: "+actualcontenttype);
			} else {
				currentscenario.setJsonPath(test.getResponseJsonPath());
				currentscenario.setResult(ResultEnum.FAIL);
				currentscenario.setError("Expected : "+expectedcontenttype+" - Actual: "+actualcontenttype);
			}
		} else if(currentcommand.equalsIgnoreCase("contains")){
			if (actualcontenttype.contains(expectedcontenttype))
			{
				currentscenario.setResult(ResultEnum.PASS);
				currentscenario.setError("Expected : "+expectedcontenttype+" - Actual: "+actualcontenttype);
			} else {
				currentscenario.setResult(ResultEnum.FAIL);
				currentscenario.setError("Expected : "+expectedcontenttype+" - Actual: "+actualcontenttype);
			}
			
		}
		
		else {
			currentscenario.setError("method for content-type should be either contains or compare");
		}		
		
		return currentscenario;
		// TODO Auto-generated method stub		
		
	}


	private Scenario executeStatus(Scenario currentscenario){
		 Integer actualStatusCode;		
		 String currentexpected=currentscenario.getExpected();
		 
		 
		 
			actualStatusCode=getActualStatusCode();
			if (currentscenario.getCommand().equalsIgnoreCase("compare"))
			{
				if (intCompare(Integer.parseInt(currentexpected),actualStatusCode))
				{
					currentscenario.setResult(ResultEnum.PASS);
					currentscenario.setError("Expected : "+currentexpected+" - Actual: "+actualStatusCode);
				} else {
					currentscenario.setResult(ResultEnum.FAIL);
					currentscenario.setError("Expected : "+currentexpected+" - Actual: "+actualStatusCode);
				}
			} else {
				currentscenario.setError("Compare command should be used to verify status code with GET method");
			}		
			
			return currentscenario;
	}
	
	
	@SuppressWarnings("unused")
	private boolean stringCompare(String expected, String actual){
		//test.createTest(currentTestCase.getTestCase()).getStatus(currentTestCase.getUrl());
		//System.out.println("Inside executeCompare");
		if(expected.contains(actual)){
			return true;
		}
		return false;
	}

	
	private boolean intCompare(int expected, int actual){
		if (expected==actual){
			return true;
		}
		return false;
	}
	
	private int getActualStatusCode(){
		//return test.createTest(currentTestCaseName).getStatus(currentUrl);
		//return test.getStatus(currentUrl);
		return test.getStatus();
	}
	
	private String getActualContentType(){
		return test.getContentType();
		//return "json";
	}
	
	private void createReport(TestCase currenttestcase){
		testReport.addTestCase(currenttestcase);
		
		int scenarios;
		List<Scenario> scenario;
		String currenttestcaseresult="Not Executed";
	
			scenario = currenttestcase.getScenarios();
			try{
			if ((scenario.size())<1){
				scenarios=0;
			} else{
			scenarios=scenario.size();
			}
			}catch(Exception e){scenarios=0;}
			
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
			currenttestcaseresult="Not Executed";
			scenario=null;
			scenarios=0;
	
	}
	
	private static String getStackTrace(Throwable e){
		StringWriter writer = new StringWriter();
		PrintWriter printwriter = new PrintWriter(writer);
		e.printStackTrace(printwriter);
		return writer.toString();
	}

}
