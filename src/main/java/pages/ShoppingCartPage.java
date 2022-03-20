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
    ////*[@id='cdk-step-content-0-0']//div[4]//div[3]//div
    @FindBy (xpath = "//*[@id='mat-radio-4']")
    WebElement deliveryRadioButton;
    @FindBy (xpath = "(//span[contains(text(),' Nastavi')])[1]")
    WebElement nastaviButton;
    @FindBy (id="MCDO_imenaziv")
    WebElement nameTextField;
    @FindBy (id="MCDO_prezime")
    WebElement surnameTextField;
    @FindBy (id="MCDO_email")
    WebElement emailTextField;
    //TODO ovaj lokator je grozan i ne bi trebalo da uopste postoji
    @FindBy (xpath = "//*[@id=\"cdk-step-content-0-1\"]/div[2]/div/div[3]/div[2]")
    // TODO nije dobar naziv web elementa
    WebElement nastaviButton2;

    public ShoppingCartPage scrollToTheBottom(){
        //scroll down

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
// TODO iydvoj ovo u dve metode
        deliveryRadioButton.click();
        return this;
    }
    public ShoppingCartPage clickNastaviButton(){
        nastaviButton.click();
        return this;

    }
    public ShoppingCartPage enterDataToFinishBuyingTickets(){
        nameTextField.click();
        nameTextField.sendKeys("Name");
        surnameTextField.click();
        surnameTextField.sendKeys("Surname");
        emailTextField.click();
        emailTextField.sendKeys(Strings.VALID_EMAIL);
        sleep(15);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", nastaviButton2);
        // mislim da ovo ispod nije potrebno
        nastaviButton2.click();
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
