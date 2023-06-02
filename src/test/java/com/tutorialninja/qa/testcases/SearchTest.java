package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;

public class SearchTest extends Base {
	public SearchTest() {
		super();
			
	}
	
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver=initialiseBrowserAndOpenApplication(prop.getProperty("browserName"));
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
@Test(priority=1)
	public void verifySearchWithValidProduct() {
	
	driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
	driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
	Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}

@Test(priority=2)
public void verifySearchWithInValidProduct() {

driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).isDisplayed());
}

@Test(priority=3)
public void verifySearchWithoutPrividingProductName() {

driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).isDisplayed());
}
}

