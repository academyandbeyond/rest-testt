package com.resttest.framework.api;

import com.google.gson.JsonElement;
import java.util.ArrayList;

import com.resttest.framework.JsonUtil;
import com.resttest.framework.TestAPI;
import com.resttest.framework.json.model.PrimaryData;
import com.resttest.framework.json.model.Scenario;
import com.resttest.framework.json.model.ResultEnum;
import org.apache.log4j.Logger;


/**
 * Created by sumana on 7/10/2016.
 */


public class Post {

    private TestAPI test;
    final Logger logger = Logger.getLogger(Post.class);

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

        logger.info("URL : "+url);


        try {
            switch(validator){
                case "status":
                    logger.info("URL before Post Call : "+ url);
                    logger.info("Header before Post Call : "+ header);
                    logger.info("Payload before Post Call : "+ payload);
                    test.post(url,header,payload);
                    statuscode=Integer.toString(test.getStatus());
                    currentscenario.setActualResponse(test.getResponsebody());
                    currentscenario.setJsonPath(test.getResponseJsonPath());
                    currentscenario.setActual(statuscode);
                    currentscenario.setResponseTime(test.responseTime());
                    statusbool=Common.stringCompare(currentscenario.getExpected(),statuscode);
                    currentscenario.setResult(Common.setResult(statusbool));
                    break;
            }
        }catch(Exception e){currentscenario.setError(Common.getStackTrace(e));return currentscenario;}
        return currentscenario;
    }




    public Scenario executePostD(Scenario currentscenario,ArrayList<PrimaryData> primarydata, String url){
        logger.info("Starting executePostD");
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
                logger.info("URL before Post-D Call : "+ url);

                JsonElement element = currentscenario.getPayload().getAsJsonObject();
                if (JsonUtil.getJsonElementValue(element, "~Scenario") != null) {
                    primaryscenario = JsonUtil.getJsonElementValue(element, "~Scenario").getAsString();
                } else {
                    logger.info("Exiting Post-D as payload is null "+ currentscenario.getPayload());
                    // fail the scenario and get out of method
                }
            }

        } catch(Exception e){logger.info("Exiting try block with error "+ e);}
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
            currentscenario.setResult(Common.setResult(false));
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
                    currentscenario.setResult(Common.setResult(statusbool));
                    break;

            }

        }catch(Exception e){currentscenario.setError(Common.getStackTrace(e));
            currentscenario.setResult(Common.setResult(false));
            return currentscenario;

        }


        return currentscenario;
    }



}
