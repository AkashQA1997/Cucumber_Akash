package driverPackage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class driverClass {


	static ThreadLocal<WebDriver> TLDriver = new ThreadLocal<WebDriver>();
	
	  //static RemoteWebDriver driver = null;
	
	public WebDriver Local_Init_Driver()  {
		
	
	  
	  
		if (GetDriver()==null) {
			
	  WebDriverManager.edgedriver().setup();
			TLDriver.set( new EdgeDriver());
	    

	    GetDriver().manage().window().maximize();
	    GetDriver().manage().deleteAllCookies();
	   // GetDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	   // GetDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	    
	    
	}
		return GetDriver();
	}
	
	public void DriverRemove_Local() {
		GetDriver().quit();
		TLDriver.remove();
		
	}
	
	public static synchronized WebDriver GetDriver() {
		
		return TLDriver.get();
	}

		




	}





	



