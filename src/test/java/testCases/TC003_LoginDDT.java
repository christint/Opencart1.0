package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {	
	
	@Test(dataProvider = "logindata",dataProviderClass = DataProviders.class)
	public void verify_LoginDDT(String userid,String password,String exp) {
		logger.info("****Started loginDDT test****");
		try {
			//data
			String useridEmail=userid;
			String pwd=password;
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
			boolean loginstatus=ma.isMyAccountPageExists();
			if(exp.equalsIgnoreCase("Valid")) {
				if (loginstatus) {
					ma.clickLogout();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
			}
			if(exp.equalsIgnoreCase("InValid")) {
				if (loginstatus) {
					ma.clickLogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
			logger.info("****Login Success****");
		}catch(Exception e){
			logger.error("Test login failed");
			Assert.fail();
		}
		logger.info("****Ended loginDDT test****");
	}

}
