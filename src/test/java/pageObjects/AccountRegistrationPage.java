package pageObjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//locator
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPwd;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfPwd;
	
	@FindBy(xpath="//input[@name='agree']") WebElement chkAgree;	
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	
	@FindBy(xpath="//h1[contains(text(),'Your Account Has Been Created!')]") WebElement regsuccessmsg;
	//methods
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}
	public void setPwd(String password) {
		txtPwd.sendKeys(password);
	}
	public void setPwdConfirm(String confpassword) {
		txtConfPwd.sendKeys(confpassword);
	}
	public void chkAgree() {
		chkAgree.click();
	}
	public void clickbtnContinue() {
		btnContinue.click();
	}
	public String getSuccessmsg() {
		
			String msg=regsuccessmsg.getText();
			return msg;
		
		
	}
}
