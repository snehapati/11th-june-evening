package pomclasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilclasses.Util1;

public class LoginPage extends Util1 {
	@FindBy(xpath="(//input[@type='text'])[2]")
	private WebElement emailID;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement password;
	
	@FindBy(xpath="(//button[@type='submit'])[2]")
	private WebElement loginBtn;
	
     public LoginPage(WebDriver driver) {
    	 
    	 PageFactory.initElements(driver, this);
    }
    
     public void EnterEmailID() throws IOException {
    	
		 emailID.sendKeys(getConfigData("Email"));
     }

     public void EnterPassword() throws IOException {
    	 password.sendKeys(getConfigData("password"));
     }
     
     public void Clickloginbtn() {
    	 loginBtn.click();
     } 
}
