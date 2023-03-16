package Methods;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import MobileWebLocators.GCV;
import MobileWebLocators.Others;
import MobileWebLocators.TW;
import Utilities.KeywordUtil;

public class TW_Method extends KeywordUtil {
	

	static int runCount = 0;
	/** The this class. */
	@SuppressWarnings("rawtypes")
	static Class thisClass = TW_Method.class;
	public static JavascriptExecutor js = (JavascriptExecutor) driver;
	static String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
			+ "var elementTop = arguments[0].getBoundingClientRect().top;"
			+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";


	
	public static void Credential(Map<String, String> map) throws InterruptedException, Exception {
		driver.get("https://smartzone.reliancegeneral.co.in:8443/Login/IMDLogin");
		executionDelay(3000);
		executeStep(writeInInput(TW.UserName, (map.get("UserName" + runCount))), thisClass, "UserName is Entered");
		executeStep(writeInInput(TW.Password, (map.get("Password" + runCount))), thisClass, "Password is Entered");
		executionDelay(20000);
		((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle,
				driver.findElement(TW.Login));
//		executeStep(clickByJavaScript(TW.Login), thisClass, "Login is clicked");
		executeStep(click(TW.Login), thisClass,"Login Page Landed");
	}
	
	public static void TwoWheeler(Map<String, String> map) throws InterruptedException, Exception {
		executeStep(click(TW.hamburgerMenu), thisClass, "Hamburger Menu is clicked");
		executeStep(click(TW.Motor), thisClass, "Motor Module is selected");
		executeStep(click(TW.PolicyTW), thisClass, "Two wheeler Policy is selected");
		executeStep(click(TW.createPolicy), thisClass, "Create Policy is opened");
		executeStep(click(TW.sellingPackage), thisClass, "Selling package is selected");
		executeStep(click(TW.newTWPolicy), thisClass, "new two wheeler Policy is clicked");
		executeStep(click(TW.newPolicy), thisClass, "new Policy is opened");
		executeStep(click(TW.proprietor), thisClass, "proprietor checkbox is selected");
//		executeStep(click(TW.searchCustomer), thisClass, "Search customer for the valid one");
		executeStep(writeInInput(TW.dOB, (map.get("doB" + runCount))), thisClass, "DOB is entered");
	}

}