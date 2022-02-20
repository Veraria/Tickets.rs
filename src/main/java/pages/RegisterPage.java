package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    @FindBy(id = "email")
    WebElement eMailField;

    @FindBy(id = "password1")
    WebElement passwordField;

    @FindBy(xpath = "//label[@for='accept_terms']")
    WebElement termsAndConditionsBox;

    @FindBy(xpath = "//label[@for='accept_privacy']")
    WebElement privacyPolicyBox;

    @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
    WebElement iFrame;

    @FindBy(className = "rc-anchor-center-container")
    WebElement recaptchaBox;

    @FindBy(id = "submitReg")
    WebElement registerButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://www.tickets.rs/user/register?lang=en");
    }

    public RegisterPage enterTextInEmailField (String text){
        print("Entering text "+text+ " in email field");
        eMailField.click();
        eMailField.sendKeys(text);
        return this;
    }

    public RegisterPage enterTextInPasswordField (String text){
        print("Entering text "+text+" in password field");
        passwordField.click();
        passwordField.sendKeys(text);
        return this;
    }

    public RegisterPage acceptingPrivacyPolicy() {
        print("Accepting Privacy Policy by clicking the box");
        privacyPolicyBox.click();
        return this;
    }
    public RegisterPage acceptingTermsAndConditions() {
        print("Accepting Terms and conditions by clicking the box");
        termsAndConditionsBox.click();
        return this;
    }
    public RegisterPage clickingRecaptcha() {
        print("Clicking recaptcha box");
        driver.switchTo().frame(iFrame);
        recaptchaBox.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public RegistrationInfoPage clickingRegisterButton (){
        print("Clicking register button");
        waitForElement(registerButton);
        registerButton.click();
        //JavascriptExecutor executor = (JavascriptExecutor)driver;
        //executor.executeScript("arguments[0].click();", registerButton);
        return new RegistrationInfoPage(driver);
    }

    public String getCurrentAlertMessage(){
        Alert alert = driver.switchTo().alert();
        String currentAlertMessage= driver.switchTo().alert().getText();
        print (currentAlertMessage);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        alert.accept();
        print("Current alert message is "+currentAlertMessage);
        return currentAlertMessage;
    }
}
