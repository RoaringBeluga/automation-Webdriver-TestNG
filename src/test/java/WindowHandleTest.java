import com.sun.tools.classfile.Opcode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.collections.Sets;

import java.util.*;

public class WindowHandleTest {
    WebDriver driver;
    WebDriverWait wait;

    // Testing new window first
    @Test
    public void test001() {

        Set<String> windowHandles = driver.getWindowHandles(); // Initial window/tab handle

        openBrowser("http://the-internet.herokuapp.com/windows");
        int expectedWindows = 2;
        int actualWindows = windowHandles.size(); // Should be 1 as the new browser window is opened

        // Assert there's only one handle in the set
        Assert.assertEquals(actualWindows, 1, "More than one window is open – suspicious! Should be one only.");
        clickLink("Click Here");
        // Wait for the number of windows to become 2 – signifying that the new one opened.
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindows));
        } catch (Exception e) {
            System.out.println("Exception: "+e.getMessage());
            Assert.fail("Window/Tab number differs from the expected value of "+expectedWindows);
        }

        windowHandles = driver.getWindowHandles();
        actualWindows = windowHandles.size();
        System.out.println("Window handles: "+windowHandles.toString());


        Assert.assertEquals(actualWindows, expectedWindows, "Oops! Expected number of windows: "+expectedWindows);
        ArrayList<String> windowTitles = new ArrayList<String>();
        for(int i = 0; i < actualWindows; i++) {
            // Sets are not ordered so we have to make an array out of it
            driver.switchTo().window(windowHandles.toArray()[i].toString());
            windowTitles.add(driver.getTitle());
        }

        System.out.println("Window titles: "+windowTitles.toString());
    }

    private void clickLink(String theLink) {
        driver.findElement(By.linkText(theLink)).click();
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
