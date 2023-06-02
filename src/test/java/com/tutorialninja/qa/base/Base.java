package com.tutorialninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public Base() {
		prop = new Properties();
		File propFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		dataProp = new Properties();
		File dataPropFile= new File(System.getProperty(("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties"));
		
		try {
			FileInputStream datafis = new FileInputStream(dataPropFile);
			dataProp.load(datafis);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}
		catch(Throwable e){
			e.getStackTrace();
		}
	}

	public WebDriver initialiseBrowserAndOpenApplication(String browserName) {  
		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
			}
			else if(browserName.equals("firefox")){
				driver = new FirefoxDriver();
			}
			else if(browserName.equals("edge")){
				driver = new EdgeDriver();
			}

		
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}
}
