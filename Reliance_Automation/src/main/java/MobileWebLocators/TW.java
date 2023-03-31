package MobileWebLocators;

import org.openqa.selenium.By;

public class TW {
	public static By UserName=By.xpath("//input[@id='txtUserName']");
	public static By Password=By.xpath("//input[@id='txtPassword']");
	public static By Login=By.xpath("//*[@id=\"frmLogin\"]/div[3]/button");
	public static By hamburgerSubmit = By.xpath("//*[@id=\"MyIMDfrm\"]/header/a/div/div");
	public static By hamburgerMenu = By.xpath("/html/body/header/a/div/div");
	public static By Motor = By.xpath("/html/body/div[5]/div[4]/ul/li[2]/a/span");
	public static By PolicyTW = By.xpath("//*[@id=\"product-card1\"]/ul[2]/div/li[2]/label");
	public static By createPolicy = By.xpath("//*[@id=\"product-card2\"]/ul[1]/div/li[2]");
	public static By sellingPackage = By.xpath("//*[@id=\"twowheeler\"]/li[1]/label");
	public static By Customer_Page=By.xpath("//div[@id='txtCustomerName']");
	public static By Marital_Status=By.xpath("//select[@id='ddlMaritalStatus']");
	public static By Continue=By.xpath("(//button[contains(text(),'Continue')])[1]");
	public static By Mobile_Number=By.xpath("//input[@id='txtMobileNumber']");
	public static By Continue_1=By.xpath("//button[@onclick='submitcustomerDetails()']");
	public static By newTWPolicy = By.xpath("//*[@id=\"twoWheelerPackage\"]/li[2]/label");
	public static By newPolicy = By.xpath("//*[@id=\"menuNew\"]");
	public static By proprietor = By.xpath("//*[@id=\"divPropritoryShip\"]/label/label");
	public static By searchCustomer = By.xpath("//*[@for=\"searchCustomerFeild\"]");
	public static By dOB = By.xpath("//*[@id=\"DPDateOfBirth\"]");
    public static By pAN = By.xpath("//*[@id=\"txtPanNumber\"]");
    public static By Verify=By.xpath("//input[@id='btnVerifyPan']");
    public static By VehicleDetailsButton = By.xpath("//*[@id=\"nextBtnBottom\"]");
    public static By customer_1 = By.xpath("//*[@id=\"IdDynamicControl0\"]");
    public static By customer_2 = By.xpath("//*[@id=\"IdDynamicControl1\"]");
    public static By saveButton = By.xpath("(//button[contains(text(),'Save')])[1]");
    public static By modelVariant = By.xpath("//*[@id=\"modelVariantFeild\"]");
    public static By modelSelection = By.xpath("//*[@id=\"BAJAJ\"]/label");
    public static By fuelType = By.xpath("//*[@id=\"TypeOfFuelVehicle\"]");
    public static By brand = By.xpath("//*[@id=\"BAJAJ\"]/div/img");
    public static By variant = By.xpath("//*[@id=\"BAJAJ | BOXER | 125 CC | CC/Watt 125\"]");
    public static By rtoLocation = By.xpath("//*[@id=\"lblrtoLocationField\"]");
    public static By locationRegistered = By.xpath("//*[@id=\"MUMBAI\"]/div/img");
    public static By MH01 = By.xpath("//*[@id=\"MAHARASHTRA | Mumbai Central-Tardeo | MH-01\"]");
    public static By manufacturingMonth = By.xpath("//*[@id=\"Manufacturing_MonthVehicle\"]");
    public static By purchaseDate = By.xpath("//*[@id=\"Date_PurchaseVehicle\"]");
    public static By registeredDate = By.xpath("//*[@id=\"Date_RegistrationVehicle\"]");
    public static By engineNumber = By.xpath("//*[@id=\"EngineNumberVehicle\"]");
    public static By chasisNumber = By.xpath("//*[@id=\"ChasisNumberVehicle\"]");
    public static By coverDetails = By.xpath("//*[@id=\"nextBtnBottom\"]");
    public static By nomineeName = By.xpath("//*[@id=\"txtPAOwnerCoverNomineeName\"]");
    public static By nomineeAge = By.xpath("//*[@id=\"txtNomineeAge\"]");
    public static By nomineeRelationship = By.xpath("//*[@id=\"Nominee_RelationshipOwnerDriver\"]");
    public static By continueButton = By.xpath("//*[@id=\"getCoversContinue\"]");
    public static By inspectionDetails = By.xpath("//*[@id=\"nextBtnBottom\"]");
    public static By calculatePremium = By.xpath("//*[@id=\"nextBtnBottom\"]");
    public static By saveCalculation = By.xpath("//*[@id=\"innerContentAdd\"]/div[2]/div[3]/div/button");
    public static By finalClose = By.xpath("//*[@id=\"MyIMDfrm\"]/div[15]/div[1]/div/div/img");
    
    // Two wheeler -> liability
    
    public static By liability = By.xpath("//*[@id=\"twowheeler\"]/li[2]/label");
    public static By liabilityNew = By.xpath("//*[@id=\"twoWheelerLiability\"]/li[2]/label");
    public static By liabilityNewPolicy = By.xpath("//*[@id=\"menuNew\"]/label");    
    
    // Two wheeler -> Quote
    
    public static By createQuote = By.xpath("//*[@id=\"product-card2\"]/ul[1]/div/li[1]/label");
  
}

