package Hooks;

import org.testng.annotations.AfterMethod;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import driverPackage.driverClass;
import driverPackage.lamdaDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class hooks {


	private lamdaDriver driverFactory_Remote;
	private WebDriver driver;
	private driverClass driverFactory_Local;
	 private String Status = "failed";


	
	@Before(order =0)
	public void openBrowser()  {

		//For Remote

		//driverFactory_Remote = new lamdaDriver();
		//driver = lamdaDriver.Remote_Init_Driver();

		//For Local
		driverFactory_Local = new driverClass();
		driver = driverFactory_Local.Local_Init_Driver();
	}




	
	@After(order = 0)

	public void CloseBrowser() throws Throwable {

		if(driver!=null) {
			
			((JavascriptExecutor) driver).executeScript("lambda-status=" + Status);

			driver.quit();

			//For Remote
			//lamdaDriver.DriverRemove_Remote();	

			//For Local
			driverFactory_Local.DriverRemove_Local();
		}



	}

	
	
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}

	}

}
