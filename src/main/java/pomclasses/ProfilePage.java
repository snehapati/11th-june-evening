package pomclasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilclasses.Util1;

public class ProfilePage extends Util1 {
WebDriver driver;
	
	@FindBy(xpath="//div[text()='Sneha']")
	private WebElement profileName;
	
	@FindBy(xpath="//div[text()='My Profile']")
	private WebElement myProfile;
	
	@FindBy(xpath="//span[text()='Personal Information']")
	private WebElement personalInfo;
	
	@FindBy(xpath="//div[text()='Manage Addresses']")
	private WebElement manageAddress;
	
	@FindBy(xpath="//div[@class='_1QhEVk']")
	private WebElement addAddress;
	
	@FindBy(xpath="//textarea[@name='addressLine1']")
	private WebElement fullAddress;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//div[@class='_1CeZIA']")
	private List<WebElement> AddressList;
	
		
	public ProfilePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		
	}
	public void hoverOnMyProfile() {
		Actions act = new Actions(driver);
		act.moveToElement(profileName).perform();;
	}
	public void clickOnMyProfile() {
		myProfile.click();
	}
	
	public String chkProfilePage() {
		String PI= personalInfo.getText();
		return PI;
	}
	
	public void clickOnManageAddress() {
		manageAddress.click();
	}
	
	public void addAddressses(List<String> addressDetails) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(addAddress));
		addAddress.click();
		for(int i=1; i<=4;i++) {
			driver.findElement(By.xpath("//input[@tabindex='"+i+"']")).sendKeys(addressDetails.get(i-1));
			
		}
		fullAddress.sendKeys(addressDetails.get(4));
		saveBtn.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-200)");

	}
	public int getAddressCount() {
		
		return AddressList.size();
	}

}
