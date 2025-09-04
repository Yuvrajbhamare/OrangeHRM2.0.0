package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class TC0001_LoginTest extends BaseClass {
	@Test(groups = "sanity")
	// @Test(groups={"sanity","Regression","smoke"})
	void Invalidlogincheck() throws InterruptedException

	{
		// logger.info("*****Starting TC0001****");
		try {
			LoginPage lp = new LoginPage(driver);

			lp.EnterUsername(p.getProperty("username")); // reading data from properties file
			// logger.info("Username Entered");
			Thread.sleep(2000);
			lp.EnterPassword(p.getProperty("invalid_password"));
			logger.info("Password Entered");
			lp.LoginButtonClick();

			String confMsg = lp.errormsg();
			Assert.assertEquals(confMsg, "Invalid credentials");

		}

		catch (Exception e) {
			logger.error("Test Failed");
			logger.debug("Debugging failure");
			// Assert.fail();
		}

	}

}
