package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelDataUtil.
 */
public class ExcelDataUtil extends LogUtil {

	/** The fs. */
	private static FileInputStream fs = null;

	/** The workbook. */
	private static Workbook workbook = null;

	/** The sheet. */
	private static Sheet sheet;

	/** The column to look test case ID. */
	private static int columnToLookTestCaseID = Integer.parseInt(Utility.getValue("columnToLookTestCaseID"));

	/** The test datafile path. */
	private static String testDatafilePath = Utility.getValue("testDataExcelPath");

	/** The test data sheet name. */
	private static String testDataSheetName = Utility.getValue("testDataSheet");

	/** The is copy template. */
	private static boolean isCopyTemplate = false;

	/** The file path. */
	private static String filePath = "";

	/** The cell number. */
	private static int cellNumber = 0;

	/** The extension xlsx. */
	private static String extensionXlsx = ".xlsx";

	/** The extension xls. */
	private static String extensionXls = ".xls";

	/** The controller sheet. */
	private static String controllerSheet = Utility.getValue("AutomationControlExcelPath");

	/**
	 * Inits the.
	 *
	 * @param filePath
	 *            the file path
	 * @param sheetName
	 *            the sheet name
	 */
	public static void init(String filePath, String sheetName) {
		String fileExtensionName = filePath.substring(filePath.indexOf('.'));

		try {

			fs = new FileInputStream(filePath);
			if (extensionXlsx.equals(fileExtensionName)) {

				workbook = new XSSFWorkbook(fs);

			}

			else if (extensionXls.equals(fileExtensionName)) {

				workbook = new HSSFWorkbook(fs);

			}

			sheet = workbook.getSheet(sheetName);

		} catch (Exception e) {

			errorLog(ExcelDataUtil.class, e.getMessage(), e);

		}

	}

	/**
	 * Gets the test data with test case ID.
	 *
	 * @param testCaseID
	 *            the test case ID
	 * @return the test data with test case ID
	 */
	@SuppressWarnings("deprecation")
	public static TestData getTestDataWithTestCaseID(String testCaseID) {
		Row row;

		boolean found = false;

		TestData testdata = new TestData();
		// Initilize class
		// Get Path and Sheet Name from Property File.

		init(testDatafilePath, testDataSheetName);

		Iterator<Row> rowIterator = sheet.iterator();
		
		try {
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				if (row.getCell(1).getStringCellValue().equalsIgnoreCase(testCaseID)) {
					row.getCell(columnToLookTestCaseID).getStringCellValue();
					Iterator<Cell> cellIterator = row.cellIterator();
					ArrayList<String> currentRowData = new ArrayList<>();
					found = true;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						String cellData = "";

						switch (cell.getCellType()) {

						case Cell.CELL_TYPE_FORMULA:
							cellData = "" + cell.getCellFormula();
							break;

						case Cell.CELL_TYPE_NUMERIC:
							cellData = "" + Double.toString(cell.getNumericCellValue());
							break;

						case Cell.CELL_TYPE_STRING:
							cellData = "" + cell.getStringCellValue();
							break;

						default:
						}
						currentRowData.add(cellData);

					} // Cell Iterator

					testdata.setTestSet(currentRowData.get(0));
					testdata.setTestCaseID(currentRowData.get(1));
					testdata.setTestCaseSummary(currentRowData.get(2));
					testdata.setComplexity(currentRowData.get(3));
					System.out.println(currentRowData.get(3));
					break;
				} // End if Found an row

			} //// Row Iterator

