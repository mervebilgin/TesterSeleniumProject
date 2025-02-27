package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_OpenPositionPage {

    public P04_OpenPositionPage(WebDriver driver) {
        //PageFactory.initElements(driver, this);
    }

    public String openPositionPageTitle = "Insider Open Positions | Insider";
    public By filterByLocationSelect = By.xpath("//select[@id='filter-by-location']");
    public By filterByDepartmentSelect = By.xpath("//select[@id='filter-by-department']");
    public By openPositionList = By.xpath("//div[@id='jobs-list']");
    public By openPositionListOgeler = By.xpath("//div[@id='jobs-list']/div[contains(@class, 'position-list-item')]");
    public By openPositionDepartmentText = By.xpath("//div[@id='jobs-list']//span[contains(@class, 'position-department')]");
    public By departmentFilterSelected = By.xpath("//span[@id='select2-filter-by-department-container' and text()='Quality Assurance']");
    public By openPositionFirstItemDepartmentText = By.xpath("(//span[text()='Quality Assurance'])[2]");
    public By openPositionLocationText = By.xpath("//div[@id='jobs-list']/div[contains(@class, 'position-list-item')]//div[@class='position-location text-large']");
    public By firstopenpositionItem = By.xpath("(//div[@class='position-list-item-wrapper bg-light'])[1]");
    public By viewRoleButton = By.xpath("(//a[contains(@class, 'btn-navy') and contains(text(), 'View Role')])[1]");
}
