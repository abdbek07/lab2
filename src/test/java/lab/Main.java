package lab;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Main {

WebDriver driver;
WebDriverWait wait;
	
	
	@BeforeTest
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver.exec");
		driver = new ChromeDriver();
		driver.get("https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313.TR12.TRC2.A0.H0.Xbook.TRS0&_nkw=book&_sacat=0");
		System.out.println("befortest");
	}
	


	
	
	@Test(priority=20)
	public void radioButton() throws InterruptedException{
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[aria-label=\"New\"]")));
		JavascriptExecutor js =(JavascriptExecutor)driver;
		
		WebElement link = (WebElement)js.executeScript("return document.querySelector(\"[name='LH_GD']:nth-child(4) a\")");
		WebElement radio = driver.findElement(By.cssSelector("[name='LH_GD']:nth-child(4) input"));
		System.out.println(link.getAttribute("href"));
		if(!(radio.isSelected())) {
			link.click();
		}
		System.out.println("testrad");
//		radio = driver.findElement(By.cssSelector("[name='LH_GD']:nth-child(4) input"));
//		assertTrue(radio.isSelected());
	}
	
	@Test(priority=10)
	public void checkBoxWithJavascriptExecutor()  {
		wait = new WebDriverWait(driver, 10);
		JavascriptExecutor js =(JavascriptExecutor)driver;
		WebElement result = (WebElement)js.executeScript("return document.querySelector('input[aria-label=\"Used\"]')");
		
		if(!(result.isSelected())) {
			result.click();
			}
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
