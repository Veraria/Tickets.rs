package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class AddingTicketsToCartPage extends BasePage{

    public AddingTicketsToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
}
    @FindBy (xpath = "//a[@href='/event/letnji_beogradski_salon_vina_1665']") //a[@href='/event/letnji_beogradski_salon_vina_1665'] //a[@href='/event/tricky_1222']
    WebElement infoAboutTicket;

    @FindBy (xpath = "//h4[contains(text(),'Broj ulaznica: 2')]")
    WebElement ticketNumberTotal;

    @FindBy (xpath = "//span[contains(text(),'Pređi na korpu ')]")
    WebElement shoppingCartButton;

    public AddingTicketsToCartPage selectTickets(){

        waitForElement(infoAboutTicket);
        //JavascriptExecutor executor = (JavascriptExecutor)driver;
        //executor.executeScript("arguments[0].click();", infoAboutTicket);
        // works fine without javascript
        infoAboutTicket.click();
        log.info("Selecting tickets");
        Reporter.log("Selecting tickets");
        return new AddingTicketsToCartPage(driver);
    }

    public void addingTicketsToCart (int ticket_number){

        sleep(5000);
        //accepting cookies
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@aria-label='dismiss cookie message']"))).click();
        //scrolling down to the web element
        Actions actions = new Actions(driver);
        actions.moveToElement(addingTicketsButton);
        actions.perform();

        for (int i = 0; i < ticket_number; i++) {
            addingTicketsButton.click();
        }
        log.info("Number of tickets in the cart: "+ticket_number);
        Reporter.log("Number of tickets in the cart: "+ticket_number);
    }

    public String getInfoText(){

        Actions actions = new Actions(driver);
        actions.moveToElement(ticketNumberTotal);
        waitForElement(ticketNumberTotal);
        actions.perform();

        return ticketNumberTotal.getText();
    }

    public ShoppingCartPage clickShoppingCart() {
        log.info("Clicking Shopping Cart");
        Reporter.log("Clicking Shopping Cart");
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCartButton);
        waitForElement(shoppingCartButton);
        actions.perform();
        shoppingCartButton.click();
        return new ShoppingCartPage(driver);
    }

}
