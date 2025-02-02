package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.DesktopIconUI;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.Desktop;
import testBase.BaseClass;

public class ExtentReportManager_02 implements ITestListener {
  
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	String repName;
	public  void onTestStart(ITestResult result) {
	    // not implemented
		System.out.println("one test started");
		
	  }

	  /**
	   * Invoked each time a test succeeds.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SUCCESS
	   */
	  public void onTestSuccess(ITestResult result) {
	    // not implemented
		  System.out.println("one test success");
		  extentTest=extentReports.createTest(result.getTestClass().getName());
		  extentTest.assignCategory(result.getMethod().getGroups());
		  extentTest.log(Status.PASS, "Test case passed:" + result.getName());
	  }

	  /**
	   * Invoked each time a test fails.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#FAILURE
	   */
	  public  void onTestFailure(ITestResult result) {
	    // not implemented
		  System.out.println("one test failed");
		  extentTest=extentReports.createTest(result.getTestClass().getName());
		  extentTest.assignCategory(result.getMethod().getGroups());
		  extentTest.log(Status.FAIL, "Test case failed:" + result.getName());
		  extentTest.log(Status.INFO, "Test case failed casue is:" + result.getThrowable().getMessage());
		  //add screenshot
		  try {
			  String imgpath=new BaseClass().captureScreen(result.getName());
			  extentTest.addScreenCaptureFromPath(imgpath);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	  }

	  /**
	   * Invoked each time a test is skipped.
	   *
	   * @param result <code>ITestResult</code> containing information about the run test
	   * @see ITestResult#SKIP
	   */
	  public  void onTestSkipped(ITestResult result) {
	    // not implemented
		  System.out.println("one test skipped");
		  extentTest=extentReports.createTest(result.getTestClass().getName());
		  extentTest.assignCategory(result.getMethod().getGroups());
		  extentTest.log(Status.SKIP, "Test case skipped:" + result.getName());
		  extentTest.log(Status.SKIP, "Test case skipped casue is:" + result.getThrowable());
	  }
	  
	  /**
	   * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
	   * tag and calling all their Configuration methods.
	   *
	   * @param context The test context
	   */
	  public void onStart(ITestContext context) {
	    // not implemented
		  String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		  repName="Test-Report"+timestamp + ".html";
		  System.out.println(" classes execution inside this test tag started");
		  sparkReporter=new ExtentSparkReporter(".\\reports\\"+ repName);
		  
		  sparkReporter.config().setDocumentTitle("Automation Report");
		  sparkReporter.config().setReportName("Functional Testing");
		  sparkReporter.config().setTheme(Theme.DARK);
		  
		  extentReports=new ExtentReports();
		  extentReports.attachReporter(sparkReporter);
		  
		  extentReports.setSystemInfo("applciation", "opencart");
		  
		  //get testng xml contents
		  String os=context.getCurrentXmlTest().getParameter("os");
		  extentReports.setSystemInfo("Operating System:", os);
		  String currentbrowser=context.getCurrentXmlTest().getParameter("browser");
		  extentReports.setSystemInfo("Browser:", currentbrowser);
		  
		  List<String> includedgroups=context.getCurrentXmlTest().getIncludedGroups();
		  if (!includedgroups.isEmpty()) {
			  extentReports.setSystemInfo("Groups included:", includedgroups.toString());
		  }
	  }

	  /**
	   * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
	   * run and all their Configuration methods have been called.
	   *
	   * @param context The test context
	   */
	  public void onFinish(ITestContext context) {
	    // not implemented
		  System.out.println(" classes execution inside this test tag finished");
		  extentReports.flush();
		  
		  //launch report onFinish
		  String pathofExtentReport= System.getProperty("user.dir") + "\\reports\\" + repName;
		  File extentReportFile=new File(pathofExtentReport);
		 
		  try {
			  Desktop.getDesktop().browse(extentReportFile.toURI());
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		  
	  }

}
