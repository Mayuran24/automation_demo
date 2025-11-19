package admin_management;

import base.BaseTest;
import helper.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminUserManagementPage;
import pages.LoginPage;
import pages.UserDashboardPage;

public class AdminManagementPageTest extends BaseTest {
    private AdminUserManagementPage adminManagementPage;
    private LoginPage loginPage;
    private UserDashboardPage userDashboardPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
        userDashboardPage = new UserDashboardPage(driver);
        adminManagementPage = new AdminUserManagementPage(driver);
    }

    @Test
    public void testAdminManagementPage() {
        loginPage.typeIntoUserNameField(ConfigReader.getProperty("username"));
        loginPage.typeIntoPasswordField(ConfigReader.getProperty("password"));
        loginPage.clickLoginBtn();
        userDashboardPage.goToAdminUserManagementPage();
        adminManagementPage.clickOnTopNavigationItem("Nationalities");
        loginPage.clickLogoutBtn();
    }
}
