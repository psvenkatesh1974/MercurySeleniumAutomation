package mercury.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import framework.testCore.Core;

public class FlightConfirmation {

	WebDriver driver;
	
	@FindBy(xpath =".//*[contains(text(),'itinerary has been booked')]")
	@CacheLookup
	WebElement msgFlightConfirmationBooking;		
	@FindBy(xpath="//img[@src='/images/forms/Logout.gif']")
	@CacheLookup
	WebElement btnLogout;
	
	//Constructor
	public FlightConfirmation(WebDriver driver){
		this.driver = Core.driver;
	}
	  
	//verfiy only for the confirmation message. 
	//Though in the real scenario we will be verifying other details of the booking parameters in this page.
	public int verifyBooking(){
		
		int rc =0;		
		try{
			String title = driver.getTitle();
			if(!title.toLowerCase().contains("confirmation")){
				rc = -1;						
			}
			else{ 
				if(!msgFlightConfirmationBooking.getText().toLowerCase().contains("itinerary has been booked")){
					rc = -1;					
				}
			}
		}catch(Exception e){
			rc = -1;
			e.printStackTrace();
			return rc;
		}		
		return rc;
	}
	
	

	public int clicklogout(){
		int rc = 0;
		try {
			btnLogout.click();
			String title = driver.getTitle();
			if(!title.toLowerCase().contains("sign-on")){
				rc = -1;						
			}
		} catch (Exception e) {
			rc = -1;
			e.printStackTrace();
			return rc;
		}		
		return rc;
	}
}
