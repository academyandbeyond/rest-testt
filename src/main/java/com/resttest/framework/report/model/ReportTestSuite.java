package com.resttest.framework.report.model;



import java.util.ArrayList;

/**
 * Created by sreepadbhagwat on 9/25/16.
 */
public class ReportTestSuite {

    private ArrayList<ReportTestCase> reporttestSuite = new ArrayList<ReportTestCase>();


    public void addTestSuite(String filename,ArrayList<ReportTestCase> newTestSuite){

        reporttestSuite.addAll(newTestSuite);
    }

    public void addTestCase(ReportTestCase testCase) {
        reporttestSuite.add(testCase);
    }

    public ArrayList<ReportTestCase> getTestCases() {
        return reporttestSuite;
    }

}
