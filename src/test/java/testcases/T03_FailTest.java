package testcases;

import managers.FileReaderManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P01_HomePage;

public class T03_FailTest extends BaseTest {
    private P01_HomePage homePage;

    @BeforeMethod
    public void pageSetUp() {
        homePage = testContext.getPageObjectManager().getHomePage();
    }

    @Test
    public void verifyCareerPageAndSections() {
        // Her testin kendi içinde URL'yi açmasını sağlıyoruz
        basePage.openUrl(FileReaderManager.getInstance().getConfigReader().getKeyValue("INSIDER_URL"));

        // The homepage should be displayed
        basePage.isDisplayed(homePage.insiderLogo);

        // Navigation bar should be visible
        Assert.assertTrue(basePage.isDisplayed(homePage.navbarNavDropdown), "Navigation bar is not visible");

        // Click on Company menu
        basePage.click(homePage.companyMenu);

        // Verify dropdown menu sections
        Assert.assertTrue(basePage.isDisplayed(homePage.aboutUs), "About Us is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.newsroomFailxpath), "Newsroom is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.partnership), "Partnership is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.integrations), "Integrations is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.careers), "Careers is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.contactUs), "Contact Us is missing");
    }
}
