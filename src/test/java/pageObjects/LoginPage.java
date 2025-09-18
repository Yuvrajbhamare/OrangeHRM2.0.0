package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// Locators

	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement UserName;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement Password;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement Login_Button;
	// @FindBy(xpath = "//button[normalize-space()='LOGIN']")
	// WebElement Login2;

	@FindBy(xpath = "//h6[normalize-space()='Dashboard']")
	WebElement Dashboard;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	WebElement errormsg;

	@FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
	WebElement profileIconforLogout;

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement logout;

	// Action Methods

	public void EnterUsername(String username) {
		UserName.sendKeys(username);
	}

	public void EnterPassword(String password) {
		Password.sendKeys(password);
	}

	public void LoginButtonClick() {
		Login_Button.click();
	}
	// public void LoginButtonClick2() {
	// Login2.click();
	// }

	public void profileIconforLogoutm() {
		profileIconforLogout.click();
		logout.click();
	}

	public boolean isMyDashboardDisplayed() {
		try {
			return (Dashboard.isDisplayed());
		} catch (Exception e) {

			return false;
		}
	}

	/*
	 * public String ErrorMsg() { try { return (errormsg.getText()); } catch
	 * (Exception e) { return (e.getMessage()); } }
	 */

	public String errormsg() {
		try {
			return (errormsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}

	}

}
