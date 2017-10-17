package mercury.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import framework.*;
import framework.TestConfig;
import framework.testCore.Core;
import framework.utils.ExcelUtils;
import mercury.pages.HomePage;


public class Login extends Core {

	@DataProvider
	public String[][] excelData() throws IOException{
		ExcelUtils excelReader = new ExcelUtils();
		return excelReader.getData(TestConfig.testDataFile, "login");
	}
	
	@Test(dataProvider="excelData")
	public void login(String username,String password) throws IOException{
		int rc = -1;
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
			rc = homePage.mercuryLogin(username, password);
			if(rc==0){
				CommonDriver.explicitWait(driver, "//select[@name='passCount']", "visibility", 10);
				String expectedTitle = "Find";
				if(!(driver.getTitle().toLowerCase()).contains(expectedTitle.toLowerCase())){
					CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/Login.jpg");
					Assert.fail("Login Failed! Please Check your credentials");	
					
				}
			}
			else{
				CommonDriver.takeSnapshotJPG(driver, TestConfig.screenshotfailed + "/Login.jpg");
				Assert.fail("Problem encountered while logging in");				
			}
	}	
}
