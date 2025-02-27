package testcases;

import managers.FileReaderManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P02_CareersPage;

public class T01_HomeToCareerPageTest extends BaseTest {
    private P01_HomePage homePage;
    private P02_CareersPage careersPage;

    @BeforeMethod
    public void pageSetUp() {
        homePage = testContext.getPageObjectManager().getHomePage();
        careersPage = testContext.getPageObjectManager().getCareersPage();
    }

    @Test
    public void verifyCareerPageAndSections() {

        // Access to "INSIDER_URL=https://useinsider.com/" is available
        basePage.openUrl(FileReaderManager.getInstance().getConfigReader().getKeyValue("INSIDER_URL"));

        // The homepage should be displayed
        basePage.isDisplayed(homePage.insiderLogo);

        // The navigation bar should be visible on the homepage
        Assert.assertTrue(basePage.isDisplayed(homePage.navbarNavDropdown), "Navigation bar is not visible");

        // Click on Company menu
        basePage.click(homePage.companyMenu);

        // The dropdown menu should display the following sections
        Assert.assertTrue(basePage.isDisplayed(homePage.aboutUs), "About Us is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.newsroom), "Newsroom is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.partnership), "Partnership is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.integrations), "Integrations is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.careers), "Careers is missing");
        Assert.assertTrue(basePage.isDisplayed(homePage.contactUs), "Contact Us is missing");

        // Click on Careers link
        basePage.click(homePage.careers);

        // Verify that the URL is correct - CareersPage
        String baseUrl = FileReaderManager.getInstance().getConfigReader().getKeyValue("INSIDER_URL") +
                FileReaderManager.getInstance().getConfigReader().getKeyValue("CAREERS_URL");
        String currentUrl = basePage.getUrlAdress();
        Assert.assertTrue(currentUrl.startsWith(baseUrl),
                "Error! Page URL does not start with the expected baseURL. Expected: " + baseUrl + " - Actual: " + currentUrl);

        // The current title value should be equal to InsiderCareers
        Assert.assertEquals(careersPage.careersPageTitle,basePage.getTitle());

        // The Our_Locations, Teams, Life_at_Insider block should be visible on the Career page
        basePage.isDisplayed(careersPage.ourLocationBlock);
        basePage.isDisplayed(careersPage.teamsBlock);
        basePage.isDisplayed(careersPage.lifeAtInsiderBlock);
    }
}
