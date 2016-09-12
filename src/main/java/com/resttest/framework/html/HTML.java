package com.resttest.framework.html;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by sreepadbhagwat on 9/9/16.
 */

public class HTML {

    static StringBuilder html = new StringBuilder();

    public static void before_1() throws IOException {
        html.append("<!DOCTYPE html PUBLIC '-//W3C/DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
        html.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
        html.append("<head>");
        html.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />'");
        html.append("<title>Report</title>");
        html.append("<link rel='stylesheet' href='/Users/sreepadbhagwat/OneStop/production/resttest/rest-test/src/main/java/com/resttest/framework/html/main.css' >");
        html.append("<style type='text/css'>");
        html.append("</style>");
    }

    public static void piechartjavascript_2(int totaltestcases, int tcspassed, int tcsfailed, int totalscenarios, int scenariospassed, int scenariosfailed) {

        int pctpassed = (tcspassed / totaltestcases) * 100;
        int pctfailed = (tcsfailed / totaltestcases) * 100;
        int pctskipped = ((totaltestcases - tcspassed - tcsfailed) / totaltestcases) * 100;

        html.append("<script type='text/javascript'' src='https://www.gstatic.com/charts/loader.js'></script>");
        html.append("<script type='text/javascript'>");
        html.append("google.charts.load('current',{'packages':['corechart']});");
        html.append("google.charts.setOnloadCallback(drawSarahChart);");
        html.append("google.charts.setOnloadCallback(drawAnthonyChart);");
        html.append("function drawSarahChart(){");
        html.append("var data = new google.visualization.DataTable();");
        html.append("data.addColumn('string','status');");
        html.append("data.addColumn('number','tccount');");

        html.append("data.addRows([");
        html.append("['', 0],");
        html.append("['', 0],");
        html.append("['skip'," + pctskipped + "],");
        html.append("['pass'," + pctpassed + "],");
        html.append("['', 0],");
        html.append("['', 0],");
        html.append("['', 0],");
        html.append("['', 0],");
        html.append("['fail'," + pctfailed + "],");
        html.append("]);");
        html.append("var options = {title:'TestCase Execution Status',");
        html.append("width:300,");
        html.append("height:200};");
        html.append("var chart new google.visualization.Piechart(document.getElementById('testcase_chart_div'));");
        html.append("chart.draw(data,options);");
        html.append("}");

        html.append("function drawAnthonyChart(){");
        html.append("var data = new google.visualization.DataTable();");
        html.append("data.addColumn('string','status');");
        html.append("data.addColumn('number','tscount');");
        html.append("data.addRows([");
        html.append("['', 0],");
        html.append("['', 0],");
        html.append("['skip'," + pctskipped + "],");
        html.append("['pass'," + pctpassed + "],");
        html.append("['', 0],");
        html.append("['', 0],");
        html.append("['', 0],");
        html.append("['', 0],");
        html.append("['fail'," + pctfailed + "],");
        html.append("]);");
        html.append("var options = {title:'Scenario Execution Status',");
        html.append("width:300,");
        html.append("height:200};");
        html.append("var chart new google.visualization.Piechart(document.getElementById('scenario_chart_div'));");
        html.append("chart.draw(data,options);");
        html.append("}");

        html.append("</script>");
        html.append("</head>");
        html.append("<body>");

    }

    public static void clear() {
        html = null;
        html = new StringBuilder();
    }

    public static void buildstatusbox_3() {
        //TODO
    }

    public static void reportheader_4() {

        html.append("<table id='box-table-a' summary='Report'>");
        html.append("<thead>");
        html.append("<tr>");
        html.append("<th scope='col'>FileName</th>");
        html.append("<th scope='col'>TestID</th>");
        html.append("<th scope='col'>TestName</th>");
        html.append("<th scope='col'>ScenarioID</th>");
        html.append("<th scope='col'>ScenarioName</th>");
        html.append("<th scope='col'>URL</th>");
        html.append("<th scope='col'>Response Time</th>");
        html.append("<th scope='col'>Total Time</th>");
        html.append("<th scope='col'>Status</th>");
        html.append("</tr>");
        html.append("</thead>");
        html.append("<tbody>");


    }

    public static void createreportrow() {
        html.append("<tr>");
    }

    public static void endreportrow() {
        html.append("</tr>");
    }

    public static void adddatacolumn(String data) {
        html.append("<td>");
        html.append(data);
        html.append("</td>");
    }

    public static void endreport_5() {
        html.append("</tbody>");
        html.append("</table>");
        html.append("</div>");
        html.append("</td>");
        html.append("</tr>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");
    }

    public static void after_6() throws IOException {
        String currentdir=System.getProperty("user.home");
        FileWriter writer = new FileWriter(currentdir+"/report.html");
        writer.write(html.toString());
        writer.close();
    }

}
