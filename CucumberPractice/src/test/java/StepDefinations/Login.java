package StepDefinations;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import Pages.LoginPage;

import org.junit.Assert;


import driverPackage.driverClass;
import driverPackage.lamdaDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class Login {
	
	
	
	//public LoginPage loginPage = new LoginPage(driverClass.GetDriver());
	

	 

	private lamdaDriver driverFactory_Remote;
	private WebDriver driver;
	private driverClass driverFactory_Local;
	 private String Status = "failed";


	 private By emailId = By.xpath("//input[@name='email']");
		private By password = By.xpath("//input[@name='password']");
		private By signInButton = By.xpath("//div[text()='Login']");
		private By forgotPwdLink = By.linkText("Forgot your password?111");
	 
	 
	 

		@Before(order =0)
		public void openBrowser()  {

			//For Remote

			driverFactory_Remote = new lamdaDriver();
			driver = lamdaDriver.Remote_Init_Driver();

			//For Local
			//driverFactory_Local = new driverClass();
			//driver = driverFactory_Local.Local_Init_Driver();
		}

	
	@Given("Open the application")
	public void i_want_to_Open_application() {
		
		
		
		
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println("Open the application");
	   
	   //For Remote
	   lamdaDriver.GetDriver().get("https://ui.cogmento.com/");
	   
	  //For Local
	   //driverClass.GetDriver().get("https://ui.cogmento.com/");
	   
	}

	@And("^Login to the application by (.*)and(.*)$")
	public void some_other_precondition(String Username, String Password) {
		//loginPage.enterUserName(Username);
		//loginPage.enterPassword(Password);
		//loginPage.clickOnLogin();
		
		driver.findElement(emailId).sendKeys(Username);
		driver.findElement(password).sendKeys(Password);
		driver.findElement(signInButton).click();
		
	}

	@When("The application is logged in")
	public void i_complete_action() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("I complete action");
	}


	@Then("the title will be {string}")
	public void check_more_outcomes(String ActualResutl) {
		String title = driver.getTitle();
		Assert.assertEquals(title, ActualResutl);
		Status = "passed";
		 
	}



	@After(order = 0)

	public void CloseBrowser() throws Throwable {

		if(driver!=null) {
			
			((JavascriptExecutor) driver).executeScript("lambda-status=" + Status);

			driver.quit();

			//For Remote
			lamdaDriver.DriverRemove_Remote();	

			//For Local
			//driverFactory_Local.DriverRemove_Local();
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
