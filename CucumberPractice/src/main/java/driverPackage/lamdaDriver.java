package driverPackage;

import java.util.HashMap;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class lamdaDriver {

	static ThreadLocal<RemoteWebDriver> TLDriver = new ThreadLocal<RemoteWebDriver>();

	//static RemoteWebDriver driver = null;

	public static RemoteWebDriver Remote_Init_Driver()  {

		if (GetDriver()==null) {


			String username = "akashsaha17051997";
			String access_key = "bH5WLyFH8NMO9ueGz4SkdvMtZcMvuzXeuyPvFCALYBNT0BdmrS";



			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName("Windows 10");
			browserOptions.setBrowserVersion("120.0");
			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("username", username);
			ltOptions.put("accessKey", access_key);
			ltOptions.put("visual", true);
			ltOptions.put("video", true);
			ltOptions.put("build", "Test_Remote");
			ltOptions.put("project", "Demo_Test");
			ltOptions.put("selenium_version", "4.2.0");
			ltOptions.put("w3c", true);
			ltOptions.put("plugin", "java-testNG");
			ltOptions.put("name", "test_Demo");
			ltOptions.put("console", "true");
			browserOptions.setCapability("LT:Options", ltOptions);

			try {
				TLDriver.set( new RemoteWebDriver (new URL ("https://" + username + ":" + access_key + "@hub.lambdatest.com/wd/hub"), browserOptions));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			GetDriver().manage().window().maximize();
			GetDriver().manage().deleteAllCookies();
			GetDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			GetDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));


		}
		return GetDriver();
	}
	
	


	public static void DriverRemove_Remote() {
		GetDriver().quit();
		TLDriver.remove();

	}

	public static synchronized RemoteWebDriver GetDriver() {

		return TLDriver.get();
	}

}
