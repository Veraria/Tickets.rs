package tests;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import pages.Strings;

import java.io.File;
import java.io.FileOutputStream;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;


public class BaseTest {
    WebDriver driver = null;
    public static final Logger log = (Logger) LogManager.getLogger(BaseTest.class);

    public WebDriver openChromeDriver() {
        log.info("Opening and setting Chrome Driver");
        Reporter.log("Opening and setting Chrome Driver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"--start-maximized"});
        options.addArguments(new String[]{"--ignore-certificate-errors"});
        options.addArguments(new String[]{"--disable-popup-blocking"});
        options.addArguments(new String[]{"--incognito"});
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        WebDriver driver = new ChromeDriver(options);
        driver.get(Strings.HOMEPAGE_URL_EN);
        return driver;
    }

    @AfterMethod
    // in this way we don't need takeScreenshot method
    public void failureTest(ITestResult result) throws Exception {
        Reporter.setCurrentTestResult(result);

    //    File img = new File(System.getProperty("user.dir")+("./screenshots/")
    //            +result.getMethod().getMethodName()+getCurrentDateTime()+".png");

        File img = new File(System.getProperty("user.dir")+("./screenshots/")+"/screen_"
                +result.getMethod().getMethodName()+".png");

        if(result.getStatus() == ITestResult.FAILURE){
            Reporter.log("Taking screenshot of failed test", true);

           FileOutputStream screenshotStream = new FileOutputStream(img);
           screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
           screenshotStream.close();

            Reporter.log(" <a href='"+img.getAbsolutePath()+"'> <img src='"+ img.getAbsolutePath()+"' height='100' width='200'/> </a>  ");

        }
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void sleep() {
        try {
            Thread.sleep(7000);
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    public boolean isCurrentUrlEqualTo(String expectedUrl) {
        Reporter.log("Current Url equals to: " + expectedUrl);
        log.info("Current Url equals to: " + expectedUrl);
        String currentUrl = driver.getCurrentUrl();
        boolean b = currentUrl.equals(expectedUrl);
        return b;
    }
    /**
     * Purpose - to get current date and time
     * @return - String (returns date and time)
     */
    public static String getCurrentDateTime() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
        return formatter.format(currentDate.getTime());
    }
}



