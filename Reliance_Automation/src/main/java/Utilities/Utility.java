package Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class Utility.
 */
public class Utility {

	/** The test data. */
	protected static TestData testData;

	/** The test result. */
	protected static TestResults testResult;

	/** The test exception. */
	protected static Exception testException;

	/** The login. */
	protected static boolean login = false;

	/** The test case ID. */
	protected static String testCaseID = "";

	/** The driver. */
	//protected static AndroidDriver driver = null;
	
	/** The Android driver. */
	protected static AppiumDriver driver = null;

	/** The cell number. */
	protected static int cellNumber = 0;

	/** The data mode. */
	static String dataMode = Utility.getValue("DataMode");

	/**
	 * Instantiates a new utility.
	 */
	Utility() {

	}

	/**
	 * Take screenshot.
	 *
	 * @param driver
	 *            the driver
	 * @param testCaseID
	 *            the test case ID
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String takeScreenshot(WebDriver driver, String testCaseID) throws IOException {
		String timestamp=new SimpleDateFormat("ddHmmss").format(new Date());
		String path = getValue("screenshotPath") + "\\" + testCaseID +timestamp+ ".jpg";
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File des = new File(path);
		// now copy the screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, des);
		return "../FailedScreenshots/"+ testCaseID+timestamp + ".jpg";
	}

	/**
	 * Check visible.
	 *
	 * @param driver
	 *            the driver
	 * @param by
	 *            the by
	 * @return true, if successful
	 */
	public static boolean checkVisible(WebDriver driver, By by) {

		return driver.findElement(by).isDisplayed() && driver.findElement(by).isEnabled();

	}

	/**
	 * Log result.
	 *
	 * @param status
	 *            the status
	 * @param logStep
	 *            the log step
	 */
	public static void logResult(boolean status, String logStep) {
		if (status) {
			LogUtil.infoLog(Utility.class, logStep + " - PASS ");
			HtmlReportUtil.stepInfo(logStep);
		} else {
			LogUtil.infoLog(Utility.class, logStep + " - FAIL ");
			HtmlReportUtil.stepInfo(logStep);
		}
	}

	/**
	 * Log result.
	 *
	 * @param status
	 *            the status
	 * @param logStep
	 *            the log step
	 */
	public static void logResult(String status, String logStep) {
		if (status.equalsIgnoreCase("info")) {
			LogUtil.infoLog(Utility.class, logStep + " - INFO ");
			HtmlReportUtil.stepInfo(logStep);
		}

	}

	/**
	 * Gets the value.
	 *
	 * @param key
	 *            the key
	 * @return the value
	 */
	public static String getValue(String key) {
		File file = new File("src\\main\\resources\\ConfigFiles\\config.properties");

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (Exception e) {
			LogUtil.errorLog(Utility.class, e.getMessage(), e);
		}
		Properties prop = new Properties();

		try {
			prop.load(fileInput);
		} catch (Exception e) {
			LogUtil.errorLog(Utility.class, e.getMessage(), e);
		}

		return prop.getProperty(key);

	}

	/**
	 * Gets the int value.
	 *
	 * @param key
	 *            the key
	 * @return the int value
	 */
	public static int getIntValue(String key) {
		File file = new File("src\\main\\resources\\ConfigFiles\\config.properties");

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (Exception e) {
			LogUtil.errorLog(Utility.class, e.getMessage(), e);
		}
		Properties prop = new Properties();

		try {
			prop.load(fileInput);
		} catch (Exception e) {
			LogUtil.errorLog(Utility.class, e.getMessage(), e);
		}

		String strbaseURL = prop.getProperty(key);
		return Integer.parseInt(strbaseURL);
	}

	/**
	 * Gets the date time.
	 *
	 * @return the date time
	 */
	public static String getDateTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * Rename file.
	 */
	public static void renameFile() {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		String timeStamp = dateFormat.format(date);

		try {

			File oldFile = new File(System.getProperty("user.dir") + Utility.getValue("testResultExcelPath"));
			String newFilePath = oldFile.getAbsolutePath().replace(oldFile.getName(), "") + "\\ReportHistory\\"
					+ timeStamp + "-TestResult.xls";
			File newFile = new File(newFilePath);

			FileUtils.copyFile(oldFile, newFile);

		} catch (IOException e) {
			LogUtil.errorLog(Utility.class, e.getMessage(), e);
		}
	}

	/**
	 * Resett data.
	 */
	public static void resettData() {
		testResult.setFailedScreenShotReference("");
		testResult.setDateOfExecution("");
		testResult.setReasonForFailure("");
		testResult.setResultStatus("");
		testResult.setTotalTimeTaken(0);
		testData.setBrowser("");
		testData.setComplexity("");
		testData.setTestCaseID("");
		testData.setTestCaseSummary("");
		testData.setTestSet("");

	}

