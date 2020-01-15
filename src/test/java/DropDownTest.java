import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.TestRunner;
import org.testng.annotations.*;
import org.testng.internal.TestResult;

import java.util.ArrayList;
import java.util.Set;

public class DropDownTest {
    WebDriver driver;
    WebDriverWait wait;

    // Testing selection from dropdown
    @Test
    public void test001() {

        By selector = By.xpath("//*[@id=\"dropdown\"]/option[3]");

        openBrowser("http://the-internet.herokuapp.com/dropdown");
        // Selecting Option 2
        clickElement(selector);
        // List element should be selected
        Assert.assertTrue(driver.findElement(selector).isSelected(), "Element NOT selected!");
        Assert.assertEquals(driver.findElement(selector).getAttribute("value"),"2", "Element value wrong!");

    }

    // Failing the test due to element not found exception
    @Test
    public void test002() {

        try {
            By selector = By.linkText("Option 2");
            openBrowser("http://the-internet.herokuapp.com/dropdown");
            clickElement(selector);
            // List element should be selected
            Assert.assertTrue(driver.findElement(selector).isSelected(), "Element NOT selected!");

        } catch (Exception e) {
            System.out.println("Exception: "+e.getMessage());
            Assert.fail("Test failed.");
        }

    }

    private void clickElement(By selector) {
        driver.findElement(selector).click();
    }

    private void openBrowser(String theURL) {
        driver.get(theURL);
    }

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
    }

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

}
