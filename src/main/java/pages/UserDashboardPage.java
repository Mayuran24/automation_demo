package pages;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import pages.AdminUserManagementPage;
import base.BasePage;
import helper.HelperClass;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;
@Listeners({AllureTestNg.class})
public class UserDashboardPage extends BasePage {
    private HelperClass helper;
    public UserDashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        helper = new HelperClass(driver);
    }

    private static Logger logger = org.apache.logging.log4j.LogManager.getLogger(UserDashboardPage.class);
    @FindBy(xpath = "//div[@class='orangehrm-dashboard-widget-name']//p[@class='oxd-text oxd-text--p']")
    private List<WebElement> dashboardWidgetTitles;

    @FindBy(xpath = "//span[@class='oxd-topbar-header-breadcrumb']//h6[text()='Admin']")
    private WebElement adminPageHeader;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']")
    private List<WebElement> mainMenuItems;

    @Step("Verify all expected dashboard widget titles are present")
    public void verifyTitlesPresent() {
        logger.info("Verifying dashboard widget titles");
        helper.waitingTime();
        List<String> expectedTitles = Arrays.asList(
                "Quick Launch",
                "Employee Distribution by Sub Unit",
                "Employees on Leave Today",
                "Time at Work",
                "My Actions",
                "Buzz Latest Posts",
                "Employee Distribution by Location"
        );
        verifyListOfElementsText(dashboardWidgetTitles, expectedTitles, "Title not found!");
    }

    @Step("Verify the Admin User Management page")
    public AdminUserManagementPage goToAdminUserManagementPage() {
        logger.info("Navigating to Admin User Management Page");
        clickOnItemFromList(mainMenuItems, "Admin");
        verifyTheElementPresent(adminPageHeader);
        return new AdminUserManagementPage(driver);
    }
}
