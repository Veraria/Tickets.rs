package pages;

import org.testng.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {

    WebDriver driver = null;
    Logger log = LogManager.getLogger(BasePage.class);

    @FindBy (className = "no-dropdown")
    WebElement loginButton;

    @FindBy (className = "has-dropdown")
    WebElement dropdownLogin;

    @FindBy (xpath = "//a[contains(text(),'Logout')]")
    WebElement dropdownLogout;

    @FindBy (id="generalSearch")
    WebElement searchTextField;

    @FindBy (id="searchButton")
    WebElement searchButton;

    @FindBy (xpath = "//a[@class = 'button accept_cookie']")
    WebElement acceptCookies;

    @FindBy (xpath = "//span[text()='+']")
    WebElement addingTicketsButton;

    @FindBy (xpath = "//span[@id='mat-badge-content-0']")
    WebElement shoppingCartBadgeNumber;

    @FindBy (xpath="//input[@value='All categories']")
    WebElement allCategoriesField;

    @FindBy (id="searchSubmit")
    WebElement filterSearchButton;


    // Methods

    public LoginPage clickLoginButton(){
        assert isElementPresent(loginButton);
        log.info("Clicking Login Button");
        Reporter.log ("Clicking login button");
        loginButton.click();
        return new LoginPage(driver);
    }

    public BasePage clickLogoutButton(){
        dropdownLogin.click();
        assert isElementPresent(dropdownLogout);
        log.info("Clicking logout button");
        Reporter.log("Clicking logout button");
        dropdownLogout.click();
        return this;

    }
    public SearchPage searchForAnEvent(String text){
        assert isElementPresent(searchTextField);
        searchTextField.click();
        searchTextField.sendKeys (text);
        assert isElementPresent(searchButton);
        searchButton.click();
        log.info ("Searching for an event");
        Reporter.log("Searching for an event");
        return new SearchPage(driver);
    }

    public FilteringEventsPage filterEvents (){

        WebElement dropdown = driver.findElement(By.xpath("//input[@value='All categories']"));
        dropdown.click();
        WebElement film = dropdown.findElement(By.xpath("//ul[@class='chosen-results']//li[@data-option-array-index = '1']"));
        WebDriverWait wait = new WebDriverWait ( driver, 15 );
        wait.until( ExpectedConditions.elementToBeClickable (By.xpath("//ul[@class='chosen-results']//li[@data-option-array-index = '1']")) );
        film.click();
        sleep(2000);

        WebElement dropdown2 = driver.findElement(By.xpath("//input[@value='All cities']"));
        dropdown2.click();
        WebElement place = dropdown.findElement(By.xpath("//li[contains(text(),'Novi Beograd')]"));
        wait.until( ExpectedConditions.elementToBeClickable (By.xpath("//li[contains(text(),'Novi Beograd')]")) );
        place.click();

        filterSearchButton.click();
        log.info("Filters are checked");
        Reporter.log("Filters are checked");
        return new FilteringEventsPage(driver);
    }

    public AddingTicketsToCartPage clickOnEventName(){

        WebElement element = driver.findElement(By.xpath(Strings.EVENT));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        log.info("Clicking event name");
        Reporter.log("Clicking event name");

        return new AddingTicketsToCartPage(driver);
    }

    public String getNumberOfTicketsInCartFromBadge (){
        log.info("Getting number of tickets in the cart from badge");
        Reporter.log("Getting number of tickets in the cart from badge");
        waitForElement(shoppingCartBadgeNumber);
        return shoppingCartBadgeNumber.getText();
    }

    public void takeScreenshot(String screenshotName){
        log.info("Taking screenshot");
        Reporter.log("Taking screenshot");
        TakesScreenshot screenshot = (TakesScreenshot) driver; // casting driver by using (TakesScreenshot)
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File("./screenshots/"+screenshotName+".png"));
        } catch (IOException e) {
           // e.printStackTrace();
            log.warn("Exception while taking screenshot" + e.getMessage());
            Reporter.log("Exception while taking screenshot" + e.getMessage());
        }

    }

    // Helper methods

    public void print (String text){
        System.out.println(text);
        }

    public String getTitleText() {
        return  driver.getTitle();
    }

    public String getPageURL(){
        return driver.getCurrentUrl();
    }
    public boolean isElementPresent(WebElement element){
        log.info("Is Element present");
        Reporter.log("Is Element present");
        try {
            boolean isPresent = element.isDisplayed();
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            Reporter.log(e.getMessage());
            log.warn("Element is not present on page");
            Reporter.log("Element is not present on page");
            return false;
        }
    }

    public void waitForElement(WebElement element){
        WebDriverWait wait = new WebDriverWait ( driver, 15 );
        wait.until( ExpectedConditions.visibilityOf (element) );
    }

    public void acceptCookies(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath ("//a[@class = 'button accept_cookie']")));
        log.info("Cookies accepted");
        Reporter.log("Cookies accepted");
    }

    public void sleep(int number){
        try {
            Thread.sleep(number);
        }
        catch (Exception e){
            print(e.getMessage());
        }
    }
}


