package pages;

import base.BasePage;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminUserManagementPage extends BasePage {
    public AdminUserManagementPage(WebDriver driver) {
        super(driver);
    }
    private static final Logger logger = LoggerFactory.getLogger(AdminUserManagementPage.class);
}
