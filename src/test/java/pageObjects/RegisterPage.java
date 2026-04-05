package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BaseClass{
	
	//constructor
	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement input_fName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement input_lName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement input_email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement input_telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement input_password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement confirm_pass;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement click_agree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement click_continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgconfirmation;
	
	//action methods
	public void setFirstName(String fname) {
		input_fName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		input_lName.sendKeys(lname);
	}
	
	public void setEmail(String mail) {
		input_email.sendKeys(mail);
	}
	
	public void setTelephone(String telno) {
		input_telephone.sendKeys(telno);
	}
	
	public void setPassword(String psw) {
		input_password.sendKeys(psw);
	}

	public void confirmPassword(String psw) {
		confirm_pass.sendKeys(psw);
	}
	
	public void chkAgree() {
		click_agree.click();
	}
	
	public void clickContinue() {
		click_continue.click();
	}
	
	
	
	
	
	
	public String getConfirmationMsg() {
		try {
			return(msgconfirmation.getText());
		}
		catch (Exception e) {
			return(e.getMessage());
		}
	}


}

