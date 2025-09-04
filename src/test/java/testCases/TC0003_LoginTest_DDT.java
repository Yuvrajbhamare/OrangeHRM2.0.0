package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.SignUpPage;
import utilities.DataProviders;

public class TC0003_LoginTest_DDT extends BaseClass
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)//getting dataprovider from different class
	public void VerifyLoginDDT(String Email,String pwd,String ExpResult) throws InterruptedException 

	{  
		//try {
		//logger.info("*****Test Case Started*****");
		LoginPage lp = new LoginPage(driver);
		
		lp.EnterUsername(Email);//reading data from properties file
		//logger.info("Username Entered");
		Thread.sleep(2000);
		lp.EnterPassword(pwd);
		logger.info("Password Entered");
		lp.LoginButtonClick();
		
		boolean targetPage=lp.isMyDashboardDisplayed();
		
		if(ExpResult.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				
				lp.profileIconforLogoutm();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertFalse(false);
			}
		}
		
		if(ExpResult.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				lp.profileIconforLogoutm();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
				
		}
		//}
		//catch(Exception e)
		//{
		//	Assert.fail();
		//}
		//String confMsg=lp.errormsg();
		//Assert.assertEquals(confMsg,"Your password has expired, please use Forgot Password below to reset." );
		
		
		//SignUpPage Sgnp = new SignUpPage(driver);
		//Sgnp.SignUp();
		//Sgnp.firstname(pwd);
		//Sgnp.Lastname(pwd);
		//Thread.sleep(6000);
		//Sgnp.Username(randomString()+"@gmail.com");
		
	

	
		
	}
		

	
}
