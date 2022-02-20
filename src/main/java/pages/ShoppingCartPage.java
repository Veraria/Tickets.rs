package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends BasePage{

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //@FindBy (xpath = "//label[@for='mat-radio-4-input']")
    @FindBy (xpath = "//*[@id='cdk-step-content-0-0']//div[4]//div[3]//div")
    WebElement deliveryRadioButton;

    public ShoppingCartPage scrollToTheBottom(){
        //scroll down

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        deliveryRadioButton.click();
        return this;
    }
/**
    public ShoppingCartPage checkDeliveryRadioButton(){
       /** Actions actions = new Actions(driver);
        actions.moveToElement(deliveryRadioButton);
        waitForElement(deliveryRadioButton);
        actions.perform();

        WebDriverWait wait = new WebDriverWait(driver,10);

        wait.until(ExpectedConditions.presenceOfElementLocated((By) deliveryRadioButton));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(deliveryRadioButton)));
        deliveryRadioButton.click();
        return this;
    }
    */
}
