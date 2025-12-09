package helper;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.testng.TestListenerAdapter;

public class AllureListener extends TestListenerAdapter {
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Scroll to element and take screenshot
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] scrollToElementAndCapture(WebDriver driver, WebElement element) {
        if (driver == null || element == null) return new byte[0];
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(300);
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
