package InteractiveBrokerageProject.MobileAutomation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestNGUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;



import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumTest extends BaseTestClass {

	AndroidDriver driver;
	String actualCount1="";
	String actualCount2="";

	@Test
	public void validateDefaultSelectedOptions() throws MalformedURLException {
		try {
		driver=launchBrowser();	
		Thread.sleep(20000);		
		scrollInViewAction(driver.findElement(By.xpath("(//button[text()='Select All/None'])[1]")));
		driver.findElement(By.xpath("(//button[text()='View Results'])[2]")).click();
		Thread.sleep(20000);
		String results=driver.findElement(By.xpath("//*[@class='results-shown']")).getText();
		String splitedResult[]=results.split(" ");
		actualCount1=splitedResult[splitedResult.length-1];
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}
	
	@Test
	public void validateHighYieldOptions() throws MalformedURLException {
		try {
		driver=launchBrowser();	
		Thread.sleep(20000);		
		scrollInViewAction(driver.findElement(By.xpath("//label[text()='Minimum']")));
		driver.findElement(By.xpath("(//button[text()='High Yield'])[1]")).click();
		Thread.sleep(20000);
		scrollInViewAction(driver.findElement(By.xpath("(//button[text()='Select All/None'])[1]")));
		driver.findElement(By.xpath("(//button[text()='View Results'])[2]")).click();
		Thread.sleep(20000);
		String results=driver.findElement(By.xpath("//*[@class='results-shown']")).getText();
		String splitedResult[]=results.split(" ");
		actualCount2=splitedResult[splitedResult.length-1];
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}
	
	@Test
	public void verifyResults() {
		int allSelectedOptionCount=Integer.parseInt(actualCount1.replace(",", ""));
		int highYieldOptionCount=Integer.parseInt(actualCount2.replace(",", ""));
		if(allSelectedOptionCount > highYieldOptionCount) {
			System.out.println("The value with selection all option is "+allSelectedOptionCount+"  and the value after selecting High Yield option is " +highYieldOptionCount);
		}else {
			System.out.println("The value of selecting all options is not greater that after selcting the High yield option");
		}
	}
	@AfterMethod(alwaysRun=true)
	public void takeScreenShot(ITestResult result) throws IOException{
	    if(result.getStatus()==2){
	        ITestNGMethod instance = result.getMethod();
	        String testName = instance.getMethodName();
	        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(screenshotFile , new File("C:\\temp\\"+testName+"_screenshot.png"));
	        driver.quit();
	    }
	}
	

	
}
