package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {	
	
	@Test(groups= {"regression","master"})
	public void verify_AccountRegistration() {
		logger.info("****Started test****");
		try {
			//data
			String useridEmail=prop.getProperty("useridEmail");
			String pwd=prop.getProperty("pwd");
			HomePage hp=new HomePage(driver);
			hp.clickMyaccount();
			logger.info("clicked myaccount");
			hp.clickRegister();
			logger.info("clicked register");
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			logger.info("entering registration page details");
			regpage.setFirstName(getRandomString().toUpperCase());
			regpage.setLastName(getRandomString().toUpperCase());
			regpage.setEmail(useridEmail);
			regpage.setTelephone(getRandomNumeric());
			regpage.setPwd(pwd);
			regpage.setPwdConfirm(pwd);
			regpage.chkAgree();
			regpage.clickbtnContinue();
			//String confmsg=regpage.getSuccessmsg();
			//logger.info("confmsg is :" + confmsg);
			logger.info("validating successful registration message");
			Assert.assertEquals(regpage.getSuccessmsg(), "Your Account Has Been Created!");
		}catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		logger.info("****Completed test****");
	}
	

}
