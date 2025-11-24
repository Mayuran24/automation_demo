package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HelperClass {
    private WebDriverWait wait;
    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(HelperClass.class);

    public HelperClass(WebDriver driver){
        this.driver = driver;
    }

    public void waitingTimeWithWebElement(int timeInSeconds, WebElement locator){
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.pollingEvery(Duration.ofMillis(500))
            .until(ExpectedConditions.visibilityOf(locator));
        }catch (Exception e){
            logger.error("Exception in waitingTime: ", e);
        }
    }

    public void waitingTimeWithLocator(int timeInSeconds, By locator){
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.pollingEvery(Duration.ofMillis(500))
                    .until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        }catch (Exception e){
            logger.error("Exception in waitingTime: ", e);
        }
    }
    public void waitingTimeImpl(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Exception in waitingTimeImpl: ", e);
        }
    }
}
