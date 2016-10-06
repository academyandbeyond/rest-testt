package com.resttest.framework;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Headers;
import com.resttest.framework.api.Common;
import com.resttest.framework.api.Get;
import com.resttest.framework.exceptions.RestAPIException;
import com.resttest.framework.html.HTML;
import com.resttest.framework.html.HTMLBS;
import com.resttest.framework.json.model.*;
import com.resttest.framework.api.Post;
import com.resttest.framework.report.model.ReportScenario;
import com.resttest.framework.report.model.ReportTestCase;
import com.resttest.framework.report.model.ReportTestSuite;
import org.apache.log4j.Logger;

import static java.lang.Thread.sleep;

public class Executor {

	final Logger logger = Logger.getLogger(Executor.class);

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
	private Get get;
	private RestTimer executiontimer;

	//Report variables
	private ArrayList<ReportTestCase> reporttestcases;
	private ReportTestCase currentreporttestcase;
	private ReportTestSuite reporttestsuite;


	public Executor(ArrayList<TestCase> allTestCases, Capabilities capabilities){

		logger.info("Inside Executor constructor");
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

		reporttestcases= new ArrayList<ReportTestCase>();
		currentreporttestcase=new ReportTestCase();
		reporttestsuite=new ReportTestSuite();
	}



	public void executeAll(){
		int totaltestcases=allTestCases.size();
		for(int i=0; i<totaltestcases;i++)
		{
			testcaseresult="Not Executed";
			currentTestCase=allTestCases.get(i);
			logger.info("Inside executeAll -- Starting Execution for  : "+ currentTestCase.getFileName()+" - "+currentTestCase.getTCID());
			//Think ... you can get separate method names and call specific method here
			executeTest();
			currentTestCase.addTCResult(testcaseresult);
			createReport(currentTestCase);
			currentTestCase=null;
			try{
				//	sleep(9000);
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
			logger.info("Inside executeTest - Number of Scenarios in "+ currentTestCase.getFileName()+" - "+currentTestCase.getTCID()+" : "+numofscenarios );

		}else {
			numofscenarios=0;
			logger.warn("Inside executeTest - Number of Scenarios in "+ currentTestCase.getFileName()+" - "+currentTestCase.getTCID()+" : "+numofscenarios );
			return;
		}

		//currentUrl=currentTestCase.getUrl();
		currentTestCaseName=currentTestCase.getTestCase();


		for(int i=0; i<numofscenarios; i++){

			test.createTest(currentTestCase.getTestCase());
			currentscenario=currentTestCase.getScenarios().get(i);
			currentscenario.setScenarioStartTime(Common.getCurrentTime().getTime());

			currentscenario.setResult(ResultEnum.NE);
			currentUrl=currentscenario.getUrl().toString();

			currentcommand=currentscenario.getCommand().toString();
			currentExpected=currentscenario.getExpected();
			currentMethod=currentscenario.getMethod();
			currentvalidate=currentscenario.getValidate();
			currentscenarioid=currentscenario.getID();
			logger.info("Inside executeTest - Executing Scenario "+ currentTestCase.getFileName()+" - "+currentTestCase.getTCID()+" : "+currentscenarioid);

			if (currentscenarioid==null||currentMethod==null || currentvalidate==null || currentcommand==null || currentExpected==null){
				currentscenario.setError("Exiting execution as one of these is null. { 'method','validate','command','expected'}");
				logger.error("Exiting execution as one of these is null. { 'method','validate','command','expected'}");
				continue;
			}

			logger.info("Execution method on file "+currentMethod);
			if (currentMethod.equalsIgnoreCase(JsonConstants.GET.toString())){
				//test=new TestAPI().createTest(currentTestCase.getTestCase()).get(currentUrl);
				logger.info("Executing for GET method");
				//test = test.get(currentUrl);
				//currentheader=test.getAPIHeaders();
				//logger.info("Response header : "+ currentheader);
				//currentscenario.setResponseHeader(currentheader.toString());
				get = new Get(currentTestCaseName);
				currentscenario =get.executeGet(currentscenario,currentUrl);
				//currentscenario = executeGet(currentscenario);

			} else if(currentMethod.equalsIgnoreCase(JsonConstants.GETD.getConstant())){
				test = test.get(currentUrl);
				currentheader=test.getAPIHeaders();
				//currentscenario.setHeader(currentheader);
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
				//	currentprimarydata.setHeader(currentscenario.getHeaders());
					primarydata.add(currentprimarydata);
					//primarytestcases.add(currentTestCase);
				}
			}
			currentscenario.setScenarioEndTime(Common.getCurrentTime().getTime());

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
	//			case "content-type":
	//				currentscenario=executeHeaderCT(currentscenario);
	//				break;
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

		int scenarios;
		List<Scenario> allscenarios;
		String currenttestcaseresult="Not Executed";
		ResultEnum currenttestscenarioresult=ResultEnum.NE;

		int totaltestcases=allTestCases.size();
		int totalscenarios=0;
		int totaltestcasespassed=0;
		int totaltestcasesfailed=0;
		int totalscenariospassed=0;
		int totalscenariosfailed=0;
		int totalscenariosskipped=0;

		boolean scenariofailed=false;

/****************************************************************************************/
		// Below code generates json file for reporting. uncomment when ready to use javascript, jquery
		// CODE TO GENERATE JSON **************************************************

		/*

		allscenarios=currenttestcase.getScenarios();
		//TestCase currenttestcaseforreport;

		ReportTestCase currentreporttestcase=new ReportTestCase();

		try {

			//for (int i=0; i<totaltestcases; i++ ){
				System.out.println("------1");
				//currenttestcaseforreport=allTestCases.get(i);
				System.out.println("------2");

				//currentreporttestcase=reporttestcases.get(i);
			System.out.println("------"+currenttestcase.getFileName());

			currentreporttestcase.setFilename(currenttestcase.getFileName());

				System.out.println("------"+currentreporttestcase.getFilename());
				currentreporttestcase.setTCID(currenttestcase.getTCID());
				//currentreporttestcase.setTestcase(currenttestcaseforreport.getTestCase());
				//currentreporttestcase.setTCResult(currenttestcaseforreport.getTCResult());


				ArrayList<Scenario> scenariosincurrenttest=(ArrayList<Scenario>) currenttestcase.getScenarios();
				ArrayList<ReportScenario> reportcurrentscenariolist=new ArrayList<ReportScenario>(scenariosincurrenttest.size());
				ReportScenario reportcurrentscenario= new ReportScenario();

				for(int j=0;j<scenariosincurrenttest.size();j++){
					System.out.println("------3 "+scenariosincurrenttest.size()+" : "+scenariosincurrenttest.get(j).getID());

					reportcurrentscenario.setId(scenariosincurrenttest.get(j).getID());
					reportcurrentscenario.setActual(scenariosincurrenttest.get(j).getActual());
					reportcurrentscenario.setCommand(scenariosincurrenttest.get(j).getCommand());
					reportcurrentscenario.setExpected(scenariosincurrenttest.get(j).getExpected());
				//	reportcurrentscenario.setHeader(scenariosincurrenttest.get(j).getHeader().getAsString());
					reportcurrentscenario.setMethod(scenariosincurrenttest.get(j).getMethod());
					reportcurrentscenario.setName(scenariosincurrenttest.get(j).getName());
//					reportcurrentscenario.setPayload(scenariosincurrenttest.get(j).getPayload().toString());
					reportcurrentscenario.setPrimary(scenariosincurrenttest.get(j).getPrimary());
					reportcurrentscenario.setResponseattribute(scenariosincurrenttest.get(j).getResponseAttribute());
					reportcurrentscenario.setResponseheader(scenariosincurrenttest.get(j).getResponseHeader());
					reportcurrentscenario.setError(scenariosincurrenttest.get(j).getError());
					reportcurrentscenario.setResponsetime(scenariosincurrenttest.get(j).getResponsetime());
					reportcurrentscenario.setResult(scenariosincurrenttest.get(j).getResult());
					reportcurrentscenario.setScenariostarttime(scenariosincurrenttest.get(j).getScenarioStartTime());
					reportcurrentscenario.setScenarioendtime(scenariosincurrenttest.get(j).getScenarioEndTime());


				}

				reportcurrentscenariolist.add(reportcurrentscenario);
				currentreporttestcase.setScenarios(reportcurrentscenariolist);


			reporttestsuite.addTestCase(currentreporttestcase);
				System.out.println(reporttestsuite.getTestCases().size());

		//	}

		}catch(Exception e){logger.error("ERROR inside try block when creating json for report : "+ Common.getStackTrace(e));}

		Gson gson = new Gson();
		System.out.println("--------------------------");
		System.out.println(reporttestsuite.getTestCases().size());

		System.out.println(gson.toJson(reporttestsuite));
		jsonreport(gson.toJson(reporttestsuite));


       */
		// END CODE TO GENERATE JSON **************************************************


		//testReport.addTestCase(currenttestcase);

		int scenariospassincurrenttest;
		int scenariosfailincurrenttest;
		int scenariosskipincurrenttest;
		int scenariosincurrenttest;


		try {

			for (TestCase testcase : allTestCases){
				ArrayList<Scenario> totalscenariosincurrenttest=(ArrayList<Scenario>) testcase.getScenarios();
				scenariospassincurrenttest=0;
				scenariosfailincurrenttest=0;
				scenariosskipincurrenttest=0;
				scenariosincurrenttest=0;
				for(Scenario scenario:totalscenariosincurrenttest){
					scenariosincurrenttest=scenariosincurrenttest+1;
					if(scenario.getResult().equals(ResultEnum.FAIL)){
						scenariosfailincurrenttest=scenariosfailincurrenttest+1;
					} else if(scenario.getResult().equals(ResultEnum.PASS)){
						scenariospassincurrenttest=scenariospassincurrenttest+1;
					} else{
						scenariosskipincurrenttest=scenariosskipincurrenttest+1;
					}

				}

				totalscenarios=totalscenarios+scenariosincurrenttest;
				totalscenariospassed=totalscenariospassed+scenariospassincurrenttest;
				totalscenariosfailed=totalscenariosfailed+scenariosfailincurrenttest;
				totalscenariosskipped=totalscenariosskipped+scenariosskipincurrenttest;

				if(scenariosfailincurrenttest>0){
					logger.info("fail");
					totaltestcasesfailed=totaltestcasesfailed+1;
				} else {
					totaltestcasespassed=totaltestcasespassed+1;
				}


			}

		}catch(Exception e){}


		logger.info("Total TestCases : "+ totaltestcases);
		logger.info("TestCases Passed : "+totaltestcasespassed);
		logger.info("Total Scenarios: "+totalscenarios);
		logger.info(" Scenarios Passed : "+totalscenariospassed);
		logger.info(" Scenarios Failed : "+totalscenariosfailed);


		long time = new Date().getTime();

		Timestamp endtime= new Timestamp(new Date().getTime());
		executiontimer.setEndTime(endtime);



		double timediff = executiontimer.getEndTime().getTime() - executiontimer.getStartTime().getTime();
		logger.info(executiontimer.getStartTime());
		logger.info(executiontimer.getEndTime());
		logger.info(timediff);
		double diffseconds = timediff/(1000);
		double diffminutes = timediff/((60*1000)%60);


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
					HTML.adddatacolumn(scenario.getName());
					HTML.adddatacolumn(scenario.getUrl());
					HTML.adddatacolumn(String.valueOf(scenario.getResponsetime()/1000.0));
					HTML.adddatacolumn(((scenario.getScenarioEndTime()-scenario.getScenarioStartTime())/1000.0)+ " seconds");
					HTML.adddatacolumn(currentscenarioresult);


				}
			}

