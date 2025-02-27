package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_HomePage {

    public P01_HomePage(WebDriver driver) {
        //PageFactory.initElements(driver, this);
    }

    public By insiderLogo = By.xpath("//img[@alt='insider_logo']");
    public String homePageTitle = "#1 Leader in Individualized, Cross-Channel CX — Insider";
    public By navbarNavDropdown = By.xpath("//div[@id='navbarNavDropdown']");
    public By companyMenu = By.xpath("//a[@id='navbarDropdownMenuLink' and normalize-space()='Company']");
    public By aboutUs = By.xpath("//a[@href='https://useinsider.com/about-us/' and @class='dropdown-sub']");
    public By newsroom = By.xpath("//a[@href='https://useinsider.com/newsroom/' and @class='dropdown-sub']");
    public By newsroomFailxpath = By.xpath("//a[@href='https://useinsider.com/newsroom/merve'");
    public By partnership = By.xpath("//a[@href='https://useinsider.com/partner-with-insider/' and @class='dropdown-sub']");
    public By integrations = By.xpath("//a[@href='https://useinsider.com/integration-hub/' and @class='dropdown-sub']");
    public By careers = By.xpath("//a[@href='https://useinsider.com/careers/' and @class='dropdown-sub']");
    public By contactUs = By.xpath("//a[@href='https://useinsider.com/contact-us/' and @class='dropdown-sub']\n");

}
