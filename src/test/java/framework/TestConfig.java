package framework;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class TestConfig {

	private static String testRoot = "./src/test";
	private static String configFile = testRoot + "/java/framework/config.properties";
	
	public static String firefoxDriver = testRoot + "/resources/drivers/geckodriver.exe";
	public static String chromeDriver = testRoot + "/resources/drivers/chromedriver.exe";
	public static String ieDriver = testRoot + "/resources/drivers/IEDriverServer.exe";	
	public static String screenshotpassed = "./test-output/screenshots/passed";
	public static String screenshotfailed = "./test-output/screenshots/failed";	
	public static String testDataFile = getConfigProperty("testdata");	
	public static String browser = getConfigProperty("browser");
	public static String url = getConfigProperty("url");
	public static String reportfile = getConfigProperty("reportfile");
	
	private static String getConfigProperty(String key) {		
		File file = new File(configFile);
		Properties config=null;		
		try{
		FileReader reader = new FileReader(file);	
		config = new Properties();
		config.load(reader);
		}catch(Exception e){
			e.printStackTrace();
		}
		return config.getProperty(key);
	}	
}