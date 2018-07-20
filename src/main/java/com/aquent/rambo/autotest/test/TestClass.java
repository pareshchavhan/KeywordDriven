package com.aquent.rambo.autotest.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aquent.rambo.autotest.utils.Resources;

public class TestClass extends Resources{

	@BeforeClass
	public void initBrowser() throws IOException
	{
		
			Initialize();
			/*dr = new ChromeDriver();
			driver = new EventFiringWebDriver(dr);
			driver.manage().window().maximize();*/
		
	}

	@Test
	public void TestCaseController() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,  IOException, InvocationTargetException {
		
		//String startTime = TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa");
		//ReportUtil.startTesting(System.getProperty("user.dir")+"//src//test//java//com//companyname//projectname//Reports//index.html", startTime, "Test", "1.5");
		//ReportUtil.startSuite("Suite1");
		String TCStatus="Pass";
		
		// loop through the test cases
		for(int TC=2;TC<=SuiteData.getRowCount("TestCases");TC++) {
			
			
			
			String TestCaseID = SuiteData.getCellData("TestCases", "TCID", TC);
			String RunMode = SuiteData.getCellData("TestCases", "RunMode", TC);
			
			if(RunMode.equals("Y")) {
				String TSStatus="Pass";
				System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
				dr = new ChromeDriver();
				driver = new EventFiringWebDriver(dr);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				int rows = TestStepData.getRowCount(TestCaseID);
				if(rows<2) { 
					rows=2;
				}
				
				// loop through test data
				for(int TD=2;TD<=rows;TD++ ) {
				
					// loop through the test steps
					System.out.println("SuiteData.getRowCount(TestCaseID)"+SuiteData.getRowCount(TestCaseID));
					
					for(int TS=2;TS<=SuiteData.getRowCount(TestCaseID);TS++) {
						
						keyword =SuiteData.getCellData(TestCaseID, "Keyword", TS);
						webElement = SuiteData.getCellData(TestCaseID, "WebElement", TS);
						ProceedOnFail = SuiteData.getCellData(TestCaseID, "ProceedOnFail", TS);
						TSID = SuiteData.getCellData(TestCaseID, "TSID", TS);
						Description = SuiteData.getCellData(TestCaseID, "Description", TS);
						
						TestDataField = SuiteData.getCellData(TestCaseID, "TestDataField", TS);
						
						
						TestData = TestStepData.getCellData(TestCaseID, TestDataField, TD);	
						//System.out.println(TestData.toString());
						
						if(TestDataField.equals("email")){
							TestData = "test"+System.currentTimeMillis()+"@gmail.com";
						}
						
						
						Method method = Keywords.class.getMethod(keyword);	
						TSStatus = (String) method.invoke(method);
						
						if(TSStatus.contains("Failed")) {
							// take the screenshot
							String filename = TestCaseID+"["+(TD-1)+"]"+TSID+"["+TestData+"]";
							//TestUtils.getScreenShot(filename);
							
							TCStatus=TSStatus;
							// report error
							//ReportUtil.addKeyword(Description, keyword, TSStatus, "Screenshot/"+filename+".jpg");

							if(ProceedOnFail.equals("N")) {
								break;
							}
						} else {
							//ReportUtil.addKeyword(Description, keyword, TSStatus, null);
						}
						
						/*
						if(ProceedOnFail.equals("N")) {
							break;
						}*/
						
						
					}
					//ReportUtil.addTestCase(TestCaseID, startTime, TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa"), TCStatus);
					driver.quit();
				}
			}else {
				// skip the test case
				//ReportUtil.addTestCase(TestCaseID, startTime, TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Skipped");
				driver.quit();
			}
		}

		//ReportUtil.endSuite();
		//ReportUtil.updateEndTime(TestUtils.now("dd.MMMM.yyyy hh.mm.ss aaa"));
		
	}

}
