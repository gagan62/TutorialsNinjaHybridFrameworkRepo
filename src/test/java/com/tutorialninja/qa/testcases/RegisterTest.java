package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;
//import java.util.Date;

public class RegisterTest extends Base {
	public RegisterTest() {
		super();
	}
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=initialiseBrowserAndOpenApplication(prop.getProperty("browserName"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=1)
public void VerifyRegisteringAnAccountWithMandotoryField() throws InterruptedException {
	 
	driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
	driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
	driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
	driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
	driver.findElement(By.name("agree")).click();
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	String actualSucessHeading =driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
	Assert.assertEquals(actualSucessHeading, "Your Account Has Been Created!","Account Page is not diplayed");
}
	@Test(priority=2)
	public void VerifyRegisteringAnAccountBYProvidingAllFields() {
		 
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualSucessHeading =driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSucessHeading, "Your Account Has Been Created!","Account Page is not diplayed");
}
	@Test(priority=3)
	public void VerifyRegisteringAnAccountWithExistingEmailAddress() {
		 
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actualWarning= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText(); 
        Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),"Warning msg is not displayed");	
}
	@Test(priority=4)
	public void VerifyRegisteringAnAccountWithoutFillingAnyDetails() {
		 
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String actuaPrivacyPolicyWarning= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText(); 
        Assert.assertTrue(actuaPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy"));	
		
        String actuaFirstNameWarning= driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(); 
        Assert.assertEquals(actuaFirstNameWarning,"First Name must be between 1 and 32 characters!");
        
        String actualLastNameWarning= driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(); 
        Assert.assertEquals(actualLastNameWarning,"Last Name must be between 1 and 32 characters!");
        
        String actualEmailWarning= driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(); 
        Assert.assertEquals(actualEmailWarning,"E-Mail Address does not appear to be valid!");
        
        String actualTelephoneWarning= driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(); 
        Assert.assertEquals(actualTelephoneWarning,"Telephone must be between 3 and 32 characters!");
        
        String actualPasswordWarning= driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(); 
        Assert.assertEquals(actualPasswordWarning,"Password must be between 4 and 20 characters!");
        
        
        
}
}
