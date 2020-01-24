// Created on 23 of January 2020 by desman

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestXPathSelectors {

    WebDriver driver;

    @Test
    public void test_001() {
        String testURL = "http://example.com";

        openBrowser(testURL);
    }

    private void openBrowser(String s) {
        driver.get(s);
    }

    @BeforeSuite
    public void setUp() {
        // *driver executables presumed to be in the resources directory
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        // System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new FirefoxDriver();
        // driver = new ChromeDriver();
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}