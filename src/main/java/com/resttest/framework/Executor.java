package com.resttest.framework;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Headers;
import com.resttest.framework.exceptions.RestAPIException;
import com.resttest.framework.html.HTML;
import com.resttest.framework.json.model.*;
import com.resttest.framework.api.Post;
import static java.lang.Thread.sleep;

public class Executor {
	
	ArrayList<TestCase> allTestCases;
	TestCase currentTestCase;
	TestAPI test;
	private String currentscenarioid;
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
	private Post post;
	private RestTimer executiontimer;
	
	public Executor(ArrayList<TestCase> allTestCases, Capabilities capabilities){
		this.allTestCases=allTestCases;
		this.capabilities=capabilities;
		test=new TestAPI();
		testReport=new TestSuite();
		primarydata= new ArrayList<PrimaryData>();
		executiontimer = new RestTimer();

		//
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		executiontimer.setStartTime(ts);
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
				try{
					sleep(9000);
				}catch (Exception e){}
			}
	}
	
	
	private void executeTest(){
		Scenario currentscenario;
		String currentvalidate;
		String currentcommand;
		String currentExpected;
		Headers currentheader;
		JsonPath currentresponse;
		int numofscenarios;
		PrimaryData currentprimarydata=new PrimaryData();
				//System.out.println("Inside executeTest : "+currentTestCase.getTCID());

		if (currentTestCase.getScenarios()!=null){
			numofscenarios=currentTestCase.getScenarios().size();
		}else {
			numofscenarios=0;
			return;
		}
		
		currentUrl=currentTestCase.getUrl();
		currentTestCaseName=currentTestCase.getTestCase();

				
		for(int i=0; i<numofscenarios; i++){
			
			test.createTest(currentTestCase.getTestCase());
			currentscenario=currentTestCase.getScenarios().get(i);
			currentscenario.setResult(ResultEnum.NE);
			currentcommand=currentscenario.getCommand().toString();
			currentExpected=currentscenario.getExpected();
			currentMethod=currentscenario.getMethod();
			currentvalidate=currentscenario.getValidate();
			currentscenarioid=currentscenario.getID();
			
			if (currentscenarioid==null||currentMethod==null || currentvalidate==null || currentcommand==null || currentExpected==null){
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
				test = test.get(currentUrl);
				currentheader=test.getAPIHeaders();
				currentscenario.setHeader(currentheader);	
				currentscenario = executeGetD(currentscenario,primarydata);	
			} else if(currentMethod.equalsIgnoreCase(JsonConstants.POST.getConstant())){
				post = new Post(currentTestCaseName);
				post.executePOST(currentscenario, currentUrl);
			}else if(currentMethod.equalsIgnoreCase(JsonConstants.POSTD.getConstant())) {
				post = new Post(currentTestCaseName);
				currentscenario=post.executePostD(currentscenario, primarydata,currentUrl);
			}
			
			//System.out.println("Inside executeAll : "+currentTestCase.getTCID());

			String currentTCID=currentTestCase.getTCID();
			
			if(currentscenario.getPrimary()!=null && currentscenario.getPrimary()!=""){
				if(currentscenario.getPrimary().equalsIgnoreCase("Yes")){
					currentprimarydata.setTCID(currentTCID);
					currentprimarydata.setTSID(currentscenario.getID());
					currentprimarydata.setJsonPath(currentscenario.getJsonPath());
					currentprimarydata.setHeader(currentscenario.getHeaders());
					primarydata.add(currentprimarydata);
					//primarytestcases.add(currentTestCase);
				}
			}

			currentscenarioid=null;
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
	
	
	
	private Scenario executeHeaderCT(Scenario currentscenario) throws RestAPIException {
		
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
		List<Scenario> allscenarios;
		String currenttestcaseresult="Not Executed";
		ResultEnum currenttestscenarioresult=ResultEnum.NE;

		int totaltestcases=allTestCases.size();
		int totalscenarios=0;
		int totaltestcasespassed=0;
		int totalscenariospassed=0;
		int totalscenariosfailed=0;
		int totalscenariosskipped=0;

		boolean scenariofailed=false;
		allscenarios=currenttestcase.getScenarios();

		try {

			for (TestCase testcase : allTestCases){
				ArrayList<Scenario> totalscenariosincurrenttest=(ArrayList<Scenario>) testcase.getScenarios();
				for(Scenario scenario:totalscenariosincurrenttest){
					totalscenarios=totalscenarios+1;
					if(scenario.getResult().equals(ResultEnum.FAIL)){
						totalscenariosfailed=totalscenariosfailed+1;
					} else if(scenario.getResult().equals(ResultEnum.PASS)){
						totalscenariospassed=totalscenariospassed+1;
					} else{
						totalscenariosskipped=totalscenariosskipped+1;
					}

				}

			}

		}catch(Exception e){}

		long time = new Date().getTime();

		Timestamp endtime= new Timestamp(new Date().getTime());
		executiontimer.setEndTime(endtime);

//		double timediff = executiontimer.getEndTime().getTime() - executiontimer.getStartTime().getTime();
//		double diffseconds = timediff/(1000%60);
//		double diffminutes = timediff/(60*1000)%60;

		try{

			HTML.clear();
			HTML.before_1();
			HTML.piechartjavascript_2(totaltestcases,totaltestcasespassed,0,totalscenarios,totalscenariospassed,totalscenariosfailed);
			HTML.buildstatusbox_3();
			HTML.reportheader_4();
			for(TestCase testCase:allTestCases){
				ArrayList<Scenario> totalscenariosincurrenttest=(ArrayList<Scenario>) testCase.getScenarios();
				for(Scenario scenario:totalscenariosincurrenttest){
					String currentscenarioresult=null;
					if(scenario.getResult()==null){
						currentscenarioresult=ResultEnum.NE.getResult();
					} else {
						currentscenarioresult=scenario.getResult().toString();
					}

					HTML.createreportrow();
					HTML.adddatacolumn(testCase.getFileName());
					HTML.adddatacolumn(testCase.getTCID());
					HTML.adddatacolumn(testCase.getTestCase());
					HTML.adddatacolumn(scenario.getID());
					HTML.adddatacolumn(scenario.getScenario());
					HTML.adddatacolumn(scenario.getUrl());
					HTML.adddatacolumn(String.valueOf(scenario.getResponsetime()/1000.0));
					HTML.adddatacolumn("10 seconds");
					HTML.adddatacolumn(currentscenarioresult);


				}
			}

			HTML.endreport_5();
			HTML.after_6();

		}catch (Exception e){}

	
	}
	
	private static String getStackTrace(Throwable e){
		StringWriter writer = new StringWriter();
		PrintWriter printwriter = new PrintWriter(writer);
		e.printStackTrace(printwriter);
		return writer.toString();
	}

}
