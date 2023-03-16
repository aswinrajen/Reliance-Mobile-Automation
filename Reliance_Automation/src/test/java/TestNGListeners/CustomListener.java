package TestNGListeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.testng.IInvokedMethod;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import com.gargoylesoftware.htmlunit.javascript.host.event.CustomEvent;

import Utilities.DriverUtil;
import Utilities.ExcelDataUtil;
import Utilities.HtmlReportUtil;
import Utilities.KeywordUtil;
import Utilities.LogUtil;
import Utilities.Utility;
import Utilities.sendMail;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving custom events. The class that is
 * interested in processing a custom event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addCustomListener<code> method. When the custom event
 * occurs, that object's appropriate method is invoked.
 *
 * @see CustomEvent
 */
public class CustomListener extends KeywordUtil implements ITestListener, ISuiteListener {

	/** The test case ID. */
	static String testCaseID = "";

	Map<String, String> testReultClasses = new HashMap<String, String>();
	Map<String, String> testClassesSummary = new HashMap<String, String>();

	/** The start time. */
	Date startTime;

	/** The end time. */
	Date endTime;

	/** The total seconds. */
	static long totalSeconds = 0;

	/** The OS name from excel. */
	public static String oSNameFromExcel = null;

	/** The Execution environment from excel. */
	public static String executionEnvironmentFromExcel = null;

	/** The Browser name from excel. */
	public static String browserNameFromExcel = null;

	/** The Browser version from excel. */
	public static String browserVersionFromExcel = null;

	/** The count. */
	int count = 0;

	/** The hours. */
	long hours;

	/** The minutes. */
	long minutes;

	/** The seconds. */
	long seconds;

	/** The time string. */
	String timeString;
	
	private String mailBody;

	/** The fail testcases. */
	List<String> failTestcases = new ArrayList<>();