	/**
	 * Gets the object value.
	 *
	 * @param key
	 *            the key
	 * @return the object value
	 */
	public static String getObjectValue(String key) {
		File file = new File("src\\main\\resources\\objects.properties");

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (Exception e) {
			LogUtil.errorLog(Utility.class, e.getMessage(), e);
		}
		Properties prop = new Properties();

		try {
			prop.load(fileInput);
		} catch (Exception e) {
			LogUtil.errorLog(Utility.class, e.getMessage(), e);
		}

		return prop.getProperty(key);

	}

	/**
	 * Gets the datafrom xls.
	 *
	 * @param subSheetName
	 *            the sub sheet name
	 * @param columnName
	 *            the column name
	 * @param row
	 *            the row
	 * @return the datafrom xls
	 */
	/*@SuppressWarnings({ "static-access", "deprecation" })
	public static String getdatafromXls(String subSheetName, String columnName, int row) {
		String cellValue = null;
		try {

			FileInputStream fileInputStream = new FileInputStream(Utility.getValue("DataSheetPath"));

			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(subSheetName);

			for (int y = 0; y < worksheet.getRow(0).getLastCellNum(); y++) {
				HSSFCell cellA2 = worksheet.getRow(0).getCell(y);
				cellA2.setCellType(cellA2.CELL_TYPE_STRING);
				String getCellName = cellA2.getStringCellValue();
				if (getCellName.contains(columnName)) {
					cellNumber = cellA2.getColumnIndex();
				}
			}

			cellValue = ExcelDataUtil.getCellData(worksheet, row, cellNumber);

		} catch (Exception e) {
			LogUtil.errorLog(Utility.class, e.getMessage(), e);

		}

		return cellValue;

	}*/

	/**
	 * Gets the data from csv.
	 *
	 * @param SheetName
	 *            the sheet name
	 * @param columnName
	 *            the column name
	 * @param row
	 *            the row
	 * @return the data from csv
	 */
	public static String getDataFromCsv(String SheetName, String columnName, int row) {
		BufferedReader br;
		String line;
		String[] DatasplitStr = null;
		int index = -1;
		String COMMA = ",";
		String csvDataFile = Utility.getValue("CvsDataSheetPath") + SheetName + ".csv";
		try {
			br = new BufferedReader(new FileReader(csvDataFile));
			line = br.readLine();

			String[] columnNames = line.split(COMMA);
			int splittedStringLen = columnNames.length;
			for (int i = 0; i < splittedStringLen; i++) {
				if (columnNames[i].equals(columnName)) {
					index = i;
					line = br.readLine();
					break;
				}
			}

			for (int i = 1; i < row; i++) {

				line = br.readLine();

			}

			DatasplitStr = line.split(COMMA);

		}

		catch (IOException e) {
			System.out.println(e.getMessage());

		}

		System.out.println(DatasplitStr[index]);
		return DatasplitStr[index];

	}