			HTML.endreport_5();
			HTML.after_6();

		}catch (Exception e){}






		/*************************** Bootstrap report code below ************************/

		try{

			HTMLBS.clear();
			HTMLBS.html_header_1();
			HTMLBS.html_heading_2();
			HTMLBS.html_statuscontainer_3(totaltestcases,totaltestcasespassed,0,totalscenarios,totalscenariospassed,totalscenariosfailed);
			HTMLBS.html_table_header_4();

			int scenariocount=0;

			for(TestCase testCase:allTestCases){
				ArrayList<Scenario> totalscenariosincurrenttest=(ArrayList<Scenario>) testCase.getScenarios();
				for(Scenario scenario:totalscenariosincurrenttest){
					String currentscenarioresult=null;
					if(scenario.getResult()==null){
						currentscenarioresult=ResultEnum.NE.getResult();
					} else {
						currentscenarioresult=scenario.getResult().toString();
					}
					scenariocount=scenariocount+1;

					HTMLBS.html_table_row_5(currentscenarioresult,Integer.toString(scenariocount));
					HTMLBS.html_table_data_fill_6(Integer.toString(scenariocount));


					HTMLBS.html_table_data_fill_6(testCase.getFileName());
					HTMLBS.html_table_data_fill_6(testCase.getTCID());
					HTMLBS.html_table_data_fill_6(testCase.getTestCase());
					HTMLBS.html_table_data_fill_6(scenario.getID());
					HTMLBS.html_table_data_fill_6(scenario.getName());
					HTMLBS.html_table_data_fill_6(scenario.getUrl());
					HTMLBS.html_table_data_fill_6(String.valueOf(scenario.getResponsetime()/1000.0));
					HTMLBS.html_table_data_fill_6(((scenario.getScenarioEndTime()-scenario.getScenarioStartTime())/1000.0)+ " seconds");
					HTMLBS.html_table_status_fill_7(currentscenarioresult);
					HTMLBS.html_table_row_collapse_8(Integer.toString(scenariocount),scenario.getExpected(),scenario.getActual(), scenario.getError(),scenario.getActualResponse());



				}
			}

			HTMLBS.html_table_end_9(totaltestcases,totaltestcasespassed,0,totalscenarios,totalscenariospassed,totalscenariosfailed);
			HTMLBS.html_save_report();
		}catch (Exception e){}







	}

	private void jsonreport(String data){

		try {
			String currentdir = System.getProperty("user.home");
			FileWriter writer = new FileWriter(currentdir + "/resttest.json");
			writer.write(data);
			writer.close();

		} catch(Exception e){}

	}

	private static String getStackTrace(Throwable e){
		StringWriter writer = new StringWriter();
		PrintWriter printwriter = new PrintWriter(writer);
		e.printStackTrace(printwriter);
		return writer.toString();
	}

}
