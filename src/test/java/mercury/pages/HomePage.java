package mercury.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import framework.testCore.Core;



public class HomePage {
	WebDriver driver;
	
	@FindBy(xpath=".//input[@name='userName']")
	@CacheLookup
	WebElement txtuserName;	
	@FindBy(xpath=".//input[@name='password']")
	@CacheLookup
	WebElement txtPassword;
	@FindBy(name="login")
	@CacheLookup
	WebElement btnLogin;
	
	//Constructor
	public HomePage(WebDriver driver){
		this.driver = Core.driver;
	}
	
	public int mercuryLogin(String username, String password) {
		int rc = 0;	
		Actions action = new Actions(driver); 
		try{
			String title = driver.getTitle();
			if(!title.toLowerCase().contains("welcome")){
				rc = -1;						
			}
			else{
				txtuserName.clear();
				txtuserName.sendKeys(username);
				action.moveToElement(txtuserName).sendKeys(Keys.TAB).perform();
				txtPassword.clear();
				txtPassword.sendKeys(password);
				action.moveToElement(txtPassword).sendKeys(Keys.TAB).perform();
				action.moveToElement(btnLogin).click().perform();
				//btnLogin.click();
			}
		}catch(Exception e){
			rc = -1;
			e.printStackTrace();
			return rc;
		}
		action=null;
		return rc;
	}	
}
