package Utilities;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class HtmlReportUtil.
 */
public class HtmlReportUtil {

	/** The extent no history. */
	private static ExtentReports extentNoHistory = null;

	/** The extent preserver history. */
//	private static ExtentReports extentPreserverHistory = null;

	/** The test. */
	private static ExtentTest test = null;

	/** The test hist. */
//	private static ExtentTest testHist = null;

	/**
	 * Inits the.
	 */
	private static void init() {
		Date date= new Date();
		 
		 long time = date.getTime();
		     System.out.println("Time in Milliseconds: " + time);
		if (extentNoHistory == null) {
			extentNoHistory = new ExtentReports(Utility.getValue("HtmlReport") + "\\TestReport_"+time+".html", true,
					DisplayOrder.OLDEST_FIRST);
			extentNoHistory = new ExtentReports(
					System.getProperty("user.dir") + "\\ExecutionReports\\HtmlReport\\TestReport_"+time+".html", true,
					DisplayOrder.OLDEST_FIRST);
			extentNoHistory.loadConfig(new File(Utility.getValue("HtmlReportConfigFile")));

		}
//		if (extentPreserverHistory == null) {
//			extentPreserverHistory = new ExtentReports(Utility.getValue("HtmlReport") + "\\TestReportHistory.html",
//					true, DisplayOrder.NEWEST_FIRST);
//			extentPreserverHistory = new ExtentReports(
//					System.getProperty("user.dir") + "\\ExecutionReports\\HtmlReport\\TestHistoryReport.html", true,
//					DisplayOrder.NEWEST_FIRST);
//			extentPreserverHistory.loadConfig(new File(Utility.getValue("HtmlReportConfigFile")));
//
//		}
	}

	/**
	 * Start report.
	 *
	 * @param testName
	 *            the test name
	 * @param testInfo
	 *            the test info
	 * @param category
	 *            the category
	 */
	public static void startReport(String testName,  String category) {
		try
		{
			/*System.out.println(testName);
			System.out.println(category);*/
		init();
		test = extentNoHistory.startTest(testName);
//		testHist = extentPreserverHistory.startTest(testName);
		test.assignCategory(category);
//		testHist.assignCategory(category);
		// if(CustomListener.platformName.equals("Native_App"))
		// {
		// extentNoHistory.addSystemInfo("OS",CustomListener.platformName +"
		// "+DriverUtil.deviceVersion);
		// extentNoHistory.addSystemInfo("Browser","N.A.");
		// extentPreserverHistory.addSystemInfo("OS",CustomListener.platformName +"
		// "+DriverUtil.deviceVersion);
		// extentPreserverHistory.addSystemInfo("Browser","N.A.");
		// }

		Map<String, String> environmentMap = new HashMap<String, String>();
		environmentMap.put("Browser", DriverUtil.webBrowserName );
		environmentMap.put("OS", DriverUtil.platformName);

		

		extentNoHistory.addSystemInfo(environmentMap);
		}
		catch (Exception e) {
			// TODO: handle exception
			//System.oust.println(e);
		}
//		extentPreserverHistory.addSystemInfo(environmentMap);

	}

	/**
	 * End report.
	 *
	 * @param status
	 *            the status
	 * @param stepName
	 *            the step name
	 */
	public static void endReport(boolean status, String stepName) {

		extentNoHistory.endTest(test);

		if (status) {
			test.log(LogStatus.PASS, stepName + " - PASSED","");

		} else {
			test.log(LogStatus.FAIL, stepName + " - FAILED","");
		}

		extentNoHistory.flush();

	}

	/**
	 * Step pass.
	 *
	 * @param stepName
	 *            the step name
	 */
	public static void stepPass(String imagePath,String stepName) {
		String image = test.addScreenCapture(imagePath);
		String html = "<span style='color:green'><b>" + stepName + " - PASS</b></span>";
		test.log(LogStatus.INFO, html,image);
//		testHist.log(LogStatus.INFO, html);
	}

