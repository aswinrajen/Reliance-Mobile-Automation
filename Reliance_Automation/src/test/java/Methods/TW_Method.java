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
//		executeStep(click(TW.proprietor), thisClass, "proprietor checkbox is selected");
//		executeStep(click(TW.searchCustomer), thisClass, "Search customer for the valid one");
		executeStep(writeInInput(TW.dOB, (map.get("DOB" + runCount))), thisClass, "DOB is entered");
		executeStep(writeInInput(TW.pAN, (map.get("PanNo" + runCount))), thisClass, "pan no is entered");
		executeStep(click(TW.Verify), thisClass, "Verify PAN is selected");
		executeStep(click(TW.Customer_Page), thisClass, "Customer Details Page is Clicked");
		executeStep(selectDropDownByText(TW.Marital_Status, (map.get("Marital" + runCount))), thisClass, "Marital Status is Selected");
		executeStep(click(TW.Continue), thisClass, "Continue is Clicked");
		executeStep(writeInInput(TW.Mobile_Number, (map.get("Number" + runCount))), thisClass, "Mobile Number is Entered");
		((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle,
				driver.findElement(TW.Continue_1));
		executeStep(clickByJavaScript(TW.Continue_1), thisClass, "Continue is Clicked");
		executeStep(click(TW.VehicleDetailsButton), thisClass, "VehicleDetailsButton is clicked");
		executeStep(writeInInput(TW.customer_1, (map.get("Passport Number" + runCount))), thisClass, "customer_1 is entered");
		executeStep(writeInInput(TW.customer_2, (map.get("Passport Number" + runCount))), thisClass, "customer_2 is entered");
		executeStep(clickByJavaScript(TW.saveButton), thisClass, "save is clicked");
		executeStep(click(TW.VehicleDetailsButton), thisClass, "VehicleDetailsButton is clicked");
	}
	
		public static void VehicleDetails (Map<String, String> map) throws InterruptedException, Exception 
		
    {
		executeStep(click(TW.modelVariant), thisClass, "model variant is selected");
		executeStep(click(TW.brand), thisClass, "brand is entered");
		executeStep(click(TW.variant), thisClass, "variant is entered");
		executeStep(selectDropDownByText(TW.fuelType, (map.get("FuelType" + runCount))), thisClass, "FuelType is selected");
		executeStep(click(TW.rtoLocation), thisClass, "rtoLocation is clicked");
		executeStep(click(TW.locationRegistered), thisClass, "locationRegistered is clicked");
		executeStep(click(TW.MH01), thisClass, "Location is entered");
		executeStep(selectDropDownByText(TW.manufacturingMonth, (map.get("Month" + runCount))), thisClass, "DOB is entered");
		executeStep(writeInInput(TW.purchaseDate, (map.get("PurchaseDate" + runCount))), thisClass, "purchase date is entered");
		executeStep(writeInInput(TW.registeredDate, (map.get("RegisteredDate" + runCount))), thisClass, "RegisteredDate is entered");
		executeStep(click(TW.engineNumber),thisClass, "Engine is entered");
		executeStep(writeInInput(TW.engineNumber, (map.get("Engine" + runCount))), thisClass, "Engine is entered");
		executeStep(writeInInput(TW.chasisNumber, (map.get("Chassis" + runCount))), thisClass, "Chassis is entered");
		executionDelay(6000);
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "cover details is clicked");
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "model summary button is clicked");
//		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "inspection details button is clicked");
//		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "calculate premium button is clicked");
		executeStep(writeInInput(TW.nomineeName, (map.get("Name" + runCount))), thisClass, "Name is entered");
		executeStep(writeInInput(TW.nomineeAge, (map.get("Age" + runCount))), thisClass, "Age is entered");
		executeStep(writeInInput(TW.nomineeRelationship, (map.get("Relation" + runCount))), thisClass, "Relation is entered");
		executeStep(clickByJavaScript(TW.continueButton),thisClass, "continue button is clicked");
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "model summary button is clicked");
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "inspection details button is clicked");
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "calculate premium button is clicked");
	}
    
	public static void TWLiability(Map<String, String> map) throws InterruptedException, Exception {
		executeStep(click(TW.Motor), thisClass, "Motor Module is selected");
		executeStep(click(TW.PolicyTW), thisClass, "Two wheeler Policy is selected");
		executeStep(click(TW.createPolicy), thisClass, "Create Policy is opened");
		executeStep(click(TW.liability), thisClass, "liability is selected");
		executeStep(click(TW.liabilityNew), thisClass, "liabilityNew Policy is clicked");
		executeStep(click(TW.liabilityNewPolicy), thisClass, "liabilityNewPolicy is opened");
//		executeStep(click(TW.proprietor), thisClass, "proprietor checkbox is selected");
//		executeStep(click(TW.searchCustomer), thisClass, "Search customer for the valid one");
		executeStep(writeInInput(TW.dOB, (map.get("DOB" + runCount))), thisClass, "DOB is entered");
		executeStep(writeInInput(TW.pAN, (map.get("PanNo" + runCount))), thisClass, "pan no is entered");
		executeStep(click(TW.Verify), thisClass, "Verify PAN is selected");
		executeStep(click(TW.Customer_Page), thisClass, "Customer Details Page is Clicked");
		executeStep(selectDropDownByText(TW.Marital_Status, (map.get("Marital" + runCount))), thisClass, "Marital Status is Selected");
		executeStep(click(TW.Continue), thisClass, "Continue is Clicked");
		executeStep(writeInInput(TW.Mobile_Number, (map.get("Number" + runCount))), thisClass, "Mobile Number is Entered");
		((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle,
				driver.findElement(TW.Continue_1));
		executeStep(clickByJavaScript(TW.Continue_1), thisClass, "Continue is Clicked");
		executeStep(click(TW.VehicleDetailsButton), thisClass, "VehicleDetailsButton is clicked");
		executeStep(writeInInput(TW.customer_1, (map.get("Passport Number" + runCount))), thisClass, "customer_1 is entered");
		executeStep(writeInInput(TW.customer_2, (map.get("Passport Number" + runCount))), thisClass, "customer_2 is entered");
		executeStep(clickByJavaScript(TW.saveButton), thisClass, "save is clicked");
		executeStep(click(TW.VehicleDetailsButton), thisClass, "VehicleDetailsButton is clicked");
		executeStep(click(TW.modelVariant), thisClass, "model variant is selected");
		executeStep(click(TW.brand), thisClass, "brand is entered");
		executeStep(click(TW.variant), thisClass, "variant is entered");
		executeStep(selectDropDownByText(TW.fuelType, (map.get("FuelType" + runCount))), thisClass, "FuelType is selected");
		executeStep(click(TW.rtoLocation), thisClass, "rtoLocation is clicked");
		executeStep(click(TW.locationRegistered), thisClass, "locationRegistered is clicked");
		executeStep(click(TW.MH01), thisClass, "Location is entered");
		executeStep(selectDropDownByText(TW.manufacturingMonth, (map.get("Month" + runCount))), thisClass, "DOB is entered");
		executeStep(writeInInput(TW.purchaseDate, (map.get("PurchaseDate" + runCount))), thisClass, "purchase date is entered");
		executeStep(writeInInput(TW.registeredDate, (map.get("RegisteredDate" + runCount))), thisClass, "RegisteredDate is entered");
		executeStep(click(TW.engineNumber),thisClass, "Engine is entered");
		executeStep(writeInInput(TW.engineNumber, (map.get("Engine" + runCount))), thisClass, "Engine is entered");
		executeStep(writeInInput(TW.chasisNumber, (map.get("Chassis" + runCount))), thisClass, "Chassis is entered");
		executionDelay(6000);
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "cover details is clicked");
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "model summary button is clicked");
//		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "inspection details button is clicked");
//		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "calculate premium button is clicked");
		executeStep(writeInInput(TW.nomineeName, (map.get("Name" + runCount))), thisClass, "Name is entered");
		executeStep(writeInInput(TW.nomineeAge, (map.get("Age" + runCount))), thisClass, "Age is entered");
		executeStep(writeInInput(TW.nomineeRelationship, (map.get("Relation" + runCount))), thisClass, "Relation is entered");
		executeStep(clickByJavaScript(TW.continueButton),thisClass, "continue button is clicked");
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "model summary button is clicked");
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "inspection details button is clicked");
		executeStep(clickByJavaScript(TW.coverDetails), thisClass, "calculate premium button is clicked");
	}
}