package com.resttest.framework.sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by sreepadbhagwat on 10/2/16.
 */
public class dummy {
    public static void main(String[] args) throws Exception {

        String pathToResourcesFolder = new String(dummy.class.getClassLoader().getResource("log4j.properties").getFile().toString());
        System.out.println(pathToResourcesFolder);
        URL url = dummy.class.getResource("/dependencies/chart/Chartjs/Chart.js");
        System.out.println(url);




    }
}
