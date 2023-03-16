package Reliance_Suites;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Methods.OthersMethod;
import TestNGListeners.CustomListener;
import Utilities.ExcelDataUtil;
import Utilities.HtmlReportUtil;
import Utilities.KeywordUtil;
import Utilities.LogUtil;
import Utilities.TestData;
import Utilities.TestResults;
import Utilities.Utility;

@Listeners(CustomListener.class)
public class Others_Module extends KeywordUtil {
	
	/** The suite name. */
	static String suiteName = "Others_Module";
	/** The err logs. */
	protected List<String[]> errLogs = new ArrayList<>();
	/** The is run. */
	static boolean isRun;
	/** The check. */
	static boolean check = true;
	/** The status. */
	static boolean status = true;
	/** The expected. */
	static String expected = "NA";
	/** The actual. */
	static String actual = "NA";
	/** The log step. */
	static String logStep;
	/** The rows. */
	static int rows = 0;
	/** The retry count. */
	static int retryCount = getIntValue("retryCount");
	/** The retrying number. */
	static int retryingNumber = 1;
	/** The this class. */
	@SuppressWarnings("rawtypes")
	static Class thisClass = Others_Module.class;
	/** The test case ID. */
	static String testCaseID = thisClass.getSimpleName();
	/** The step. */
	static String step;
	/** The run count. */
	static int runCount = 0;

	/**
	 * Before test.
	 */
	@BeforeTest
	public static void beforeTest() {
		Utility.testData = new TestData();
		Utility.testResult = new TestResults();
		Utility.testResult.setDateOfExecution(Utility.getDateTime());
		LogUtil.infoLog(thisClass,
				"**********************Test Started:" + testCaseID + "*************************************");
		/* for sepearate sheet added getting lastrownumber to dataprovider */
		// rows = getExecutionsNumber(testCaseID);
		// System.out.println("Rows : " + rows);
	}

	/**
	 * Before method.
	 */
	@BeforeMethod
	public static void beforeMethod() {
		System.out.println("Before Method");
		Utility.testData = ExcelDataUtil.getTestDataWithTestCaseID(testCaseID);
		System.out.println("Utility.testData::::" + Utility.testData);
		// DriverUtil.getBrowser(browserNameFromExcel) = testCaseID + " - " +
		// (runCount + 1);
	}

	/**
	 * Data supplier.
	 *
	 * @return the object[]
	 */
	@DataProvider(name = "BrowserLaunch")
	public static Object[] BrowserLaunch() {
		System.out.println("Test");
		rows = getExecutionsNumber("Data");
		System.out.println("Rows : " + rows);
		return Utility.dataSupplier("Data", rows);
	}

	
	@Test(priority = 0, dataProvider = "BrowserLaunch", enabled = true)
	public static void RGI_Website(Map<String, String> map) throws Exception {
		System.out.println("Flag 1");
		HtmlReportUtil.startReport(
				"Doctor's Professional Indemnity",Utility.testData.getTestCaseSummary());
		try {
			isRun = ExcelDataUtil.getControls(suiteName, "RGI_Website");
			if ((!isRun))
				throw new SkipException("Skipping this exception");
			System.out.println("Running for " + (runCount + 1) + " time");
			//logStep = Utility.testData.getTestCaseSummary();
			// ------------------------//--------Code for method starts here---------
			System.out.println("Test");
			OthersMethod.RGI_Website(map);

			// ---------------------------//--------Code for method ends here---------
		} catch (SkipException skip) {
			Utility.testException = skip;
			throw skip;
		} catch (Exception e) {
			// LogUtil.infoLog(thisClass, KeywordUtil.step + " - FAIL ");
			// HtmlReportUtil.stepFail(KeywordUtil.step);
			if (retryCount > 0) {
				String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				HtmlReportUtil.stepInfo("Trying to Rerun" + " " + testCaseID + " for " + retryingNumber + " time");
				retryCount--;
				retryingNumber++;
				LogUtil.infoLog(thisClass, "****************Waiting for " + getIntValue("retryDelayTime")
						+ " Secs before retrying.***********");
				executionDelay(getIntValue("retryDelayTime"));
			} else {
				String imagePath = Utility.takeScreenshot(driver, testCaseID);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				HtmlReportUtil.stepError(testCaseID, e);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				Assert.fail();
			}
		} finally {
			runCount++;
		}
	}
	
