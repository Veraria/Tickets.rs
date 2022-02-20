package tests;

import org.testng.annotations.Test;
import pages.RegisterPage;
import pages.RegistrationInfoPage;
import pages.Strings;
import static org.testng.Assert.assertEquals;

public class RegisterTest extends BaseTest{

/**  Register with valid username and valid password without clicking the register button.
     NOTE: because of anti-spam protection the registration is not completed.

Test steps: 1. Navigate to "https://www.tickets.rs/user/register?lang=en"
            2. Accept cookies
            3. Enter valid Email and password
            4. Accept Terms and conditions
            5. Accept Privacy policy
            6. Tick the reCAPTCHA box
            7. Click the register button


Expected results: 1. Verify the alert message
                  2. Verify that we are still on the register page
                  3. After clicking the register button an alert modal is displayed
                  4. The user is still on the register page and the registration is impossible
*/
    @Test
    public void registerWithValidUsernameAndValidPassword () {
        driver = openChromeDriver();
        try {
            RegisterPage registerPage = new RegisterPage(driver);

            registerPage.acceptCookies();

            registerPage.enterTextInEmailField(Strings.VALID_EMAIL)
                        .enterTextInPasswordField(Strings.VALID_PASSWORD)
                        .acceptingTermsAndConditions()
                        .acceptingPrivacyPolicy()
                        .clickingRecaptcha();

            RegistrationInfoPage registrationInfoPage = registerPage.clickingRegisterButton();

            // verify the alert message
            assertEquals(registerPage.getCurrentAlertMessage(),Strings.REGISTER_ALERT_MESSAGE,"Alert text incorrect");

            // verify that we are still on the same page (Register Page)
            String currentUrl = driver.getCurrentUrl();
            assert isCurrentUrlEqualTo(Strings.REGISTER_PAGE_URL) : "Wrong URL. Expected " + Strings.REGISTER_PAGE_URL+"" +
                    " Actual is "+ currentUrl;

        } finally {
            driver.quit();
        }
    }
}