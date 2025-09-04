package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
//import org.apache.logging.log4j.core.Logger;

public class BaseClass 
{
	public static WebDriver driver;//static because Extent Report class we have created object of baseClass,and it will ceate conflict.so to make variable common accross multiple objects,we are making webdriver as static.static keyword use:to make variable common accross multiple objects
	public org.apache.logging.log4j.Logger logger;
	public Properties p;
	@Parameters({"os","browser"})
	@BeforeClass(groups="sanity")
	
	public void setup(String os,String br) throws IOException 
	{    
		
		//Loading config.properties file
		
		FileReader file= new FileReader("./src//test//resources//config.properties");//Can user FileInput stream here also,this class is used just for information
		p=new Properties();
		p.load(file);
		logger=LogManager.getLogger(this.getClass());
		//if needed Selenium Grid 
				//Remeber:Even if you are using local or remote or Hub or node or docker,No need to change in code now,change will be in xml file only,so dont worry about code in BaseClass,any setup standalone or different machine or docker or anything setup,no change in BaseClass set up from nw onwards
				if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
				{
					DesiredCapabilities Capabilities = new DesiredCapabilities();
					//Capabilities.setPlatform(Platform.WIN11);
					//Capabilities.setBrowserName("chrome");
					
					//os
					if(os.equalsIgnoreCase("windows"))
					{
						Capabilities.setPlatform(Platform.WIN11);
					}
					else if (os.equalsIgnoreCase("linux"))
					{
						Capabilities.setPlatform(Platform.LINUX);
					}
					else if (os.equalsIgnoreCase("mac"))
					{
						Capabilities.setPlatform(Platform.MAC);
					}
					
					
					else
					{
						System.out.println("No matching Os");
						return;
					}	
		
					switch (br.toLowerCase())
					{
					case "chrome":Capabilities.setBrowserName("chrome");break;
					case "edge":Capabilities.setBrowserName("edge");break;

					default :System.out.println("No matching browser");
					return;
					
					}
					driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),Capabilities);
					}
					
					if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		
					{
		
		
		
	
		switch (br.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default :System.out.println("Invalid Browser");
		return;
		
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURl1"));//Reading url from properties file	
	}
	}
		
	public String randomString()
	{
		String RandomValue=RandomStringUtils.randomAlphanumeric(6);
		return RandomValue;
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
        return targetFilePath;
	}
	@AfterClass
	void tearDown() 
	{
		//driver.close();
	}
}
