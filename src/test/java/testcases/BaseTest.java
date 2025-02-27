package testcases;

import custom.TestContext;
import listeners.ScreenshotListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.BasePage;

@Listeners(ScreenshotListener.class)  // Listener burada eklendi!
public class BaseTest {
    public TestContext testContext;
    protected BasePage basePage;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        testContext = new TestContext();
        basePage = testContext.getPageObjectManager().getBasePage();
    }

    @AfterMethod
    public void tearDown() {
        testContext.getWebDriverManager().closeDriver();
    }
}