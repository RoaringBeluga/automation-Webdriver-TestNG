import org.testng.annotations.*;


public class AnnotationsTest {

    @Test
    public void testCase1() {
        System.out.println("In Case 1");
    }

    @Test
    public void testCase2() {
        System.out.println("In Case 2");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("In beforeMethod");
    }

    @BeforeMethod
    public void afterMethod() {
        System.out.println("In afterMethod");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("In beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("In afterClass");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("in beforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("in afterTest");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("in beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("in afterSuite");
    }
}
