package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//locator
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkmyaccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkregister;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLogin;
	//methods
	public void clickMyaccount() {
		lnkmyaccount.click();
	}
	public void clickRegister() {
		lnkregister.click();
	}
	public void clickLogin() {
		lnkLogin.click();
	}
}
