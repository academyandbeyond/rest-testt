
package com.resttest.framework.report.model;

import java.util.List;

/**
 * Created by sreepadbhagwat on 9/25/16.
 */

public class ReportTestCase {
    private String filename;
    private String TCID;
    private String testcase;
    private String TCResult;
    private List<ReportScenario> scenarios;

    public String getTCResult() {
        return TCResult;
    }

    public void setTCResult(String TCResult) {
        this.TCResult = TCResult;
    }




    public void setFilename(String filename){
        this.filename=filename;
    }

    public String getFilename(){
        return filename;
    }

    public void setTCID(String TCID){
        this.TCID=TCID;
    }

    public String getTCID(){
        return TCID;
    }

    public void setTestcase(String testcase){
        this.testcase=testcase;
    }

    public String getTestcase(){
        return testcase;
    }

    public void setScenarios(List<ReportScenario> scenarios){
        this.scenarios=scenarios;
    }

    public List<ReportScenario> getScenarios(){
        return scenarios;
    }

}


 /*

 {

"one.json":

[{"TCID":"TC1","TestCase":"Test Case One smoke regression",

		"scenarios":[
			{"id": "SC1"},
			{"id": "SC2"}
		] },

{"TCID":"TC1","TestCase":"Test Case One smoke regression",

		"scenarios":[
			{"id": "SC1"},
			{"id": "SC2"}
		] }

]



}

  */