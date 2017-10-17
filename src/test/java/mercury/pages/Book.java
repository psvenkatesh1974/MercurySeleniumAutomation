package mercury.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import framework.testCore.Core;


	
	public class Book {

		WebDriver driver;
		
		@FindBy(xpath="//input[@name='passFirst0']")
		@CacheLookup
		WebElement txtFirstName;		
		@FindBy(xpath="//input[@name='passLast0']")
		@CacheLookup
		WebElement txtLastName;		
		@FindBy(xpath="//input[@name='creditnumber']")
		@CacheLookup
		WebElement txtCreditCardNo;				
		@FindBy(xpath="//input[@name='buyFlights']")
		@CacheLookup
		WebElement btnBuyFlights;
		
		//Constructor
		public Book(WebDriver driver){
			this.driver = Core.driver;
		}
		  
		//Enter only firstname, lastname and credit card number and leave other details as default
		public int enterPassengerDetails(String firstname, String lastname){
			
			int rc =0;
			Actions action = new Actions(driver);
			try{
				String title = driver.getTitle();
				if(!title.toLowerCase().contains("book")){
					rc = -1;						
				}
				else{
					if(!firstname.isEmpty()){
						txtFirstName.sendKeys(firstname);
						action.moveToElement(txtFirstName).sendKeys(Keys.TAB).perform();
					}
					if(!lastname.isEmpty()){
						txtLastName.sendKeys(lastname);
						action.moveToElement(txtLastName).sendKeys(Keys.TAB).perform();
					}
				}
			}catch(Exception e){
				rc = -1;
				e.printStackTrace();
				return rc;
			}		
			return rc;
		}
		
		//Enter only the card number and leave other details as default
		public int enterCardDetails(String cardno){
			
			int rc=0;
			Actions action = new Actions(driver);
			try{
				String title = driver.getTitle();
				if(!title.toLowerCase().contains("book")){
					rc = -1;						
				}
				else{								
					if(!cardno.isEmpty()){
						txtCreditCardNo.sendKeys(cardno);
						action.moveToElement(txtCreditCardNo).sendKeys(Keys.TAB).perform();
					}	
				}
			}catch(Exception e){
				rc = -1;
				e.printStackTrace();
				return rc;
			}		
			return rc;
		}
		
		
		public int clickSecurePurchase(){
			int rc = 0;
			try {
				btnBuyFlights.click();
			} catch (Exception e) {
				rc = -1;
				e.printStackTrace();
				return rc;
			}		
			return rc;
		}
	}