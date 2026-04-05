package testCases;
//this class created for reusable 

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBases.ClassBase;

public class TC001_AccountRegistrationPage extends ClassBase {
	
	
	@Test (groups={"Regression", "Master"})
	public void testRegister() throws InterruptedException {
		//create object of homepage
		HomePage hp = new HomePage(driver);
		logger.info("**** Started TC001_AccountRegistrationPage ******");
		
		hp.clickMyAccount();
		logger.info("Clicked on My Account...");
		
		hp.clickRegister();
		logger.info("clicked on Register...");
		
		
		try {
		RegisterPage rp = new RegisterPage(driver);
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomString()+ "@gmail.com");
		rp.setTelephone(randomNumber());
		
		Thread.sleep(5);
		String password = randomPassword();
		rp.setPassword(password);
		rp.confirmPassword(password);
		
		rp.chkAgree();
		rp.clickContinue();
		
		String cofirm_msg = rp.getConfirmationMsg();
		if(cofirm_msg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("test failed");
			logger.debug("debug code");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(cofirm_msg, "Your Account Has Been Created!!!");
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("**** Finished TC001_AccountRegistrationPage ****");
		
	}
	
			
			
			//we are creating another baseclass here because we want setup method and random methods for multiple times 

}
