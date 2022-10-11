package testclasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseclasses.BaseClass;
import listenersClasses.ListenerClass1;
import pomclasses.HomePage;
import pomclasses.LoginPage;
@Listeners(ListenerClass1.class)
public class VerifyUserCanGetLowestPrice {
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
		 extenttest = BaseClass.getTest("VerifyUserCanGetLowestPrice"); 
		driver = BaseClass.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}
	@Test(priority=1)
	public void VerifyUserCanSearchProduct() {
		hp.searchProduct();
		hp = new HomePage(driver);
		String list = hp.productList();
		
			
		AssertJUnit.assertTrue(list.contains("Showing 1"));
		
	}
	@Test(priority=2)
	public void VerifyUserCanGetLowestprice() throws InterruptedException {
		//List<String> lowpriceListfrom5pages = new ArrayList<>();
		Map<Integer,String> lowPriceMapExpected= new HashMap<>();
		lowPriceMapExpected.put(1, "10000");
		lowPriceMapExpected.put(2, "10000");
		lowPriceMapExpected.put(3, "10000");
		lowPriceMapExpected.put(4, "10000");
		lowPriceMapExpected.put(5, "10000");
		
		Map<Integer,String> lowPriceMapActual= new HashMap<>();
		
		for(int i =1; i<=5; i++) {	
			if(i!=1) {
				hp.SwitchToPage(i);
				 }
			hp = new HomePage(driver);	
			Thread.sleep(1000);
		    //lowpriceListfrom5pages.add(hp.getLowestProductPrice());
		    
			lowPriceMapActual.put(i, hp.getLowestProductPrice());
		}
		Assert.assertNotEquals(lowPriceMapActual, lowPriceMapExpected);
		
		System.out.println(lowPriceMapActual);
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result){
		if(result.getStatus()==ITestResult.SUCCESS) {
			extenttest.log(Status.PASS, "Test: " + result.getName());	
		}else if(result.getStatus()==ITestResult.FAILURE) {
			extenttest.log(Status.FAIL,"Test: " + result.getName());
		}else if(result.getStatus()==ITestResult.SKIP) {
			extenttest.log(Status.SKIP,"Test: " + result.getName());
		}	
	}
	
	
	@AfterClass
	public void afterClass() {
		reports.flush();
	}
}
