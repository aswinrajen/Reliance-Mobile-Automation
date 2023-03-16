package Utilities;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class LogUtil.
 */
public class LogUtil {

	/** The error logger. */
	static Logger errorLogger;

	/** The normal logger. */
	static Logger normalLogger;

	/** The normal file app. */
	static FileAppender normalFileApp = null;

	/** The error file app. */
	static FileAppender errorFileApp;

	/** The con app. */
	static ConsoleAppender conApp;

	/** The is init. */
	protected static boolean isInit = false;

	/** The pattern layout. */
	static PatternLayout patternLayout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p - %m%n");

	/** The console pattern layout. */
	static PatternLayout consolePatternLayout = new PatternLayout("\tLOG-: [%m -  %d{yyyy-MM-dd HH:mm:ss a}] %n");

	/**
	 * Inits the.
	 *
	 * @param clazz
	 *            the clazz
	 */
	public static void init(Class<?> clazz) {
		if (!isInit) {
			normalLogger = Logger.getLogger(clazz + ",NormalLogger");
			normalLogger.setLevel(Level.INFO);

			try {
				normalFileApp = new FileAppender(patternLayout, Utility.getValue("logInfoFilePath"), false);
			} catch (IOException e) {
				errorLog(ExcelDataUtil.class, e.getMessage(), e);
			}
			normalFileApp.setLayout(patternLayout);
			Utility.getValue("logInfoFilePath");

			normalLogger.addAppender(normalFileApp);

			normalFileApp.activateOptions();

			errorLogger = Logger.getLogger(clazz + ",ErrorLogger");
			errorLogger.setLevel(Level.ERROR);

			try {
				errorFileApp = new FileAppender(patternLayout, Utility.getValue("logErrorFilePath"), false);
			} catch (IOException e) {

				errorLog(ExcelDataUtil.class, e.getMessage(), e);
			}

			errorFileApp.setLayout(patternLayout);
			errorFileApp.setFile(Utility.getValue("logErrorFilePath"));

			errorFileApp.setImmediateFlush(true);
			errorLogger.addAppender(errorFileApp);
			errorFileApp.activateOptions();
			errorFileApp.setAppend(false);

			conApp = new ConsoleAppender();
			conApp.setLayout(consolePatternLayout);
			conApp.setTarget("System.out");
			conApp.activateOptions();

			normalLogger.addAppender(conApp);

			isInit = true;
		}
	}

	/**
	 * Inits the.
	 *
	 * @param className
	 *            the class name
	 */
	public static void init(String className) {
		if (!isInit) {

			normalLogger = Logger.getLogger(className + ",NormalLogger");
			normalLogger.setLevel(Level.INFO);

			normalFileApp = new FileAppender();
			normalFileApp.setLayout(patternLayout);

			normalFileApp.setFile("Logs\\AppLog.txt");
			normalFileApp.setImmediateFlush(true);
			normalLogger.addAppender(normalFileApp);
			normalFileApp.activateOptions();

			errorLogger = Logger.getLogger(className + ",ErrorLogger");
			errorLogger.setLevel(Level.ERROR);
			errorFileApp = new FileAppender();
			errorFileApp.setLayout(patternLayout);
			errorFileApp.setFile("Logs\\ErrorLog.txt");
			errorFileApp.setImmediateFlush(true);
			errorLogger.addAppender(errorFileApp);
			errorFileApp.activateOptions();

			conApp = new ConsoleAppender();
			conApp.setLayout(consolePatternLayout);
			conApp.setTarget("System.out");
			conApp.activateOptions();

			normalLogger.addAppender(conApp);

			isInit = true;
		}
	}

	/**
	 * Info log.
	 *
	 * @param clazz
	 *            the clazz
	 * @param message
	 *            the message
	 */
	public static void infoLog(Class<?> clazz, String message) {
		init(clazz);
		normalLogger.info(message);
	}

	/**
	 * Info log.
	 *
	 * @param className
	 *            the class name
	 * @param message
	 *            the message
	 */
	public static void infoLog(String className, String message) {
		init(className);
		normalLogger.info(message);
	}

	/**
	 * Error log.
	 *
	 * @param clazz
	 *            the clazz
	 * @param message
	 *            the message
	 * @param t
	 *            the t
	 */
	public static void errorLog(Class<?> clazz, String message, Throwable t) {
		init(clazz);
		errorLogger.error(message, t);
	}

	/**
	 * Error log.
	 *
	 * @param clazz
	 *            the clazz
	 * @param message
	 *            the message
	 */
	public static void errorLog(Class<?> clazz, String message) {
		init(clazz);
		errorLogger.error(message);
		errorLogger.error("-----------------------------------------------------------------------");
	}

	/**
	 * Error log.
	 *
	 * @param name
	 *            the name
	 * @param message
	 *            the message
	 */
	public static void errorLog(String name, String message) {
		init(name);
		errorLogger.error(message);
		errorLogger.error("-----------------------------------------------------------------------");
	}

}
