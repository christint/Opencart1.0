package testBase;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	@BeforeClass(groups= {"master","regression"})
	@Parameters({"os","browser"})
	public void setup(String os,String browser) throws IOException {
		//load config.properties values
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		prop=new Properties();
		prop.load(file);
		//log 4J
		logger=LogManager.getLogger(this.getClass());
		//local / remote
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			//os for remote execution
			if(os.equalsIgnoreCase("windows")) {
				System.out.println("remotedriver os:windows");
				cap.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("linux"))  {				
				cap.setPlatform(Platform.LINUX);
			}			
			else{
				System.out.println("noo matching OS in testng xml-so exit");
				return;
			}
			//browser for remote execution
			switch(browser.toLowerCase())
			{			
			case "chrome":	cap.setBrowserName("chrome");
			System.out.println("remotedriver browser setup started");break;
			case "edge":	cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox":	cap.setBrowserName("firefox"); break;
			default:
				System.out.println("invalid browser for remote execution in testng xml-so exit");
				return;
			}
			//remote driver creation
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);	
			System.out.println("remote webdriver created");
		}
		else {
			//local execution
			switch(browser.toLowerCase())
			{
			case "chrome":	driver=new ChromeDriver(); break;
			case "edge":	driver=new EdgeDriver(); break;
			case "firefox":	driver=new FirefoxDriver(); break;
			default:
				System.out.println("invalid browser");
				return;
			}
			System.out.println("local webdriver created");
		}
		System.out.println("abt to launch URL");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		///driver.get("https://tutorialsninja.com/demo/");
		//read property file value
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	@AfterClass(groups= {"master","regression"})
	public void teardown() {
		driver.quit();
	}

	public String getRandomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}
	public String getRandomNumeric() {
		return RandomStringUtils.randomNumeric(10);
	}
	//screenshot function
	public String captureScreen(String tname) {		
		String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sourceFile=ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilepath=System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
		File targetfile=new File(targetFilepath);
		
		sourceFile.renameTo(targetfile);
		return targetFilepath;
	}
}
