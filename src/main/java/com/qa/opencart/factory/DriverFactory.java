package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * 
	 * @param This is used to intialize the driver on the bases of given browsername
	 * 
	 */

	public WebDriver initDriver(Properties prop) {

		String browsername = prop.getProperty("browser");

		optionsManager = new OptionsManager(prop);
		switch (browsername.toLowerCase().trim()) {
		case "chrome":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver("chrome");
			} else
				// driver = new ChromeDriver();
				tlDriver.set(new ChromeDriver());
			break;
		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver("firefox");
			} else
				tlDriver.set(new FirefoxDriver());

			// driver = new FirefoxDriver();

			break;
		case "edge":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver("edge");
			} else
				// driver = new EdgeDriver();
				tlDriver.set(new EdgeDriver());

			break;
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

			break;

		default:
			System.out.println("Please Pass the Proper browser " + browsername);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	/**
	 * 
	 * @param browserName
	 * @throws Exception
	 */

	private void initRemoteDriver(String browserName) {
		// TODO Auto-generated method stub

		System.out.println("Running the testcases on  grid..with browser  " + browserName);

		try {
			switch (browserName) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));

				break;

			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));

				break;

			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));

				break;

			default:
				System.out.println("Please Pass Proper Browser ");
				throw new BrowserException(AppError.BROWSER_NOT_FOUND);

			}
		} catch (MalformedURLException e) {

		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/*
	 * This is used to init the properties from the properties file return
	 * properties(prop)
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		// mvn clean install -Denv="qa"
		// mvn clean install

		String envName = System.getProperty("env");
		System.out.println("running test suite on env--->" + envName);

		if (envName == null) {
			System.out.println("env name is not given, hence running it on QA environment !");
			try {
				ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
					break;
				case "stage":
					ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
					break;
				case "dev":
					ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
					break;
				case "uat":
					ip = new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
					break;
				case "prod":
					ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
					break;

				default:
					System.out.println("please pass the right env name.." + envName);
					throw new FrameworkException("===WRONGENVPASSED===");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	public static String getScreenshot(String methodName) {
		File srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp location

		String path = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcfile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
