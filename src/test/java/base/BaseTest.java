package base;

import helper.AllureListener;
import helper.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({AllureListener.class})
public abstract class BaseTest {
    //protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String browser;

    private static String baseUrl;

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    @BeforeClass(alwaysRun = true)
    public static void setUp() {
        browser = ConfigReader.getProperty("browser").toLowerCase();
        baseUrl = ConfigReader.getProperty("baseUrl");

        if (browser == null) browser = "chrome";
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                //driver = new ChromeDriver();
                setDriver(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                //driver = new FirefoxDriver();
                setDriver(new FirefoxDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                //driver = new EdgeDriver();
                setDriver(new EdgeDriver());
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();

        getDriver().get(baseUrl);
        logger.info("Navigated to base URL: " + baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public static void captureFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test Failed: " + result.getName());
            AllureListener.takeScreenshot(getDriver());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            logger.info("Closed browser");
        }
    }
}
