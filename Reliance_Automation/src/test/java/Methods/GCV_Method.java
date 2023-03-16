package Methods;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import MobileWebLocators.GCV;
import MobileWebLocators.Others;
import Utilities.KeywordUtil;

public class GCV_Method extends KeywordUtil {
	

	static int runCount = 0;
	/** The this class. */
	@SuppressWarnings("rawtypes")
	static Class thisClass = GCV_Method.class;
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
	
	public static void A1_Pack_New(Map<String, String> map) throws Exception {
		
		executeStep(click(GCV.Menu), thisClass, "Menu is clicked");
		executeStep(click(GCV.Motor), thisClass, "Motor is Clicked");
		executeStep(click(GCV.GCV), thisClass, "GCV is Clicked");
		executeStep(click(GCV.Quote), thisClass, "Quote is Clicked");
		executeStep(click(GCV.Package), thisClass, "Package is Clicked");
		executeStep(click(GCV.A1_Pack), thisClass, "A1 Package New is Clicked");
		executeStep(click(GCV.New), thisClass, "New is Clicked");
		executeStep(click(GCV.Model), thisClass, "Model is Clicked");
		executeStep(click(GCV.Mahindra), thisClass, "Mahindra is Clicked");
		executeStep(click(GCV.Pack_Suggestion), thisClass, "Vehicle Suggestion is Clicked");
		executeStep(selectDropDownByText(GCV.Fuel_Type, (map.get("FuelType" + runCount))), thisClass, "Fuel Type is Selected");
		executeStep(click(GCV.RTO), thisClass, "RTO location is Clicked");
		executeStep(click(GCV.Mumbai), thisClass, "Mumbai is Clicked");
		executeStep(click(GCV.MH_01), thisClass, "RTO Suggestion is Clicked");
		executeStep(click(GCV.Calculate_Premium), thisClass, "Calculate premium is Clicked");
		executionDelay(4000);
		executeStep(click(GCV.Convert), thisClass, "Covert is Clicked");
		executionDelay(2000);
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
		executeStep(writeInInput(GCV.Engine_Number, (map.get("Engine" + runCount))), thisClass, "Engine Number is Entered");
		executeStep(writeInInput(GCV.Chassis_Number, (map.get("Chassis" + runCount))), thisClass, "Chassis Number is Entered");
		executeStep(selectDropDownByText(GCV.Vehicle_Type, (map.get("Vehicle" + runCount))), thisClass, "Vehicle Type is Selected");
		executeStep(selectDropDownByText(GCV.Goods_Type, (map.get("Goods" + runCount))), thisClass, "Goods Type is Selected");
		executeStep(click(GCV.Get_Cover_Details), thisClass, "Get cover Details is Clicked");
		executeStep(click(GCV.Additional_Cover), thisClass, "Additional Cover is Clicked");
		executeStep(writeInInput(GCV.NomineeName, (map.get("Name" + runCount))), thisClass, "Nominee Name is Entered");
		executeStep(writeInInput(GCV.NomineeAge, (map.get("Age" + runCount))), thisClass, "Nominee Age is Entered");
		executeStep(selectDropDownByText(GCV.NomineeRelationship, (map.get("Relation" + runCount))), thisClass, "Nominee Relation Type is Selected");
		executeStep(click(GCV.Continue_NominnePage), thisClass, "Continue To Nominee Page is Clicked");
		executeStep(click(GCV.OtherDetails), thisClass, "Other Details is Clicked");
		executeStep(click(GCV.InspectionDetails), thisClass, "Inspection Details is Clicked");
		executeStep(click(GCV.CalculatePremium_1), thisClass, "Calculate premium is Clicked");
		executionDelay(2000);
		executeStep(click(GCV.Submit), thisClass, "Submit is Clicked");
		executionDelay(6000);
		if (driver.findElement(GCV.SuccessMessage).isDisplayed()) {
			executeStep(true, thisClass, "Proposal Generated Succesfully");
		} else {
			executeStepWarnVal(false, thisClass, "Proposal  Not Generated Succesfully");
		}
	}
	
public static void A2_Pack_New(Map<String, String> map) throws Exception {
		
		driver.get("https://smartzone.reliancegeneral.co.in:8443/Login/IMDLogin");
		executionDelay(3000);
		executeStep(click(GCV.Menu), thisClass, "Menu is clicked");
		executeStep(click(GCV.Motor), thisClass, "Motor is Clicked");
		executeStep(click(GCV.GCV), thisClass, "GCV is Clicked");
		executeStep(click(GCV.Quote), thisClass, "Quote is Clicked");
		executeStep(click(GCV.Package), thisClass, "Package is Clicked");
		executeStep(click(GCV.A2_Pack), thisClass, "A2 Package New is Clicked");
		executeStep(click(GCV.New), thisClass, "New is Clicked");
		executeStep(click(GCV.Model), thisClass, "Model is Clicked");
		executeStep(click(GCV.Mahindra), thisClass, "Mahindra is Clicked");
		executeStep(click(GCV.A2_Pack_Suggestion), thisClass, "Vehicle Suggestion is Clicked");
		executeStep(selectDropDownByText(GCV.Fuel_Type, (map.get("FuelType" + runCount))), thisClass, "Fuel Type is Selected");
		executeStep(click(GCV.RTO), thisClass, "RTO location is Clicked");
		executeStep(click(GCV.Mumbai), thisClass, "Mumbai is Clicked");
		executeStep(click(GCV.MH_01), thisClass, "RTO Suggestion is Clicked");
		executeStep(click(GCV.Calculate_Premium), thisClass, "Calculate premium is Clicked");
		executionDelay(4000);
		executeStep(click(GCV.Convert), thisClass, "Covert is Clicked");
		executionDelay(2000);
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
		executeStep(writeInInput(GCV.Engine_Number, (map.get("Engine" + runCount))), thisClass, "Engine Number is Entered");
		executeStep(writeInInput(GCV.Chassis_Number, (map.get("Chassis" + runCount))), thisClass, "Chassis Number is Entered");
		executeStep(selectDropDownByText(GCV.Vehicle_Type, (map.get("Vehicle" + runCount))), thisClass, "Vehicle Type is Selected");
		executeStep(selectDropDownByText(GCV.Goods_Type, (map.get("Goods" + runCount))), thisClass, "Goods Type is Selected");
		executeStep(click(GCV.Get_Cover_Details), thisClass, "Get cover Details is Clicked");
		executeStep(click(GCV.Additional_Cover), thisClass, "Additional Cover is Clicked");
		executeStep(writeInInput(GCV.NomineeName, (map.get("Name" + runCount))), thisClass, "Nominee Name is Entered");
		executeStep(writeInInput(GCV.NomineeAge, (map.get("Age" + runCount))), thisClass, "Nominee Age is Entered");
		executeStep(selectDropDownByText(GCV.NomineeRelationship, (map.get("Relation" + runCount))), thisClass, "Nominee Relation Type is Selected");
		executeStep(click(GCV.Continue_NominnePage), thisClass, "Continue To Nominee Page is Clicked");
		executeStep(click(GCV.OtherDetails), thisClass, "Other Details is Clicked");
		executeStep(click(GCV.InspectionDetails), thisClass, "Inspection Details is Clicked");
		executeStep(click(GCV.CalculatePremium_1), thisClass, "Calculate premium is Clicked");
		executionDelay(2000);
		executeStep(click(GCV.Submit), thisClass, "Submit is Clicked");
		executionDelay(6000);
		if (driver.findElement(GCV.SuccessMessage).isDisplayed()) {
			executeStep(true, thisClass, "Proposal Generated Succesfully");
		} else {
			executeStepWarnVal(false, thisClass, "Proposal  Not Generated Succesfully");
		}
	}
	
public static void A3_Pack_New(Map<String, String> map) throws Exception {
	
	driver.get("https://smartzone.reliancegeneral.co.in:8443/Login/IMDLogin");
	executionDelay(3000);
	executeStep(click(GCV.Menu), thisClass, "Menu is clicked");
	executeStep(click(GCV.Motor), thisClass, "Motor is Clicked");
	executeStep(click(GCV.GCV), thisClass, "GCV is Clicked");
	executeStep(click(GCV.Quote), thisClass, "Quote is Clicked");
	executeStep(click(GCV.Package), thisClass, "Package is Clicked");
	executeStep(click(GCV.A3_Pack), thisClass, "A3 Package New is Clicked");
	executeStep(click(GCV.New), thisClass, "New is Clicked");
	executeStep(click(GCV.Model), thisClass, "Model is Clicked");
	executeStep(click(GCV.Mahindra), thisClass, "Mahindra is Clicked");
	executeStep(click(GCV.A3_Pack_Suggestion), thisClass, "Vehicle Suggestion is Clicked");
	executeStep(selectDropDownByText(GCV.Fuel_Type, (map.get("FuelType" + runCount))), thisClass, "Fuel Type is Selected");
	executeStep(click(GCV.RTO), thisClass, "RTO location is Clicked");
	executeStep(click(GCV.Mumbai), thisClass, "Mumbai is Clicked");
	executeStep(click(GCV.MH_01), thisClass, "RTO Suggestion is Clicked");
	executeStep(click(GCV.Calculate_Premium), thisClass, "Calculate premium is Clicked");
	executionDelay(4000);
	executeStep(click(GCV.Convert), thisClass, "Covert is Clicked");
	executionDelay(2000);
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
	executeStep(writeInInput(GCV.Engine_Number, (map.get("Engine" + runCount))), thisClass, "Engine Number is Entered");
	executeStep(writeInInput(GCV.Chassis_Number, (map.get("Chassis" + runCount))), thisClass, "Chassis Number is Entered");
	executeStep(selectDropDownByText(GCV.Vehicle_Type, (map.get("Vehicle" + runCount))), thisClass, "Vehicle Type is Selected");
	executeStep(click(GCV.Get_Cover_Details), thisClass, "Get cover Details is Clicked");
	executeStep(click(GCV.Additional_Cover), thisClass, "Additional Cover is Clicked");
	executeStep(writeInInput(GCV.NomineeName, (map.get("Name" + runCount))), thisClass, "Nominee Name is Entered");
	executeStep(writeInInput(GCV.NomineeAge, (map.get("Age" + runCount))), thisClass, "Nominee Age is Entered");
	executeStep(selectDropDownByText(GCV.NomineeRelationship, (map.get("Relation" + runCount))), thisClass, "Nominee Relation Type is Selected");
	executeStep(click(GCV.Continue_NominnePage), thisClass, "Continue To Nominee Page is Clicked");
	executeStep(click(GCV.OtherDetails), thisClass, "Other Details is Clicked");
	executeStep(click(GCV.InspectionDetails), thisClass, "Inspection Details is Clicked");
	executeStep(click(GCV.CalculatePremium_1), thisClass, "Calculate premium is Clicked");
	executionDelay(2000);
	executeStep(click(GCV.Submit), thisClass, "Submit is Clicked");
	executionDelay(6000);
	if (driver.findElement(GCV.SuccessMessage).isDisplayed()) {
		executeStep(true, thisClass, "Proposal Generated Succesfully");
	} else {
		executeStepWarnVal(false, thisClass, "Proposal  Not Generated Succesfully");
	}
}

public static void A4_Pack_New(Map<String, String> map) throws Exception {
	
	driver.get("https://smartzone.reliancegeneral.co.in:8443/Login/IMDLogin");
	executionDelay(3000);
	executeStep(click(GCV.Menu), thisClass, "Menu is clicked");
	executeStep(click(GCV.Motor), thisClass, "Motor is Clicked");
	executeStep(click(GCV.GCV), thisClass, "GCV is Clicked");
	executeStep(click(GCV.Quote), thisClass, "Quote is Clicked");
	executeStep(click(GCV.Package), thisClass, "Package is Clicked");
	executeStep(click(GCV.A4_Pack), thisClass, "A4 Package New is Clicked");
	executeStep(click(GCV.New), thisClass, "New is Clicked");
	executeStep(click(GCV.Model), thisClass, "Model is Clicked");
	executeStep(click(GCV.Mahindra), thisClass, "Mahindra is Clicked");
	executeStep(click(GCV.A3_Pack_Suggestion), thisClass, "Vehicle Suggestion is Clicked");
	executeStep(selectDropDownByText(GCV.Fuel_Type, (map.get("FuelType" + runCount))), thisClass, "Fuel Type is Selected");
	executeStep(click(GCV.RTO), thisClass, "RTO location is Clicked");
	executeStep(click(GCV.Mumbai), thisClass, "Mumbai is Clicked");
	executeStep(click(GCV.MH_01), thisClass, "RTO Suggestion is Clicked");
	executeStep(click(GCV.Calculate_Premium), thisClass, "Calculate premium is Clicked");
	executionDelay(4000);
	executeStep(click(GCV.Convert), thisClass, "Covert is Clicked");
	executionDelay(2000);
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
	executeStep(writeInInput(GCV.Engine_Number, (map.get("Engine" + runCount))), thisClass, "Engine Number is Entered");
	executeStep(writeInInput(GCV.Chassis_Number, (map.get("Chassis" + runCount))), thisClass, "Chassis Number is Entered");
	executeStep(selectDropDownByText(GCV.Vehicle_Type, (map.get("Vehicle" + runCount))), thisClass, "Vehicle Type is Selected");
	executeStep(click(GCV.Get_Cover_Details), thisClass, "Get cover Details is Clicked");
	executeStep(click(GCV.Additional_Cover), thisClass, "Additional Cover is Clicked");
	executeStep(writeInInput(GCV.NomineeName, (map.get("Name" + runCount))), thisClass, "Nominee Name is Entered");
	executeStep(writeInInput(GCV.NomineeAge, (map.get("Age" + runCount))), thisClass, "Nominee Age is Entered");
	executeStep(selectDropDownByText(GCV.NomineeRelationship, (map.get("Relation" + runCount))), thisClass, "Nominee Relation Type is Selected");
	executeStep(click(GCV.Continue_NominnePage), thisClass, "Continue To Nominee Page is Clicked");
	executeStep(click(GCV.OtherDetails), thisClass, "Other Details is Clicked");
	executeStep(click(GCV.InspectionDetails), thisClass, "Inspection Details is Clicked");
	executeStep(click(GCV.CalculatePremium_1), thisClass, "Calculate premium is Clicked");
	executionDelay(2000);
	executeStep(click(GCV.Submit), thisClass, "Submit is Clicked");
	executionDelay(6000);
	if (driver.findElement(GCV.SuccessMessage).isDisplayed()) {
		executeStep(true, thisClass, "Proposal Generated Succesfully");
	} else {
		executeStepWarnVal(false, thisClass, "Proposal  Not Generated Succesfully");
	}
	
}
	
}
