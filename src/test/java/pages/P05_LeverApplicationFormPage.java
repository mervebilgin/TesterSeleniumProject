package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_LeverApplicationFormPage {
    public P05_LeverApplicationFormPage(WebDriver driver) {
        //PageFactory.initElements(driver, this);
    }

    public By applyForThisJobButton = By.xpath("//a[@class='postings-btn template-btn-submit shamrock']");
}
