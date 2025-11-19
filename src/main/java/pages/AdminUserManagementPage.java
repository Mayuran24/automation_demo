package pages;

import base.BasePage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AdminUserManagementPage extends BasePage {
    public AdminUserManagementPage(WebDriver driver) {
        super(driver);
    }
    private static final Logger logger = LoggerFactory.getLogger(AdminUserManagementPage.class);
    @FindBy(xpath = "//li[contains(@class,'oxd-topbar-body-nav-tab')]//*[not(self::i)]")
    private List<WebElement> topNavigationList;

    @Step("Verifying top navigation list on Admin User Management Page")
    public void verifyTopNavigationList(){
        logger.info("Verifying top navigation list on Admin User Management Page");
        waitingTimeWithWebElement(10, topNavigationList.get(0));
        List<String> expectedNavItems = List.of("User Management", "Job", "Organization", "Qualifications", "Nationalities", "Corporate Branding" ,"Configuration");
        verifyListOfElementsText(topNavigationList, expectedNavItems, "Top navigation list items do not match expected values");
    }
    @Step("Clicking on top navigation item: {itemName}")
    public void clickOnTopNavigationItem(String itemName){
        logger.info("Clicking on top navigation item: {}", itemName);
        clickOnItemFromList(topNavigationList, itemName);
    }
}
