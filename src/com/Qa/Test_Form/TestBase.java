package com.Qa.Test_Form;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase 
{
	public static Properties prop;
	public static WebDriver driver;
	public TestBase()
	{
		try 
		{
			prop = new Properties();
			FileInputStream ip;
			ip = new FileInputStream("C:\\workspace\\com.Qa.Test_Form\\src\\com\\Qa\\Test_Form\\Config.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public  void initialisation()
	{
		String browsername= prop.getProperty("Browser");
		if (browsername.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\workspace\\com.Qa.Test_Form\\Drivers\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		else if (browsername.equals("FF"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\workspace\\com.Qa.Test_Form\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));	
	}
}

