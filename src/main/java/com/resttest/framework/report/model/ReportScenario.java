package com.resttest.framework.report.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jayway.restassured.response.Headers;
import com.resttest.framework.json.model.ResultEnum;

/**
 * Created by sreepadbhagwat on 9/25/16.
 */
public class ReportScenario {


    // Fields from json
    private String id;
    private String url;
    private String command;
    private String expected;
    private String validate;
    private String method;
    private String primary;
    private String header;
    private String payload;
    private String responseattribute;
    private String name;




    // future
    private String[] tags;

    // Runtime
    private ResultEnum result;
    private String error;
    private String actual;
    private long responsetime;
    private String responseheader;
    private double scenariostarttime;
    private double scenarioendtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getResponseattribute() {
        return responseattribute;
    }

    public void setResponseattribute(String responseattribute) {
        this.responseattribute = responseattribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public ResultEnum getResult() {
        return result;
    }

    public void setResult(ResultEnum result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public long getResponsetime() {
        return responsetime;
    }

    public void setResponsetime(long responsetime) {
        this.responsetime = responsetime;
    }

    public String getResponseheader() {
        return responseheader;
    }

    public void setResponseheader(String responseheader) {
        this.responseheader = responseheader;
    }

    public double getScenariostarttime() {
        return scenariostarttime;
    }

    public void setScenariostarttime(double scenariostarttime) {
        this.scenariostarttime = scenariostarttime;
    }

    public double getScenarioendtime() {
        return scenarioendtime;
    }

    public void setScenarioendtime(double scenarioendtime) {
        this.scenarioendtime = scenarioendtime;
    }



}
