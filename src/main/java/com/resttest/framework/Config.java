package com.resttest.framework;

import java.io.FileWriter;
import java.io.IOException;

public class Config {

	static StringBuilder html = new StringBuilder();
	
	public static void before() throws IOException{
		
		html.append("<html><body>");
		
		
		
	}
	
	public static void writehtmlcolor(String color,String text){
		
		if (color.equalsIgnoreCase("red")){
			html.append("<font color='red>"+text+"</font>");
			html.append("        ");
		}
	}
	
	public static void writehtml(String text){
		html.append(text);
		html.append("        ");
	}
	
	public static void after() throws IOException{
		html.append("</body></html>\n");
		String currentDir = System.getProperty("user.home");
        System.out.println("Current dir using System:" +currentDir);
		FileWriter writer = new FileWriter(currentDir+ "/report.html");
		writer.write(html.toString());
		writer.close();
	}
	
}
