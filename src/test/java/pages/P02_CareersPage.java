package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_CareersPage {

    public P02_CareersPage(WebDriver driver) {
        //PageFactory.initElements(driver, this);
    }

    public String careersPageTitle = "Ready to disrupt? | Insider Careers";
    public By ourLocationBlock = By.xpath("//section[@id='career-our-location']");
    public By teamsBlock = By.xpath("//section[@id='career-find-our-calling']");
    public By lifeAtInsiderBlock = By.xpath("//section[contains(@class, 'elementor-section') and .//h2[text()='Life at Insider']]");
}