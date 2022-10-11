package baseclasses;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilclasses.Util1;

public class BaseClass {
	static WebDriver driver;
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports reports;
	static ExtentTest extenttest;
	
	public static WebDriver getDriver(String browser) throws IOException {
		
		if(driver == null) {
			
			if(browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				
//			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Browsers\\chromedriver.exe");
			driver = new ChromeDriver();
			       }
			
		else {
			WebDriverManager.firefoxdriver().setup();
//			System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\Browsers\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		      }	
			
			//if(Util1.getConfigData("env").equals("qa")) {
				driver.get("https://www.flipkart.com/");	
		//	}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
}
		return driver;
	}
	
	public static void unloadDriver() {
		driver = null;

   }
	public static ExtentHtmlReporter getHtmlReporter() {
		if(htmlReporter== null) {
			htmlReporter = new ExtentHtmlReporter("ExtentReports\\reports.html");
		}
		return htmlReporter;
	}
	public static ExtentReports getExtentReports() {
		if(reports==null) {
			reports = new ExtentReports();
			reports.attachReporter(htmlReporter);
		}
		return reports;
	}
	public static ExtentTest getTest(String testName) {
		
		extenttest = reports.createTest(testName);
		return extenttest;
	}
	public static ExtentTest getAlreadyExisteTest() {
		return extenttest;
	}
	
	
}