package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {	
	
	@Test(groups= {"master"})
	public void verify_Login() {
		logger.info("****Started login test****");
		try {
			//data
			String useridEmail=prop.getProperty("useridEmail");
			String pwd=prop.getProperty("pwd");
			//logic
			HomePage hp=new HomePage(driver);
			hp.clickMyaccount();
			hp.clickLogin();
			LoginPage lp=new LoginPage(driver);
			lp.setLoginUSerIDEmail(useridEmail);
			lp.setLoginPwd(pwd);
			lp.clickbtnLogin();
			logger.info("****Login btn clicked****");
			MyAccountPage ma=new MyAccountPage(driver);
			Assert.assertEquals(true, ma.isMyAccountPageExists());
			logger.info("****Login Success****");
		}catch(Exception e){
			logger.error("Test login failed");
			Assert.fail();
		}
		logger.info("****Ended login test****");
	}

}
