package com.resttest.framework.excel.model;

public class Scenario {

	private String id;

	private String url;

	private String method;
	private String validate;

	private String command;
	private String responseattribute;

	private double expected;

	private String primary;
	Object payload;
	Object header;

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public Object getHeader() {
		return header;
	}

	public void setHeader(Object header) {
		this.header = header;
	}

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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getResponseattribute() {
		return responseattribute;
	}

	public void setResponseattribute(String responseattribute) {
		this.responseattribute = responseattribute;
	}

	public double getExpected() {
		return expected;
	}

	public void setExpected(double expected) {
		this.expected = expected;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}
}
