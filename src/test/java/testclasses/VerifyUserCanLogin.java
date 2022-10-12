package testclasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseclasses.BaseClass;
import listenersClasses.ListenerClass1;
import pomclasses.HomePage;
import pomclasses.LoginPage;
import utilclasses.Util1;

@Listeners(ListenerClass1.class)
public class VerifyUserCanLogin {
static WebDriver driver;
	
	LoginPage lp;
	HomePage hp;
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extenttest;
	
	@BeforeClass
	@Parameters("Browser")
	public void beforeClass(String browser) throws IOException {
		
		 htmlReporter = BaseClass.getHtmlReporter();
		 reports = BaseClass.getExtentReports(); 
		 extenttest = BaseClass.getTest("VerifyUserCanLogin"); 
		driver = BaseClass.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		
	}
	
	
	@Test(priority=1)
	public void VerifyUserCanLogIn() throws InterruptedException, IOException {
		
		lp.EnterEmailID();
		lp.EnterPassword();
		lp.Clickloginbtn();
		
		String profileName = hp.getProfileName();
		
		Assert.assertNotEquals(profileName, "Sneha", "Profile Name is not matching");
		
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.SUCCESS) {
			extenttest.log(Status.PASS, "Test: " + result.getName());	
		}else if(result.getStatus()==ITestResult.FAILURE) {
			
			String path = Util1.getScreenshot(driver, result.getName());
			
			extenttest.log(Status.FAIL,"Test: " + result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
		}else if(result.getStatus()==ITestResult.SKIP) {
			extenttest.log(Status.SKIP,"Test: " + result.getName());
		}
		
	}
	
	
	@AfterClass
	public void afterClass() {
		reports.flush();
	}
		}


