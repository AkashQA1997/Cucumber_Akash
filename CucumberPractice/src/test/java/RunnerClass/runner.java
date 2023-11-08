
package RunnerClass;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;




@CucumberOptions(
		features = {".\\src\\test\\resourses\\FetaureFiles"},
		glue= {"StepDefinations"}
		,plugin = { "pretty",
		
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
				monochrome = true
		)









public class runner  extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();

}
}
