package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class LoginPage extends BasePage{

    @FindBy (id="email")
    WebElement eMailField;

    @FindBy (id = "password1")
    WebElement passwordField;

    @FindBy (id="submitReg")
    WebElement submitLoginButton;

    @FindBy (xpath = "//a[contains(text(),'ovojezapotrebeprojekta@gmail.com')]")
    WebElement userName;

    @FindBy (xpath = "//h2[contains(text(),'Unsuccessful Login')]")
    WebElement errorLoginMessage;

    @FindBy (xpath = "//span[contains(text(),'Please enter your email address')]")
    WebElement emptyEmailErrorMessage;

    @FindBy (xpath = "//span[contains(text(),'mandatory')]")
    WebElement voidPasswordFieldMessage;

    @FindBy (xpath = "//span[contains(text(),'Please enter a valid email address')]")
    WebElement invalidEmail;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage enterTextInEmailField (String text) {
        log.info("Entering text " + text + " in email field");
        Reporter.log("Entering text " + text + " in email field");
        eMailField.click();
        eMailField.sendKeys(text);
        return this;
    }
    public LoginPage enterTextInPasswordField (String text){
        log.info("Entering text "+text+" in password field");
        Reporter.log("Entering text "+text+" in password field");
        passwordField.click();
        passwordField.sendKeys(text);
        return this;
    }

    public void clickSubmitLoginButton() {
        log.info("Clicking submit login button");
        Reporter.log("Clicking submit login button");
        submitLoginButton.click();

    }

    public String getCurrentUserName(){
        String currentUserAccountName = userName.getText();
        return currentUserAccountName;
    }

    public boolean isErrorMessagePresent(){
        log.info("Error message is present.");
        Reporter.log("Error message is present.");
        return errorLoginMessage.isDisplayed();
    }
    public boolean isEmptyEmailErrorMessagePresent(){
        log.info("Error message is present.");
        Reporter.log("Error message is present.");
        return emptyEmailErrorMessage.isDisplayed();
    }
    public boolean isEmptyPasswordErrorMessagePresent() {
        log.info("Error message is present.");
        Reporter.log("Error message is present.");
        return voidPasswordFieldMessage.isDisplayed();
    }
    public boolean isInvalidEmailErrorMessagePresent() {
        log.info("Error message is present.");
        Reporter.log("Error message is present.");
        return invalidEmail.isDisplayed();
    }

    public String getErrorMessageText (){
        assert isErrorMessagePresent(): "Error message is not present on Login page.";
        String actualErrorLoginMessage = errorLoginMessage.getText();
        log.info ("Error message is: " + actualErrorLoginMessage);
        Reporter.log("Error message is: " + actualErrorLoginMessage);
        return actualErrorLoginMessage;
    }

    public String getEmptyEmailErrorMessageText (){
        assert isEmptyEmailErrorMessagePresent(): "Error message is not present.";
        String actualMessage = emptyEmailErrorMessage.getText();
        log.info("Error message is: " + actualMessage);
        Reporter.log("Error message is: " + actualMessage);
        return actualMessage;
    }

    public String getEmptyPasswordErrorMessageText () {
        assert isEmptyPasswordErrorMessagePresent() : "Error message is not present.";
        String actualMessage = voidPasswordFieldMessage.getText();
        log.info ("Error message is: " + actualMessage);
        Reporter.log("Error message is: " + actualMessage);
        return actualMessage;
    }

    public String getInvalidEmailErrorMessageText () {
        assert isInvalidEmailErrorMessagePresent() : "Error message is not present.";
        String actualMessage = invalidEmail.getText();
        log.info("Error message is: " + actualMessage);
        Reporter.log("Error message is: " + actualMessage);
        return actualMessage;
    }

}