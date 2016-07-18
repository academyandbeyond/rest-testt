package com.resttest.framework.api;

import com.resttest.framework.json.model.ResultEnum;
import com.resttest.framework.json.model.Scenario;
import com.resttest.framework.TestAPI;

/**
 * Created by sreepadb on 7/10/2016.
 */
public class Util {

    TestAPI test;
    public Util(String testcase){
        test = new TestAPI().createTest(testcase);
    }

    public Scenario executePost(Scenario scenario){

        return null;
    }

    public Scenario executeStatus(Scenario currentscenario){
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
            currentscenario.setError("Compare command should be used to verify status code");
        }

        return currentscenario;
    }


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
      // System.out.println("asdf :"+ test.postStatus());
        return test.postStatus();
    }
}
