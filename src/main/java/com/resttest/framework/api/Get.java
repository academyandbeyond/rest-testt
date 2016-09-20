package com.resttest.framework.api;

import com.resttest.framework.TestAPI;
import com.resttest.framework.json.model.Scenario;
import org.apache.log4j.Logger;

/**
 * Created by sreepadbhagwat on 9/19/16.
 */
public class Get {

    private TestAPI test;
    final Logger logger = Logger.getLogger(Get.class);

    public Get(String testcase){
        test = new TestAPI().createTest(testcase);
    }

    public Scenario executeGet(Scenario currentscenario, String url){
        String validator = currentscenario.getValidate();
        String statuscode;
        boolean statusbool;
        String header=null;
        String payload=null;

        try {
            header = currentscenario.getHeader().toString();
        }catch(Exception e){logger.info("Header information not provided in data file : "+e);}

        try {
            payload = currentscenario.getPayload().toString();
        } catch(Exception e){logger.info("Payload information not provided in data file : "+e);}

        if((url==null) || url==""){
            url=currentscenario.getUrl();
        }

        logger.info("URL : "+url);


        try {
            switch(validator){
                case "status":
                    logger.info("URL before Get Call : "+ url);
                    logger.info("Header before Get Call : "+ header);
                    logger.info("Payload before Get Call : "+ payload);
                    test.get(url);
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


}
