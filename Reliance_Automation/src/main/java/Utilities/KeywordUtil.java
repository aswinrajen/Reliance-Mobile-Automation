package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


// TODO: Auto-generated Javadoc
/**
 * The Class KeywordUtil.
 */
public class KeywordUtil extends Utility
{
	/** The fail. */
	protected static int fail = 0;
	public static String step;
	/** The web element. */
	static WebElement webElement;
	/** The url. */
	protected static String url = "";
	/** The user dir. */
	private static String userDir = "user.dir";
	/** The splitted mail id. */
	public static String[] splittedMailId;
	/** The time. */
	public static long time;
	/** The logging step. */
	public static String logging_step;
	public static String environment;
	public static StringBuilder sb = new StringBuilder();
	static int retryCount = getIntValue("retryCount");
	/** The retrying number. */
	static int retryingNumber = 1;

	/**
	 * This function opens the website. The URL is mentioned in the
	 * config.properties.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public static void navigate() throws Exception
	{
		try
		{
			driver.manage().deleteAllCookies();
			if (System.getProperty("url") == null)
				url = getValue("BASE_URL");
			else
				url = System.getProperty("url");
			if (url.contains("staging"))
				environment = "Staging";
			else
				environment = "Production";
			driver.navigate().to(url);
			executionDelay(6000);
			executionDelay(5000);
			driver.manage().timeouts().pageLoadTimeout(getIntValue("pageLoadTimeOut"), TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(getIntValue("implicitlyWait"), TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		catch (Exception e)
		{
			LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
		}
	}

	/**
	 * Perform Double click.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean doubleClick(By by) throws InterruptedException
	{
		Actions action = new Actions(driver);
		executionDelay(1000);
		action.doubleClick(returnWebElement(by)).build().perform();
		return true;
	}

	/**
	 * Perform Click twice.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean ClickTwice(By by) throws InterruptedException
	{
		WebElement element = returnWebElement(by);
		executionDelay(3000);
		element.click();
		element.click();
		return true;
	}

	/**
	 * Check string contains.
	 *
	 * @param subString
	 *            the sub string
	 * @param textString
	 *            the text string
	 * @return true, if successful
	 */
	public static boolean checkStringContains(String subString, String textString)
	{
		if (textString.contains(subString))
			return true;
		else
			return false;
	}

	/**
	 * Check strings equal ignore case.
	 *
	 * @param actual
	 *            the actual
	 * @param expected
	 *            the expected
	 * @return true, if successful
	 */
	public static boolean checkStringsEqualIgnoreCase(String actual, String expected)
	{
		if (expected.equalsIgnoreCase(actual))
			return true;
		else
			return false;
	}

	/**
	 * Generate random mail id by appending Nano seconds.
	 *
	 * @param mailId
	 *            the mail id
	 * @return the string
	 */
	public static String generateRandomMailIdByAppendingNanoSeconds(String mailId)
	{
		splittedMailId = mailId.split("@");
		// these two lines get milli seconds
		// cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		// long time = cal.getTimeInMillis();
		time = System.nanoTime();
		mailId = splittedMailId[0] + time + "@" + splittedMailId[1];
		return mailId;
	}

	/**
	 * Split A string on comma.
	 *
	 * @param inputData
	 *            the input data
	 * @return the string[]
	 */
	public static String[] splitAStringOnComma(String inputData)
	{
		splittedMailId = inputData.split(",");
		return splittedMailId;
	}

	/**
	 * Handles Window authentication popup.
	 *
	 * @param url
	 *            the url
	 * @throws Exception
	 *             the exception
	 */
	/**
	 * Press Enter key in a field.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void enter() throws InterruptedException
	{
		try
		{
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).perform();
			executionDelay(1000);
		}
		catch (Exception e)
		{
			LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
		}
	}

	/**
	 * Keyboard enter using AutoIt.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	/**
	 * Locator type.
	 *
	 * @param type
	 *            the type
	 * @param value
	 *            the value
	 * @return the by
	 */
	public static By locatortype(String type, String value)
	{
		By locName;
		if ("xPath".equalsIgnoreCase(type))
		{
			locName = By.xpath(value);
		} else if ("linkText".equalsIgnoreCase(type))
		{
			locName = By.linkText(value);
		} else if ("classname".equalsIgnoreCase(type))
		{
			locName = By.className(value);
		} else if ("name".equalsIgnoreCase(type))
		{
			locName = By.name(value);
		} else if ("id".equalsIgnoreCase(type))
		{
			locName = By.name(value);
		} else
			locName = By.partialLinkText(value);
		return locName;
	}

	/**
	 * Checks if is web element present.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if is web element present
	 */
	public static boolean isWebElementPresent(String path, String type)
	{
		By by = locatortype(type, path);
		WebElement element = returnWebElement(by);
		// highLightElement(driver, element);
		boolean elementPresent = element.isDisplayed();
		return elementPresent;
	}

	/**
	 * Gets the text.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return the text
	 */
	public static String getText(String path, String type)
	{
		By by = locatortype(type, path);
		WebElement element = returnWebElement(by);
		highLightElement(driver, element);
		String elementText = element.getText();
		return elementText;
	}

	/**
	 * Gets the text.
	 *
	 * @param by
	 *            the by
	 * @return the text
	 */
	public static String getText(By by)
	{
		WebElement element = explicitWaitForElementVisible(by);
		String elementText = element.getText();
		return elementText.trim();
	}

	/////gettext Novisibility//////////////
	public static String getTextNoVisibility(By by)
	{
		/* By by = locatortype(type, path); */
		WebElement element = driver.findElement(by);
		highLightElement(driver, element);
		String elementText = element.getText();
		return elementText;
	}

	/**
	 * Gets the text for A list of web elements.
	 *
	 * @param by
	 *            the by
	 * @return the text for A list of web elements
	 */
	public static List<String> getTextForAListOfWebElements(By by)
	{
		List<WebElement> listOfElements = returnWebElements(by);
		List<String> textOfListElements = new ArrayList<>();
		for (WebElement iterator : listOfElements)
		{
			textOfListElements.add(iterator.getText());
		}
		return textOfListElements;
	}
	
