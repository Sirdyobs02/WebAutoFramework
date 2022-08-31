package Selenium;

import Reporting.Report;
//import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver {


    protected static WebDriver webDriver;
    protected Properties prop;
    Report report = null;

    public SeleniumDriver(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\src\\main\\java\\Config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        //report = new Report();
        //report.setTester_name(prop.getProperty("user"));
    }

    public boolean start_browser(String browser_name) {

        try {
            if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver.exe");
            } else {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
            }
            if (browser_name.equalsIgnoreCase("chrome")) {
                webDriver = new ChromeDriver();

            } else if (browser_name.equalsIgnoreCase("firefox")) {
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                webDriver = new FirefoxDriver(capabilities);
            }
            return true;
        } catch (Exception e) {
            System.out.println("ERROR Failed to start '" + browser_name + "' browser - " + e);
            return false;
        }
    }





}
