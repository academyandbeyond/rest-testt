package com.resttest.framework.html;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by sreepadbhagwat on 9/9/16.
 */

public class HTMLBS {

    static StringBuilder html = new StringBuilder();

    public static void html_header_1() throws IOException {

        html
                .append("<!DOCTYPE html>\n")
                .append("<html lang='en'>\n")
                .append("<head> <meta charset=\"UTF-8\"> <title>Title</title>\n")
                .append("<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">\n")
        .append("<link rel='stylesheet' href='/Users/sreepadbhagwat/OneStop/production/resttest/rest-test/src/main/java/com/resttest/framework/html/htmlbs.css' >\n")
        .append("  <script src=\"/Users/sreepadbhagwat/OneStop/production/resttest/rest-test/src/main/java/com/resttest/framework/html/Chart.bundle.js\"></script>\n")
        .append(" </head> <body>\n");



    }




    public static void html_heading_2(){

        html
                .append("<div class=\"container\">\n")
                .append("<div class=\"page-header\" >\n")
                .append("<h1>RestTest Report</h1>\n")
                .append(" </div>");
    }

    public static void html_statuscontainer_3(int totaltestcases, int tcspassed, int tcsfailed, int totalscenarios, int scenariospassed, int scenariosfailed) {

        int pctpassed = (tcspassed / totaltestcases) * 100;
        int pctfailed = (tcsfailed / totaltestcases) * 100;
        int pctskipped = ((totaltestcases - tcspassed - tcsfailed) / totaltestcases) * 100;

        html
              //  .append("<div class=\"container\">\n")

                //  .append("<div class=\"jumbotron\">\n")

                .append("<div class='well well-sm' >\n")
                /*
                .append("<table id=\"\" style='width: auto;' class=\"table table-condensed table-hover table-bordered table-responsive\" width=\"80%\" cellspacing=\"0\">\n")
                .append("<thead> \n")

                // Row 1
                .append("<tr >\n")

              //  .append("<th class=\"width20 dispnone\"> \n")
                .append("<th>\n")

                .append("<button type=\"button\" class=\"btn btn-primary btn-sm\">Testcases<span class=\"badge\">" + totaltestcases + "</span></button>\n")
                .append("</th>\n")

                .append("<th background='white'>\n")
                .append("</th>\n")
                .append("<th>\n")

                .append("<button type=\"button\" class=\"btn btn-primary btn-sm\">Scenarios <span class=\"badge\">" + totalscenarios + "</span></button>\n")
                .append("</th>\n")

                .append("</tr>\n")

                // PAssed
                .append("<tr>\n")
                .append("<th>\n")
                .append("<button type=\"button\" class=\"btn btn-success btn-sm\">Passed <span class=\"badge\">" + tcspassed + "</span></button>\n")
                .append("</th>\n")

                .append("<th>\n")
                .append("</th>\n")

                .append("<th>\n")
                .append("<button type=\"button\" class=\"btn btn-success btn-sm\">Passed <span class=\"badge\">" + scenariospassed + "</span></button>\n")

                .append("</th>\n")

                .append("</tr>\n")

                // FAILED

                // PAssed
                .append("<tr>\n")
                .append("<th>\n")
                .append(" <button type=\"button\" class=\"btn btn-danger btn-sm\">Failed <span class=\"badge\">" + tcsfailed + "</span></button>\n")                .append("</th>\n")

                .append("<th>\n")
                .append("</th>\n")

                .append("<th>\n")
                .append(" <button type=\"button\" class=\"btn btn-danger btn-sm\">Failed <span class=\"badge\">" + scenariosfailed + "</span></button>\n")

                .append("</th>\n")

                .append("</tr>\n")


           //
             //   .append(" <button type=\"button\" class=\"btn btn-danger\">Failed <span class=\"badge\">" + tcsfailed + "</span></button>\n")
                .append("</table>")
             //   .append("</div>")
             */



                /*

        .append("<div class=\"row\">")
                .append("<div class=\"col-xs-9\">.col-xs-9</div>")
                .append("<div class=\"col-xs-6 col-sm-6\"  style='width:50% ; height:150px'>")
                .append("<div style='width:50%;  display: inline-block;'>")
                .append("<canvas id=\"testcase-area\" height=\"80\" /> \n")
                .append("</div>")

                .append("</div>")

                .append("<div class=\"col-xs-6\">.col-xs-6<br>Subsequent columns continue along the new line.</div>")
                .append("</div>")


                 */





   .append("<div>\n")



                        .append("<div style='width:50%;  display: inline-block;'>")
                        .append("<canvas id=\"testcase-area\" height=\"80\" /> \n")
                        .append("</div>")




                .append("<div style='width:50%; display: inline-block;'>")
                .append("<canvas id=\"scenario-area\" height=\"80\" />\n")
                .append("</div>")




               // .append("</div>")


                      .append(" </div>\n")
                   .append("</div>")
                .append(" </div>\n");
    }


    public static void html_table_header_4(){

        html
                .append("<div class=\"container\">\n")
                .append("<div class=\"table-responsive tableStyle\">\n")
                .append("<table id=\"\" class=\"table table-condensed table-hover table-bordered table-responsive\" width=\"100%\" cellspacing=\"0\">\n")
                .append("<thead> \n    ")
                .append("<tr class=\"info\">\n")
                .append("<th class=\"width50 \">##</th>\n")
                .append(" <th>File</th>\n")
                .append(" <th>TCID</th>\n")
                .append("<th>TestName</th>\n")
                .append("<th>SCID</th>\n")
                .append("<th>ScenarioName</th>\n")
                .append("<th>URL</th>\n")
                .append("<th>ResponseTime</th>\n")
                .append("<th>TotalTime</th>\n")
                .append(" <th>Status</th>\n")
                .append("</tr>\n")
                .append(" </thead>\n")
                .append(" <tbody>");

    }

    public static void html_table_row_5(String result, String scenario_num){

        if (result.equalsIgnoreCase("pass")) {
            html
                    // btn-danger, btn-warning btn-info

                    .append("<tr class=\"success\" class=\"active\" data-toggle=\"collapse\" data-target=\"#"+scenario_num+"\" class=\"accordion-toggle\" style=\"cursor:pointer\">\n\n");

                   // .append("<td><span class=\"icon_expand\"></span> <span class=\"icon_green\"></span></td>");


        } else if(result.equalsIgnoreCase("fail")){
            html
                    .append("<tr class=\"danger\" class=\"active\" data-toggle=\"collapse\" data-target=\"#"+scenario_num+"\" class=\"accordion-toggle\" style=\"cursor:pointer\">\n\n");

                  //  .append("<td><span class=\"icon_expand\"></span> <span class=\"icon_green\"></span></td>");
        } else {
            html
                    .append("<tr class=\"active\" class=\"active\" data-toggle=\"collapse\" data-target=\"#"+scenario_num+"\" class=\"accordion-toggle\" style=\"cursor:pointer\">\n\n");

                  //  .append("<td><span class=\"icon_expand\"></span> <span class=\"icon_green\"></span></td>");
        }




                // Other classes danger,warning,active






    }

    public static void html_table_data_fill_6(String data){
        html.append("<td>"+data+"</td>");
    }

    public static void html_table_status_fill_7(String data){


        if (data.equalsIgnoreCase("pass")) {
            html
                    // btn-danger, btn-warning btn-info

                    .append("<td><button class=\"btn btn-success btn-xs\">" + data + "</button></td>\n")

                    .append("</tr>");
        } else if(data.equalsIgnoreCase("fail")){
            html
                    .append("<td><button class=\"btn btn-danger btn-xs\">" + data + "</button></td>\n")
                    .append("</tr>");
        } else {
            html
                    .append("<td><button class=\"btn btn-warning btn-xs\">" + data + "</button></td>\n")
                    .append("</tr>");
        }

    }

    public static void html_table_row_collapse_8(String scenario_num, String exptd_statuscode, String actual_statuscode, String error, String response){
        html

                .append("<tr class=\"active collapse.in \">\n")
                .append("<td colspan=\"11\"><div class=\"accordion-body collapse\" id=\""+scenario_num+"\">\n")
               // .append("<div class=\"bs-callout bs-callout-info\" style=\"margin:0px;\">\n")
                //.append("<h4><i class=\"fa fa-info\"></i>&nbsp;&nbsp;Detalles</h4>\n")
                //.append("<p> Details for row #"+scenario_num+" </p>\n")
                //.append("</div>\n")
               // .append("<div class='well well-vsm text-center offset4 span4' >\n")

                .append("<div class='row'>")
                .append("<div class='col-md-2'> <b>Status Code </b> <br> Expected ["+exptd_statuscode+"] <br> Actual ["+actual_statuscode+"]</div>")
               // .append("<div class='col-md-1.5'>Expected ["+exptd_statuscode+"]</div>")
                //.append("<div class='col-md-1.5'>Actual ["+actual_statuscode+"]</div>")



                .append("</div>")


                .append("<div class='row'>")
                .append("<div class='col-md-6'> <b>Error </b> <br> ["+error+"]</div>")
                .append("</div>")

                .append("<div class='row'>")
                .append("<div class='col-md-6'> <b>Response </b> <br> ["+response+"]</div>")
                .append("</div>")

                .append("<div class='row'>")

                .append("</div>")

                .append(" <div class='row'>")

                .append("</div>")

                /*
                .append("<div class='row'>")
                .append("<div class='square-box  col-md-4'>")
                .append("  <div class='square-content'>Status Code : Actual["+exptd_statuscode+"] Expected["+actual_statuscode+"]</div>")
                .append("</div>\n")


                .append("</div>\n")
                */



              //  .append("</div>\n")

                .append("</div></td>\n")
                .append("</tr>");





    }


    public static void html_table_end_9(int totaltestcases, int tcspassed, int tcsfailed, int totalscenarios, int scenariospassed, int scenariosfailed){

        int scenarios_ne=totalscenarios-(scenariospassed+scenariosfailed);
        int tcs_ne=totaltestcases-(tcspassed+tcsfailed);
        html
                .append("</tbody>\n")
                .append("</table>\n")
                .append("</div>\n")
                .append("</div>\n")
                .append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>\n")
                .append("<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\n")
                .append("<script>\n")



                .append("var testcase = {")
                .append(" type: 'pie',")
                .append("   data: {")
                .append("datasets: [{")
                .append("   data: [")
                .append(" "+tcsfailed+",")
                .append("          "+tcspassed+",")
                .append("          "+tcs_ne+",")

                .append("   ],")
                .append("   backgroundColor: [")
                .append("  \"#F7464A\",")
                .append("          \"green\",")
                .append("          \"#FDB45C\",")

                .append(" ],")
                .append(" }, ],")
                .append(" labels: [")
                .append("   \"Fail\",")
                .append("    \"Pass\",")
                .append("      \"NE\",")

                .append("]")
                .append("  },")

                .append("  };")




                .append("var scenario = {")
                .append(" type: 'pie',")
                .append("   data: {")
                .append("datasets: [{")
                .append("   data: [")
                .append("   "+scenariosfailed+",")
                .append(""+scenariospassed+",")
                .append(""+scenarios_ne+",")

                .append("   ],")
                .append("   backgroundColor: [")
                .append("  \"#F7464A\",")
                .append("          \"green\",")
                .append("          \"#FDB45C\",")

                .append(" ],")
                .append(" }, ],")
                .append(" labels: [")
                .append(" 'Fail',")
                .append("    \"Pass\",")
                .append("      \"NE\",")

                .append("]")
                .append("  },")

                .append("  };")

                .append("  window.onload = function() {")
                                .append("     var ctx = document.getElementById(\"testcase-area\").getContext(\"2d\");")
                .append("     window.myPie = new Chart(ctx, testcase);")

                .append("     var ctx = document.getElementById(\"scenario-area\").getContext(\"2d\");")
               // .append("ctx.fillText(\"Text Here\";")
                .append("     window.myPie = new Chart(ctx, scenario);")

                        .append("  };")



        .append("</script>\n")
                .append("</body>\n")
                .append("</html>");

    }

    public static void clear() {
        html = null;
        html = new StringBuilder();
    }





    public static void html_save_report() throws IOException {
        String currentdir=System.getProperty("user.home");
        FileWriter writer = new FileWriter(currentdir+"/resttest.html");
        writer.write(html.toString());
        writer.close();
    }

}
