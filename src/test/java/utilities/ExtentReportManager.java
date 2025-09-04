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

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testcontext)
	{
		//Timestamp of Report
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName ="Test-Report-"+timestamp+".html";
		//Specified location of Report which will be generated
		sparkReporter=new ExtentSparkReporter(".\\Reports\\"+repName);
		//Title of the Report
		sparkReporter.config().setDocumentTitle("ORANGEHRM AUTOMATION REPORT");
		sparkReporter.config().setReportName("FUNCTIONAL TESTING");
		sparkReporter.config().setTheme(Theme.STANDARD);	
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("APPLICATION", "SCAN ONLINE");
		extent.setSystemInfo("MODULE", "END TO END TESTING");
		extent.setSystemInfo("USERNAME",System.getProperty("user.name"));
		extent.setSystemInfo("ENVIRONMENT", "QA");
		//Getting data from XML
		String os=testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		//If Group run
		List<String>includedGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
				//To display groups in report
		test.assignCategory(result.getMethod().getGroups());	
		test.log(Status.PASS,result.getName()+"got successfully executed");
	}
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+"got Failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			 
		String imgPath=new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		}catch(IOException e1)
		{
			e1.printStackTrace();
		}	
	}
	public void onTestSkipped(ITestResult result)
	{
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"Got Skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
		
		//This is to automatically open the report in the URI after the exectution,this is not mandatory,you can comment this also
		String pathExtentReport = System.getProperty("user.dir")+"\\Reports\\"+repName;
		File extentReport=new File(pathExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		/*try {
		URL url=new URL("file:///"+System.getProperty("user.dir")+"\\Reports"+repName);
		//REMEMBER:TO RUN THIS EMAIL CODE,Email chages needed
		//creating the email message
		ImageHtmlEmail email=new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");//thi swill work for gmail id,if you r working in any company then here corporate Id
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("yuvrajbhamare11@gmail.com","password"));
		email.setSSLOnConnect(true);
		email.setFrom("ybhamare@shipco.com");//sender
		email.setSubject("SELENIUM SCANONLINE TEST RESULTS");
		email.setMsg("Please Find Attached Report");
		email.addTo("yuvrajbhamare037@gmail.com");//Receiver
		email.attach(url,"extent Repot","please check report");
		email.send();//send the email
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}	
}
