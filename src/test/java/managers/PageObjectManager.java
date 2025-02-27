package managers;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageObjectManager {
    private WebDriver driver;


    // BASE PAGE LİST
    private BasePage basePage;
    private P01_HomePage homePage;
    private P02_CareersPage careersPage;
    private P03_QAJobsPage qaJobsPage;
    private P04_OpenPositionPage openPositionPage;
    private P05_LeverApplicationFormPage leverApplicationFormPage;


    // BASE PAGE LİST
    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage getBasePage(){
        return (basePage == null) ? basePage = new BasePage(driver) : basePage;
    }

    public P01_HomePage getHomePage() {
        return (homePage == null) ? homePage = new P01_HomePage(driver) : homePage;
    }

    public P02_CareersPage getCareersPage() {
        return (careersPage == null) ? careersPage = new P02_CareersPage(driver) : careersPage;
    }

    public P03_QAJobsPage getQaJobsPage() {
        return (qaJobsPage == null) ? qaJobsPage = new P03_QAJobsPage(driver) : qaJobsPage;
    }

    public P04_OpenPositionPage getOpenPositionPage() {
        return (openPositionPage == null) ? openPositionPage = new P04_OpenPositionPage(driver) : openPositionPage;
    }

    public P05_LeverApplicationFormPage getLeverApplicationFormPage() {
        return (leverApplicationFormPage == null) ? leverApplicationFormPage = new P05_LeverApplicationFormPage(driver) : leverApplicationFormPage;
    }
}