	@Test(priority = 1, dataProvider = "BrowserLaunch", enabled = true)
	public static void Doctors(Map<String, String> map) throws Exception {
		System.out.println("Flag 1");
		HtmlReportUtil.startReport(
				"Doctor's Professional Indemnity",Utility.testData.getTestCaseSummary());
		try {
			isRun = ExcelDataUtil.getControls(suiteName, "Doctors");
			if ((!isRun))
				throw new SkipException("Skipping this exception");
			System.out.println("Running for " + (runCount + 1) + " time");
			logStep = Utility.testData.getTestCaseSummary();
			// ------------------------//--------Code for method starts here---------
			OthersMethod.Doctors(map);

			// ---------------------------//--------Code for method ends here---------
		} catch (SkipException skip) {
			Utility.testException = skip;
			throw skip;
		} catch (Exception e) {
			// LogUtil.infoLog(thisClass, KeywordUtil.step + " - FAIL ");
			// HtmlReportUtil.stepFail(KeywordUtil.step);
			if (retryCount > 0) {
				String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				HtmlReportUtil.stepInfo("Trying to Rerun" + " " + testCaseID + " for " + retryingNumber + " time");
				retryCount--;
				retryingNumber++;
				LogUtil.infoLog(thisClass, "****************Waiting for " + getIntValue("retryDelayTime")
						+ " Secs before retrying.***********");
				executionDelay(getIntValue("retryDelayTime"));
			} else {
				String imagePath = Utility.takeScreenshot(driver, testCaseID);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				HtmlReportUtil.stepError(testCaseID, e);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				Assert.fail();
			}
		} finally {
			runCount++;
		}
	}
	
	@Test(priority = 2, dataProvider = "BrowserLaunch", enabled = true)
	public static void Basic_Details(Map<String, String> map) throws Exception {
		System.out.println("Flag 1");
		HtmlReportUtil.startReport(
				"Doctor's Professional Indemnity",Utility.testData.getTestCaseSummary());
		try {
			isRun = ExcelDataUtil.getControls(suiteName, "Basic_Details");
			if ((!isRun))
				throw new SkipException("Skipping this exception");
			System.out.println("Running for " + (runCount + 1) + " time");
			logStep = Utility.testData.getTestCaseSummary();
			// ------------------------//--------Code for method starts here---------
			OthersMethod.Basic_Details(map);

			// ---------------------------//--------Code for method ends here---------
		} catch (SkipException skip) {
			Utility.testException = skip;
			throw skip;
		} catch (Exception e) {
			// LogUtil.infoLog(thisClass, KeywordUtil.step + " - FAIL ");
			// HtmlReportUtil.stepFail(KeywordUtil.step);
			if (retryCount > 0) {
				String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				HtmlReportUtil.stepInfo("Trying to Rerun" + " " + testCaseID + " for " + retryingNumber + " time");
				retryCount--;
				retryingNumber++;
				LogUtil.infoLog(thisClass, "****************Waiting for " + getIntValue("retryDelayTime")
						+ " Secs before retrying.***********");
				executionDelay(getIntValue("retryDelayTime"));
			} else {
				String imagePath = Utility.takeScreenshot(driver, testCaseID);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				HtmlReportUtil.stepError(testCaseID, e);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				Assert.fail();
			}
		} finally {
			runCount++;
		}
	}
	
	@Test(priority = 3, dataProvider = "BrowserLaunch", enabled = true)
	public static void Policy_Details(Map<String, String> map) throws Exception {
		System.out.println("Flag 1");
		HtmlReportUtil.startReport(
				"Doctor's Professional Indemnity",Utility.testData.getTestCaseSummary());
		try {
			isRun = ExcelDataUtil.getControls(suiteName, "Policy_Details");
			if ((!isRun))
				throw new SkipException("Skipping this exception");
			System.out.println("Running for " + (runCount + 1) + " time");
			logStep = Utility.testData.getTestCaseSummary();
			// ------------------------//--------Code for method starts here---------
			OthersMethod.Policy_Details(map);

			// ---------------------------//--------Code for method ends here---------
		} catch (SkipException skip) {
			Utility.testException = skip;
			throw skip;
		} catch (Exception e) {
			// LogUtil.infoLog(thisClass, KeywordUtil.step + " - FAIL ");
			// HtmlReportUtil.stepFail(KeywordUtil.step);
			if (retryCount > 0) {
				String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				HtmlReportUtil.stepInfo("Trying to Rerun" + " " + testCaseID + " for " + retryingNumber + " time");
				retryCount--;
				retryingNumber++;
				LogUtil.infoLog(thisClass, "****************Waiting for " + getIntValue("retryDelayTime")
						+ " Secs before retrying.***********");
				executionDelay(getIntValue("retryDelayTime"));
			} else {
				String imagePath = Utility.takeScreenshot(driver, testCaseID);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				HtmlReportUtil.stepError(testCaseID, e);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				Assert.fail();
			}
		} finally {
			runCount++;
		}
	}
	
