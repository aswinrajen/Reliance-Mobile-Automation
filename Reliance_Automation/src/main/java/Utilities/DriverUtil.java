package Utilities;

import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class DriverUtil.
 */
public class DriverUtil extends KeywordUtil
{
	/* The this class. */
	static Class<?> thisClass = DriverUtil.class;
	/* The Constant URL. */
	public static String URL = null;
	/* The wait. */
	@SuppressWarnings("rawtypes")
	protected static Wait wait;
	public static String webBrowserName;
	public static String platformName;
	public static String PackageName;

	/**
	 * Gets the browser.
	 *
	 * @param browserNameFromExcel
	 *            the browser name from excel
	 * @return the browser
	 * @throws Exception
	 *             the exception
	 */
	public static void getBrowser(String browserNameFromExcel) throws Exception
	{
		webBrowserName = browserNameFromExcel;
		System.out.println(webBrowserName);
		platformName = System.getProperty("os.name");
		DesiredCapabilities capabilities = new DesiredCapabilities();

		if(browserNameFromExcel.equalsIgnoreCase("Local Real device"))
		{
			capabilities.setCapability("platformName","Android");
			capabilities.setCapability("deviceName", "vivo Y51A");
			capabilities.setCapability("udid", "96357341230027S");
			capabilities.setCapability("chromedriverExecutable","D:\\Reliance_Automation\\Reliance_Project\\Reliance\\src\\main\\resources\\Driver\\chromedriver.exe");
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			capabilities.setCapability(CapabilityType.VERSION, "12.0");
			capabilities.setCapability("noReset",true);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("androidKeepAppDataDir", true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		  driver= new AppiumDriver (new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			driver.context("CHROMIUM");
		}
		else if(browserNameFromExcel.equalsIgnoreCase("BrowserStack"))
		{
			
			capabilities.setCapability("browserstack.user", "saiavinashpenubo_R9twBL");
			capabilities.setCapability("browserstack.key", "wYt3uejW5ACPRECuqfor");
			capabilities.setCapability("browserName", "chrome");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
			browserstackOptions.put("osVersion", "8.0");
			browserstackOptions.put("deviceName", "Samsung Galaxy S9");
			browserstackOptions.put("realMobile", "true");
			browserstackOptions.put("appiumVersion", "1.22.0");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("networkLogs", "true");
			browserstackOptions.put("interactiveDebugging", "true");
			capabilities.setCapability("bstack:options", browserstackOptions);
			driver = new AppiumDriver(new URL("http://hub.browserstack.com/wd/hub"), capabilities);		
			driver.context("CHROMIUM");   
		}

		else 

		{
			
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability("deviceName", "Google Pixel");
			capabilities.setCapability("platformVersion", "8");
			driver =new AndroidDriver(new URL("https://dorankulamounika:Nf6ttrtNyLmyuude5wX6T4am3hc1mSjmqz5e2GpxbFr7nQdbTI@hub.lambdatest.com/wd/hub"), capabilities);
			driver.context("CHROMIUM");
		}

	}

}