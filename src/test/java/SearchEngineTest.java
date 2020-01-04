// TODO: Write automated test for Yahoo search and other search engines
// TODO: Try to create generic methods and reuse them for each test.
//
// Class methods changed for greater flexibility


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

    // Our Firefox driver. We could add Chrome driver as well, I think. NOTE: Look into other drivers.
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
        // <span> tag has no attributes so we use CSS selector here
        String resultsSelector = "#left > div > ol.reg.searchBottom > li > div > div > span";

        openBrowser();
        navigateTo("https://www.yahoo.com");
        typeQuery(By.id(searchID), this.testQuery);
        submitQuery(By.id(submitID));
        assertResultsPage(By.cssSelector(resultsSelector));
    }

    @Test
    public void testDuckDuckGo() {
        // Let's use XPath for this one!
        String searchXPath = "/html/body/div/div[1]/div[2]/form/input[1]";
        String submitSelector = "#search_form_homepage_top > input.search__button.js-search-button";
        String resultsSelector = "#rld-1 > a";

        openBrowser();
        navigateTo("https://www.duckduckgo.com");
        typeQuery(By.xpath(searchXPath), this.testQuery);
        submitQuery(By.cssSelector(submitSelector));
        assertResultsPage(By.cssSelector(resultsSelector));
    }

    @Test
    public void testYaRu() {
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
        String searchID = "kw";
        String submitID = "su";
        String resultsSelector = "#container > div.head_nums_cont_outer.OP_LOG > div > div.nums > span";

        openBrowser();
        navigateTo("https://www.baidu.com");
        typeQuery(By.id(searchID), this.testQuery);
        submitQuery(By.id(submitID));
        assertResultsPage(By.cssSelector(resultsSelector));
    }

    private void openBrowser() {
        // Nothing to do here – everything done using annotations
        this.geckoDriver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.geckoDriver,30);
    }

    private void navigateTo(String s) {
        geckoDriver.get(s);
    }

    private void typeQuery(By selector, String query) {
        // CSS selectors may be not that reliable, or we may wish to use other means
        // Like using element IDs or XPath
        // That's why we send a By object as a parameter
        WebElement element = this.geckoDriver.findElement(selector);
        element.sendKeys(query);
    }

    private void waitForResults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));
    }

    private void assertResultsPage(By selector) {
        waitForResults(selector);
        boolean elementShown = geckoDriver.findElement(selector).isDisplayed();

        Assert.assertTrue(elementShown);
    }

    private void waitForResults(By selector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    private void submitQuery(By selector) {
        // Again we may wish to use some other method of element selection
        WebElement element = geckoDriver.findElement(selector);
        element.submit();
    }

    @BeforeSuite
    public void initialSetUp() {
        // A bit convoluted, yes.
        String dirSep;

        dirSep = System.getProperty("file.separator");

        this.geckodriver_path = "src"+dirSep+"test"+dirSep+"resources"+dirSep+"geckodriver";
        System.setProperty("webdriver.gecko.driver", this.geckodriver_path); //Configure driver path(s)
        // And check whether the property was set up properly
        if(System.getProperty("webdriver.gecko.driver").equals("src/test/resources/geckodriver"))
            System.out.println("Everything seems to be in order?..");

        // This may not be the best place to initialize class property, but...
        this.testQuery = "Portnov computer school";
    }


}
