package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Browsers {

	public WebDriver WebDriverMain;
	DesiredCapabilities capability;
	
		public WebDriver getDriverHandle(String browser){
			
			this.capability = new DesiredCapabilities();
			try{
			if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", TestConfig.chromeDriver);
				WebDriverMain = new ChromeDriver();			
			}
			else if(browser.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver", TestConfig.firefoxDriver);
				WebDriverMain = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("ie")){			
				
				capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				System.setProperty("webdriver.ie.driver", TestConfig.ieDriver);
				WebDriverMain = new InternetExplorerDriver();			
			}
			else{
				System.out.println("Please provide a valid browser name");
			}
			}catch(Exception e){
				System.out.println("Browser Initialization Failed");
				e.printStackTrace();
			}
			
			return WebDriverMain;
		}
}