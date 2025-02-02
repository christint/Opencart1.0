package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//locator
	@FindBy(xpath="//h2[text()='My Account']") WebElement lblMyAccountHeader;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnkLogout;
	//methods
	public boolean isMyAccountPageExists() {		
		return lblMyAccountHeader.isDisplayed();		
	}
	public void clickLogout() {
		lnkLogout.click();
	}
}