	public static List<String> getCountForAListOfWebElements(By by)
	{
		List<WebElement> listOfElements = returnWebElements(by);
		List<String> textOfListElements = new ArrayList<>();
		for (WebElement iterator : listOfElements)
		{
			textOfListElements.add(iterator.getText());
		}
		return textOfListElements;
	}

	
	/**
	 * Gets the attribute.
	 *
	 * @param by
	 *            the by
	 * @param attributeName
	 *            the attribute name
	 * @return the attribute
	 */
	public static String getAttribute(By by, String attributeName)
	{
		WebDriverWait wait = new WebDriverWait(driver, getIntValue("explicit_timeout"));
		// wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		WebElement element = driver.findElement(by);
		String elementAttributeValue = element.getAttribute(attributeName);
		return elementAttributeValue;
	}

	/* for no webdriver Wait */
	public static String getAttributeNoVisibilityWait(By by, String attributeName)
	{
		WebDriverWait wait = new WebDriverWait(driver, getIntValue("explicit_timeout"));
		// wait.until(ExpectedConditions.presenceOfElementLocated(by));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		WebElement element = driver.findElement(by);
		highLightElement(driver, element);
		String elementAttributeValue = element.getAttribute(attributeName);
		return elementAttributeValue;
	}

	/**
	 * Checks if is web element not present.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if is web element not present
	 */
	public static boolean isWebElementNotPresent(String path, String type)
	{
		By by = locatortype(type, path);
		WebElement element = returnWebElement(by);
		// highLightElement(driver, element);
		boolean elementPresent = element.isDisplayed();
		return !elementPresent;
	}

	/**
	 * Checks if is web element not present.
	 *
	 * @param by
	 *            the by
	 * @return true, if is web element not present
	 */
	public static boolean isWebElementNotPresent(By by)
	{
		WebElement element = returnWebElement(by);
		// highLightElement(driver, element);
		boolean elementPresent = element.isDisplayed();
		return !elementPresent;
	}

	/**
	 * Checks if is web element present by list.
	 *
	 * @param locator
	 *            the locator
	 * @return true, if is web element present by list
	 */
	public static boolean isWebElementPresentByList(By locator)
	{
		List<WebElement> elements = (List<WebElement>) driver.findElements(locator);
		if (elements.size() != 0)
		{
			return true;
		} else
			return false;
	}

	/**
	 * Checks if is web element present.
	 *
	 * @param by
	 *            the by
	 * @return true, if is web element present
	 */
	public static boolean isWebElementPresent(By by)
	{
		WebElement element = returnWebElement(by);
		highLightElement(driver, element);
		boolean elementPresent = element.isDisplayed();
		return elementPresent;
	}

	/**
	 * Checks if is attribute of web element present.
	 *
	 * @param locator
	 *            the locator
	 * @param attributeName
	 *            the attribute name
	 * @return true, if is attribute of web element present
	 */
	public static boolean isAttributeOfWebElementPresent(By locator, String attributeName)
	{
		WebElement element = explicitWaitForElementVisible(locator);
		String attributePresent = element.getAttribute(attributeName);
		return (attributePresent == null);
	}
	/**
	 * Touch Actions
	 *
	 * @return the touch action
	 */
	public static void scrollVerticallyUpTillElementFoundForDropdown(By by) 
	{
		boolean status = false;
		int counter = 1;
		WebDriverWait wait = new WebDriverWait(driver, 2);

		while (!status && counter < 15) {
			try {
			
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				status = true;
				break;
			} catch (Exception e) {
				System.out.println("entered catch block " + counter + "/t status: " + status);
				WebElement w=driver.findElement(By.xpath("//android.widget.ListView[@index='0']"));
				System.out.println(w.getSize().getWidth());
				System.out.println(w.getSize().getHeight());
				System.out.println(w.getLocation().getX());
				System.out.println(w.getLocation().getY());
				
				
				int width=(w.getSize().getWidth()/2)+w.getLocation().getX();
				int startHeight=w.getSize().getHeight()+(w.getLocation().getY()*4/5);
				int endHeight=w.getSize().getHeight()+(w.getLocation().getY()*2/5);
				
				System.out.println("start" + startHeight + "/n end" + endHeight);

				new TouchAction(driver).press(PointOption.point(width,startHeight))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
						.moveTo(PointOption.point(width, endHeight)).release().perform();
				counter++;
			}

		}
	}
	
	/**
	 * Touch Actions
	 *
	 * @return the touch action
	 */
	public static void scrollVerticallyUpTillElementFound(By by) {
		boolean status = false;
		int counter = 1;
		WebDriverWait wait = new WebDriverWait(driver, 2);

		while (!status && counter < 8) {
			try {
			
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				status = true;
				break;
			} catch (Exception e) {
				System.out.println("entered catch block " + counter + "/t status: " + status);
				int Width = (driver.manage().window().getSize().getWidth());
				int height = driver.manage().window().getSize().getHeight();
				System.out.println(Width);
				System.out.println(height);
				System.out.println("start" + (height * 4 / 5) + "/n end" + (height / 8));

				new TouchAction(driver).press(PointOption.point(Width / 2, height * 4 / 5))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
						.moveTo(PointOption.point(Width / 2, height / 8)).release().perform();
				counter++;
			}

		}
	}
	/**
	 * Touch Actions
	 *
	 * @return the touch action
	 */
	public static void scrollhorizontallyleftTillElementFound(By by,int y)
	{
		boolean status = false;
		int counter = 1;
		WebDriverWait wait = new WebDriverWait(driver, 05);

		while (!status && counter < 4) {
			try {
			
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				status = true;
				break;
			} catch (Exception e) {
				System.out.println(e.getCause());
				System.out.println("entered catch block " + counter + "/t status: " + status);
				int Width = (driver.manage().window().getSize().getWidth());
				int height = driver.manage().window().getSize().getHeight();
				System.out.println(Width);
				System.out.println(y)
;
				
				new TouchAction(driver).press(PointOption.point(Width, y))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
						.moveTo(PointOption.point(0, y)).release().perform();
				counter++;
			}

		}	
	}

	/**
	 * Explicit wait object.
	 *
	 * @return the web driver wait
	 */
	public static WebDriverWait explicitWaitObject()
	{
		Long explicit_time = Long.parseLong(getValue("explicit_timeout"));
		return new WebDriverWait(driver, explicit_time);
	}

