import org.testng.annotations.*;
// Got some strange stuff going on.
// Extra line breaks (2 of those, to be precise!) appear after each println
// Otherwise works as expected

public class TestngAnnotation {
    // test case 1
    @Test
    public void testCase1() {
        System.out.println("in test case 1");
    }

    // test case 2
    @Test
    public void testCase2() {
        System.out.println("in test case 2");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("in beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("in afterMethod");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("in beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("in afterClass");
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

/*public class AnnotationsTest {

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
}*/
