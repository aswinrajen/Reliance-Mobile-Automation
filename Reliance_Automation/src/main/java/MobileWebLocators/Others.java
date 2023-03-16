package MobileWebLocators;

import org.openqa.selenium.By;

public class Others {

		public static By Menu = By.xpath("(//button[contains(@class,'navbar-toggle')])[1]");
	    public static By Others=By.xpath("//a[contains(text(),'Others')]");
	    public static By Doctors=By.xpath("//div[contains(text(),'Doctors - Professional Indemnity Insurance ')]");
	    public static By Select_Profession=By.xpath("//select[contains(@id,'ddlMBBS')]");
	    public static By EventLimit=By.xpath("//input[@id='txtPIPerEventLimit']");
	    public static By Select_ratio=By.xpath("//select[contains(@id,'ddlPIratio')]");
	    
	    public static By Email=By.xpath("//input[contains(@id,'EmailId')]");
	    public static By MobileNum=By.xpath("//input[contains(@id,'MobileNo')]");
	    public static By BuyOnline=By.xpath("//input[contains(@id,'btnCalculatePremium')]");
	    public static By Continuetobuy=By.xpath("//input[contains(@id,'btnContinueToBuy')]");
		
	    public static By ClaimStatus_No=By.xpath("//label[contains(@id,'ClaimconsentNo')]");
	    public static By Next=By.xpath("//input[contains(@id,'btnNext')]");
	    public static By FirstName=By.xpath("//input[contains(@id,'FirstName')]");
	    public static By LastName=By.xpath("//input[contains(@id,'LastName')]");
	    public static By DOB=By.xpath("//input[contains(@id,'BirthDate')]");
	    public static By Year = By.xpath("//select[contains(@class,'datepicker-year')]");
		public static By Date = By.xpath("(//select[contains(@class,'datepicker-year')]//following::a[contains(text(),'1')])[1]");
	    public static By InsuredAddress=By.xpath("//a[contains(text(),'Insured Member’s Address')]");
	    public static By CommunicationAddress=By.xpath("//textarea[contains(@id,'Communi_FlatBuilding')]");
	    public static By LandMark=By.xpath("//textarea[contains(@id,'Communi_Area')]");
	    public static By Pincode=By.xpath("//input[contains(@id,'Communi_Pincode')]");
	    public static By Pincode_Opt=By.xpath("//a[contains(text(),'422001')]");
	    public static By BranchofMedicine=By.xpath("//select[contains(@id,'BranchOfMedicine')]");
	    public static By ProfessionalQualification=By.xpath("//select[contains(@id,'ProfessionalQualification')]");
	    public static By LineofSpecilization=By.xpath("//select[contains(@id,'LineOfSpecilization')]");
	    public static By YearofPassing=By.xpath("//select[contains(@id,'YearofPassing')]");
	    public static By Communication=By.xpath("//a[contains(text(),'Communication Address')]");
	    public static By YearsofPractice=By.xpath("//input[contains(@id,'YearsOfPractice')]");
	    public static By MedicalNo=By.xpath("//input[contains(@id,'MedicalRegNo')]");
	    public static By Pay=By.xpath("//input[contains(@id,'btnNext')]");
		
	    public static By Makepayment=By.xpath("//input[contains(@name,'btnCCAvenue')]");
	    public static By RelianceHeading=By.xpath("//span[contains(text(),'Reliance General Insurance Company Ltd')]");
	    
	    
	    public static By PANnum=By.xpath("//input[@name='KYCPanno']");
	    public static By PANdob=By.xpath("//input[@placeholder='DOB As Per PAN*']");
	    public static By Month=By.xpath("//select[@class='ui-datepicker-month']");
	    public static By ValidatePAN=By.xpath("//input[contains(@id,'BtnVerifyPAN')]");
	    public static By Ok=By.xpath("//button[contains(text(),'OK')]");
	}


