package listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testcases.BaseTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    private static final String SCREENSHOT_DIR = "src/test/java/ScreenshotFail/";

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {  // Sadece BaseTest üzerinden çalıştır
            WebDriver driver = null;
            try {
                driver = ((BaseTest) testClass).testContext.getWebDriverManager().getDriver();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (driver != null) {
                takeScreenshot(driver, result.getName());
            }
        }
    }

    public void takeScreenshot(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotPath = SCREENSHOT_DIR + testName + "_" + timestamp + ".png";

            // Eğer ScreenshotFail klasörü mevcut değilse oluşturmaz, sadece var olan klasöre kayıt yapar
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
