package framework.testCore;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import framework.*;

public class Core {
	
	public static WebDriver driver;
		
	@BeforeSuite
	public void setup(){
		Browsers browser = new Browsers();
		if(driver==null){
			driver = browser.getDriverHandle(TestConfig.browser);		
		}
		navigateToURL();
		setBrowserProperties();
	}
	
	public void navigateToURL(){
		try{
			driver.get(TestConfig.url);
		}catch(Exception e){
			System.out.println("Unable to navigate to the URL");
			e.printStackTrace();
		}
	}
	
	
	public void setBrowserProperties(){
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60l, TimeUnit.SECONDS);
		
	}
	
	
	@AfterSuite
	public void tearDown(){		
		if(driver!=null){			
			try{
				driver.close();
				driver.quit();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}