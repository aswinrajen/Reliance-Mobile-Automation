package Methods;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import MobileWebLocators.GCV;
import MobileWebLocators.Others;
import Utilities.KeywordUtil;

public class ReusableMethod extends KeywordUtil {
	

	static int runCount = 0;
	/** The this class. */
	@SuppressWarnings("rawtypes")
	static Class thisClass = ReusableMethod.class;
	public static JavascriptExecutor js = (JavascriptExecutor) driver;
	static String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
			+ "var elementTop = arguments[0].getBoundingClientRect().top;"
			+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";

	public static void Login(Map<String, String> map) throws Exception {
	
	driver.get("https://smartzone.reliancegeneral.co.in:8443/Login/IMDLogin");
	executionDelay(3000);
	executeStep(writeInInput(GCV.UserName, (map.get("UserName" + runCount))), thisClass, "UserName is Entered");
	executeStep(writeInInput(GCV.Password, (map.get("Password" + runCount))), thisClass, "Password is Entered");
	executionDelay(8000);
	((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle,
			driver.findElement(GCV.Login));
	executeStep(clickByJavaScript(GCV.Login), thisClass, "Login is clicked");
	}
	
	public static void MenuPage(Map<String, String> map) throws Exception {
		
		executeStep(click(GCV.Menu), thisClass, "Menu is clicked");
		executeStep(click(GCV.Motor), thisClass, "Motor is Clicked");
		
	}

	public static void Cutomer_Page(Map<String, String> map) throws Exception {
	executeStep(click(GCV.Sourcing_Info), thisClass, "Sourcing Info is Clicked");
	executeStep(writeInInput(GCV.Cust_1, (map.get("Cust" + runCount))), thisClass, "Cust1 is Entered");
	executeStep(writeInInput(GCV.Cust_2, (map.get("Cust" + runCount))), thisClass, "Cust2 is Entered");
	((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle,
			driver.findElement(GCV.Save));
	executionDelay(1000);
	executeStep(clickByJavaScript(GCV.Save), thisClass, "Save is Clicked");
	executionDelay(2000);
	executeStep(click(GCV.DOB), thisClass, "Calendar is Clicked");
	executionDelay(2000);
	executeStep(selectDropDownByText(GCV.Year, (map.get("Year" + runCount))), thisClass, "Year is Selected");
	executeStep(selectDropDownByText(GCV.Month, (map.get("Month" + runCount))), thisClass, "Month is Selected");
	executeStep(click(GCV.Date), thisClass, "Date is Clicked");
	executeStep(writeInInput(GCV.Pan_No, (map.get("PanNo" + runCount))), thisClass, "Pan Number is Entered");
	executeStep(click(GCV.Verify_Pan), thisClass, "Verify Pan is Clicked");
	executeStep(click(GCV.Customer_Page), thisClass, "Customer Details Page is Clicked");
	executeStep(selectDropDownByText(GCV.Marital_Status, (map.get("Marital" + runCount))), thisClass, "Marital Status is Selected");
	executeStep(click(GCV.Continue), thisClass, "Continue is Clicked");
	executeStep(writeInInput(GCV.Mobile_Number, (map.get("Number" + runCount))), thisClass, "Mobile Number is Entered");
	((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle,
			driver.findElement(GCV.Continue_1));
	executeStep(clickByJavaScript(GCV.Continue_1), thisClass, "Continue is Clicked");
	executeStep(click(GCV.Vehicle_Details), thisClass, "Vehicle Details is Clicked");
	}	
	
	
	
}
	