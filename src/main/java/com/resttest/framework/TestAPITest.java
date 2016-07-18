package com.resttest.framework;

import com.resttest.framework.exceptions.RestAPIException;

/**
 * Created by Sumana on 7/15/2016.
 */
public class TestAPITest {

    public static void main(String[] args) throws RestAPIException {

        TestAPI test = new TestAPI();
        //System.out.println(test.testcase);

        test.getResponseJsonPath();
        System.out.println(test.getError());
        //test.getAPIHeaders();

    }

}
