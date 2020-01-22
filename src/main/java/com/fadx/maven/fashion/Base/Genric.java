package com.fadx.maven.fashion.Base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.fadx.maven.fasion.Utils.PropertyReader;

public class Genric {
	public WebDriver driver;
	private static String browser;
	public String winHandle;
	private static DesiredCapabilities capabilities = new DesiredCapabilities();

	public Genric(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		driver = startDriver();
		return driver;
	}

	public WebDriver startDriver() {
		browser = PropertyReader.readGlobalProperty("browser").toString();
		if (PropertyReader.readGlobalProperty("device").equalsIgnoreCase("Web")) {

			if (PropertyReader.readGlobalProperty("seleniumserver").toString()
					.equalsIgnoreCase("local")) {
				if (browser.equalsIgnoreCase("firefox")) {
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					driver = getChromeDriver(PropertyReader.readGlobalProperty(
							"chromedriverpath").toString());
				} else if (browser.equalsIgnoreCase("Safari")) {
					driver = getSafariDriver();
				} else if ((browser.equalsIgnoreCase("ie"))
						|| (browser.equalsIgnoreCase("internetexplorer"))
						|| (browser.equalsIgnoreCase("internet explorer"))) {
					driver = getInternetExplorerDriver(PropertyReader
							.readGlobalProperty("iedriverpath").toString());
				}
			} else if (PropertyReader.readGlobalProperty("seleniumserver")
					.toString().equalsIgnoreCase("remote")) {
				return setRemoteDriver();
			}
		} else if (PropertyReader.readGlobalProperty("device")
				.equalsIgnoreCase("Mobile")) {

			if (browser.equalsIgnoreCase("Chrome")) {
				driver = getMobileChromeDriver(PropertyReader
						.readGlobalProperty("chromedriverpath").toString());
			} 

		}
		return driver;
	}

