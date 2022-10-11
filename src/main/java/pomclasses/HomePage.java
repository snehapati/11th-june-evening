package pomclasses;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilclasses.Util1;

public class HomePage extends Util1 {
WebDriver driver;
	
	@FindBy(xpath="//div[text()='Sneha']")
	private WebElement profileName;
	
	@FindBy(xpath="//input[@name='q']")
	private WebElement SearchField;
	
	@FindBy(xpath="//div[@class='_2kHMtA']")
	private WebElement ProdList;
	
	@FindBy(xpath="//span[@class='_10Ermr']")
	private WebElement textOnprodList;
	
	@FindBy(xpath="//div[@class='_30jeq3 _1_WHN1']")
	private List<WebElement> prodpriceList;
	
		
	public HomePage(WebDriver driver) {
   	
   	 PageFactory.initElements(driver, this);
     this.driver = driver;
   }
	
	public String getProfileName() throws InterruptedException {
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement newelement = wait.until(ExpectedConditions.visibilityOf(profileName));
		
		String pName = newelement.getText();
		return pName;
		
	}
	
	public void searchProduct() {
		SearchField.sendKeys("realme");
		SearchField.sendKeys(Keys.ENTER);
		
	}
	
	public String productList() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ProdList));
		
		
		String text = textOnprodList.getText();
		
		return text;
	}
	
	public String getLowestProductPrice() {
		List<Integer> priceList = new ArrayList<Integer>();
		for(WebElement priceElement: prodpriceList) {
			priceList.add(Integer.parseInt(priceElement.getText().replace("₹", "").replace(",", "")));
		}
		
		System.out.println(priceList);
		
		Collections.sort(priceList);
		
		return priceList.get(0).toString();
	}
	
	public void SwitchToPage(int a) {
		JavascriptExecutor k = (JavascriptExecutor) driver;
		
		
	    WebElement x=	driver.findElement(By.xpath("//a[@class='ge-49M' and text()='"+a+"']"));
	    k.executeScript("arguments[0].scrollIntoView(true);", x);
		k.executeScript("arguments[0].click();", x);
		
			}
}
