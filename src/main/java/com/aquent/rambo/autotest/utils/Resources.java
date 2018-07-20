package com.aquent.rambo.autotest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.aquent.rambo.autotest.data.Xls_Reader;


public class Resources {
	
	
	public static WebDriver dr; 
	public static EventFiringWebDriver driver; 
	public static Properties Repository = new Properties();
	public static Properties Config = new Properties();
	public static Properties AppText = new Properties();
	public static Xls_Reader TestStepData;
	public static Xls_Reader SuiteData;

	public static String keyword;
	public static String webElement;
	public static String TestDataField;
	public static String TestData;
	public static String ProceedOnFail;
	public static String TSID;
	public static String Description;
	public static File f ;
	public static FileInputStream FI;
	
	public static void Initialize() throws IOException {
		
		
		TestStepData = new Xls_Reader("TestData/TestSuiteData.xls");
		System.out.println(TestStepData);
		SuiteData = new Xls_Reader("TestData/TestSuite1.xls");
		
		System.out.println(System.getProperty("user.dir"));
		
		f = new File("TestData/login.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
		//FI.close();
		
		/*f = new File(System.getProperty("user.dir")+"//src//test//java//com//companyname//projectname//repository//creatAnAccount.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);*/
		
	}
	private final static Properties properties = new Properties();
	static {
		
		try {
			FileInputStream in = new FileInputStream("TestData/login.properties");
			properties.load(in);
			in.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		
		return properties.getProperty(key);
	}
	public static void main(String[] args) throws IOException {
		Initialize();	
	}
	
}
