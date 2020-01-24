import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class LoginXPathTesting {
    WebDriver driver;

    // TODO: Homework: Practice with XPath on different sites. Try to find best ways to select attributes in each case.
    // TODO: TestNG test suite for the above. Start from this login page with different data?
    @Test
    public void test_001() {
        String usernameXPathRelative = "//*[@id=\"username\"]";
        String usernameXPathFull = "/html/body/div[2]/div/div/form/div[1]/div/input";
        String passwordXPathRelative = "//*[@id=\"password\"]";
        String passwordXPathFull = "/html/body/div[2]/div/div/form/div[2]/div/input";
        String submitXPathRelative = "//*[@id=\"login\"]/button";
        String submitXPathFull = "/html/body/div[2]/div/div/form/button";

        String loginErrorXPathFull = "/html/body/div[1]/div/div";
        String loginErrorXPathRelative = "//*[@id=\"flash\"]";

        openBrowser("http://the-internet.herokuapp.com/login");
        System.out.println("And here is the breakpoint");

    }

    private void openBrowser(String s) {
        driver.get(s);
    }

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
