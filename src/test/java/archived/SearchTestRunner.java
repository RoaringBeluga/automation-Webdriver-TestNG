package SearchTestRunner;
/*
    Now the tests are moved to their separate classes
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Class responsible for actually running tests
 *
 */
public class SearchTestRunner {
    /**
     * Actual instance of browserTest class responsible for running tests
     */
    browserTest testRunner; // Here we have the object that will take care of test steps

    /**
     * Test Firefox browser with Bing search engine
     */
    @Test
    public void testFirefoxBing() {
        testRunner = new FirefoxTest(); //
        testRunner.navigateTo("https://www.bing.com");
        testRunner.typeQuery(By.id("sb_form_q"), "Portnov computer school");
        testRunner.submitQuery(By.id("sb_form_go"));
        testRunner.assertResultsPage(By.cssSelector("#b_tween > span.sb_count"));
        testRunner.tearDown();
    }

    /**
     * Test Chrome browser with Bing search engine
     */
    @Test
    public void testChromeBing() {
        testRunner = new ChromeTest();
        testRunner.navigateTo("https://www.bing.com");
        testRunner.typeQuery(By.id("sb_form_q"), "Portnov computer school");
        testRunner.submitQuery(By.id("sb_form_go"));
        testRunner.assertResultsPage(By.cssSelector("#b_tween > span.sb_count"));
        testRunner.tearDown();
    }

    /**
     * Test Firefox browser with Yahoo search engine
     */
    @Test
    public void testFirefoxYahoo() {
        testRunner = new FirefoxTest();
        testRunner.navigateTo("https://www.yahoo.com");
        testRunner.typeQuery(By.id("header-search-input"), "Portnov computer school");
        testRunner.submitQuery(By.id("header-desktop-search-button"));
        testRunner.assertResultsPage(By.cssSelector("#left > div > ol.reg.searchBottom > li > div > div > span"));
        testRunner.tearDown();
    }

    /**
     * Test Chrome browser with Yahoo search engine
     */
    @Test
    public void testChromeYahoo() {
        testRunner = new ChromeTest();
        testRunner.navigateTo("https://www.yahoo.com");
        testRunner.typeQuery(By.id("header-search-input"), "Portnov computer school");
        testRunner.submitQuery(By.id("header-desktop-search-button"));
        testRunner.assertResultsPage(By.cssSelector("#left > div > ol.reg.searchBottom > li > div > div > span"));
        testRunner.tearDown();
    }
}

/**
 * browserTest class contains test steps to be performed.
 * All browser-specific stuff goes to specific classes extending this one.
 */
class browserTest {
    /**
     * WebDriver instance used for testing.
     */
    WebDriver driver;
    /**
     * WebDriverWait instance used to wait for an element to become visible.
     */
    WebDriverWait wait;
    /**
     * Browser name. No actual purpose, just so we can print a browser name.
     */
    String browserName;

    public browserTest() {
        // Nothing to do here
    }

    /**
     * Close the browser instance after running all test steps.
     * Makes for tidier desktop. Also, modern browsers are memory hogs.
     */
    public void tearDown(){
        System.out.println(this.browserName+": Closing...");
        this.driver.quit(); // Let's clean up after tests
    }

    /**
     * Navigate to the specified URL
     * @param gotoURL Website address to navigate to
     */
    public void navigateTo(String gotoURL) {
        System.out.println(this.browserName+": Navigating to "+gotoURL);
        this.driver.get(gotoURL);
    }

    /**
     * Enter query text into the search field
     * @param selector Element selector. CSS selector, element id, element name or XPath could be used.
     *                 Basically any method of the By object
     * @param query    Query text
     */
    public void typeQuery(By selector, String query) {
        WebElement element = this.driver.findElement(selector); // It could be CSS selector, element id, XPath expression...
        element.sendKeys(query);
    }

    /**
     * Submit search query
     * @param selector Element selector. CSS selector, element id, element name or XPath could be used.
     *                 Basically any method of the By object
     */
    public void submitQuery(By selector) {
        // Again we may wish to use some other method of element selection
        WebElement element = this.driver.findElement(selector);
        element.submit();
    }

    /**
     * Assert that we have search results returned
     * @param selector Element selector. CSS selector, element id, element name or XPath could be used.
     *                 Basically any method of the By object
     */
    public void assertResultsPage(By selector) {
        waitForResults(selector);
        boolean elementShown = this.driver.findElement(selector).isDisplayed();
        // Are the results really on-screen?
        // In our case the check is somewhat redundant as we check for the same element in waitForResults() method
        // But we could be checking for other things as well here
        Assert.assertTrue(elementShown);
    }

    /**
     * Wait for results to be returned.
     * @param selector Element selector. CSS selector, element id, element name or XPath could be used.
     *                 Basically any method of the By object
     *                 When the element becomes visible OR the page load times out, stop.
     *                 This method is redundant in the current iteration.
     */
    private void waitForResults(By selector) {
        // Wait for the element signifying search results being displayed...
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }
}

/**
 * Initialize with Chrome driver
 */
class ChromeTest extends browserTest {

    public ChromeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(this.driver, 40);
        this.browserName = "Chrome";
    }
}

/**
 * Initialize with Firefox driver
 */
class FirefoxTest extends browserTest {
    public FirefoxTest() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        this.driver = new FirefoxDriver();
        this.wait = new WebDriverWait(this.driver, 40);
        this.browserName = "Firefox";
    }
}