	/**
	 * Gets the data from xml.
	 *
	 * @param elementName
	 *            the element name
	 * @param childName
	 *            the child name
	 * @param row
	 *            the row
	 * @return the data from xml
	 */
	public static String getDataFromXml(String elementName, String childName, int row) {
		String xmlDataFile = Utility.getValue("XmlDataSheetPath");

		Element eElement = null;
		try {
			File fXmlFile = new File(xmlDataFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("testcase");
			Node nNode = null;
			for (int temp = 0; temp < nList.getLength(); temp++) {
				nNode = nList.item(temp);
				if (elementName.contains(nNode.getAttributes().getNamedItem("id").toString()))

					break;

			}

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				eElement = (Element) nNode;

			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return eElement.getElementsByTagName(childName).item(0).getTextContent();

	}

	/**
	 * Gets the test data.
	 *
	 * @param testCaseID
	 *            the test case ID
	 * @param columnName
	 *            the column name
	 * @param row
	 *            the row
	 * @return the test data
	 */
	/*public static String getTestData(String testCaseID, String columnName, int row) {

		String data = "";

		if (dataMode.equals("xls")) {
			data = Utility.getdatafromXls(testCaseID, columnName, row);
		}

		else if (dataMode.equals("csv")) {
			data = Utility.getDataFromCsv(testCaseID, columnName, row);
		}

		else if (dataMode.equals("xml")) {
			data = Utility.getDataFromXml(testCaseID, columnName, row);
		}

		else {

			System.out.println("No Data Found!!!!!");

		}

		return data;

	}*/

	/**
	 * Gets the data from csv 2.
	 *
	 * @param SheetName
	 *            the sheet name
	 * @param columnName
	 *            the column name
	 * @param row
	 *            the row
	 * @return the data from csv 2
	 */
	public static String getDataFromCsv2(String SheetName, String columnName, int row) {
		BufferedReader br;
		String line;
		String[] DatasplitStr = null;
		int index = -1;
		String COMMA = ",";
		Boolean flag = true;
		String csvDataFile = Utility.getValue("CvsDataSheetPath") + SheetName + ".csv";
		try {
			br = new BufferedReader(new FileReader(csvDataFile));
			line = br.readLine();

			while (flag) {
				String[] columnNames = line.split(COMMA);
				int splittedStringLen = columnNames.length;
				if (SheetName.contains(columnNames[0])) {

					for (int i = 0; i < splittedStringLen; i++) {

						if (columnNames[i].equals(columnName)) {
							index = i;
							line = br.readLine();
							flag = false;
							break;
						}

					}

				}

				else {

					line = br.readLine();
					line = br.readLine();
					line = br.readLine();
				}

			}

			for (int i = 1; i < row; i++) {

				line = br.readLine();

			}

			DatasplitStr = line.split(COMMA);

		}

		catch (IOException e) {
			System.out.println(e.getMessage());

		}
		return DatasplitStr[index];

	}

	/**
	 * Gets the executions number.
	 *
	 * @param sheetName
	 *            the sheet name
	 * @return the executions number
	 */
	public static int getExecutionsNumber(String sheetName) {
		FileInputStream fis;
		int rowCount = 0;
		String DataFile = null;

		BufferedReader br;
		@SuppressWarnings("unused")
		String line = null;
		try {

			if (dataMode.equals("csv")) {
				DataFile = Utility.getValue("CvsDataSheetPath") + sheetName + ".csv";
				br = new BufferedReader(new FileReader(DataFile));
				while ((line = br.readLine()) != null) {
					rowCount++;
				}
				rowCount = rowCount - 1;
			}

			else if (dataMode.equals("xls")) {
				DataFile = Utility.getValue("DataSheetPath");
				fis = new FileInputStream(DataFile);
				Workbook wb = WorkbookFactory.create(fis);
				rowCount = wb.getSheet(sheetName).getLastRowNum();
			}

			else if (dataMode.equals("xml")) {
				return 1;
			}

		}

		catch (Exception e) {

			e.printStackTrace();
		}

		return rowCount;

	}

	/**
	 * Del directory.
	 *
	 * @param dir
	 *            the dir
	 */
	public static void delDirectory(File dir) {
		File[] currList;
		Stack<File> stack = new Stack<File>();
		stack.push(dir);
		while (!stack.isEmpty()) {
			if (stack.lastElement().isDirectory()) {
				currList = stack.lastElement().listFiles();
				if (currList.length > 0) {
					for (File curr : currList) {
						stack.push(curr);
					}
				} else {
					stack.pop().delete();
				}
			} else {
				stack.pop().delete();
			}
		}
		if (new File(System.getProperty("user.dir") + "/ExecutionReports/ExecutionReports.zip").exists()) {
			delDirectory(new File(System.getProperty("user.dir") + "/ExecutionReports/ExecutionReports.zip"));
		}
	}

	/** The result folder name. */
	public static String result_FolderName = System.getProperty("user.dir") + "\\ExecutionReports\\ExecutionReports";

	/**
	 * Creates the zip file.
	 *
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String createZipFile() throws IOException {
		result_FolderName = result_FolderName.replace("\\", "/");
		String outputFile = result_FolderName + ".zip";
		FileOutputStream fos = new FileOutputStream(outputFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		packCurrentDirectoryContents(result_FolderName, zos);
		zos.closeEntry();
		zos.close();
		fos.close();
		return outputFile;
	}

	/**
	 * Pack current directory contents.
	 *
	 * @param directoryPath
	 *            the directory path
	 * @param zos
	 *            the zos
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void packCurrentDirectoryContents(String directoryPath, ZipOutputStream zos) throws IOException {
		for (String dirElement : new File(directoryPath).list()) {
			String dirElementPath = directoryPath + "/" + dirElement;
			if (new File(dirElementPath).isDirectory()) {
				packCurrentDirectoryContents(dirElementPath, zos);
			} else {
				ZipEntry ze = new ZipEntry(dirElementPath.replaceAll(result_FolderName + "/", ""));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(dirElementPath);
				byte[] bytesRead = new byte[512];
				int bytesNum;
				while ((bytesNum = fis.read(bytesRead)) > 0) {
					zos.write(bytesRead, 0, bytesNum);
				}

				fis.close();
			}
		}
	}

	public static Object[] dataSupplier(String sheetName, int rows) {

		try {
			FileInputStream fileInputStream = new FileInputStream(Utility.getValue("DataSheetPath"));
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(sheetName);
			int cells = worksheet.getRow(0).getLastCellNum();
			Object[] obj = new Object[rows];
			Map<Object, Object> datamap = new HashMap<>();

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cells; j++) {
					datamap.put(ExcelDataUtil.getCellData(worksheet, 0, j)+i ,
							ExcelDataUtil.getCellData(worksheet, (i + 1), j));
				}
				obj[i] = datamap;
			}
			workbook.close();
			System.out.println(datamap);
			return obj;
		
		} catch (Exception e) {
			LogUtil.errorLog(Utility.class, e.getMessage(), e);
			return null;
		}
	}
	
	
	
}


