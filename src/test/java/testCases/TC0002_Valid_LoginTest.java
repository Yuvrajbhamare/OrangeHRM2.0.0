package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class TC0002_Valid_LoginTest extends BaseClass

{
	@Test
	void ValidLoginCheck() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);

		lp.EnterUsername(p.getProperty("username")); // reading data from properties file
		// logger.info("Username Entered");
		Thread.sleep(2000);
		lp.EnterPassword(p.getProperty("valid_password"));
		logger.info("Password Entered");
		lp.LoginButtonClick();

		boolean dashboard_status = lp.isMyDashboardDisplayed();
		Assert.assertEquals(dashboard_status, true);

	}

}
