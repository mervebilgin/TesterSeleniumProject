package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_QAJobsPage {
    public P03_QAJobsPage(WebDriver driver) {
        //PageFactory.initElements(driver, this);
    }

    public String qaJobsPageTitle = "Insider quality assurance job opportunities";
    public By cookieBanner = By.xpath("//h5[@id='wt-cli-cookie-banner-title']");
    public By cookieOnlyNecessaryButton = By.xpath("//a[@id='wt-cli-accept-btn']");
    public By seeAllQAJobsButton = By.xpath("//a[contains(@href, 'careers/open-positions') and contains(text(), 'See all QA jobs')]");
}