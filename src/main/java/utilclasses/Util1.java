package utilclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Util1 {

	//JavaScriptExecutor
	//Screenshot
	//Mouse Actions
	//Explicit Wait
	
	public static String getConfigData(String key) throws IOException {
		
		FileInputStream file = new FileInputStream("configuration\\config.properties");
		
		Properties prop = new Properties();
		
		prop.load(file);
		
		return prop.getProperty(key);
		
	}
	
	public static String getScreenshot(WebDriver driver, String methodName) throws IOException {
		
		String path = methodName + ".png" ;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		
		FileHandler.copy(source, dest);
		return path;
		
		
	}
	
	
	
	
	
}
