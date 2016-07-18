package com.resttest.framework.api;

import com.resttest.framework.json.model.PrimaryData;
import com.resttest.framework.json.model.ResultEnum;
import com.resttest.framework.json.model.Scenario;
import com.resttest.framework.api.Util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Created by sumana on 7/10/2016.
 */

public class Post {
private Util util;

    public Post(String testcase){
        util= new Util(testcase);
    }

    public Scenario executePOST(Scenario currentscenario, String url){
        String validator = currentscenario.getValidate();
        util.test.post(url);
        try {
            switch(validator){
                case "status":
                    System.out.println("1111 : " +currentscenario.getID() );
                    currentscenario=util.executeStatus(currentscenario);
                    break;
            }
        }catch(Exception e){currentscenario.setError(Common.getStackTrace(e));return currentscenario;}
        return currentscenario;
    }



    public Scenario executePostD(Scenario currentscenario,ArrayList<PrimaryData> primarydata, String url){
        Scenario resultscenario=null;
        String headervalue;
        String validator=currentscenario.getValidate();
        String dependencyvalue;
        util.test.post(url);

        //System.out.println("Inside GetD : 1");
        // Get the dependency info
        String[] primaryscenario= currentscenario.getDependent();

        //System.out.println("Inside executeGetD - Length - "+primaryscenario.length +" "+primaryscenario[0] );
        // TODO if GET-D is null

        // 1. get dependency value
        // 2. pass it as parameter

        // If
        for (PrimaryData pd : primarydata) {
            System.out.println(pd.getTCID().concat(pd.getTSID()));
            if(  ( pd.getTCID().concat(pd.getTSID()).equalsIgnoreCase(primaryscenario[0]) )   ) {
                System.out.println("Inside GetD : 2");
                // print primary data here like headers and response body
                System.out.println(pd.getJsonPath().toString());
            }
        }

        try {
            switch(validator){
                case "status":
                    currentscenario=util.executePost(currentscenario);
                    break;

            }

			/*if (currentscenario.getValidate().equalsIgnoreCase("status")){
				currentscenario=executeStatus(currentscenario);
				//return currentscenario;
			}*/
        }catch(Exception e){currentscenario.setError(Common.getStackTrace(e));return currentscenario;}
        return currentscenario;
    }



}
