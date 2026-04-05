package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BaseClass{
	//constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement text_heading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement click_logout;
	
	
	
	public boolean textMyAccount() {
		try {
		return (text_heading.isDisplayed());
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		click_logout.click();
	}
	
		
}
