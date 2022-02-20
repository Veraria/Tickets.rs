package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Strings;
import static org.testng.Assert.assertEquals;

public class LoginTests extends BaseTest {


/** Login with valid credentials and logout
Test steps:
        1. Navigate to https://www.tickets.rs/?lang=en
        2. Click login button that will open a new Login page
        3. Enter valid Email and valid password in the Email and Password text fields
        4. Click Login button
        5. Log out

Expected result:
        1. Verify that the correct user is logged in
        2. The correct user is equal to the current user which is visible in the page header and asserted
        3. After logout, the current user notification is no longer visible and the user is on the homepage tickets.rs
 */
    @Test (priority = 1)
    public void loginWithValidCredentialsAndLogout() {
        driver = openChromeDriver();
        Reporter.log("The browser is opened now");
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickLoginButton()
                    .enterTextInEmailField(Strings.VALID_EMAIL)
                    .enterTextInPasswordField(Strings.VALID_PASSWORD)
                    .clickSubmitLoginButton();

            // verify that the user is successfully logged in
            // by comparing the current user account name with the expected user account name
            assertEquals(loginPage.getCurrentUserName(), Strings.USER_ACCOUNT_NAME, "User is incorrect");
            log.info("Current user is: " + loginPage.getCurrentUserName());
            Reporter.log("Current user is: " + loginPage.getCurrentUserName());

            loginPage.clickLogoutButton();
            sleep();
            // verify that after logging out current page URL equals to Homepage URL.
            assert isCurrentUrlEqualTo(Strings.HOMEPAGE_URL);

        } finally {
            driver.quit();
        }
    }

    /** Login with valid Email and invalid password
Test steps:
        1. Navigate to https://www.tickets.rs/?lang=en
        2. Click login button that will open a new Login page
        3. Enter valid Email and invalid password in the Email and Password text fields
        4. Click Login button


Expected result:
        1. Verify the error message
        2. The login is unsuccessful, the user stays on the login page and the error message "Unsuccessful Login"
           is visible.
 */
    @Test (priority = 2)
    public void loginWithValidEmailAndInvalidPassword() {
        driver = openChromeDriver();
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickLoginButton()
                     .enterTextInEmailField(Strings.VALID_EMAIL)
                     .enterTextInPasswordField(Strings.INVALID_PASSWORD)
                     .clickSubmitLoginButton();

            //verify that the user stays on the login page
            assert isCurrentUrlEqualTo (Strings.LOGIN_PAGE_URL):"Expected url: "+ Strings.LOGIN_PAGE_URL;


            // verify that the error message "Unsuccessful Login" is visible.
            String currentErrorMessageText = loginPage.getErrorMessageText();
            assert currentErrorMessageText.equals(Strings.LOGIN_ERROR_MESSAGE): "Expected error message: " + Strings.LOGIN_ERROR_MESSAGE +
                    " Actual: " + currentErrorMessageText;

        } finally {
            driver.quit();
        }
    }

    /** Login with empty Email and password fields
Test steps:
    1. Navigate to https://www.tickets.rs/?lang=en
    2. Click login button that will open a new Login page
    3. Leave empty Email and password text fields
    4. Click Login button


Expected result:
     1. Verify the error message
     2. The login is unsuccessful and two error messages are displayed
       under Email and password text fields respectively
*/
        @Test (priority = 3)
        public void loginWithEmptyEmailAndPasswordFieldTest() {
            driver = openChromeDriver();
            try {
                LoginPage loginPage = new LoginPage(driver);
                loginPage.clickLoginButton()
                         .enterTextInEmailField("")
                         .enterTextInPasswordField("")
                         .clickSubmitLoginButton();

                // verify the messages of unsuccessful login

                String currentEmptyEmailErrorMessage = loginPage.getEmptyEmailErrorMessageText();
                assertEquals(currentEmptyEmailErrorMessage,Strings.EMAIL_ERROR_MESSAGE,"Error message is incorrect");

                String currentVoidPasswordFieldMessage = loginPage.getEmptyPasswordErrorMessageText();
                assertEquals(currentVoidPasswordFieldMessage,Strings.VOID_PASSWORD_MESSAGE,"Error message is incorrect");

            }finally {
                driver.quit();
            }
        }

    /** Login with numerical Email and valid password
Test steps:
    1. Navigate to https://www.tickets.rs/?lang=en
    2. Click login button that will open a new Login page
    3. Enter numerical Email and valid password in the Email and Password text fields
    4. Click Login button


Expected result:
     1. Verify the error message
     2. The login is unsuccessful and the error message is displayed under the Email text field
*/
        @Test (priority = 4)
        public void loginWithNumericalEmailAndValidPassword (){
            driver = openChromeDriver();
            try {
                LoginPage loginPage = new LoginPage(driver);
                loginPage.clickLoginButton()
                         .enterTextInEmailField(Strings.NUMERICAL_EMAIL)
                         .enterTextInPasswordField(Strings.VALID_PASSWORD)
                         .clickSubmitLoginButton();

            // verify the message under the Email text field
                String currentEmailFieldErrorMessage = loginPage.getInvalidEmailErrorMessageText() ;
                assertEquals(currentEmailFieldErrorMessage,"Please enter a valid email address","Alert text incorrect");

                }finally {
                driver.quit();
            }
        }
    }

