package lab;


import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class Main {

RemoteWebDriver driver;
WebDriverWait wait;
	

	@BeforeSuite
		public void setup() throws MalformedURLException {
	    
	    System.out.println("beforesuite");
	    
	    String username = "abdbek07%40gmail.com"; // Your username
		String authkey = "u4869ffde41da35e";  // Your authkey
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");    // Automatically pulls the latest version of Chrome
        caps.setCapability("platform", "Windows 10");   // To specify a version, add setCapability("version", "desired version")
//        caps.setCapability("screenResolution", "2560x1920");
//        caps.setCapability("record_video", "true");
        
        driver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey +"@hub.crossbrowsertesting.com:80/wd/hub"), caps);
}
	@BeforeTest(enabled=false)
	public void setUp() throws MalformedURLException {

		System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver");
		driver = new ChromeDriver();
		
		Dimension d = new Dimension(1382,744); 
		driver.manage().window().setSize(d); 
		
		driver.get("https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313.TR12.TRC2.A0.H0.Xbook.TRS0&_nkw=book&_sacat=0");
		System.out.println("befortest");
	}
	


	
	
	@Test(priority=20)
	public void radioButton() throws InterruptedException, IOException{
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[aria-label=\"New\"]")));
		JavascriptExecutor js =(JavascriptExecutor)driver;
		
		WebElement link = (WebElement)js.executeScript("return document.querySelector(\"[name='LH_GD']:nth-child(4) a\")");
		WebElement radio = driver.findElement(By.cssSelector("[name='LH_GD']:nth-child(4) input"));
		System.out.println(link.getAttribute("href"));
		if(!(radio.isSelected())) {
			link.click();
		}
		File scr=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dest= new File("/Users/nnuerdun/Desktop/books/"+System.currentTimeMillis()+".png");
        com.google.common.io.Files.copy(scr, dest);
		System.out.println("testrad");
//		radio = driver.findElement(By.cssSelector("[name='LH_GD']:nth-child(4) input"));
//		assertTrue(radio.isSelected());
	}
	
	@Test(priority=10)
	public void checkBoxWithJavascriptExecutor() throws IOException  {
		wait = new WebDriverWait(driver, 10);
		JavascriptExecutor js =(JavascriptExecutor)driver;
		WebElement result = (WebElement)js.executeScript("return document.querySelector('input[aria-label=\"Used\"]')");
		
		if(!(result.isSelected())) {
			result.click();
			}
		File scr=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dest= new File("/Users/nnuerdun/Desktop/books/"+System.currentTimeMillis()+".png");
        com.google.common.io.Files.copy(scr, dest);
		System.out.println("test check");
//		result = (WebElement)js.executeScript("return document.querySelector('input[aria-label=\"Used\"]')");
//		assertTrue(result.getAttribute("checked").equals("true"));
	}
	
	@Test()
	public void getTextFromElement() {
		
	}
	
	@AfterTest(enabled=true)
	public void shutDown() {
		driver.quit();
	}
	
}
