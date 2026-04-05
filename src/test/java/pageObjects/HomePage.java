package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass{
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement my_account_click;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement register_click;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement login_click;
	
	//action methods
	public void clickMyAccount() {
		my_account_click.click();
	}
	
	public void clickRegister() {
		register_click.click();
	}
	
	public void clickLogin() {
		login_click.click();
	}
	
	

}
