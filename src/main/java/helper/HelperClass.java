package ui.helper;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperClass {
    private WebDriverWait wait;
    protected WebDriver driver;
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(HelperClass.class);

    public HelperClass(WebDriver driver) {
        this.driver = driver;
    }

    public void waitingTime(int timeInSeconds, WebElement locator) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.pollingEvery(Duration.ofMillis(500))
                    .until(ExpectedConditions.visibilityOf(locator));
        } catch (Exception e) {
            logger.error("Exception in waitingTime: ", e);
        }
    }

    public void waitingTime(int timeInSeconds, By locator) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.pollingEvery(Duration.ofMillis(500))
                    .until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        } catch (Exception e) {
            logger.error("Exception in waitingTime: ", e);
        }
    }

    public void waitingTime() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Exception in waitingTimeImpl: ", e);
        }
    }
}
