package mercury.testcases;

import java.io.IOException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.CommonDriver;
import framework.TestConfig;
import framework.testCore.Core;
import mercury.pages.Book;
import mercury.pages.FlightConfirmation;
import mercury.pages.FlightFinder;
import mercury.pages.SelectFlight;

public class BookFlight extends Core {

	@Test(priority=1)
	public void findFlight() throws IOException{
		int rc = -1;
		FlightFinder flighfinder = PageFactory.initElements(driver, FlightFinder.class);
		rc = flighfinder.selectFlightDetails("roundtrip", "2", "Frankfurt", "London", "October", "2", "November", "4");
		if(rc ==0){
			rc = flighfinder.selectFlightPreferences("economy", "");
			if(rc==0){
				flighfinder.clickContinue();				
				String expectedTitle = "Select";
				if(!(driver.getTitle().toLowerCase()).contains(expectedTitle.toLowerCase())){
					CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/Flighfinder.jpg");
					Assert.fail("Flight Find did not finish successfully!!!");					
				}
			}
			else{
				CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/Flighfinder.jpg");
				Assert.fail("Category class selection failed in Find Flight Page!!!");				
			}
		}
		else{
			CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/Flighfinder.jpg");
			Assert.fail("Flight Detail Selection Failed in Find Flight Page!!!");			
		}
	}
	
	
	@Test(priority=2)
	public void selectFlight() throws IOException{
		int rc = -1;
		SelectFlight selectflight = PageFactory.initElements(driver, SelectFlight.class);
		rc = selectflight.selectDepartFlight("Pangaea Airlines", "362");
		if(rc==0){
			rc = selectflight.selectReturnFlight("Unified Airlines", "633");
			if(rc==0){
				selectflight.clickContinue();
				String expectedTitle = "Book";
				if(!(driver.getTitle().toLowerCase()).contains(expectedTitle.toLowerCase())){
					CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/SelectFlight.jpg");
					Assert.fail("Flight Selection did not finish successfully!!!");					
				}
			}
			else{
				CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/SelectFlight.jpg");
				Assert.fail("Return flight selection failed in the Select Flight Page!!!");	
			}
		}
		else{
			CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/SelectFlight.jpg");
			Assert.fail("Departure flight selection failed in the Select Flight Page!!!");
		}
	}
	
	
	@Test(priority=3)
	public void bookPurchase() throws IOException{
		int rc = -1;
		Book book = PageFactory.initElements(driver, Book.class);
		rc = book.enterPassengerDetails("Venkat", "Shah");
		if(rc==0){
			rc = book.enterCardDetails("3333444455556666");
			if(rc==0){
				rc = book.clickSecurePurchase();
				FlightConfirmation fc = PageFactory.initElements(driver, FlightConfirmation.class);
				if(rc==0){					
					rc = fc.verifyBooking();
					if(rc!=0){
						CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/FlightConformation.jpg");
						Assert.fail("Flight Booking Confirmation Failed. Could not be verified in the confirmation page!!!");
					}									
				}
				fc.clicklogout();
				fc=null;
			}
			else{
				CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/BookPurchase.jpg");
				Assert.fail("Card details could not be entered in the Book Flight Page!!!");
			}		
		}
		else{
			CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/BookPurchase.jpg");
			Assert.fail("Passenger details could not be entered in the Book Flight Page!!!");
		}
	}
}