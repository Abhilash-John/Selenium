package window;


import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WindowHandleTest{

	WebDriver driver;
	String path = "D:\\Softwares\\Selenium\\drivers\\chromedriver.exe";
	String windowHandleURL="http://demo.guru99.com/popup.php";
	String id = "53920";

	@BeforeTest
	public void beforeTest() throws SecurityException {
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		
	}

	@AfterTest
	public void afterTest() throws Exception {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	@Test
	public void windowHandle(){
		try {
			driver.get(windowHandleURL);
			driver.manage().window().maximize();
			Assert.assertEquals(driver.getTitle(), "Guru99 Bank Home Page", "Title Not Matched");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
			driver.quit();
		} 
		driver.findElement(By.linkText("Click Here")).click();
		String mainwin=driver.getWindowHandle();
		Set<String> lis = driver.getWindowHandles();
		Iterator<String> ls=lis.iterator();
		
		while(ls.hasNext()){
			
			String childWin=ls.next();
			
			if(!mainwin.equalsIgnoreCase(childWin))
			{
				driver.switchTo().window(childWin);
				driver.findElement(By.name("emailid")).sendKeys("gaurav.3n@gmail.com");                			
                driver.findElement(By.name("btnLogin")).click();
				driver.close();
			}
		}
		driver.switchTo().window(mainwin);
		Assert.assertTrue(driver.getTitle().contains("Guru99 Bank Home Page"));
	}
}