	/**
	 * Gets the browser name from excel.
	 *
	 * @return the browser name from excel
	 */
	public String getBrowserNameFromExcel() {
		browserNameFromExcel = ExcelDataUtil.getColumnValue("Execution Device Platform", "Devices", 1);
		return browserNameFromExcel;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ISuiteListener#onStart(org.testng.ISuite)
	 */
	@Override
	public void onStart(ISuite suite) {
		startTime = new Date();
		/*try {
			FileUtils.cleanDirectory(new File(getValue("screenshotPath")));
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		try {
			DriverUtil.getBrowser(getBrowserNameFromExcel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
		}
		
		/*
		 * if (driver == null) {
		 * 
		 * try {
		 * 
		 * platformName = ExcelDataUtil.getColumnValue("Platform(Windows,Android)",
		 * "Platform", 1);
		 * 
		 * } catch (Exception e) { LogUtil.errorLog(KeywordUtil.class, e.getMessage(),
		 * e); }
		 * 
		 * if ("Windows".equals(platformName)) { try { browserName =
		 * ExcelDataUtil.getColumnValue("Browser Configuration", "Browsers", 1); } catch
		 * (IOException e1) {
		 * 
		 * LogUtil.errorLog(KeywordUtil.class, e1.getMessage(), e1); }
		 * 
		 * try { // DriverUtil.getBrowser(browserName);
		 * 
		 * DriverUtil.getBrowser(getBrowserNameFromExcel(),
		 * getBrowserVersionFromExcel(), getOSNameFromExcel(),
		 * getExecutionEnvironmentFromExcel()); } catch (Exception e) {
		 * LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e); } }
		 */

		// }

		// driver.manage().deleteAllCookies();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ISuiteListener#onFinish(org.testng.ISuite)
	 */
	@Override
	public void onFinish(ISuite suite) {

		// HtmlReportUtil.endReport(true, Utility.testData.getTestCaseID());
		HtmlReportUtil.tearDownReport();
		Utility.renameFile();

		System.out.println(testReultClasses);
		ArrayList<String> keySet = new ArrayList<>(testReultClasses.keySet());

		try {
			mailBody = "<html>" + 
					"	<body>" + 
					"		<p>Hi ,<br><br>Please find below the test results.<br><br>Platform -->  " + 
					"		<table border=\"1\" cellpadding=\"2\">" + 
					"		<tr>" + 
					"		<th>S. No.</th>" + 
					"		<th>Module</th>" + 
					"		<th>Summary</th>" + 
					"		<th>Result</th>" + 
					"		</tr>";
			
			for(int i = 0;i<keySet.size();i++) {
				
				mailBody = mailBody + "		<tr>" + 
						"		<td><center>" + (i+1) +"</center></td>" + 
						"		<td>"+keySet.get(i).substring(4)+"</td>" + 
						"		<td>"+testClassesSummary.get(keySet.get(i))+"</td>" + 
						testReultClasses.get(keySet.get(i)) + 
						"		</tr>";
				
			}
			mailBody = mailBody + 
					"		</table>"+
					"		<br><br>" + 
					"		<p>Please find attached the detailed report.<br><br>Regards,<br><br>TestingXperts</p>" + 
					"	</body>" + 
					"</html>";
			
			if(getValue("sendMail").equalsIgnoreCase("Y"))
			sendMail.sendEmailToClient( mailBody, "Report Generated - BNY Daily Sanity Test - "+DriverUtil.platformName+" - "+DriverUtil.webBrowserName.toUpperCase(), true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// driver.quit();

		// if (Utility.getValue("sendMail").equalsIgnoreCase("Y"))
		// try {
		// sendMail.sendEmailToClient(
		// "Hi Ruth,\n\nGreetings for the day!\n\nPlease find the Email for the
		// Report.\n\n\nThanks & Regards\nTesingXperts");
		//
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	
	
	/*@Override
	public void onTestStart(ITestResult result) {
		startTime = new Date();
		try {
			DriverUtil.getBrowser(getBrowserNameFromExcel());
		} catch (Exception e) {
			LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
		}
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult testResult) {

		LogUtil.infoLog(getClass().getSimpleName(), "Total Time taken in(seconds):" + seconds);
		Utility.testResult.setResultStatus("PASS");
		Utility.testResult.setTotalTimeTaken(seconds);
		try {
			ExcelDataUtil.updateTestResults(Utility.testData, Utility.testResult);
		} catch (IOException e) {

			LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
		}
		HtmlReportUtil.endReport(true, testResult.getMethod().getMethodName());
		//driver.quit();
		testReultClasses.put(testResult.getMethod().getMethodName(), "<td style='color: #008000'><b>Passed</b></td>");
		testClassesSummary.put(testResult.getMethod().getMethodName(), Utility.testData.getTestCaseSummary());
		// try {
		// sendMail.sendEmailToClient(
		// "Hi Team,\n\nThe website is working as expected.\nFollowing features has been
		// verified\n1. Pagination\n2. Filters\n3. Sorting\n4. Checkout with credit card
		// and card\nPlease find attached report.\n\nThanks &
		// Regards\nTesingXperts",
		// "BUILD PASSED");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// driver.manage().deleteAllCookies();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult testResult) {

		LogUtil.infoLog(getClass().getSimpleName(), "Total Time taken in(seconds):" + seconds);
		Utility.testResult.setResultStatus("FAIL");
		Utility.testResult.setTotalTimeTaken(seconds);
		Utility.testResult.setReasonForFailure(testResult.getThrowable().getMessage());
		LogUtil.errorLog(getClass().getSimpleName(), testResult.getThrowable().getMessage());
//		LogUtil.errorLog(getClass().getSimpleName(),testResult.getThrowable().getCause().toString());

		try {
			ExcelDataUtil.updateTestResults(Utility.testData, Utility.testResult);
		} catch (IOException e) {
			LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
		}
		HtmlReportUtil.endReport(false, testResult.getMethod().getMethodName());
		//driver.quit();
		testReultClasses.put(testResult.getMethod().getMethodName(), "<td style='color: red'><b>Failed</b></td>");
		testClassesSummary.put(testResult.getMethod().getMethodName(), Utility.testData.getTestCaseSummary());
		// try {
		// sendMail.sendEmailToClient(
		// "Hi Team,\n\nThe website is working but test has been failed.\nPlease check
		// the attached report.\n\nThanks & Regards\nTesingXperts",
		// "BUILD PASSED WITH TEST Failures");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// driver.manage().deleteAllCookies();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult testResult) {

		LogUtil.infoLog(getClass().getSimpleName(), "Test Skipped - Total Time taken in(seconds):" + seconds);
		Utility.testResult.setResultStatus("SKIPPED");
		Utility.testResult.setTotalTimeTaken(seconds);
		try {
			ExcelDataUtil.updateTestResults(Utility.testData, Utility.testResult);
		} catch (IOException e) {
			LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
		}
		HtmlReportUtil.endReportSkipped(Utility.testData.getTestCaseID(), Utility.testException);
		//driver.quit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.
	 * testng. ITestResult)
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Not implemented
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext context) {
		// Not implemented
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext context) {
		// Not implemented
	}

	/**
	 * Before invocation.
	 *
	 * @param method
	 *            the method
	 * @param testResult
	 *            the test result
	 */
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

		// Not implemented

	}

	/**
	 * After invocation.
	 *
	 * @param method
	 *            the method
	 * @param testResult
	 *            the test result
	 */
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// Not implemented

	}



	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
