package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;
//import org.openqa.selenium.firefox.FirefoxDriver;
public class LoginTest extends Base{

	public LoginTest() {
		super();
	}
	
	WebDriver driver;

@BeforeMethod
public void setup() {
	
	driver= initialiseBrowserAndOpenApplication(prop.getProperty("browserName"));
	driver.findElement(By.linkText("My Account")).click();
	driver.findElement(By.linkText("Login")).click();
}
@AfterMethod
public void tearDown() {
	driver.quit();
}
@Test(priority=1,alwaysRun=true)
public void verifyLoginWithValidCredentials() {
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\batth\\Downloads\\chromedriver_win32");
	
	driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	driver.findElement(By.xpath("//input[@value= 'Login']")).click();
	Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	
	
}
@Test(priority=2)
public void verifyLoginWithInvalidCredentials() {
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\batth\\Downloads\\chromedriver_win32");
	
	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
	driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
	driver.findElement(By.xpath("//input[@value= 'Login']")).click();
	String warnigmsg=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	Assert.assertTrue(warnigmsg.contains("Warning: No match for E-Mail Address and/or Password."), "Warning not displayed");
	

}

@Test(priority=3)
public void verifyLoginWithInvalidEmailValidPassword() {
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\batth\\Downloads\\chromedriver_win32");
	
	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	driver.findElement(By.xpath("//input[@value= 'Login']")).click();
	String warnigmsg=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	Assert.assertTrue(warnigmsg.contains("Warning: No match for E-Mail Address and/or Password."), "Warning not displayed");
	
}
@Test(priority=4)
public void verifyLoginWithValidEmailInvalidPassword() {
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\batth\\Downloads\\chromedriver_win32");
	
	driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
	driver.findElement(By.id("input-password")).sendKeys("12345232");
	driver.findElement(By.xpath("//input[@value= 'Login']")).click();
	String warnigmsg=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	Assert.assertTrue(warnigmsg.contains("Warning: No match for E-Mail Address and/or Password."), "Warning not displayed");
	
}
@Test(priority=5)
public void verifyLoginWithoutProvidingCredentials() {
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\batth\\Downloads\\chromedriver_win32");
	
	driver.findElement(By.xpath("//input[@value= 'Login']")).click();
	String warnigmsg=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	Assert.assertTrue(warnigmsg.contains("Warning: No match for E-Mail Address and/or Password."), "Warning not displayed");
	
}
}