	/**
	 * Step fail.
	 *
	 * @param stepName
	 *            the step name
	 */
	public static void stepFail(String imagePath,String stepName) {
		String image = test.addScreenCapture(imagePath);
		String html = "<span style='color:red'><b>" + stepName + " - FAIL</b></span>";
		test.log(LogStatus.INFO, html,image);
//		testHist.log(LogStatus.INFO, html);

	}

	/**
	 * Step info.
	 *
	 * @param stepName
	 *            the step name
	 */
	public static void stepInfo(String stepName) {
		// String html = "<span style='color:#F39C12'><b>" + stepName + " -
		// INFO</b></span>";
//		String image = test.addScreenCapture(imagePath);
		test.log(LogStatus.INFO, stepName,"");
//		testHist.log(LogStatus.INFO, stepName);

	}
	
	/**
	 * Step info.
	 *
	 * @param stepName
	 *            the step name
	 */
	public static void stepWarning(String imagePath,String stepName) {
		// String html = "<span style='color:#F39C12'><b>" + stepName + " -
		// INFO</b></span>";
		String image = test.addScreenCapture(imagePath);
		test.log(LogStatus.WARNING, stepName,"");
//		testHist.log(LogStatus.INFO, stepName);

	}


	/**
	 * Step error.
	 *
	 * @param stepName
	 *            the step name
	 */
	public static void stepError(String stepName) {

		String html = "<span class='fatal'><b>" + stepName + "</b></span>";
		test.log(LogStatus.ERROR, html,"");
//		testHist.log(LogStatus.ERROR, html);

	}

	/**
	 * Step error.
	 *
	 * @param stepName
	 *            the step name
	 * @param t
	 *            the t
	 */
	public static void stepError(String stepName, Throwable t) {

		String html = "<span class='fatal'>" + stepName + "</span>";
		test.log(LogStatus.ERROR, html, t);
//		testHist.log(LogStatus.ERROR, html, t);

	}

	/**
	 * End report skipped.
	 *
	 * @param stepName
	 *            the step name
	 * @param t
	 *            the t
	 */
	public static void endReportSkipped(String stepName, Throwable t) {

		String html = "<span style='color:#e59127'>" + stepName + "</span>";

		test.log(LogStatus.SKIP, html, t);
//		testHist.log(LogStatus.SKIP, html, t);

		extentNoHistory.endTest(test);
//		extentPreserverHistory.endTest(testHist);

		extentNoHistory.flush();
//		extentPreserverHistory.flush();

	}

	/**
	 * Attach screenshot.
	 *
	 * @param imagePath
	 *            the image path
	 * @param flag
	 *            the flag
	 */
	public static void attachScreenshot(String imagePath, Boolean flag) {

		String image = test.addScreenCapture(imagePath);

		if (flag) {
			test.log(LogStatus.FAIL, "ScreenShot: " + Utility.testException, image);
//			testHist.log(LogStatus.FAIL, "ScreenShot:" + Utility.testException, image);

		} else {

			test.log(LogStatus.INFO, "ScreenShot: " + Utility.testException, image);
//			testHist.log(LogStatus.INFO, "ScreenShot:" + Utility.testException, image);

		}

	}

	/**
	 * Tear down report.
	 */
	public static void tearDownReport() {
		extentNoHistory.flush();

		if (extentNoHistory != null)
			extentNoHistory.close();

	}
	/*
	 * This method is used for warning the statement with the screen shot if it is fail or pass
	 */
	public static void stepWarnImage(String imagePath,String stepName,boolean flag)
	{
		String image = test.addScreenCapture(imagePath);
		if (flag) {
			test.log(LogStatus.INFO,stepName,image);
			//			testHist.log(LogStatus.FAIL, "ScreenShot:" + Utility.testException, image);

		} else { 

			test.log(LogStatus.WARNING,stepName, image);
			//			testHist.log(LogStatus.INFO, "ScreenShot:" + Utility.testException, image);

		}
	}

}
