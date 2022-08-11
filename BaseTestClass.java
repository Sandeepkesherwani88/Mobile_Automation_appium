package InteractiveBrokerageProject.MobileAutomation;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTestClass {

	AndroidDriver driver;
	
	
	public AndroidDriver launchBrowser() throws MalformedURLException {
		UiAutomator2Options options=new UiAutomator2Options();
		options.setDeviceName("Sandeep29");
		options.setChromedriverExecutable("C:\\Drivers\\chromedriver_win32 (3)\\chromedriver.exe");
		options.setCapability("browserName", "Chrome");
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
		driver.get("http://www.interactivebrokers.com/en/index.php?f=45559#/");
		return driver;
	}
	
	public void setImplcitWait(int totalTimeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(totalTimeout));
	}
	
	public void scrollInViewAction(WebElement element) {
		String javascript = "arguments[0].scrollIntoView()";  
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript(javascript, element);
	}
	

}
