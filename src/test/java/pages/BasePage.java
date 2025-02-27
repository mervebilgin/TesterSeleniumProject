package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    WebDriver driver;
    Select slc = null;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String getUrlAdress(){
        return driver.getCurrentUrl();
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public WebElement find(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void click(By locator){
        WebElement element = find(locator);
        highlightElement(element, "red");
        element.click();
    }

    public Boolean isDisplayed(By locator){
        WebElement element = find(locator);
        boolean displayed = element.isDisplayed();
        if (displayed) {
            highlightElement(element, "green");
        }
        return displayed;
    }

    private void highlightElement(WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid " + color + "'", element);
    }

    public void setSelectedText(By locator, String value){
        slc = new Select(find(locator));
        slc.selectByVisibleText(value);
        slc = null;
    }

    public int findSize(By locator){
        return driver.findElements(locator).size();
    }

    public boolean SleepLoopBoolean(By Locator, int sleep) throws InterruptedException {
        boolean donus = false;
        for (int i=0; i<sleep; i++){
            System.out.println("Loop : " + i);
            if (findSize(Locator) != 0){
                donus=true;
                break;
            }
            Thread.sleep(1000);
        }
        return donus;
    }

    public int getElementsCount(By locator) {
        return driver.findElements(locator).size();
    }

    public void changeWindow() {
        Set<String> WindowAllWindows = driver.getWindowHandles();
        for (String window: WindowAllWindows) {
            driver.switchTo().window(window);
        }
    }

    public void scrollToElement(By locator) {
        try {
            WebElement element = find(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Scroll işlemi başarısız oldu: " + e.getMessage());
        }
    }
}