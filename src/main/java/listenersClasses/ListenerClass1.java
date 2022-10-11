package listenersClasses;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import baseclasses.BaseClass;

public class ListenerClass1 implements ITestListener{

	ExtentTest extenttest;
	public void onTestStart(ITestResult result) {
		extenttest = BaseClass.getAlreadyExisteTest();
	   System.out.println("Test started : " + result.getName() + "From listener");
	   
		
	  }

	 
	  public void onTestSuccess(ITestResult result) {
		  extenttest.log(Status.PASS, "Test: " + result.getName()); 
		  System.out.println("Test passed : " + result.getName() + "From listener");
	  }

	  
	public void onTestFailure(ITestResult result) {
		extenttest.log(Status.FAIL,"Test: " + result.getName());
		System.out.println("Test failed : " + result.getName() + "From listener");
	  }

	  
	 public void onTestSkipped(ITestResult result) {
		 extenttest.log(Status.SKIP,"Test: " + result.getName());
		 System.out.println("Test skipped : " + result.getName() + "From listener");
	  }

	
	
}
