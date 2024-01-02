package api.utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Listener records the event in your script execution and it is a interface which is available in testNG framework

public class ExtentReportManager implements ITestListener { //Listener is used for post test execution results generation like pass or fail for test cases 

	public ExtentSparkReporter extentsparkreporter; //responsible for the UI of report
	public ExtentReports extent=new ExtentReports(); //responsible for the common details of report.
	public ExtentTest test; // responsible for monitoring the test result and recording it.
	String reportname;
	
	public  void onstart(ITestContext testContext) { //create report UI and add all necessary details for the report
		
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //add time stamp for report when it is generated
		
		reportname="Test-Report"+timestamp+".html"; //report name 
		
		extentsparkreporter= new ExtentSparkReporter("C:\\Users\\emb-saurkum\\eclipse-workspace\\RESTAPITestAutomation\\reports"+reportname); //report location
		
		extentsparkreporter.config().setDocumentTitle("RestAPIDemoReport");
		extentsparkreporter.config().setReportName("Users pet store");
		extentsparkreporter.config().setTheme(Theme.DARK);
		//add common details of report like environment, os,user,etc.
		extent= new ExtentReports();
		extent.attachReporter(extentsparkreporter);
		extent.setSystemInfo("Application", "Pet store user information");
		extent.setSystemInfo("Operation system", System.getProperty("os.name"));
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Saurab");
	}
	
	public void onTestSuccess(ITestResult iTestResult) { // on test success mark the test as passed
		test=extent.createTest(iTestResult.getName());
		test.assignCategory(iTestResult.getMethod().getGroups());
		test.createNode(iTestResult.getName());
		test.log(Status.PASS, "Test passed");
	}
	
	public void onTestFailure(ITestResult iTestResult) {// on test failure mark the test as failed and log the error.
		test=extent.createTest(iTestResult.getName());
		test.assignCategory(iTestResult.getMethod().getGroups());
		test.createNode(iTestResult.getName());
		test.log(Status.FAIL, "Test failed");
		test.log(Status.FAIL, iTestResult.getThrowable().getMessage());
		
	}
	
	public void onTestSkipped(ITestResult iTestResult) {// on test skipped mark the test as skipped
		test=extent.createTest(iTestResult.getName());
		test.assignCategory(iTestResult.getMethod().getGroups());
		test.createNode(iTestResult.getName());
		test.log(Status.SKIP, "Test skipped");
		test.log(Status.SKIP, iTestResult.getThrowable().getMessage());
		
	}
	
	public void onTestFinished(ITestContext context) {//finally flush the report once test is finished.
		extent.flush();
	}
}
