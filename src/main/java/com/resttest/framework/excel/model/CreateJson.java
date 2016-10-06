package com.resttest.framework.excel.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CreateJson {

	public static void main(String[] args) throws Exception {

		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");

		WorkBookRead read = (WorkBookRead) context.getBean("read");

		context.close();

		Gson gson = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.setPrettyPrinting().serializeNulls().create();

		String json = gson.toJson(read.getMapOfExcelBaseObjects());
		System.out.printf(json);


	}
}
