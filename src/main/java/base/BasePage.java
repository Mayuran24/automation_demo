package base;

import helper.AllureListener;
import helper.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage extends HelperClass {
    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnButton(WebElement element, String buttonName) {
        try {
            waitingTimeWithWebElement(5, element);
            element.click();
        } catch (NoSuchElementException e) {
            AllureListener.takeScreenshot(driver); // attach screenshot
            throw new AssertionError(buttonName + " not found to click: " + element.toString());
        }
    }

    public void typeIntoTheTextField(WebElement element, String text) {
        try {
            waitingTimeWithWebElement(10, element);
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
        waitingTimeImpl();
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

    public void selectFromDropdown(WebElement dropdownElement, By optionsLocator, String optionText) {
        try {
            waitingTimeWithWebElement(10, dropdownElement);
            dropdownElement.click();    // Expand dropdown

            List<WebElement> options = driver.findElements(optionsLocator);

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

    public void clickOnItemFromList(List<WebElement> element, String optionText) {
        try {
            waitingTimeImpl();
            for (WebElement option : element) {
                if (option.getText().trim().equals(optionText)) {
                    option.click();
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
