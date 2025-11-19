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
import org.testng.annotations.*;

@Listeners({AllureListener.class})
public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public void setUp(){
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        String baseUrl = ConfigReader.getProperty("baseUrl");
        if(browser == null) browser = "chrome";
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(); break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(); break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(); break;
            default: throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(baseUrl);
        logger.info("Opened {} browser with URL: {}", browser, baseUrl);
    }

    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
            logger.info("Closed browser");
        }
    }
}