			fs.close();

		} catch (Exception e) {
			errorLog(ExcelDataUtil.class, e.getMessage(), e);

			LogUtil.infoLog("", e.getMessage());

		}

		if (!found)
			errorLog(ExcelDataUtil.class, "No data found with given key!!");

		return testdata;

	}// End of getSheetData

	/**
	 * Gets the cell data.
	 *
	 * @param sheet
	 *            the sheet
	 * @param RowNum
	 *            the row num
	 * @param ColNum
	 *            the col num
	 * @return the cell data
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings({ "deprecation", "static-access" })
	public static String getCellData(Sheet sheet, int RowNum, int ColNum) throws Exception {
		Cell cell;
		try {

			cell = sheet.getRow(RowNum).getCell(ColNum);
			cell.setCellType(cell.CELL_TYPE_STRING);
			String cellData = "";

			switch (cell.getCellType()) {

			case Cell.CELL_TYPE_FORMULA:
				cellData = "" + cell.getCellFormula();
				break;

			case Cell.CELL_TYPE_NUMERIC:
				cellData = "" + Double.toString(cell.getNumericCellValue());
				break;

			case Cell.CELL_TYPE_STRING:
				cellData = "" + cell.getStringCellValue();
				break;

			default:
			}

			return cellData;

		} catch (Exception e) {
			errorLog(ExcelDataUtil.class, e.getMessage(), e);
			return "";

		}

	}

	/**
	 * Gets the copy of template file.
	 *
	 * @return the copy of template file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File getCopyOfTemplateFile() throws IOException {

		// Copy a fresh Result ExcelFile from Master

		File dest = null;
		File source = null;

		try {
			String workingDir = System.getProperty("user.dir");

			String sourceFilePath = Utility.getValue("Template_testResultExcelPath");
			source = new File(sourceFilePath);
			String fileName = "";

			String fileExtensionName = sourceFilePath.substring(sourceFilePath.indexOf('.'));

			if (extensionXlsx.equals(fileExtensionName)) {
				// If it is xlsx file then create object of XSSFWorkbook class
				fileName = "TestResult" + extensionXlsx;

			}

			// Check condition if the file is xls file
			else if (extensionXls.equals(fileExtensionName)) {
				// If it is xls file then create object of XSSFWorkbook class
				fileName = "TestResult" + extensionXls;

			}
			String destFilePath = workingDir + "\\ExecutionReports\\ExcelReport\\" + fileName;

			dest = new File(destFilePath);

			FileUtils.copyFile(source, dest);

			return dest;

		} catch (IOException e) {
			errorLog(ExcelDataUtil.class, e.getMessage(), e);
		}
		return dest;

	}

	/**
	 * Update test results.
	 *
	 * @param testData
	 *            the test data
	 * @param testResult
	 *            the test result
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void updateTestResults(TestData testData, TestResults testResult) throws IOException {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		String executionDate = dateFormat.format(date);
		FileInputStream fis = null;
		Workbook wb = null;
		FileOutputStream fos = null;
		String sheetName;

		try {
			if (!isCopyTemplate) {
				filePath = getCopyOfTemplateFile().getAbsolutePath();
				isCopyTemplate = true;
			}

			fis = new FileInputStream(filePath);

			wb = WorkbookFactory.create(fis);
			fos = new FileOutputStream(filePath);

			sheetName = Utility.getValue("testResultSheet");
			Sheet sheet = wb.getSheet(sheetName);

			int startRow;

			startRow = sheet.getLastRowNum();
			startRow++;

			// Fill in a row of Result Set

			sheet.createRow(startRow).createCell(0).setCellValue(testData.getTestSet());
			sheet.getRow(startRow).createCell(1).setCellValue(testData.getTestCaseID());
			sheet.getRow(startRow).createCell(2).setCellValue(testData.getTestCaseSummary());
			sheet.getRow(startRow).createCell(3).setCellValue(executionDate);
			sheet.getRow(startRow).createCell(4).setCellValue(testData.getComplexity());
			sheet.getRow(startRow).createCell(5).setCellValue(testResult.getResultStatus());

		} catch (Exception e) {
			errorLog(ExcelDataUtil.class, e.getMessage(), e);

		}

		finally {
			fos.flush();
			wb.write(fos);
			wb.close();
			fis.close();
			fos.close();
		}

	}

	/**
	 * Gets the flag excel.
	 *
	 * @param sheetName
	 *            the sheet name
	 * @param searchValue
	 *            the search value
	 * @return the flag excel
	 * @throws Exception
	 *             the exception
	 */
	public static String getFlagExcel(String sheetName, String searchValue) throws Exception {
		int sheetSize;
		int rowNum = 1;
		String strVal = "NA";
		String strflag = "NA";
		try {
			FileInputStream fis = new FileInputStream(controllerSheet);

			Workbook wb = WorkbookFactory.create(fis);

			int indexOfheet = wb.getSheetIndex(wb.getSheet(sheetName));

			if (indexOfheet == -1) {
				LogUtil.infoLog(ExcelDataUtil.class, "Error! No such sheet available in Excel file: " + sheetName);

				throw new IOException("No such sheet available in Excel file: " + sheetName);
			}

			Sheet sheet = wb.getSheet(sheetName);
			sheetSize = sheet.getLastRowNum();
			for (int i = rowNum; i <= sheetSize; i++) {
				strVal = sheet.getRow(i).getCell(0).getStringCellValue();
				System.out.println(strVal);
				if (searchValue.equals(strVal)) {
					strflag = wb.getSheet(sheetName).getRow(i).getCell(1).getStringCellValue();
					System.out.println(strflag);
					break;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
			errorLog(ExcelDataUtil.class, e.getMessage(), e);
			throw e;
		}
		
		return strflag;
	}

	// Read Data from Excel File AutomationControlSheet.xls(SuiteControlSheet),
	/**
	 * Checks if is suite runnable.
	 *
	 * @param suiteName
	 *            the suite name
	 * @return true, if is suite runnable
	 * @throws Exception
	 *             the exception
	 */
	// Reading Y/N for including a test case in suite to run.
	public static boolean isSuiteRunnable(String suiteName) throws Exception {
		boolean isRunnable = false;
		String strVal;
		try {
			strVal = getFlagExcel("SuiteControlSheet", suiteName);

			if ("Y".equalsIgnoreCase(strVal)) {
				System.out.println("True");
				isRunnable = true;
			} else if ("N".equalsIgnoreCase(strVal) || "NA".equalsIgnoreCase(strVal)) {
				System.out.println("False");
				isRunnable = false;
			}

		} catch (Exception e) {

			errorLog(ExcelDataUtil.class, e.getMessage(), e);
			throw e;
		}

		return isRunnable;
	}

	/**
	 * Checks if is script runnable.
	 *
	 * @param suiteName
	 *            the suite name
	 * @param scriptName
	 *            the script name
	 * @return true, if is script runnable
	 * @throws Exception
	 *             the exception
	 */
	// Read Excel file for Script to run. Like Regression, Smoke, Functional
	public static boolean isScriptRunnable(String suiteName, String scriptName) throws Exception {
		boolean isRunnable = false;
		String strVal = null;
		try {

//			if ("Demo".equalsIgnoreCase(suiteName)) 
//			{
				strVal = getFlagExcel(suiteName, scriptName);
//			} 
			
			
//			else
//				strVal = "";

			if (strVal.equals("")) {
				System.out.println("\n************************************************************************");
				System.out.println("No Data is available for:FunctionalSuite_HI, Script Name:" + scriptName);
				System.out.println("************************************************************************\n");

				isRunnable = false;
			}

			if ("Y".equals(strVal)) {
				isRunnable = true;
			} else if ("N".equals(strVal)) {
				isRunnable = false;
			} else if (strVal.equals("NA")) {
				isRunnable = false;
			}
		} catch (Exception e) {

			errorLog(ExcelDataUtil.class, e.getMessage(), e);
			throw e;
		}
		return isRunnable;
	}

	/**
	 * Prints the test status.
	 *
	 * @param suiteName
	 *            the suite name
	 * @param searchValue
	 *            the search value
	 * @param testStatus
	 *            the test status
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws EncryptedDocumentException
	 *             the encrypted document exception
	 * @throws InvalidFormatException
	 *             the invalid format exception
	 */
	public void printTestStatus(String suiteName, String searchValue, String testStatus)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		int nsheetSize;
		int rowNum = 2;

		String strVal;
		String sheetName = null;

		if ("Smoke".equalsIgnoreCase(suiteName))
			sheetName = "SmokeSuite";

		FileOutputStream fos = null;
		FileInputStream fis = null;
		Workbook wb = null;
		try {

			fis = new FileInputStream(controllerSheet);

			wb = WorkbookFactory.create(fis);
			fos = new FileOutputStream(controllerSheet);
			nsheetSize = wb.getSheet(sheetName).getLastRowNum();

			for (int i = rowNum; i <= nsheetSize; i++) {
				strVal = wb.getSheet(sheetName).getRow(i).getCell(0).getStringCellValue();
				if (searchValue.equals(strVal)) {
					wb.getSheet(sheetName).getRow(i).createCell(2).setCellValue(testStatus);
					wb.write(fos);

					break;
				}
			}

		} catch (IOException e) {

			errorLog(ExcelDataUtil.class, e.getMessage(), e);
			throw e;
		}

		finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
					wb.close();

				} catch (IOException e) {
					errorLog(ExcelDataUtil.class, e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Gets the controls.
	 *
	 * @param suiteName
	 *            the suite name
	 * @param testCaseID
	 *            the test case ID
	 * @return the controls
	 * @throws Exception
	 *             the exception
	 */
	public static boolean getControls(String suiteName, String testCaseID) throws Exception {
		boolean isSuiteRun;
		boolean isScriptRun;
		boolean control = false;

		isSuiteRun = ExcelDataUtil.isSuiteRunnable(suiteName);

		if (isSuiteRun) {
			isScriptRun = ExcelDataUtil.isScriptRunnable(suiteName, testCaseID);

			if (isScriptRun)
				System.out.println("True");
				control = true;

		} else
			control = false;

		return control;
	}

	/**
	 * Gets the column value.
	 *
	 * @param column
	 *            the column
	 * @param sheetName
	 *            the sheet name
	 * @param rowNum
	 *            the row num
	 * @return the column value
	 */
	@SuppressWarnings("resource")
	public static String getColumnValue(String column, String sheetName, int rowNum) {
		String cellValue = null;
		try (FileInputStream fileInputStream = new FileInputStream(controllerSheet)) {

			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet(sheetName);

			for (int y = 0; y < worksheet.getRow(0).getLastCellNum(); y++) {
				HSSFCell cellA2 = worksheet.getRow(0).getCell(y);
				String getCellName = cellA2.getStringCellValue();
				if (getCellName.contains(column)) {
					cellNumber = cellA2.getColumnIndex();
				}
			}
			cellValue = worksheet.getRow(rowNum).getCell(cellNumber).getStringCellValue();

			fileInputStream.close();

		}

		catch (Exception e) {

			errorLog(ExcelDataUtil.class, e.getMessage(), e);

		}

		return cellValue;
	}
}
