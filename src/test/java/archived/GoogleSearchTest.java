import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


/*
TODO: Brush up on the test case formats and styles
TODO: Convert to a user stories

1. Start the browser (Firefox in our case)
2. Navigate to: https://www.google.com. Empty search form should be displayed
3. Enter the search term: "Apple stock price history"
4. Click "Search" button. Search results should be displayed.

*/

public class GoogleSearchTest {

    WebDriver driver;
    WebDriverWait wait;


    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(this.driver,10);
    }

    @AfterTest
    public void tearDown(){
//        driver.quit();
    }

    @Test
    public void test0001() { // Open the browser window, fill in the text
        System.setProperty("webdriver.gecko.driver", "/Users/desman/IdeaProjects/automation-Webdriver-TestNG/src/test/resources/geckodriver");

        openBrowser();
        navigateTo("https://www.google.com");
        typeQuery("Portnov computer school");
        submitQuery();
        assertResultsPage();
    }

    private void assertResultsPage() {
        waitForResults();
        boolean elementShown = driver.findElement(By.id("resultStats")).isDisplayed();

        Assert.assertTrue(elementShown);
    }

    private void waitForResults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));
    }

    private void submitQuery() {
        String selector = "#tsf > div:nth-child(2) > div.A8SBwf > div.FPdoLc.tfB0Bf > center > input.gNO89b";

        WebElement element = driver.findElement(By.cssSelector(selector));
        element.submit();
    }

    private void typeQuery(String q) {
        String selector = "#tsf > div:nth-child(2) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input";

        WebElement element = driver.findElement(By.cssSelector(selector));
        element.sendKeys(q);

    }



    private void navigateTo(String s) {
        driver.get(s);
    }

    private void openBrowser() {
        driver.get("about:blank"); // Just a nice empty page
        System.out.println("Opened blank page");
    }


}
