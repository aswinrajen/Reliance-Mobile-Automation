package Methods;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import MobileWebLocators.Others;
import Utilities.KeywordUtil;

public class OthersMethod extends KeywordUtil {
	

	static int runCount = 0;
	/** The this class. */
	@SuppressWarnings("rawtypes")
	static Class thisClass = OthersMethod.class;
	public static JavascriptExecutor js = (JavascriptExecutor) driver;
	static String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
			+ "var elementTop = arguments[0].getBoundingClientRect().top;"
			+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";

		
	
	public static void RGI_Website(Map<String, String> map) throws Exception {
		driver.get("https://www.reliancegeneral.co.in/Insurance/Home.aspx");
		Thread.sleep(3000);
		
	}
	public static void Doctors(Map<String, String> map) throws Exception {
		
		executeStep(click(Others.Menu), thisClass,"Menu is clicked");
	    executeStep(click(Others.Others), thisClass, "Others is Clicked");
	    executeStep(click(Others.Doctors), thisClass, "Doctors Professional Indemnity is Clicked");
	    executionDelay(3000);
	   
	}
	public static void Basic_Details(Map<String, String> map) throws Exception { 
		 executionDelay(3000);
	    executeStep(selectDropDownByText(Others.Select_Profession, (map.get("Profession" + runCount))), thisClass, "Selected Profession");
	    executeStep(writeInInput(Others.EventLimit, (map.get("Event Limit" + runCount))), thisClass, "Entered Event Limit");
		
	    executeStep(selectDropDownByText(Others.Select_ratio, (map.get("Ratio" + runCount))), thisClass, "Selected Ratio");
	    executeStep(writeInInput(Others.Email, (map.get("TEmail" + runCount))), thisClass, "Entered Email");
		executeStep(writeInInput(Others.MobileNum, (map.get("TMobile Number" + runCount))), thisClass, "Entered Mobile Number");
	    executeStep(click(Others.BuyOnline), thisClass, "Buy Online is Clicked");
	    Thread.sleep(8000);
	    executeStep(click(Others.Continuetobuy), thisClass, "Continue to Buy is Clicked");
	    Thread.sleep(5000);
	}
	    public static void Policy_Details(Map<String, String> map) throws Exception { 
	    ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle,
				driver.findElement(Others.ClaimStatus_No));
	    
	    executeStep(click(Others.ClaimStatus_No), thisClass, "Claim Status is Clicked");
	    executeStep(click(Others.Next), thisClass,"Next is clicked");
	    executeStep(writeInInput(Others.PANnum, (map.get("PanNo" + runCount))), thisClass, "Entered Pan Number");
	    executeStep(click(Others.PANdob), thisClass, "PAN DOB is Clicked");
	    executeStep(selectDropDownByText(Others.Year, (map.get("Year" + runCount))), thisClass, "Selected Year");
	    executeStep(selectDropDownByText(Others.Month, (map.get("Month" + runCount))), thisClass, "Selected Month");
	    executeStep(click(Others.Date), thisClass,"Date is clicked");
	    executeStep(click(Others.ValidatePAN), thisClass, "Validate PAN is Clicked");
	    executeStep(click(Others.Ok), thisClass, "OK is Clicked");
	    executeStep(click(Others.Next), thisClass,"Next is clicked");
	    Thread.sleep(5000);
	    }
	    public static void Customer_Details(Map<String, String> map) throws Exception { 
//	    executeStep(writeInInput(Others.FirstName, (map.get("FName" + runCount))), thisClass, "Entered FName");
//		executeStep(writeInInput(Others.LastName, (map.get("LName" + runCount))), thisClass, "Entered LName");
//		executeStep(writeInInput(Others.DOB, (map.get("DOB" + runCount))), thisClass, "Entered DOB");
//		
//		executeStep(selectDropDownByText(Others.Year, (map.get("TYear" + runCount))), thisClass, "Selected Year");
//		 Thread.sleep(2000);
//		executeStep(click(Others.Date), thisClass,"Date is clicked");
		
		executeStep(click(Others.InsuredAddress), thisClass,"Insured Address is clicked");
//		executeStep(writeInInput(Others.CommunicationAddress, (map.get("Communication Address" + runCount))), thisClass, "Entered Communication Address");
//		executeStep(writeInInput(Others.LandMark, (map.get("LandMark" + runCount))), thisClass, "Entered LandMark");
		executeStep(writeInInput(Others.Pincode, (map.get("Pincode" + runCount))), thisClass, "Entered Pincode");
		 Thread.sleep(2000);
		executeStep(click(Others.Pincode_Opt), thisClass,"Pincode Suggestion is clicked");
		 Thread.sleep(2000);
	    executeStep(click(Others.Next), thisClass,"Next is clicked");
	    Thread.sleep(5000);
	    }  
	    
	    public static void Professional_Details(Map<String, String> map) throws Exception { 
	   	Thread.sleep(5000);
	    executeStep(selectDropDownByText(Others.BranchofMedicine, (map.get("Branch" + runCount))), thisClass, "Selected Branch of Medicine");
	    executeStep(selectDropDownByText(Others.ProfessionalQualification, (map.get("Qualification" + runCount))), thisClass, "Selected Qualification");
	    executeStep(selectDropDownByText(Others.LineofSpecilization, (map.get("Specilization" + runCount))), thisClass, "Selected Specilization");
	    executeStep(selectDropDownByText(Others.YearofPassing, (map.get("Passed Year" + runCount))), thisClass, "Selected Passed Year");
	    executeStep(writeInInput(Others.YearsofPractice, (map.get("Practice" + runCount))), thisClass, "Entered Years of Practice");
	    executeStep(writeInInput(Others.MedicalNo, (map.get("Reg No" + runCount))), thisClass, "Entered Medical Reg No");
	    executeStep(click(Others.Next), thisClass,"Next is clicked");
	    Thread.sleep(5000);
	    } 
	    public static void Summary_Payment_Page(Map<String, String> map) throws Exception { 
	        Thread.sleep(5000);
	    executeStep(clickByJavaScript(Others.Pay), thisClass,"Pay is clicked");
	   
		  
	}
}
