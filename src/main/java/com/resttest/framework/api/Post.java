package com.resttest.framework.api;

import com.google.gson.JsonElement;
import java.util.ArrayList;

import com.resttest.framework.JsonUtil;
import com.resttest.framework.TestAPI;
import com.resttest.framework.json.model.PrimaryData;
import com.resttest.framework.json.model.Scenario;
import com.resttest.framework.json.model.ResultEnum;


/**
 * Created by sumana on 7/10/2016.
 */


public class Post {

    private TestAPI test;

    public Post(String testcase){
       test = new TestAPI().createTest(testcase);
    }

    public Scenario executePOST(Scenario currentscenario, String url){
        String validator = currentscenario.getValidate();
        String statuscode;
        boolean statusbool;
        String header=currentscenario.getHeader().toString();
        String payload=currentscenario.getPayload().toString();

        if((url==null) || url==""){
            url=currentscenario.getUrl();
        }

        try {
            switch(validator){
                case "status":
                    test.post(url,header,payload);
                    statuscode=Integer.toString(test.getStatus());
                    currentscenario.setActualResponse(test.getResponsebody());
                    currentscenario.setJsonPath(test.getResponseJsonPath());
                    currentscenario.setActual(statuscode);
                    currentscenario.setResponseTime(test.responseTime());
                    statusbool=Common.stringCompare(currentscenario.getExpected(),statuscode);
                    currentscenario.setResult(setResult(statusbool));
                    break;
            }
        }catch(Exception e){currentscenario.setError(Common.getStackTrace(e));return currentscenario;}
        return currentscenario;
    }


    private ResultEnum setResult(boolean value){
        if(value==true){
            return ResultEnum.PASS;
        }else if(value==false){
            return ResultEnum.FAIL;
        } else{
            return ResultEnum.NE;
        }
    }


    public Scenario executePostD(Scenario currentscenario,ArrayList<PrimaryData> primarydata, String url){
       System.out.println("Inside Post:executePostD");
        Scenario resultscenario=null;
        String headervalue;
        String validator=currentscenario.getValidate();
        String dependencyvalue;
        String payload="";
        String primaryscenario="";

        if(url==null || url==""){
            url=currentscenario.getUrl();
        }

        // Custom Payload
        try {
            if (currentscenario.getPayload().toString() != "" & currentscenario.getPayload() != null) {
                JsonElement element = currentscenario.getPayload().getAsJsonObject();
                if (JsonUtil.getJsonElementValue(element, "~Scenario") != null) {
                    primaryscenario = JsonUtil.getJsonElementValue(element, "~Scenario").getAsString();
                } else {
                    // fail the scenario and get out of method
                }
            }

        } catch(Exception e){}
        // TODO if GET-D is null

        // 1. get dependency value
        // 2. pass it as parameter

        Boolean primaryscenariofound=false;



            for (PrimaryData pd : primarydata) {
                System.out.println(pd.getTCID().concat(pd.getTSID()));
                if ((pd.getTCID().concat(pd.getTSID()).equalsIgnoreCase(primaryscenario))) {
                    primaryscenariofound = true;
                    JsonUtil.giveElement(pd.getJsonPath());
                    if(currentscenario.getResponseAttribute()!=null & currentscenario.getResponseAttribute()!="") {
                        dependencyvalue=currentscenario.getResponseAttribute();
                        payload = JsonUtil.getJsonElementValue(pd.getJsonPath(), dependencyvalue).toString();
                    }

                }
            }


        // Error if primary value not found

        if(primaryscenariofound==false){
            currentscenario.setResult(setResult(false));
            return currentscenario;
        }

        String header = currentscenario.getHeader().toString();
        String statuscode;
        Boolean statusbool;

        try{
            switch (validator){
                case "status":
                    test.post(url,header,payload);
                    statuscode=Integer.toString(test.getStatus());
                    currentscenario.setActualResponse(test.getResponsebody());
                    currentscenario.setJsonPath(test.getResponseJsonPath());
                    currentscenario.setActual(statuscode);
                    statusbool=Common.stringCompare(currentscenario.getExpected(),statuscode);
                    currentscenario.setResult(setResult(statusbool));
                    break;

            }

        }catch(Exception e){currentscenario.setError(Common.getStackTrace(e));
            currentscenario.setResult(setResult(false));
            return currentscenario;

        }


        return currentscenario;
    }



}