	@Test(priority = 4, dataProvider = "BrowserLaunch", enabled = true)
	public static void Customer_Details(Map<String, String> map) throws Exception {
		System.out.println("Flag 1");
		HtmlReportUtil.startReport(
				"Doctor's Professional Indemnity",Utility.testData.getTestCaseSummary());
		try {
			isRun = ExcelDataUtil.getControls(suiteName, "Customer_Details");
			if ((!isRun))
				throw new SkipException("Skipping this exception");
			System.out.println("Running for " + (runCount + 1) + " time");
			logStep = Utility.testData.getTestCaseSummary();
			// ------------------------//--------Code for method starts here---------
			OthersMethod.Customer_Details(map);

			// ---------------------------//--------Code for method ends here---------
		} catch (SkipException skip) {
			Utility.testException = skip;
			throw skip;
		} catch (Exception e) {
			// LogUtil.infoLog(thisClass, KeywordUtil.step + " - FAIL ");
			// HtmlReportUtil.stepFail(KeywordUtil.step);
			if (retryCount > 0) {
				String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				HtmlReportUtil.stepInfo("Trying to Rerun" + " " + testCaseID + " for " + retryingNumber + " time");
				retryCount--;
				retryingNumber++;
				LogUtil.infoLog(thisClass, "****************Waiting for " + getIntValue("retryDelayTime")
						+ " Secs before retrying.***********");
				executionDelay(getIntValue("retryDelayTime"));
			} else {
				String imagePath = Utility.takeScreenshot(driver, testCaseID);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				HtmlReportUtil.stepError(testCaseID, e);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				Assert.fail();
			}
		} finally {
			runCount++;
		}
	}
	
	@Test(priority = 5, dataProvider = "BrowserLaunch", enabled = true)
	public static void Professional_Details(Map<String, String> map) throws Exception {
		System.out.println("Flag 1");
		HtmlReportUtil.startReport(
				"Doctor's Professional Indemnity",Utility.testData.getTestCaseSummary());
		try {
			isRun = ExcelDataUtil.getControls(suiteName, "Professional_Details");
			if ((!isRun))
				throw new SkipException("Skipping this exception");
			System.out.println("Running for " + (runCount + 1) + " time");
			logStep = Utility.testData.getTestCaseSummary();
			// ------------------------//--------Code for method starts here---------
			OthersMethod.Professional_Details(map);

			// ---------------------------//--------Code for method ends here---------
		} catch (SkipException skip) {
			Utility.testException = skip;
			throw skip;
		} catch (Exception e) {
			// LogUtil.infoLog(thisClass, KeywordUtil.step + " - FAIL ");
			// HtmlReportUtil.stepFail(KeywordUtil.step);
			if (retryCount > 0) {
				String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				HtmlReportUtil.stepInfo("Trying to Rerun" + " " + testCaseID + " for " + retryingNumber + " time");
				retryCount--;
				retryingNumber++;
				LogUtil.infoLog(thisClass, "****************Waiting for " + getIntValue("retryDelayTime")
						+ " Secs before retrying.***********");
				executionDelay(getIntValue("retryDelayTime"));
			} else {
				String imagePath = Utility.takeScreenshot(driver, testCaseID);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				HtmlReportUtil.stepError(testCaseID, e);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				Assert.fail();
			}
		} finally {
			runCount++;
		}
	}
	
	@Test(priority = 6, dataProvider = "BrowserLaunch", enabled = true)
	public static void Summary_Payment_Page(Map<String, String> map) throws Exception {
		System.out.println("Flag 1");
		HtmlReportUtil.startReport(
				"Doctor's Professional Indemnity",Utility.testData.getTestCaseSummary());
		try {
			isRun = ExcelDataUtil.getControls(suiteName, "Summary_Payment_Page");
			if ((!isRun))
				throw new SkipException("Skipping this exception");
			System.out.println("Running for " + (runCount + 1) + " time");
			logStep = Utility.testData.getTestCaseSummary();
			// ------------------------//--------Code for method starts here---------
			OthersMethod.Summary_Payment_Page(map);

			// ---------------------------//--------Code for method ends here---------
		} catch (SkipException skip) {
			Utility.testException = skip;
			throw skip;
		} catch (Exception e) {
			// LogUtil.infoLog(thisClass, KeywordUtil.step + " - FAIL ");
			// HtmlReportUtil.stepFail(KeywordUtil.step);
			if (retryCount > 0) {
				String imagePath = Utility.takeScreenshot(driver, testCaseID + "_" + retryingNumber);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				HtmlReportUtil.stepInfo("Trying to Rerun" + " " + testCaseID + " for " + retryingNumber + " time");
				retryCount--;
				retryingNumber++;
				LogUtil.infoLog(thisClass, "****************Waiting for " + getIntValue("retryDelayTime")
						+ " Secs before retrying.***********");
				executionDelay(getIntValue("retryDelayTime"));
			} else {
				String imagePath = Utility.takeScreenshot(driver, testCaseID);
				Utility.testResult.setFailedScreenShotReference(imagePath);
				HtmlReportUtil.stepError(testCaseID, e);
				Utility.testException = e;
				HtmlReportUtil.attachScreenshot(imagePath, true);
				Assert.fail();
			}
		} finally {
			runCount++;
		}
	}
	
}
