package testcases;

import managers.FileReaderManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class T02_QAJobFilteringAndApplicationTest extends BaseTest {
    private P03_QAJobsPage qaJobsPage;
    private P04_OpenPositionPage openPositionPage;
    private P05_LeverApplicationFormPage leverApplicationFormPage;

    @BeforeMethod
    public void pageSetUp() {
        qaJobsPage = testContext.getPageObjectManager().getQaJobsPage();
        openPositionPage = testContext.getPageObjectManager().getOpenPositionPage();
        leverApplicationFormPage = testContext.getPageObjectManager().getLeverApplicationFormPage();
    }

    @Test
    public void testQAJobFilteringAndApplicationFlow() throws InterruptedException {
        // Open QA Jobs page and perform basic operations
        navigateToQAJobsPage();

        // Apply filtering operation
        applyJobFilters();

        // List and verify open positions
        verifyJobListings();

        // Check the position details and proceed to the application step
        verifyJobApplicationProcess();
    }

    private void navigateToQAJobsPage() {
        // Access to "QA_JOBS_URL = https://useinsider.com/careers/quality-assurance/" is available
        basePage.openUrl(FileReaderManager.getInstance().getConfigReader().getKeyValue("QA_JOBS_URL"));

        // The QA Jobs page should be displayed
        Assert.assertEquals(qaJobsPage.qaJobsPageTitle,basePage.getTitle());

        // See all QA jobs button should be displayed on the current page
        if (basePage.isDisplayed(qaJobsPage.cookieBanner)) {
            basePage.click(qaJobsPage.cookieOnlyNecessaryButton);
            basePage.isDisplayed(qaJobsPage.seeAllQAJobsButton);
        } else {
            basePage.isDisplayed(qaJobsPage.seeAllQAJobsButton);
        }

        // If the See all QA jobs button is clicked
        basePage.click(qaJobsPage.seeAllQAJobsButton);

        // The current URL address value should be displayed as equal to the {OPEN_POSITION_URL} url value
        Assert.assertEquals(basePage.getUrlAdress(),
                FileReaderManager.getInstance().getConfigReader().getKeyValue("INSIDER_URL") +
                        FileReaderManager.getInstance().getConfigReader().getKeyValue("OPEN_POSITION_URL"));
    }

    private void applyJobFilters() throws InterruptedException {
        // The current title value should be equal to InsiderOpenPosition
        Assert.assertEquals(openPositionPage.openPositionPageTitle,basePage.getTitle());

        // Filter by Location, Filter by Department select field should be displayed on the screen
        basePage.isDisplayed(openPositionPage.filterByLocationSelect);
        basePage.isDisplayed(openPositionPage.filterByDepartmentSelect);

        // Filter by Location select field {Istanbul, Turkey} value is selected
        for (int num = 0; num < 5; num++) {
            if (basePage.SleepLoopBoolean(openPositionPage.departmentFilterSelected, 3)) {
                System.out.println("SleepLoopBoolean tarafında çözüm sağlandı ! Filter by Location select alani aktif hale geldi.");
                basePage.setSelectedText(openPositionPage.filterByLocationSelect,
                        FileReaderManager.getInstance().getConfigReader().getKeyValue("Open_Position_1_Location"));
                break;
            } else {
                basePage.setSelectedText(openPositionPage.filterByLocationSelect,
                        FileReaderManager.getInstance().getConfigReader().getKeyValue("Open_Position_1_Location"));
            }
        }

        // If {Quality Assurance} value is selected in the Filter by Department select field
        basePage.setSelectedText(openPositionPage.filterByDepartmentSelect,
                FileReaderManager.getInstance().getConfigReader().getKeyValue("Open_Position_1_Department"));
    }

    private void verifyJobListings() {

        //The open positions listed in the Browse Open Positions field should be displayed.
        basePage.scrollToElement(openPositionPage.openPositionFirstItemDepartmentText);
        Assert.assertTrue(basePage.isDisplayed(openPositionPage.openPositionList), "Açık pozisyon listesi görüntülenemedi!");
        int jobCount = basePage.getElementsCount(openPositionPage.openPositionListOgeler);
        Assert.assertTrue(jobCount > 0, "Açık pozisyon listesinde ilan yok!");
        System.out.println("Toplam ilan sayısı: " + jobCount);

        // Position department verification
        verifyDepartmentAndLocation();
    }

    private void verifyDepartmentAndLocation() {
        //The position department text value of the listed open positions should be displayed as {Quality Assurance}
        basePage.scrollToElement(openPositionPage.openPositionFirstItemDepartmentText);
        List<WebElement> departments = basePage.getElements(openPositionPage.openPositionDepartmentText);
        for (WebElement department : departments) {
            String departmentText = department.getText();
            System.out.println("Department: " + departmentText);
            Assert.assertEquals(departmentText, FileReaderManager.getInstance().getConfigReader().getKeyValue("Open_Position_1_Department"), "Hata! Departman bilgisi yanlış: " + departmentText);
        }

        //The position location text value of the listed open positions should be displayed as {Istanbul, Turkey}
        List<WebElement> locations = basePage.getElements(openPositionPage.openPositionLocationText);
        for (WebElement location : locations) {
            String locationText = location.getText();
            System.out.println("Location: " + locationText);
            Assert.assertEquals(locationText, FileReaderManager.getInstance().getConfigReader().getKeyValue("Open_Position_1_Location"), "Hata! Departman bilgisi yanlış: " + locationText);
        }
    }

    private void verifyJobApplicationProcess() {
        //The View Role button should be displayed on the listed open positions.
        basePage.click(openPositionPage.firstopenpositionItem);
        basePage.isDisplayed(openPositionPage.viewRoleButton);

        //If the Wiev role button is clicked
        basePage.click(openPositionPage.viewRoleButton);

        // After the click action, a new tab opens. Switch to the newly opened tab.
        basePage.changeWindow();

        // The current base URL value should be verified as equal to the {LeverApplicationFormPage} URL value.
        String baseUrl = FileReaderManager.getInstance().getConfigReader().getKeyValue("LEVER_APPLICATION_FORM_BASE_URL");
        String currentUrl = basePage.getUrlAdress();
        Assert.assertTrue(currentUrl.startsWith(baseUrl),
                "Error! Page URL does not start with the expected baseURL. Expected: " + baseUrl + " - Actual: " + currentUrl);

        // The "Apply for this job" button should be visible on the page.
        basePage.isDisplayed(leverApplicationFormPage.applyForThisJobButton);
    }

}


