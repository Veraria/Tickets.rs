package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.FilteringEventsPage;
import pages.Strings;

import static org.testng.Assert.assertTrue;

public class FilteringEventsTest extends BaseTest{

    /** Filtering events by category and city
Test steps:
        1. Navigate to https://www.tickets.rs/?lang=en
        2. Accept cookies
        3. Click "All categories" field and from the dropdown menu choose "Film"
        4. Click "All cities" field and from the dropdown menu choose "Novi Beograd"
        5. Click search button
        6. A new page is opened ( https://www.tickets.rs/city/novi-beograd/category/film-2)

Expected results:
        1. Verify that events are filtered by wanted categories (film and city)
        2. The URL of verified page is https://www.tickets.rs/city/novi-beograd/category/film-2)
        3. The page title is "Novi Beograd"
        4. There is a display of films currently in movie theatres in Novi Beograd
 */
    @Test
    public void FilteringEventsByCategoryAndCityTest(){

        driver = openChromeDriver();
        try {


                FilteringEventsPage filteringEventsPage = new FilteringEventsPage(driver);
                filteringEventsPage.acceptCookies();
                filteringEventsPage.filterEvents();
                sleep();
            // verify that the URL of verified page is https://www.tickets.rs/city/novi-beograd/category/film-2)
                String currentURLPage = filteringEventsPage.getPageURL();
                assert isCurrentUrlEqualTo(Strings.FILTERED_PAGE_URL) : "We are on the wrong URL. Expected: " + Strings.FILTERED_PAGE_TITLE +
                                                                       " Actual is " + currentURLPage;
                sleep();
                // verify that the page title is "Novi Beograd"
                assertTrue (filteringEventsPage.getTitleText().contains(Strings.FILTERED_PAGE_TITLE),
                        "Title text is incorrect");
                log.info("Page title is " + filteringEventsPage.getTitleText());
                Reporter.log("Page title is " + filteringEventsPage.getTitleText());
        }finally {
            driver.quit();
            }
        }
    }
