package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBases.ClassBase;

public class ExtentReportUtility implements ITestListener{

	public ExtentSparkReporter sparkReport;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context) {
		//to get date and time we a special class classe
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");  //Creates a formatter for date & time.
		Date dt = new Date();      //Gets the current system date and time.
		String currentdatetimeStamp = df.format(dt);   //we can write this in simple one line
		//Converts the date into a formatted string using the pattern.
		*/
		
		String currentdatetimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + currentdatetimeStamp + ".html";  //Report Name will create 
		sparkReport =new ExtentSparkReporter(".\\reports\\" + repName);  //folder named reports inside your project
		
		sparkReport.config().setDocumentTitle("OpenCart Automation Report");  //Document Title (Browser Tab)
		sparkReport.config().setReportName("OpenCart Functional Report");  //Report Name (Inside Report UI Header)
		sparkReport.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		//This is the main engine of Extent Reports.
		//It collects all test results, logs, screenshots, etc.
		
		extent.attachReporter(sparkReport); //Connects your ExtentSparkReporter
		extent.setSystemInfo("Application", "OpenCart");  //Adds extra details in the report.
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customers");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = context.getCurrentXmlTest().getParameter("OS");  //Getting value from TestNG XML
		//context → comes from ITestContext (TestNG interface)
		extent.setSystemInfo("Operating System", os);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups(); //Fetches groups you included in testng.xml
		if(!includeGroups.isEmpty()) {
			//Avoids showing blank info in report
			extent.setSystemInfo("Groups", includeGroups.toString());  //toString() converts list → string
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());  //Creates a new test in Extent Report. gets class name : TC002_LoginTest
		//from the result which class we will execute
		test.assignCategory(result.getMethod().getGroups());  //Gets groups defined in your test:
		test.log(Status.PASS, result.getName()+ "got Succesfully executed"); //gets method name : verifyLogin()
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage()); //Returns the exception/error object that caused the test to fail
		
		try {
			String imgpath = new ClassBase().captureScreen(result.getName());  //Creates a new object of your base class, Calls captureScreen() method
			//Calls your screenshot method //result.getName() → test method name
			test.addScreenCaptureFromPath(imgpath);  //Adds screenshot to Extent Report
		}
		catch (IOException e1) {
			e1.printStackTrace();  //It prints error in console, helps debigging
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName; //Builds the full path of your report file
		//System.getProperty("user.dir") → your project folder
		File extentReport = new File(pathOfExtentReport);  //Represents the report file in Java
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); //Converts file → URI (file link)
			//Opens it using default browser
		}
		catch (IOException e) {
			e.printStackTrace();  //Prints error in console
		}
	}
}
