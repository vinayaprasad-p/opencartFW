package testBases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class ClassBase {

	
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass (groups= {"Sanity", "Regression", "Master"})
	@Parameters ({ "browser", "os" })
	public void setup(String br, String os) throws IOException {
		//loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		
		//to run remote execution
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//for setting the os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			
			if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			
			else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			
			else {
				System.out.println("No operating system is found");
				return;
			}
			
			//for browser
			switch(br.toLowerCase()){
			case "chrome" : capabilities.setBrowserName("chrome"); break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : capabilities.setBrowserName("firefox"); break;
			default : System.out.println("Invalid browser"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
		}
		
		//for local excecution
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("invalid browser"); return;
			
			}
		}
		
		/*switch(br.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("invalid browser"); return;
		
		}*/
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//driver.get("https://tutorialsninja.com/demo/");	
		driver.get(p.getProperty("appURL"));   //reading url from property file
		driver.manage().window().maximize();
	}
	
	@AfterClass (groups= {"Sanity", "Regression", "Master"})
	public void teardown() {
		driver.quit();
	}
	
	
	

	//we have to genetare random string for email
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(6);
		return generatedString;
	}
	
	//to get random numbers
	public String randomNumber() {
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	//to get password with mixed number and string 
	public String randomPassword() {
		String generateStri = RandomStringUtils.randomAlphabetic(8);
		String generatenum = RandomStringUtils.randomNumeric(8);
		
		return (generateStri +"@"+ generatenum);
	}
	
	public String captureScreen(String tname) throws IOException {
		
		String currentdatetimeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); //Generates current date & time
		
		TakesScreenshot ts = (TakesScreenshot) driver; //WebDriver → converted into screenshot capability, Now ts can take screenshots
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);  //Takes screenshot and stores it as a temporary file
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + currentdatetimeStamp + ".png"; //screenshot where to be stored in the folder
		File targetFile = new File(targetFilePath); //Represents where screenshot will be saved
		
		sourceFile.renameTo(targetFile); //Moves screenshot from temp → your folder
		//FileHandler.copy(sourceFile, targetFile);
		
		return targetFilePath;  //Returns screenshot location
	}

}

