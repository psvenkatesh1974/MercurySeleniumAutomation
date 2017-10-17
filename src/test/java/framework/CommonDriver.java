package framework;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonDriver {

	public static String parentWindow=null;
	
	public static void switchToNewWindow(WebDriver driver) throws InterruptedException{
		parentWindow = driver.getWindowHandle();
		Thread.sleep(3000l);
		Set<String> windows = driver.getWindowHandles();
		for(String window : windows){
			if(!window.equals(parentWindow)){
				driver.switchTo().window(window);
			}
		}
		
	}
	
	public static void switchToParentWindow(WebDriver driver){
		driver.switchTo().window(parentWindow);	
	}
	
	
	public static void takeSnapshotJPG(WebDriver driver, String filename) throws IOException{
		 File ss3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);  
		 FileUtils.copyFile(ss3, new File(filename));		 
	}
	
	
	public static void explicitWait(WebDriver driver,String xpath,String condition, int maxtime){
		WebDriverWait wait = new WebDriverWait(driver, maxtime);		
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
		
		
	}
}
