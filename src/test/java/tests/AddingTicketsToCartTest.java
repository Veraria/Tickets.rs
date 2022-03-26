package tests;

import org.testng.annotations.Test;
import pages.AddingTicketsToCartPage;
import pages.ShoppingCartPage;
import pages.Strings;


public class AddingTicketsToCartTest extends BaseTest{

/** Adding tickets to the cart
 Test steps:
        1. Navigate to https://www.tickets.rs/?lang=en
        2. Scroll down and click the event name
        3. A new tab is opened which contains information about tickets
        4. Select the ticket by clicking the field with information on ticket
        5. A new page is opened. On that page add wanted number of tickets by clicking the plus sign.
           In this test two (2) tickets are added
        6. Click "Pređi na korpu" button
        7. The Shopping cart page is opened

 Expected results:
        1. Verify that number shown in the cart corresponds with the number of added tickets
        2. Verify that the text that informs the total amount of added tickets is correct.
        3. Verify that the current URL equals to Shopping Cart Page URL
 */
    @Test
    public void addingTicketsToCartTest(){
        driver = openChromeDriver();
        try {

            AddingTicketsToCartPage addingTicketsToCartPage = new AddingTicketsToCartPage(driver);
            addingTicketsToCartPage.clickOnEventName()
                              .selectTickets()
                              .addingTicketsToCart(2);

            String numberInCart = addingTicketsToCartPage.getNumberOfTicketsInCartFromBadge();
            sleep();
            //Verify that number shown in the cart corresponds with the number of added tickets
            assert numberInCart.equals("0") : "Wrong number of tickets.";

            sleep();
            //Verify that the text that informs the total amount of added tickets is correct
            String totalNumberOfTickets = addingTicketsToCartPage.getInfoText();
            assert totalNumberOfTickets.equals("Broj ulaznica: 2"): "Wrong info text.";

          addingTicketsToCartPage.clickShoppingCart();

            //Verify that the current URL equals to Shopping Cart Page URL
            sleep();
            assert isCurrentUrlEqualTo(Strings.SHOPPING_CART_PAGE_URL);
            ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);


            shoppingCartPage.step1ShoppingCart();
            sleep();
            shoppingCartPage.step2EnterDataToFinishBuyingTickets();
            shoppingCartPage.step3ShoppingCartReview();


        }finally {
            driver.quit();
        }
    }
}
