package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage
{
	
public SignUpPage(WebDriver driver)
{
	super(driver);
}
	

@FindBy(xpath="//a[normalize-space()='Signupq']")
WebElement signup1;

@FindBy(xpath="//input[@id='firstnameq']")
WebElement firstName;

@FindBy(xpath="//input[@id='lastnameq']")
WebElement LastName;

@FindBy(xpath="//input[@id='usernameq']")
WebElement UserName;

@FindBy(xpath="//input[@id='mobileq']")
WebElement Mobile;

@FindBy(xpath="//input[@id='companynameq']")
WebElement companyname;

@FindBy(xpath="//div[@class='col-xs-6']//input[@id='emailq']")
WebElement email;

@FindBy(xpath="//button[normalize-space()='Submitq']")
WebElement Submitq;


public void SignUp()
{
	signup1.click();
}


public void firstname(String firstName1)
{
	firstName.sendKeys(firstName1);
}
public void Lastname(String LastName1)
{
	LastName.sendKeys(LastName1);
}

public void Username(String username1)
{
	UserName.sendKeys(username1);
}


//ActionMethods
	
	
	
	
}
