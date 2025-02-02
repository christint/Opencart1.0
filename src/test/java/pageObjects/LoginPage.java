package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//locator
	@FindBy(xpath="//input[@id='input-email']") WebElement txtloginUseridEmail;	
	@FindBy(xpath="//input[@id='input-password']") WebElement txtloginPwd;	
	@FindBy(xpath="//input[@value='Login']") WebElement btnLogin;
		
	//methods
	public void setLoginUSerIDEmail(String email) {
		txtloginUseridEmail.sendKeys(email);
	}
	public void setLoginPwd(String pwd) {
		txtloginPwd.sendKeys(pwd);
	}
	
	public void clickbtnLogin() {
		btnLogin.click();
	}	
	
	
}
