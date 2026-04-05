package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseClass{

	//constructor
	public LoginPage(WebDriver driver) {  //constructor doesnt have any trype of return type
		super(driver);
	}

	//locators
	@FindBy(xpath="//input[@id='input-password']")
	WebElement input_password;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement input_mail;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement click_login;
	
	
	
	public void giveEmail(String mail) {
		input_mail.sendKeys(mail);
	}
	
	public void givePassword(String psw) {
		input_password.sendKeys(psw);
	}
	
	public void clickLogin() {
		click_login.click();
	}
	
	
	
}
