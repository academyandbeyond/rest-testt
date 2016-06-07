package com.resttest.framework;

public class Capabilities {
	
	private String folderpath;
	private String environment;
	
	public void setFolderPath(String folderpath){
		this.folderpath=folderpath;
	}
	
	public String getFolderPath(){
		return folderpath;
	}
	
	public void setEnvironment(String environment){
		this.environment=environment;	
	}
	
	public String getEnvironment(){
		return environment;
	}

}
