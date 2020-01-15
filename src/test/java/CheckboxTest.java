import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class CheckboxTest {

    WebDriver chromedriver;

    List<WebElement> listOfInputs;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        chromedriver = new ChromeDriver();
        openBrowser();
        listOfInputs = chromedriver.findElements(By.tagName("input"));
    }

    @AfterSuite
    public void tearDown() {
        chromedriver.quit();
    }


    @Test
    public void test_001() {

        // Test whether inputs exist at all
        Assert.assertNotNull(listOfInputs, "No elements returned.");
    }

    @Test
    public void test_002() {

        // Check the quantity of checkboxes
        Assert.assertEquals(listOfInputs.size(), 2, "Number of checkboxes is wrong! Should be: 2");
    }

    @Test
    public void test_003() {
        // Toggle checkboxes
        WebElement checkbox1 = listOfInputs.get(0);
        WebElement checkbox2 = listOfInputs.get(1);
        String isBox1Checked = checkbox1.getAttribute("checked");
        String isBox2Checked = checkbox2.getAttribute("checked");

        Assert.assertNull(isBox1Checked, "Checkbox is checked when it should not be.");
        checkbox1.click();
        isBox1Checked = checkbox1.getAttribute("checked");
        Assert.assertEquals(isBox1Checked, "true", "Checkbox 1 is not checked");

        Assert.assertEquals(isBox2Checked, "true", "Checkbox 2 is checked");
        checkbox2.click();
        isBox2Checked = checkbox2.getAttribute("checked");
        Assert.assertNull(isBox2Checked, "Checkbox is not checked when it should be.");



    }
    

    public void openBrowser() {
        chromedriver.get("http://the-internet.herokuapp.com/checkboxes");
    }
}
