package Test_cases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class FrameTest {
	private static WebDriver driver;
	private String url="https://the-internet.herokuapp.com/";
	
  @Test(enabled=false)
  public void frame() {
	  driver.findElement(By.xpath("//*[text()='Nested Frames']")).click();
	  int frameList = driver.findElements(By.tagName("frame")).size();
	  System.out.println(frameList);
	  for (int i=0;i<frameList;i++) {
		  driver.switchTo().frame(i);
		  for (int j = 0; j < driver.findElements(By.tagName("frame")).size(); j++) {
			  System.out.println(driver.switchTo().frame(j));
			  driver.switchTo().defaultContent();
		  }  
	  driver.switchTo().defaultContent();
	  }	
	}
  @Test(enabled=false)
  public void window() throws InterruptedException {
	  driver.findElement(By.xpath("//*[text()='Multiple Windows']")).click();
	  String parentWindow= driver.getWindowHandle();
	  driver.findElement(By.xpath("//*[text()='Click Here']")).click();
	  Set<String> handle =driver.getWindowHandles();
	  Iterator it =handle.iterator();
	  String childWindow=null;
	  while (it.hasNext()) {
		childWindow = (String)it.next();	
	  }
	  driver.switchTo().window(childWindow);
	  if (driver.findElements(By.xpath("//*[text()='New Window']")).size() >0 ) {
		  Thread.sleep(2000);
		  System.out.println("Child");
			System.out.println(driver.getCurrentUrl());
			driver.close();
		}
	  driver.switchTo().window(parentWindow);
	  System.out.println("Parent");
	  

  }
  
  @Test
  public void alert() throws InterruptedException {
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[text()='JavaScript Alerts']")).click();
	  Thread.sleep(2000);
	  
	  driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
//	  driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
	  WebDriverWait wait = new WebDriverWait(driver,20);
	  wait.until(ExpectedConditions.alertIsPresent());
	  Thread.sleep(2000);
	  Alert alert= driver.switchTo().alert();
	  alert.accept();
	  assertEquals(driver.findElement(By.id("result")).getText(),"You successfuly clicked an alert");
	  driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
	  wait.until(ExpectedConditions.alertIsPresent());
	  Alert jsalert= driver.switchTo().alert();
	  jsalert.dismiss();
	  assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
	  driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
	  wait.until(ExpectedConditions.alertIsPresent());
	  Alert jsdlert= driver.switchTo().alert();
	  jsdlert.sendKeys("Raman");
	  jsdlert.accept();
	  assertEquals(driver.findElement(By.id("result")).getText(),"You entered: Raman");
	  
	  
  }
	 
 
  @BeforeMethod
  public void beforeMethod() {
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
	  driver.get(url);
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Raman\\drivers\\chromedriver.exe");
	  
  }

  @AfterSuite
  public void afterSuite() {
  }

}
