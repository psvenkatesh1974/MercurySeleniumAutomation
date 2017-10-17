package mercury.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import framework.testCore.Core;

import java.util.List;

public class SelectFlight {

	WebDriver driver;
	
	@FindAll(@FindBy(name="outFlight"))
	@CacheLookup
	List<WebElement> lstoutFlights;	
	@FindAll(@FindBy(name="inFlight"))
	@CacheLookup
	List<WebElement> lstreturnFlights;		
	@FindBy(xpath="//input[@name='reserveFlights']")
	@CacheLookup
	WebElement btnContinue;
	
	//Constructor
	public SelectFlight(WebDriver driver){
		this.driver = Core.driver;
	}
	  
	public int selectDepartFlight(String flightName, String flightNo){
		
		int rc =0;
		int i = 0;
		try{
			String title = driver.getTitle();
			if(!title.toLowerCase().contains("select")){
				rc = -1;						
			}
			else{
							
				for(WebElement flight : lstoutFlights){
					  String flightname = flight.getAttribute("value");
					  if(flightname.contains(flightName) && flightname.contains(flightNo)){
						  lstoutFlights.get(i).click();
						  i=-1;
						  break;
					  }
					  i++;
				  }
				if(i==0){	//If flight was not found
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
	
	
	public int selectReturnFlight(String flightName,String flightNo){
		
		int rc =0;
		int i = 0;
		try{
			String title = driver.getTitle();
			if(!title.toLowerCase().contains("select")){
				rc = -1;						
			}
			else{
							
				for(WebElement flight : lstreturnFlights){
					  String flightname = flight.getAttribute("value");
					  if(flightname.contains(flightName) && flightname.contains(flightNo)){
						  lstreturnFlights.get(i).click();
						  i=-1;
						  break;
					  }
					  i++;
				  }
				if(i==0){	//If flight was not found
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
