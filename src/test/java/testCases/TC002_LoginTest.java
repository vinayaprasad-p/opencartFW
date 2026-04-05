package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBases.ClassBase;

public class TC002_LoginTest extends ClassBase{
	
	@Test (groups={"Sanity"})
	public void verifyLogin() {
		logger.info("***** started login test page *****");
		
		try {
		//we need to acces homepage		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//access loginpage
		LoginPage lp = new LoginPage(driver);
		lp.giveEmail(p.getProperty("email"));
		lp.givePassword(p.getProperty("password"));
		lp.clickLogin();
		
		//access my account page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean target = macc.textMyAccount();
		
		Assert.assertTrue(target);
		//Assert.assertEquals(target, true, "Login Failed");
		}
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished login test page *****");
	}
		
	

}