	private WebDriver setRemoteDriver() {
		DesiredCapabilities cap = null;
		browser = PropertyReader.readGlobalProperty("browser").toString();
		if (browser.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
		} else if (browser.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
		} else if (browser.equalsIgnoreCase("Safari")) {
			cap = DesiredCapabilities.safari();
		} else if ((browser.equalsIgnoreCase("ie"))
				|| (browser.equalsIgnoreCase("internetexplorer"))
				|| (browser.equalsIgnoreCase("internet explorer"))) {
			cap = DesiredCapabilities.internetExplorer();
		}
		String seleniuhubaddress = PropertyReader
				.readGlobalProperty("seleniumserverhost");
		URL selserverhost = null;
		try {
			selserverhost = new URL(seleniuhubaddress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		cap.setJavascriptEnabled(true);
		SetImplicitWait();
		return new RemoteWebDriver(selserverhost, cap);
	}

	public String StopDriver() {
		driver.quit();
		return ("browser closed");
	}
	
	  private static WebDriver getChromeDriver(String driverpath) {
		  ChromeDriverService service = new ChromeDriverService.Builder()
		                              .usingDriverExecutable(new File(driverpath))
		                              .usingAnyFreePort()
		                              .build();
		  ChromeOptions options = new ChromeOptions();
		  options.merge(capabilities);    
		  return new ChromeDriver(service, options);		  
		} 


	private static WebDriver getMobileChromeDriver(String driverpath) {
		System.setProperty("webdriver.chrome.driver", driverpath);
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Galaxy S5");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		//WebDriver driver = new ChromeDriver(chromeOptions);
		return new ChromeDriver(chromeOptions);	
	
	}


	@SuppressWarnings("deprecation")
	private static WebDriver getInternetExplorerDriver(String driverpath) {
		System.setProperty("webdriver.ie.driver", driverpath);
		capabilities.setCapability("ignoreZoomSetting", true);
		return new InternetExplorerDriver(capabilities);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}

	/** private static WebDriver getFirefoxDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.cache.disk.enable", false);
		return new FirefoxDriver(profile);
	}*/

	/** Base Functions Start Here **/
	public void launchApplication() {
		driver.get(PropertyReader.readDataProperty("Application_URL")
				.toString());
	}

	public void launchApplication(String url) {
		driver.get(url);
	}

	protected String getPageTitle() {
		return driver.getTitle();
	}

	protected void logMessage(String message) {
		Reporter.log(message, true);
	}

	public void printOutPutOnConsole(String message) {
		System.out.println(message);
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	protected void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	public void hover(WebElement element) {
		Actions mousehover = new Actions(driver);
		mousehover.moveToElement(element).build().perform();
	}

	/** Alert */
	
	public void waitForAlert(WebDriver driver)
	{
	   int i=0;
	   while(i++<10)
	   {
	        try
	        {
	            driver.switchTo().alert();
	            break;
	        }
	        catch(NoAlertPresentException e)
	        {
	          //Thread.sleep(1000);
	          hardWait(1);
	        	continue;
	        }
	   }
	}
	
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}
	
	public void acceptAlert() {
		try {
			switchToAlert().accept();
			logMessage("Alert accepted..");
			driver.switchTo().defaultContent();
			waitForLoading();
		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
	}

	public String getAlertMessage() {
		String AlertText = "";
		try {
			AlertText = switchToAlert().getText();
			logMessage(switchToAlert().getText());

		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
		return AlertText;
	}

	public void dismissAlert() {
		try {
			switchToAlert().dismiss();
			logMessage("Alert dismissed..");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
	}

	public Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	/** Window Handle */
	public void GetWindowHandle() {
		winHandle = driver.getWindowHandle();
	}

	public void SwitchtoNewWindow() {
		GetWindowHandle();
		for (String windowsHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowsHandle);
		}
	}

	public void CloseNewWindow() {
		driver.close();
	}

	public void SwitchtoOriginalWindow() {
		driver.switchTo().window(winHandle);
	}

	/** Wait */
	public void hardWait(double d) {
		try {
			Thread.sleep((long) (d * 1000));
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public void waitUntilClickable(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(Element));

	}

	public void SetImplicitWait() {
		driver.manage()
				.timeouts()
				.implicitlyWait(
						Integer.parseInt(PropertyReader
								.readGlobalProperty("timeout")),
						TimeUnit.SECONDS);
		}

	public void GoToSleep(int TimeInMillis) {
		try {
			Thread.sleep(TimeInMillis);
		} catch (Exception e) {
		}
	}

	/** Element Handling **/
	public WebElement element(String elementName) {
		return driver.findElement(getLocator(elementName));
	}

	public List<WebElement> elements(String elementName) {
		return  driver.findElements(getLocator(elementName));
	}
	

	
	protected By getLocator(String elementName) {
		String[] locator = elementName.split(":");
		return getLocators(locator[0], locator[1]);
	}

	// protected By getLocator(String elementToken, String replacement) {
	// String[] locator = getELementFromFile(this.pageName, elementToken);
	// locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
	// return getLocators(locator[1].trim(), locator[2].trim());
	// }

	private By getLocators(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case linkText: 
			return By.linkText(locatorValue);
		case tagname:
			return By.tagName(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}	
	
		
		
	public void waitForLoading(){
		int i=0;
		   while(i++<Integer.parseInt(PropertyReader.readGlobalProperty("timeout")))
		   {
		        try
		        {
		        	if(driver.findElement(By.xpath(".//i[@class='fa fa-spinner fa-spin']")).isDisplayed()){
		        		hardWait(1);
		        		//System.out.println("waiting 1 sec");
		        		continue;
		        	}
		           
		        }
		        catch(Exception e)
		        {
		        	hardWait(2);
		        	break;
		        }
		   }
	}
		
	/*Screenshot*/
		public String captureScreenShot(WebDriver driver, String ScreenShotName){
			String ScreenShotPath=PropertyReader
					.readDataProperty("ExtentPath")+ScreenShotName+".png";
			// Take screenshot and store as a file format
		 File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
			// now copy the  screenshot to desired location using copyFile method

			FileUtils.copyFile(src, new File(ScreenShotPath));
						
			}

			catch (Exception e)

			{

			System.out.println(e.getMessage());
		}
			return ScreenShotPath;
			}
}