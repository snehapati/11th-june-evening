package testclasses;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
import pomclasses.ProfilePage;
import utilclasses.Util1;
@Listeners(ListenerClass1.class)
public class VerifyUserCanAddAddress {
static WebDriver driver;
	
	LoginPage lp;
	HomePage hp;
	ProfilePage pp;
	String oldAddressCount;
	String newAddressCount;
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extenttest;
	
	@BeforeClass
	@Parameters("Browser")
	public void beforeClass(String browser) throws IOException {
		htmlReporter = BaseClass.getHtmlReporter();
		 reports = BaseClass.getExtentReports(); 
		 extenttest = BaseClass.getTest("VerifyUserCanAddAddress"); 
		driver = BaseClass.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	    pp= new ProfilePage(driver);
	}
	
	@Test(priority=1)
	public void verifyUserCanSeeProfilePage() {
		//Hover on Profile Name
		pp.hoverOnMyProfile();
		
		//click on my profile
		pp.clickOnMyProfile();
			
		AssertJUnit.assertEquals(pp.chkProfilePage(), "Personal Information");
	 oldAddressCount = String.valueOf(pp.getAddressCount());
		
	}
	@DataProvider(name="addressData") 
	public Object[][] getData(){
		Object[][] k = {{"Sneha Patil","9021457146","413512","Kore Garden","Patil Niwas, Near Suvarna Hanuman, Moti Nagar"},{"Sneha Patil","9021457146","412105","Indrayani Park","Flat no.207,Shobha Empire, Gaikwad Wasti, Moshi"}};
		return k;
	}
	
	@Test(priority=2)
	public void addAddress() {
		pp.clickOnManageAddress();
		
	}
	@Test(priority=2, dataProvider="addressData")
	public void addAddresses(String Name, String Mobile,String pincode,String Locality,String fullAddress) {
		List<String> addressDetails = Arrays.asList(Name, Mobile,pincode,Locality,fullAddress);
		
		pp.addAddressses(addressDetails);
		
		 newAddressCount = String.valueOf(pp.getAddressCount());
		 
		 boolean isCountMatching = newAddressCount.equals(oldAddressCount);
		
		 AssertJUnit.assertFalse(isCountMatching);
	}
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
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
		BaseClass.unloadDriver();
	}
}
