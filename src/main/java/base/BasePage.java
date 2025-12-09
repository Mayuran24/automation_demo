package base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import helper.AllureListener;
import ui.helper.HelperClass;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    private HelperClass helper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        helper = new HelperClass(driver);
    }

    public void clickOnButton(WebElement element, String buttonName) {
        try {
            helper.waitingTime(5, element);
            element.click();
        } catch (NoSuchElementException e) {
            AllureListener.takeScreenshot(driver); // attach screenshot
            throw new AssertionError(buttonName + " not found to click: " + element.toString());
        }
    }

    public void clickWithRetry(By locator, int retryCount) {
        while (retryCount > 0) {
            try {
                driver.findElement(locator).click();
                break;
            } catch (Exception e) {
                retryCount--;
                if (retryCount == 0) {
                    throw e;
                }
            }
        }
    }

    public void clickWithWaitingTime(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        WebElement element = wait.until(driver -> driver.findElement(locator));
        element.click();
    }

    public void typeIntoTheTextField(WebElement element, String text) {
        try {
            helper.waitingTime(10, element);
            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            AllureListener.takeScreenshot(driver); // attach screenshot
            throw new AssertionError("Element not found to type: " + element.toString());
        }
    }

    public void verifyElementText(WebElement element, String expectedText) {
        String actualText = element.getText();
        if (!actualText.equals(expectedText)) {
            AllureListener.scrollToElementAndCapture(driver, element); // attach screenshot
            throw new AssertionError("Text mismatch! Expected: " + expectedText + ", but found: " + actualText);
        }
    }

    public void verifyTheElementPresent(WebElement element) {
        helper.waitingTime();
        if (!element.isDisplayed()) {
            AllureListener.takeScreenshot(driver); // attach screenshot
            throw new AssertionError("Element is not displayed: " + element.toString());
        }
    }

    public void verifyListOfElementsText(List<WebElement> elementsLocator, List<String> expectedTexts, String customErrorMessage) {
        List<WebElement> elements = elementsLocator;
        for (WebElement element : elements) {
            String text = element.getText();
            if (!expectedTexts.contains(text)) {
                AllureListener.scrollToElementAndCapture(driver, element); // attach screenshot
                throw new AssertionError(customErrorMessage + " : " + text);
            }
        }
    }

    public void selectFromDropdown(WebElement dropdownElement, List<WebElement> options, String optionText) {
        try {
            helper.waitingTime(10, dropdownElement);
            dropdownElement.click();    // Expand dropdown
            for (WebElement option : options) {
                if (option.getText().trim().equals(optionText)) {
                    option.click();
                    return;
                }
            }

            AllureListener.takeScreenshot(driver);
            throw new AssertionError("Option '" + optionText + "' not found.");
        } catch (Exception e) {
            AllureListener.takeScreenshot(driver);
            throw new RuntimeException("Dropdown selection failed: " + e.getMessage());
        }
    }

    public void clickOnItemFromList(List<WebElement> elements, String optionText) {
        try {
            helper.waitingTime();
            for (WebElement element : elements) {
                if (element.getText().trim().equals(optionText)) {
                    element.click();
                    return;
                }
            }
            AllureListener.takeScreenshot(driver);
            throw new AssertionError("Option '" + optionText + "' not found.");
        } catch (Exception e) {
            AllureListener.takeScreenshot(driver);
            throw new RuntimeException("Click from list failed: " + e.getMessage());
        }
    }
}
