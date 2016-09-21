package com.resttest.framework.Excel.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TestCase {

	private static  String key;
	private  String testCase;	
	@SerializedName("scenarios")
	private  ArrayList<Object> listOfScenario;
	private transient Scenario scenario;

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scObj) {
		scenario = scObj;
	}

	public String getTestCase() {
		return testCase;
	}

	public void setTestCase(String testCase) {
		this.testCase = testCase;
	}

	public ArrayList<Object> getlistOfScenario() {
		if (null == listOfScenario) {
			listOfScenario = new ArrayList<Object>();
		}
		return listOfScenario;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
	 this.key = key;
	}
}
