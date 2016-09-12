package com.resttest.framework;

/**
 * Created by sumana on 7/11/2016.
 */

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import com.jayway.restassured.specification.RequestSpecification;
import com.resttest.framework.json.model.JsonConstants;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JsonUtil {


    public static String giveElement(JsonElement jsonelement){
        Gson gson = new Gson();
        return gson.toJson(jsonelement);
    }

    public static JsonElement getBodyAsElement(ResponseBody body){
        JsonParser parser = new JsonParser();
        Object jsonelement = new Object();
        if (body!=null){
            jsonelement=parser.parse(new StringReader(body.asString()));
        }
        return (JsonElement)jsonelement;
    }

    public static RequestSpecification specBuilder(String headers, String payload){
        RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
        RequestSpecification requestspec=null;
        Map<String,String> headersmap = new Gson().fromJson(headers, new TypeToken<HashMap<String,String>>() {}.getType());

        if (payload!=null & payload!="" & headersmap.size()>0){
            requestspecbuilder.addHeaders(headersmap).setBody(payload);
            requestspec=requestspecbuilder.build();
        }else if((payload==null || payload=="") & headersmap.size()>0){
            requestspecbuilder.addHeaders(headersmap);
            requestspec=requestspecbuilder.build();
        }
        return requestspec;
    }

    public static Response getResponseBodyWhenContent(String header, String payload, String url, String method){
       Map<String,String> headersmap=null;
        Response response;
        boolean headerexists=false;
        boolean payloadexists=false;

        if (header!=null & header!=""){
            headersmap = new Gson().fromJson(header,new TypeToken<HashMap<String, String>>() {}.getType());
            headerexists=true;
        }

        if (payload!=null & payload!=""){
            payloadexists=true;
        }

        if(headerexists & payloadexists){
            response=RestAssured.given().headers(headersmap).body(payload).post(url);
        }else if(headerexists & payloadexists==false){
            response=RestAssured.given().headers(headersmap).post(url);
        }else {
            response=RestAssured.given().post(url);
        }

        return response;
    }

    public static JsonElement getJsonElementValue(JsonElement jsonelement, String attribute){

        if((jsonelement).isJsonObject()){
            Set<Map.Entry<String, JsonElement>> entryset = ((JsonObject)jsonelement).entrySet();
            if (entryset!=null){
                for(Entry<String, JsonElement> en : entryset){
                    if(en.getKey().equals(attribute)|| attribute==""){
                        return en.getValue();
                    }
                }
            }
        }
        return null;
    }

    public static String getResponseStringWhenContent(String header, String payload, String url, String method, String attribute){
        String jsonstring=null;
        ResponseBody responsebody = getResponseBodyWhenContent(header,payload,url,method);
        JsonElement jsonelementbody = getBodyAsElement(responsebody);

        if(attribute!="" & attribute!=null){
            JsonElement jsonelement = getJsonElementValue(jsonelementbody,attribute);
            jsonstring = jsonelement.toString();
        }else{
            Gson gson = new Gson();
            jsonstring = gson.toJson(jsonelementbody);
        }

        return jsonstring;
    }






}
