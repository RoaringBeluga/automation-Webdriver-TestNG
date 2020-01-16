/*
 Write automated test for Yahoo search and other search engines
 Try to create generic methods and reuse them for each test.

 Class methods changed for greater flexibility:
 Now we can look up the element by any means supported

 Variable names in methods called by tests:
      searchID, searchSelector, searchXPath – used to select the search field by id, CSS selector or XPath
      submitID, submitSelector, submitXPath – the same as above, but for the submit button
      resultID, resultSelector, resultXPath – for the element we use to confirm that search has returned results
 All selectors and XPath expressions were obtained via Developer tools (Copy -> Selector or Copy –> XPath)

 Ran into problem with timeouts when testing – looks like connection issues.
 Also, does WebDriver wait for the page load first if we use WebDriver.get()?..
*/


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SearchEngineTest {

    // Our Firefox driver. We could add Chrome driver as well, I think. Note to self: Look into other drivers.
    WebDriver geckoDriver;
    // We'll have to wait for search results – let's just reuse the object for that
    WebDriverWait wait;
    // Path to geckodriver
    String geckodriver_path = "";
    // Test query string
    String testQuery;

    @Test
    public void testYahooSearch() {
        String searchID = "header-search-input"; // Search field element id
        String submitID = "header-desktop-search-button"; // Submit button element id
        // <span> tag with number of search results has no attributes so we use CSS selector here
        String resultsSelector = "#left > div > ol.reg.searchBottom > li > div > div > span";

        openBrowser();
        navigateTo("https://www.yahoo.com");
        typeQuery(By.id(searchID), this.testQuery);
        submitQuery(By.id(submitID));
        assertResultsPage(By.cssSelector(resultsSelector));
    }

    @Test
    public void testDuckDuckGo() {
        // And what if we mind our privacy?..
        // Let's use XPath for this one! CSS selector was not looking right for me...
        String searchXPath = "/html/body/div/div[1]/div[2]/form/input[1]";
        String submitSelector = "#search_form_homepage_top > input.search__button.js-search-button";
        String resultsSelector = "#rld-1 > a"; // Not the best choice, as this is the selector

        openBrowser();
        navigateTo("https://www.duckduckgo.com");
        typeQuery(By.xpath(searchXPath), this.testQuery);
        submitQuery(By.cssSelector(submitSelector));
        assertResultsPage(By.cssSelector(resultsSelector));
    }

    @Test
    public void testYaRu() {
        // Works better for Russian pages than Google. Worse for other languages, tho.
        String searchXPath = "/html/body/table/tbody/tr[2]/td/form/div[1]/span/span/input";
        String submitSelector = "body > table > tbody > tr.b-table__row.layout__search > td > form > div.search2__button > button";
        String resultsXPath = "/html/body/div[3]/div[1]/div[2]/div[1]/div[2]/div/div[2]";


        openBrowser();
        navigateTo("https://www.ya.ru");
        typeQuery(By.xpath(searchXPath), this.testQuery);
        submitQuery(By.cssSelector(submitSelector));
        assertResultsPage(By.xpath(resultsXPath));
    }

    @Test
    public void testBaidu() {
        // THE search engine in China.
        String searchID = "kw"; // Element id was the best choice in this case – simpler and cleaner
        String submitID = "su"; // Same as above
        String resultsSelector = "#container > div.head_nums_cont_outer.OP_LOG > div > div.nums > span"; // only CSS works here – XPath was flaky

        openBrowser();
        navigateTo("https://www.baidu.com");
        typeQuery(By.id(searchID), this.testQuery);
        submitQuery(By.id(submitID));
        assertResultsPage(By.cssSelector(resultsSelector));
    }

    private void openBrowser() {
        this.geckoDriver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.geckoDriver,30);
    }

    private void navigateTo(String s) {
        // Here's the easy part
        geckoDriver.get(s);
    }

    private void typeQuery(By selector, String query) {
        // CSS selectors may be not that reliable, or we may wish to use other means
        // Like using element IDs or XPath
        // That's why we send a By object as a parameter
        WebElement element = this.geckoDriver.findElement(selector);
        element.sendKeys(query);
    }

    private void assertResultsPage(By selector) {
        waitForResults(selector);
        boolean elementShown = geckoDriver.findElement(selector).isDisplayed();
        // Are the results really on-screen?
        // In our case the check is somewhat redundant as we check for the same element in waitForResults() method
        // But we could be checking for other things as well here
        Assert.assertTrue(elementShown);
    }

    private void waitForResults(By selector) {
        // Wait for the element signifying search cresults being displayed...
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    private void submitQuery(By selector) {
        // Again we may wish to use some other method of element selection
        WebElement element = geckoDriver.findElement(selector);
        element.submit();
    }

    @BeforeSuite
    public void initialSetUp() {
        // A bit convoluted, yes. Pretending to make this a bit more cross-platform...
        String dirSep;

        dirSep = System.getProperty("file.separator");
        // We don't need the full path, relative path from project root works just fine
        this.geckodriver_path = "src"+dirSep+"test"+dirSep+"resources"+dirSep+"geckodriver";
        System.setProperty("webdriver.gecko.driver", this.geckodriver_path); //Configure driver path(s)

        // This may not be the best place to initialize a class property, but let's do it anyway...
        this.testQuery = "Portnov computer school";
    }


}
