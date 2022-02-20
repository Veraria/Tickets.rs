package tests;

import org.testng.annotations.Test;
import pages.SearchPage;
import pages.Strings;

import static org.testng.Assert.assertTrue;

public class SearchTest extends BaseTest{

    /** Search for an event using search text field
Test steps:
     1. Navigate to https://www.tickets.rs/?lang=en
     2. Accept cookies
     3. Click the Search text field
     4. Type in the event name
     5. Click the Search button

Expected results:
     1. Verify that we are on a Search result page and the title is "Search: event name"
     2. We are on a Search result page which title contains text "Search: event name". Event name will be the one we typed in.
     */
    @Test
    public void searchForAnEventTest(){
        driver = openChromeDriver();

        try {
            SearchPage searchPage = new SearchPage(driver);
            searchPage.acceptCookies();
            searchPage.searchForAnEvent (Strings.EVENT_TO_SEARCH_FOR);


            //Verify that the title is "Search: (event name we are searching for)"
            assertTrue (searchPage.getTitleText().contains("Search: " + Strings.EVENT_TO_SEARCH_FOR),
                    "Title text is incorrect");

            //Verify that we are on a Search result page
            String currentUrl = driver.getCurrentUrl();
            assert isCurrentUrlEqualTo(Strings.SEARCH_RESULT_PAGE_URL):"Error. Expected: " + Strings.SEARCH_RESULT_PAGE_URL +
                    " Actual: "+ currentUrl;

        }finally {
            driver.quit();
        }
    }
}
