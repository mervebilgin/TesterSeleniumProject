package managers;

import enums.DriverType;
import enums.EnvironmentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.HashMap;

public class WebDriverManager {
    private WebDriver driver;
    private ChromeOptions options;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() throws InterruptedException {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() throws InterruptedException {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    public String getChromeVersion() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("reg query " + "HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon " +  "/v version");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            String s;
            int sayac = 0;
            String version = null;

            while ((s = stdInput.readLine()) != null) {
                sayac++;
                if (sayac == 3) {
                    version = s.substring(25, 28);
                    System.out.println("Chrome version = " + version);
                    break;
                }
            }

            while ((s = stdError.readLine()) != null) {
                System.out.println("Chrome versiyon alınırken hata alındı!" + s);
            }

            return version;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private WebDriver createLocalDriver() throws InterruptedException {

        String myString = getChromeVersion();

        switch (driverType) {
            case FIREFOX : driver = new FirefoxDriver();
                break;
            case CHROME :
                setChromeOption();
                driverCreateLoop(5,6);
                break;
            case EDGE :
                driver = new EdgeDriver();
                break;
            case EXPLORER :
                driver = new InternetExplorerDriver();
                break;
        }

        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FileReaderManager.getInstance().getConfigReader().getImplicitWait()));
        return driver;
    }

    public void setChromeOption(){
        String downloadFilepath = System.getProperty("user.dir") + FileReaderManager.getInstance().getConfigReader().getKeyValue("downloadFilePath");
        System.out.println("Dosya Indirme Yolu Bilgilendirme : " + downloadFilepath);
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
    }

    public void driverCreateLoop(int repeat, int sleep) throws InterruptedException {

        Boolean durum = true;

        for (int i=1; i <= repeat; i++){
            try
            {
                durum =true;
                driver = new ChromeDriver(options);
            }
            catch(Exception e)
            {
                System.out.println("DENEME - " + i);
                closeDriver();

                durum = false;
                Thread.sleep(sleep * 1000);
                continue;
            }
            break;
        }

        if (!durum){
            System.out.println("TUM DENEMELER HATA ALDI");
            driver = new ChromeDriver(options);
        }
    }

    public void closeDriver() {
        if (driver == null) {
            System.out.println("DRIVER - NULL");
        }
        else {
            driver.manage().deleteAllCookies();
            //driver.close();
            driver.quit(); }
    }
}
