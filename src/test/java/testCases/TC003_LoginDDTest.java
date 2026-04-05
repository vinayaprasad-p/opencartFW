package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBases.ClassBase;
import utilities.DataProviders;

/*data is valid -> login success -> test pass -> logout
                -> login failed -> test fail
                
  data is invalid -> login sucess -> test fail -> logout
  				  -> login failed -> test pass
*/
public class TC003_LoginDDTest extends ClassBase{
	
	@Test (dataProvider="loginData", dataProviderClass=DataProviders.class, groups="datadriver")
	public void verifyLoginTestDDT(String email, String pwd, String exp) { //exp=expected
		logger.info("***** started login test page *****");
		
			try {
			//we need to acces homepage		
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			//access loginpage
			LoginPage lp = new LoginPage(driver);
			lp.giveEmail(email);
			lp.givePassword(pwd);
			lp.clickLogin();
			
			//access my account page
			MyAccountPage macc = new MyAccountPage(driver);
			boolean target = macc.textMyAccount();
			
			if(exp.equalsIgnoreCase("Valid")) {
				if (target==true) {
					Assert.assertTrue(true);
					macc.clickLogout();
				}
				else {
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid")) {
				if(target==true) {
					Assert.assertTrue(false);
					
				}
				else {
					Assert.assertTrue(true);
				}
			}
			
			Assert.assertTrue(target);
			//Assert.assertEquals(target, true, "Login Failed");
			
			}
			catch(Exception e) {
				Assert.fail();
		}
			
			logger.info("***** Finished loginDD test page *****");
	}

}