	/**
	 * Return a list of web elements.
	 *
	 * @param aBy
	 *            the a by
	 * @return the list
	 */
	public static List<WebElement> returnWebElements(By aBy)
	{
		WebDriverWait wait = explicitWaitObject();
		// wait.until(ExpectedConditions.presenceOfElementLocated(aBy));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(aBy));
		// wait.until(ExpectedConditions.elementToBeClickable(aBy));
		List<WebElement> webElement = (List<WebElement>) driver.findElements(aBy);
		return webElement;
	}

	/**
	 * Wait tobe clickable.
	 *
	 * @param aBy
	 *            the a by
	 */
	public static void waitTobeClickable(By aBy)
	{
		WebDriverWait wait = explicitWaitObject();
		// wait.until(ExpectedConditions.elementToBeClickable(aBy));
	}

	/**
	 * Wait tobe clickable.
	 *
	 * @param element
	 *            the element
	 */
	public static void waitTobeClickable(WebElement element)
	{
		WebDriverWait wait = explicitWaitObject();
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait for the presence of element.
	 *
	 * @param aBy
	 *            the a by
	 */
	public static void waitForThePresenceOfElement(By aBy)
	{
		WebDriverWait wait = explicitWaitObject();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(aBy));
	}

	/**
	 * Wait for the invisibility of element.
	 *
	 * @param aBy
	 *            the a by
	 */
	public static void waitForTheInvisibilityOfElement(By aBy)
	{
		WebDriverWait wait = explicitWaitObject();
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(aBy));
	}

	/**
	 * Return web element.
	 *
	 * @param aBy
	 *            the a by
	 * @return the web element
	 */
	public static WebElement returnWebElement(By aBy)
	{
		WebDriverWait wait = explicitWaitObject();
		// wait.until(ExpectedConditions.presenceOfElementLocated(aBy));
		wait.until(ExpectedConditions.visibilityOfElementLocated(aBy));
		// wait.until(ExpectedConditions.elementToBeClickable(aBy));
		WebElement webElement = driver.findElement(aBy);
		return webElement;
	}

	/**
	 * Write in input.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean writeInInput(String path, String type, String data)
	{
		By by = locatortype(type, path);
		WebElement element = returnWebElement(by);
		// highLightElement(driver,element );
		element.clear();
		element.sendKeys(data);
		return true;
	}

	/**
	 * Random number.
	 *
	 * @param max
	 *            the max
	 * @param min
	 *            the min
	 * @return the int
	 */
	public static int randomNumber(int max, int min)
	{
		return new Random().nextInt((max - min) + 1) + min;
	}

	/**
	 * Select value.
	 *
	 * @param by
	 *            the by
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean selectValue(By by, String data)
	{
		WebElement select = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", select, data);
		return true;
	}
	///javascript
		public static boolean writeInInput_javaScript(By by, Object data)
		{
			try
			{
				executionDelay(1000);
				WebElement element = returnWebElement(by);
				// highLightElement(driver, element);
				element.clear();
				executionDelay(500);
				element.click();
				element.sendKeys((CharSequence[]) data);
				return true;
			}
			catch (Exception e)
			{ 
				WebElement element = explicitWaitForElementClickable(by);
				element.click();
				((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", element, data);
				return true;
			}
		}
	/**
	 * Write in input string data.
	 *
	 * @param by
	 *            the by
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean writeInInput(By by, String data)
	{
		try
		{
			executionDelay(500);
			WebElement element = returnWebElement(by);
			// highLightElement(driver, element);
			element.clear();
			executionDelay(100);
			element.sendKeys(data);
			return true;
		}
		catch (Exception e)
		{
			WebElement element = explicitWaitForElementVisible(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", element, data);
			return true;
		}
	}

	/**
	 * Write in input integer data.
	 *
	 * @param by
	 *            the by
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean writeInInput(By by, int data)
	{
		executionDelay(3000);
		WebElement element = returnWebElement(by);
		element.clear();
		element.sendKeys(Integer.toString(data));
		return true;
	}

	/**
	 * High light element.
	 *
	 * @param driver
	 *            the driver
	 * @param element
	 *            the element
	 */
	public static void highLightElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 2; i++)
		{
			js.executeScript("arguments[0].setAttribute('style','border: solid 4px yellow');", element);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

	/**
	 * Enter input.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean enterInput(String path, String type, String data)
	{
		WebElement element = driver.findElement(locatortype(type, path));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", element, data);
		return true;
	}

	/**
	 * Explicit wait for element.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return the web element
	 */
	public static WebElement explicitWaitForElement(String path, String type)
	{
		WebDriverWait wait = new WebDriverWait(driver, getIntValue("explicit_timeout"));
		// wait.until(ExpectedConditions.presenceOfElementLocated(locatortype(type,
		// path)));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(locatortype(type,
		// path)));
		// //wait.until(ExpectedConditions.elementToBeClickable(locatortype(type,
		// path)));
		return driver.findElement(locatortype(type, path));
	}

	/**
	 * Explicit wait for element.
	 *
	 * @param by
	 *            the by
	 * @return the web element
	 */
	public static WebElement explicitWaitForElementClickable(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, getIntValue("explicit_timeout"));
		// wait.until(ExpectedConditions.presenceOfElementLocated(by));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		 wait.until(ExpectedConditions.elementToBeClickable(by));
		return driver.findElement(by);
	}
	public static WebElement explicitWaitForElementVisible(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, getIntValue("explicit_timeout"));
		// wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		 //wait.until(ExpectedConditions.elementToBeClickable(by));
		return driver.findElement(by);
	}

	/**
	 * Hover on element.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if successful
	 */
	public static boolean hoverOnElement(String path, String type)
	{
		WebElement element = explicitWaitForElement(path, type);
		// highLightElement(driver,element );
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
		return true;
	}

	/**
	 * Hover on element.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 */
	public static boolean hoverOnElement(By by)
	{
		WebElement element = explicitWaitForElementVisible(by);
		// highLightElement(driver,element );
		executionDelay(10000);
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
		executionDelay(5000);
		return true;
	}

	/**
	 * Hover on element.
	 *
	 * @param element
	 *            the element
	 * @return true, if successful
	 */
	public static boolean hoverOnElement(WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
		executionDelay(5000);
		return true;
	}

	/**
	 * Hover on element with click.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 */
	public static boolean hoverOnElementWithClick(By by)
	{
		WebElement element = explicitWaitForElementVisible(by);
		// highLightElement(driver,element );
		executionDelay(4000);
		Actions act = new Actions(driver);
		act.moveToElement(element).click().build().perform();
		executionDelay(4000);
		return true;
	}

	/**
	 * Click.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean click(String path, String type) throws InterruptedException
	{
		WebElement element = explicitWaitForElement(path, type);
		executionDelay(500);
		// highLightElement(driver,element );
		element.click();
		return true;
	}

	/**
	 * Check visibility.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 */
	public static boolean checkVisibility(By by)
	{
		WebElement element = explicitWaitForElementVisible(by);
		return element.isDisplayed();
	}

	/**
	 * Click.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean click(By by) throws InterruptedException
	{
		try
		{
			executionDelay(1000);
			WebElement element = explicitWaitForElementClickable(by);
			element.click();
			return true;
		}
		catch (Exception e)
		{
			// System.out.println("exception click: "+e);
			//System.out.println("with JavaScript");
			executionDelay(1000);
			WebElement element = explicitWaitForElementClickable(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			return true;
		}
	}

	public static boolean verifyStringIsNotNull(String string) throws InterruptedException
	{
		executionDelay(1000);
		if (string != null)
			;
		{
			return true;
		}
	}

	/**
	 * Click by java script.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean clickByJavaScript(By by) throws InterruptedException
	{
		executionDelay(5000);
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		return true;
	}

	/**
	 * Click by java script.
	 *
	 * @param element
	 *            the element
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean clickByJavaScript(WebElement element) throws InterruptedException
	{
		executionDelay(1000);
		waitTobeClickable(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		return true;
	}

	/**
	 * Click.
	 *
	 * @param element
	 *            the element
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean click(WebElement element) throws InterruptedException
	{
		executionDelay(3000);
		element.click();
		return true;
	}

	/**
	 * Switch to tab.
	 *
	 * @param tabNumber
	 *            the tab number
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean switchToTab(int tabNumber) throws InterruptedException
	{
		executionDelay(10000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(tabNumber));
		executionDelay(5000);
		return true;
	}

	/**
	 * Close A particular browser tab.
	 *
	 * @param tabNumber
	 *            the tab number
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean closeAParticularBrowserTab(int tabNumber) throws InterruptedException
	{
		executionDelay(10000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(tabNumber));
		executionDelay(5000);
		driver.close();
		executionDelay(5000);
		return true;
	}

	/**
	 * Checks if is select.
	 *
	 * @param by
	 *            the by
	 * @return true, if is select
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean isSelect(By by) throws InterruptedException
	{
		executionDelay(3000);
		WebElement element = explicitWaitForElementVisible(by);
		executionDelay(1000);
		return element.isSelected();
	}

	public static boolean isEnabled(By by) throws InterruptedException
	{
		executionDelay(3000);
		WebElement element = explicitWaitForElementVisible(by);
		executionDelay(1000);
		return element.isEnabled();
	}

	/**
	 * Scrolling to element of A page.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if successful
	 */
	public static boolean scrollingToElementofAPage(String path, String type)
	{
		WebElement element = driver.findElement(locatortype(type, path));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		highLightElement(driver, element);
		return true;
	}

	/**
	 * Scrolling to elementof A page.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean scrollingToElementofAPage(By by) throws InterruptedException
	{
		Thread.sleep(5000);
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		return true;
	}

	/**
	 * Scroll to bottom.
	 *
	 * @param scrollDriver
	 *            the scroll driver
	 */
	public static void scrollToBottom(WebDriver scrollDriver)
	{
		JavascriptExecutor scroller = (JavascriptExecutor) scrollDriver;
		try
		{
			System.out.println("Sleeping");
			Thread.sleep(30000);
			System.out.println("Slept");
			double pos = 0;
			while (true)
			{
				scroller.executeScript("window.scrollBy(0,755)");
				pos += 755;
				Thread.sleep(1500);
				if (pos > Double.parseDouble(scroller.executeScript("return document.body.scrollHeight;").toString()))
					break;
			}
		}
		catch (InterruptedException e)
		{
			System.err.println("Sleep Routine was Interrupted");
			e.printStackTrace();
		}
	}

	/**
	 * Execute step.
	 *
	 * @param check
	 *            the check
	 * @param className
	 *            the class name
	 * @param logstep
	 *            the logstep
	 * @throws Exception
	 *             the exception
	 */
