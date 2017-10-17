package mercury.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.CommonDriver;
import framework.TestConfig;
import framework.testCore.Core;
import framework.utils.ExcelUtils;
import mercury.pages.HomePage;

public class UserCredentialValidations extends Core {

	@DataProvider
	public String[][] excelData() throws IOException{
		ExcelUtils excelReader = new ExcelUtils();
		return excelReader.getData(TestConfig.testDataFile, "credentials");
	}
	
	
	@Test(dataProvider="excelData")
	public void login(String username,String password) throws IOException{
		int rc = -1;
		driver.get(TestConfig.url);
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			rc = homePage.mercuryLogin(username, password);
			if(rc==0){				
				String expectedTitle1 = "Sign";
				String expectedTitle2 = "Find";
				if((driver.getTitle().toLowerCase()).contains(expectedTitle1.toLowerCase())){
					Reporter.log("Negative test to check the credentials passed");
				}
				if((driver.getTitle().toLowerCase()).contains(expectedTitle2.toLowerCase())){
					Reporter.log("Positive test to check the credentials passed");								
				}
			}
			else{
				CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/Login.jpg");
				Assert.fail("Problem encountered while logging in");				
			}
	}	
}
