package mercury.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import framework.testCore.Core;

public class FlightFinder {

	WebDriver driver;
	
	@FindBy(xpath=".//input[@value='roundtrip']")
	@CacheLookup
	WebElement radioRoundTrip;
	@FindBy(xpath=".//input[@value='oneway']")
	@CacheLookup
	WebElement radioOneWay;
	@FindBy(xpath="//select[@name='passCount']")
	@CacheLookup
	WebElement selectPassengers;
	@FindBy(xpath="//select[@name='fromPort']")
	@CacheLookup
	WebElement selectFromPort;
	@FindBy(xpath="//select[@name='fromMonth']")
	@CacheLookup
	WebElement selectFromMonth;
	@FindBy(xpath="//select[@name='fromDay']")
	@CacheLookup
	WebElement selectFromDay;
	@FindBy(xpath="//select[@name='toPort']")
	@CacheLookup
	WebElement selectToPort;	
	@FindBy(xpath="//select[@name='toMonth']")
	@CacheLookup
	WebElement selectReturnMonth;
	@FindBy(xpath="//select[@name='toDay']")
	@CacheLookup
	WebElement selectReturnDay;
	@FindBy(xpath="//input[@value='Coach']")
	@CacheLookup
	WebElement radioEconomyClass;
	@FindBy(xpath="//select[@name='Business']")
	@CacheLookup
	WebElement radioBusinessClass;
	@FindBy(xpath="//select[@name='First']")
	@CacheLookup
	WebElement radioFirstClass;
	@FindBy(xpath="//select[@name='airline']")
	@CacheLookup
	WebElement selectairline;
	@FindBy(xpath="//input[@name='findFlights']")
	@CacheLookup
	WebElement btnContinue;
	
	//Constructor
	public FlightFinder(WebDriver driver){
		this.driver = Core.driver;
	}
	  
	public int selectFlightDetails(String triptype, String passengers,String sourceport, String destport, String frommonth, String fromday, String returnmonth,String returnday){
		
		int rc =0;
		try{
			String title = driver.getTitle();
			if(!title.toLowerCase().contains("find")){
				rc = -1;						
			}
			else{
				if(!triptype.isEmpty()){
					if(triptype.equalsIgnoreCase("roundtrip")){						
						radioRoundTrip.click();
					}
					else if(triptype.equalsIgnoreCase("oneway")){
						radioOneWay.click();
					}
				}				
				if(!passengers.isEmpty()){
					Select p = new Select(selectPassengers);
					p.selectByVisibleText(passengers);		
				}				
				if(!sourceport.isEmpty()){
					Select p = new Select(selectFromPort);
					p.selectByVisibleText(sourceport);		
				}
				if(!destport.isEmpty()){
					Select p = new Select(selectToPort);
					p.selectByVisibleText(destport);						
				}
				if(!frommonth.isEmpty()){
					Select p = new Select(selectFromMonth);
					p.selectByVisibleText(frommonth);	
					//p.selectByVisibleText(frommonth);		
				}
				if(!fromday.isEmpty()){
					Select p = new Select(selectFromDay);
					p.selectByVisibleText(fromday);		
				}
				if(!returnmonth.isEmpty()){
					Select p = new Select(selectReturnMonth);
					p.selectByVisibleText(returnmonth);		
				}
				if(!returnday.isEmpty()){
					Select p = new Select(selectReturnDay);
					p.selectByVisibleText(returnday);		
				}
				
			}
		}catch(Exception e){
			rc = -1;
			e.printStackTrace();
			return rc;
		}		
		return rc;
	}
	
	
	public int selectFlightPreferences(String categoryclass, String airline){
		int rc = 0;
		try{
			if(!categoryclass.isEmpty()){
				if(categoryclass.equalsIgnoreCase("economy")){
					radioEconomyClass.click();
				}
				if(categoryclass.equalsIgnoreCase("business")){
					radioBusinessClass.click();
				}
				if(categoryclass.equalsIgnoreCase("first")){
					radioFirstClass.click();
				}
			}
			
		}catch(Exception e){
			rc = -1;
			e.printStackTrace();
			return rc;
		}		
		return rc;
	}
	
	public int clickContinue(){
		int rc = 0;
		try {
			btnContinue.click();
		} catch (Exception e) {
			rc = -1;
			e.printStackTrace();
			return rc;
		}		
		return rc;
	}
}