//	public static void executeStep(Boolean check, Class<?> className, String logstep) throws Exception
//	{
//		logging_step = logstep;
//		step = logstep;
//		if (check)
//		{
//			String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
//			Utility.testResult.setPassedScreenShotReference(imagePath);
//			LogUtil.infoLog(className, logstep + " - PASS ");
//			HtmlReportUtil.stepPass(imagePath,logstep + " - PASS");
//			/*
//			 * String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" +
//			 * retryingNumber); Utility.testResult.setPassedScreenShotReference(imagePath);
//			 * HtmlReportUtil.attachScreenshot(imagePath, false); retryCount--;
//			 * retryingNumber++; executionDelay(getIntValue("retryDelayTime"));
//			 */
//		} else
//		{
//			String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
//			Utility.testResult.setFailedScreenShotReference(imagePath);
//			LogUtil.infoLog(className, logstep + " - FAIL ");
//			HtmlReportUtil.stepFail(imagePath,logstep + " - FAIL");
//			throw new Exception(logstep);
//		}
//	}
	
	public static void executeStep(Boolean check, Class<?> className, String logstep) throws Exception {
		logging_step = logstep;
		step = logstep;
		if (check) {
			String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
			Utility.testResult.setPassedScreenShotReference(imagePath);
			LogUtil.infoLog(className, logstep + " - PASS ");
			HtmlReportUtil.stepPass(imagePath, logstep + " - PASS");
			/*
			 * String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" +
			 * retryingNumber); Utility.testResult.setPassedScreenShotReference(imagePath);
			 * HtmlReportUtil.attachScreenshot(imagePath, false); retryCount--;
			 * retryingNumber++; executionDelay(getIntValue("retryDelayTime"));
			 */
		} else {
			String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
			Utility.testResult.setFailedScreenShotReference(imagePath);
			LogUtil.infoLog(className, logstep + " - FAIL ");
			HtmlReportUtil.stepFail(imagePath, logstep + " - FAIL");
			throw new Exception(logstep);
		}
	}
	public static void executeStepWarning(String check, Class<?> className, String logstep) throws Exception
	{
		logging_step = logstep;
		step = logstep;
		String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
		Utility.testResult.setPassedScreenShotReference(imagePath);
		if (check != null)
		{
			LogUtil.infoLog(className, logstep + " - WARNING ");
			HtmlReportUtil.stepWarning(imagePath,logstep + " - WARNING");
			/*
			 * String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" +
			 * retryingNumber); Utility.testResult.setPassedScreenShotReference(imagePath);
			 * HtmlReportUtil.attachScreenshot(imagePath, false); retryCount--;
			 * retryingNumber++; executionDelay(getIntValue("retryDelayTime"));
			 */
		} else
		{
			LogUtil.infoLog(className, logstep + " - FAIL ");
			HtmlReportUtil.stepFail(imagePath,logstep + " - FAIL");
			throw new Exception(logstep);
		}
	}

	
	
	public static void executeStepWarningBool(Boolean check, Class<?> className, String logstep) throws Exception
	{
		logging_step = logstep;
		step = logstep;
		String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
		Utility.testResult.setPassedScreenShotReference(imagePath);
		if (check != null)
		{
			LogUtil.infoLog(className, logstep + " - WARNING ");
			HtmlReportUtil.stepWarning(imagePath,logstep + " - WARNING");
			/*
			 * String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" +
			 * retryingNumber); Utility.testResult.setPassedScreenShotReference(imagePath);
			 * HtmlReportUtil.attachScreenshot(imagePath, false); retryCount--;
			 * retryingNumber++; executionDelay(getIntValue("retryDelayTime"));
			 */
		} else
		{
			LogUtil.infoLog(className, logstep + " - FAIL ");
			HtmlReportUtil.stepFail(imagePath,logstep + " - FAIL");
			throw new Exception(logstep);
		}
	}

	/* Added for gettext in excel report */
	public static void executeStepVal(String check, Class<?> className, String logstep) throws Exception
	{
		logging_step = logstep;
		step = logstep;
		String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
		Utility.testResult.setPassedScreenShotReference(imagePath);
		if (check != null)
		{
			LogUtil.infoLog(className, logstep + " - PASS ");
			HtmlReportUtil.stepPass(imagePath,logstep + " - PASS");
		} else
		{
			LogUtil.infoLog(className, logstep + " - FAIL ");
			HtmlReportUtil.stepFail(imagePath,logstep + " - FAIL");
			throw new Exception(logstep);
		}
	}
	
	

	
	/* added for List<String> */
	public static void executeStepVallist(List<String> check, Class<?> className, String logstep) throws Exception
	{
		logging_step = logstep;
		step = logstep;
		String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
		Utility.testResult.setPassedScreenShotReference(imagePath);
		if (check != null)
		{
			LogUtil.infoLog(className, logstep + " - PASS ");
			HtmlReportUtil.stepPass(imagePath,logstep + " - PASS");
		} else
		{
			LogUtil.infoLog(className, logstep + " - FAIL ");
			HtmlReportUtil.stepFail(imagePath,logstep + " - FAIL");
			throw new Exception(logstep);
		}
	}

	/* Added to check boolean status of the element */
	public static void executeStepVal(boolean check, Class<?> className, String logstep) throws Exception
	{
		logging_step = logstep;
		step = logstep;
		String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
		Utility.testResult.setPassedScreenShotReference(imagePath);
		if (check = true)
		{
			LogUtil.infoLog(className, logstep + " - PASS ");
			HtmlReportUtil.stepPass(imagePath,logstep + " - PASS");
		} else
		{
			LogUtil.infoLog(className, logstep + " - FAIL ");
			HtmlReportUtil.stepFail(imagePath,logstep + " - FAIL");
			throw new Exception(logstep);
		}
	}

	/**
	 * Time stamp.
	 *
	 * @return the string
	 */
	public static String timeStamp()
	{
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Execution delay.
	 *
	 * @param time
	 *            the time
	 */
	public static void executionDelay(long time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Verify all vaues of drop down.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public boolean verifyAllVauesOfDropDown(String path, String type, String data)
	{
		boolean flag = false;
		WebElement element = explicitWaitForElement(path, type);
		List<WebElement> options = element.findElements(By.tagName("option"));
		String temp = data;
		String[] allElements = temp.split(",");
		String actual;
		for (int i = 0; i < allElements.length; i++)
		{
			LogUtil.infoLog(KeywordUtil.class, options.get(i).getText());
			LogUtil.infoLog(KeywordUtil.class, allElements[i].trim());
			actual = options.get(i).getText().trim();
			if (actual.equals(allElements[i].trim()))
			{
				flag = true;
			} else
			{
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * Verify current date input.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if successful
	 */
	public boolean verifyCurrentDateInput(String path, String type)
	{
		boolean flag = false;
		WebElement element = explicitWaitForElement(path, type);
		String actual = element.getAttribute("value").trim();
		DateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		dtFormat.setTimeZone(TimeZone.getTimeZone("US/Central"));
		String expected = dtFormat.format(date).trim();
		if (actual.trim().contains(expected))
		{
			flag = true;
		}
		return flag;
	}

	/**
	 * Select by text using element.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	public static boolean selectByTextUsingElement(String path, String type, String text)
	{
		boolean status;
		try
		{
			WebElement element = explicitWaitForElement(path, type);
			highLightElement(driver, element);
			Select select = new Select(element);
			select.selectByVisibleText(text);
			click(path, type);
			status = true;
			logResult(status, "Select action is performed !!!");
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Validate notes input.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param errorMessage
	 *            the error message
	 * @return the boolean
	 */
	public static Boolean validateNotesInput(String path, String type, String errorMessage)
	{
		Boolean flag = false;
		WebElement element = explicitWaitForElement(path, type);
		String[] pattern =
		{ "<", ">", "(", ")", "'", "\\" };
		for (int i = 0; i < pattern.length; i++)
		{
			element.clear();
			element.sendKeys(pattern[i]);
			flag = isWebElementPresent(errorMessage, type);
			if (!flag)
			{
				break;
			}
		}
		return flag;
	}

	/**
	 * Select list.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean selectList(final String path, String type, String data)
	{
		Boolean flag = false;
		WebElement select = explicitWaitForElement(path, type);
		List<WebElement> options = select.findElements(By.tagName("option"));
		String expected = data.trim();
		LogUtil.infoLog(KeywordUtil.class, "Expected: " + expected);
		for (WebElement option : options)
		{
			String actual = option.getText().trim();
			LogUtil.infoLog(KeywordUtil.class, "Actual: " + actual);
			if (actual.equals(expected))
			{
				option.click();
				flag = true;
				try
				{
					Thread.sleep(2000);
				}
				catch (Exception e)
				{
					LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
				}
				return flag;
			}
		}
		return flag;
	}

	/**
	 * Verify dropdown selected value.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public boolean verifyDropdownSelectedValue(String path, String type, String data)
	{
		Boolean flag = false;
		WebElement select = explicitWaitForElement(path, type);
		Select sel = new Select(select);
		String defSelectedVal = sel.getFirstSelectedOption().getText();
		if (defSelectedVal.trim().equals(data.trim()))
		{
			flag = true;
			return flag;
		} else
		{
			return flag;
		}
	}

	/**
	 * Verify element size.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param size
	 *            the size
	 * @return true, if successful
	 */
	public static boolean verifyElementSize(String path, String type, int size)
	{
		List<WebElement> elements = (List<WebElement>) driver.findElements(locatortype(type, path));
		if (elements.size() == size)
		{
			LogUtil.infoLog(KeywordUtil.class, "Element is Present " + size + "times");
			return true;
		} else
		{
			LogUtil.infoLog(KeywordUtil.class, "Element is not Present with required size");
			return false;
		}
	}

	/**
	 * Upload files using send keys.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean uploadFilesUsingSendKeys(String path, String type, String data)
	{
		WebElement element = driver.findElement(locatortype(type, path));
		// element.clear();
		// element.sendKeys(System.getProperty(userDir) +
		// "\\src\\test\\resources\\uploadFiles\\" + data);
		element.sendKeys(System.getProperty(userDir) + "\\AutoIt\\" + data);
		return true;
	}

	/**
	 * Write in input char by char.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean writeInInputCharByChar(String path, String type, String data)
	{
		WebElement element = driver.findElement(locatortype(type, path));
		element.clear();
		String[] b = data.split("");
		for (int i = 0; i < b.length; i++)
		{
			element.sendKeys(b[i]);
			executionDelay(1000);
		}
		return true;
	}

	/**
	 * Checks if is radio selected.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if is radio selected
	 */
	public static boolean isRadioSelected(String path, String type)
	{
		WebElement element = explicitWaitForElement(path, type);
		if (element.isSelected())
			return true;
		return false;
	}

	/**
	 * Checks if is check box selected.
	 *
	 * @param by
	 *            the by
	 * @return true, if is check box selected
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean isCheckBoxSelected(By by) throws InterruptedException
	{
		executionDelay(3000);
		WebElement element = explicitWaitForElementVisible(by);
		if (element.isSelected())
			return true;
		return false;
	}

	/**
	 * Checks if is radio not selected.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if is radio not selected
	 */
	public static boolean isRadioNotSelected(String path, String type)
	{
		WebElement element = explicitWaitForElement(path, type);
		if (element.isSelected())
			return false;
		return true;
	}

	/**
	 * Checks if is check box not selected.
	 *
	 * @param by
	 *            the by
	 * @return true, if is check box not selected
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static boolean isCheckBoxNotSelected(By by) throws InterruptedException
	{
		executionDelay(3000);
		WebElement element = explicitWaitForElementVisible(by);
		if (element.isSelected())
			return false;
		return true;
	}

	/**
	 * Select by index.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param index
	 *            the index
	 * @return true, if successful
	 */
	public static boolean selectByIndex(String path, String type, Integer index)
	{
		boolean status;
		try
		{
			WebElement element = driver.findElement(locatortype(type, path));
			highLightElement(driver, element);
			Select select = new Select(element);
			select.selectByIndex(index);
			status = true;
			logResult(status, "Select action is performed !!!");
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Select by value.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public static boolean selectByValue(String path, String type, String value)
	{
		boolean status = false;
		try
		{
			WebElement element = driver.findElement(locatortype(type, path));
			Thread.sleep(3000);
			// highLightElement(driver, element);
			List<WebElement> options = element.findElements(By.tagName("option"));
			for (int i = 0; i < options.size(); i++)
			{
				String option = options.get(i).getText();
				if (option.toString().equalsIgnoreCase(value))
				{
					Select select = new Select(options.get(i));
					select.selectByValue(value);
					System.out.println("******Select     ----index: " + i + "  name: " + option);
					status = true;
					logResult(status, "Select action is performed !!!");
				}
			}
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Select by value.
	 *
	 * @param by
	 *            the by
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public static boolean selectByValue(By by, String value)
	{
		boolean status = false;
		try
		{
			WebElement element = returnWebElement(by);
			Thread.sleep(3000);
			// highLightElement(driver, element);
			List<WebElement> options = element.findElements(By.tagName("option"));
			for (int i = 0; i < options.size(); i++)
			{
				String option = options.get(i).getAttribute("value");
				System.out.println("******Select     ----index: " + i + "  value: " + option);
				if (value.equalsIgnoreCase(option))
				{
					Select select = new Select(options.get(i));
					select.selectByValue(value);
					System.out.println("******Select     ----index: " + i + "  value: " + option);
					status = true;
					logResult(status, "Select action is performed !!!");
				}
			}
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Select by value text.
	 *
	 * @param by
	 *            the by
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public static boolean selectByValueText(By by, String value)
	{
		boolean status = false;
		try
		{
			WebElement element = returnWebElement(by);
			Thread.sleep(3000);
			Select select = new Select(element);
			select.selectByValue(value);
			status = true;
			logResult(status, "Select action is performed !!!");
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Select by index.
	 *
	 * @param by
	 *            the by
	 * @param index
	 *            the index
	 * @return true, if successful
	 */
	public static boolean selectByIndex(By by, Integer index)
	{
		boolean status;
		try
		{
			WebElement element = returnWebElement(by);
			Select select = new Select(element);
			select.selectByIndex(index);
			status = true;
			logResult(status, "Select action is performed !!!");
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Select by index.
	 *
	 * @param element
	 *            the element
	 * @param index
	 *            the index
	 * @return true, if successful
	 */
	public static boolean selectByIndex(WebElement element, Integer index)
	{
		boolean status;
		try
		{
			Select select = new Select(element);
			select.selectByIndex(index);
			status = true;
			logResult(status, "Select action is performed !!!");
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Select by click.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param index
	 *            the index
	 * @return true, if successful
	 */
	public static boolean selectByClick(String path, String type, int index)
	{
		boolean status;
		try
		{
			WebElement element = driver.findElement(locatortype(type, path));
			Thread.sleep(3000);
			highLightElement(driver, element);
			List<WebElement> options = element.findElements(By.tagName("option"));
			options.get(index).click();
			status = true;
			logResult(status, "Select action is performed !!!");
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Select by visiable text.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	public static boolean selectByVisiableText(String path, String type, String text)
	{
		boolean status;
		try
		{
			WebElement element = driver.findElement(locatortype(type, path));
			// highLightElement(driver, element);
			executionDelay(1000);
			Select select = new Select(element);
			select.selectByVisibleText(text);
			highLightElement(driver, element);
			status = true;
			logResult(status, "Select action is performed !!!");
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Select by visible text.
	 *
	 * @param by
	 *            the by
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	public static boolean selectByVisibleText(By by, String text)
	{
		boolean status;
		try
		{
			WebElement element = driver.findElement(by);
			// highLightElement(driver, element);
			executionDelay(1000);
			Select select = new Select(element);
			select.selectByVisibleText(text);
			// highLightElement(driver, element);
			status = true;
			// logResult(status, "Select action is performed !!!");
			return status;
		}
		catch (Exception e)
		{
			status = false;
			logResult(status, "No Select action performed !!!");
			return status;
		}
	}

	/**
	 * Clear input.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if successful
	 */
	public static boolean clearInput(String path, String type)
	{
		WebElement element = driver.findElement(locatortype(type, path));
		element.clear();
		return true;
	}

	/**
	 * Clear input.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 */
	public static boolean clearInput(By by)
	{
		WebElement element = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(by)).clear();
		return true;
	}

	/**
	 * Verify PDF data.
	 *
	 * @param data
	 *            the data
	 * @param page
	 *            the page
	 * @param fileName
	 *            the file name
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	/*
	 * public static boolean verifyPDFData(String data, int page, String fileName)
	 * throws IOException { FileInputStream fis = null; String dwnFile = null; try {
	 * String dirName = System.getProperty(userDir) +
	 * "\\src\\test\\resources\\downloadFile\\"; File dir = new File(dirName);
	 * File[] path1 = dir.listFiles();
	 * 
	 * for (int k = 0; k < path1.length; k++) { dwnFile = path1[k].toString();
	 * 
	 * if (dwnFile.contains(fileName)) { break; }
	 * 
	 * continue;
	 * 
	 * } File file = new File(dwnFile); fis = new
	 * FileInputStream(file.getAbsolutePath()); PdfReader text = new PdfReader(fis);
	 * //String expected = PdfTextExtractor.getTextFromPage(text, page);
	 * 
	 * String[] b = data.split(","); fis.close(); for (int i = 0; i < b.length; i++)
	 * {
	 * 
	 * //if (expected.contains(b[i])) continue; } }
	 * 
	 * catch (Exception e) { LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
	 * } return true; }
	 */
	/**
	 * Del directory.
	 *
	 * @return true, if successful
	 */
	public boolean delDirectory()
	{
		File delDestination = new File(System.getProperty(userDir) + "\\src\\test\\resources\\downloadFile");
		if (delDestination.exists())
		{
			File[] files = delDestination.listFiles();
			for (int i = 0; i < files.length; i++)
			{
				if (files[i].isDirectory())
				{
					delDirectory();
				} else
				{
					files[i].delete();
				}
			}
		}
		return delDestination.delete();
	}

	/**
	 * Verify css property.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public boolean verifyCssProperty(String path, String type, String data)
	{
		String[] property = data.split(":", 2);
		String expProp = property[0];
		String expValue = property[1];
		boolean flag = false;
		String prop = (explicitWaitForElement(path, type)).getCssValue(expProp);
		if (prop.trim().equals(expValue.trim()))
		{
			flag = true;
			return flag;
		} else
		{
			return flag;
		}
	}

	/**
	 * Save reference number.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @return true, if successful
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public static boolean saveReferenceNumber(String path, String type) throws FileNotFoundException, UnsupportedEncodingException
	{
		WebElement element = driver.findElement(locatortype(type, path));
		String refernce_number = element.getText();
		String filePth = Utility.getValue("ReferenceNumberNote");
		PrintWriter writer = new PrintWriter(filePth, "UTF-8");
		writer.println(refernce_number);
		writer.close();
		System.out.println(refernce_number);
		return true;
	}

	/**
	 * Verify text input.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean verifyTextInput(String path, String type, String data)
	{
		WebElement element = driver.findElement(locatortype(type, path));
		boolean flag = false;
		String actual_text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value", element);
		// String actual_text=element.getAttribute("value");
		if (data.equals(actual_text))
		{
			flag = true;
			return flag;
		}
		return flag;
	}

	/**
	 * Verify text using data.
	 *
	 * @param path
	 *            the path
	 * @param type
	 *            the type
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public static boolean verifyTextUsingData(By by, String data)
	{
		WebElement element = driver.findElement(by);
		boolean flag = false;
		String actual_txt = element.getAttribute("value").replaceAll(",", "");
		if (data.equals(actual_txt))
		{
			flag = true;
			return flag;
		}
		return flag;
	}

	public static boolean verifyText(String actual, String data)
	{
		boolean flag = false;
		if (data.equalsIgnoreCase(actual))
		{
			flag = true;
			return flag;
		}
		return flag;
	}

	public static boolean verifyIntValues(int actual, int data)
	{
		boolean flag = false;
		if (data == actual)
		{
			flag = true;
			return flag;
		}
		return flag;
	}

	public static boolean verifyDoubleValues(double actual, double data)
	{
		boolean flag = false;
		if (data == actual)
		{
			flag = true;
			return flag;
		}
		return flag;
	}

	public static boolean verifyTextUsingDataFromSelectTag(By by, String data)
	{
		Select select = new Select(driver.findElement(by));
		boolean flag = false;
		String actual_txt = select.getFirstSelectedOption().getText();
		if (data.equals(actual_txt))
		{
			flag = true;
			return flag;
		}
		return flag;
	}

	public static boolean verifyTextUsingDataTextFromSelection(By by, String data)
	{
		WebElement element = explicitWaitForElementVisible(by);
		boolean flag = false;
		String actual_txt = element.getText();
		System.out.println(actual_txt);
		if (data.equals(actual_txt))
		{
			flag = true;
			return flag;
		}
		return flag;
	}

	/* No method for contains,Implemented method for contains */
	public static boolean verifyTextContainsDataTextFromSelection(By by, String data)
	{
		WebElement element = explicitWaitForElementVisible(by);
		boolean flag = false;
		String actual_txt = element.getText();
		System.out.println(actual_txt);
		System.out.println(data);
		if (actual_txt.contains(data))
		{
			//System.out.println("String Match Successfull");
			flag = true;
			return flag;
		}
		return flag;
	}

	public static boolean printingDataInReports(String data)
	{
		System.out.println(data);
		return true;
	}

	public static void writingInExcel(String value, int runCount) throws Throwable
	{
		try
		{
			File src = new File("C:\\Users\\502770598\\TX_ClixCapital_PL-DSA\\ExecutionReports\\PL_DSA_QDE_Applcation_Numbers.xlsx");
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh1 = wb.getSheet("ApplicationNumbers");
			System.out.println(value);
			XSSFRow row = sh1.getRow(runCount);
			XSSFRow newRow = sh1.createRow(runCount);
			XSSFCell cell = newRow.createCell(runCount);
			cell.setCellValue(value);
			fis.close();
			FileOutputStream fout = new FileOutputStream(new File("C:\\Users\\502770598\\TX_ClixCapital_PL-DSA\\ExecutionReports\\PL_DSA_QDE_Applcation_Numbers.xlsx"));
			wb.write(fout);
			wb.close();
			fout.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static void readingFromExcel(Map<String, String> map) throws Throwable
	{
		try
		{
			File src = new File("C:\\Users\\502770598\\TX_ClixCapital_PL-DSA\\ExecutionReports\\PL_DSA_QDE_Applcation_Numbers.xlsx");
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh1 = wb.getSheet("ApplicationNumbers");
			XSSFRow row = sh1.getRow(0);
			String val = row.getCell(0).getStringCellValue();
			System.out.println("value in excel: " + val);
			wb.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public static String generatingRandomNumberTill(int limit) throws Throwable
	{
		try
		{
			String AlphaNumericString = "0123456789";
			for (int i = 0; i < limit; i++)
			{
				int index = (int) (AlphaNumericString.length() * Math.random());
				sb.append(AlphaNumericString.charAt(index));
			}
			System.out.println("Generating Random String with any of the range provided: " + sb.toString());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return sb.toString();
	}

	public static String generateRandomString(int RANDOM_STRING_LENGTH)
	{
		StringBuffer randStr = new StringBuffer();
		String CHAR_LIST = "abcde";
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++)
		{
			int index = (int) (CHAR_LIST.length() * Math.random());
			randStr.append(CHAR_LIST.charAt(index));
		}
		return randStr.toString();
	}

	public static String generateRandomNumbers(int RANDOM_NUMBER_LENGTH)
	{
		StringBuffer randStr = new StringBuffer();
		String CHAR_LIST = "0123456789";
		for (int i = 0; i < RANDOM_NUMBER_LENGTH; i++)
		{
			int index = (int) (CHAR_LIST.length() * Math.random());
			randStr.append(CHAR_LIST.charAt(index));
		}
		return randStr.toString();
	}
	public static boolean selectDropDownByText(By by, String dropDownText)
	{
		Select dropDown = new Select(driver.findElement(by));
		dropDown.selectByVisibleText(dropDownText);
		return true;
	}


	public static boolean verifyRadioSelected(By locator)
	{
		WebElement element = driver.findElement(locator);
		if (element.isSelected())
			return true;
		return false;
	}

	public static boolean verifyTextNotEqual(String actual, String expected)
	{
		boolean flag = false;
		if (!expected.equalsIgnoreCase(actual))
		{
			flag = true;
			return flag;
		}
		return flag;
	}

	public static By getDynamicObject(By loc, String dynamicText)
	{
		String locator = loc.toString();
		if (locator.contains("Dynamic"))
		{
			locator = locator.replace("Dynamic", dynamicText);
			if (locator.contains("By.cssSelector"))
			{
				locator = locator.replace("By.cssSelector: ", "");
				loc = By.cssSelector(locator);
			} else if (locator.contains("By.xpath"))
			{
				locator = locator.replace("By.xpath: ", "");
				loc = By.xpath(locator);
			} else if (locator.contains("By.linkText"))
			{
				locator = locator.replace("By.linkText: ", "");
				loc = By.linkText(locator);
			} else
			{
				logResult(false, "Object not found");
			}
			return loc;
		} else
		{
			logResult(false, "Object is not Dynamic");
			return null;
		}
	}

	public static String getTextByTitle(By locator)
	{
		String str = null;
		try
		{
			str = driver.findElement(locator).getAttribute("title").trim();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return str;
	}
	/**
	 * This Method Is Used For Creating a Warning In Report If It Is False Or Else Info 
	 * @param check
	 * @param className
	 * @param logstep
	 * @throws Exception
	 */
	public static void executeStepWarnVal(Boolean check, Class<?> className, String logstep) throws Exception
	{
		logging_step = logstep;
		step = logstep;
		if (check)
		{
			String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
			Utility.testResult.setPassedScreenShotReference(imagePath);
			LogUtil.infoLog(className, logstep + " - PASS ");
			//HtmlReportUtil.stepWarnImage(imagePath,logstep,true);
			HtmlReportUtil.stepWarnImage(imagePath,logstep + " - PASS",true);
		} else
		{
			String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
			Utility.testResult.setWarnScreenShotReference(imagePath);
			LogUtil.infoLog(className, logstep + " - WARNING ");
			HtmlReportUtil.stepWarnImage(imagePath,logstep + " - WARNING",false);

		}

	}

}