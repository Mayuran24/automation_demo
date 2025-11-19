package dashboard_test;

import base.BaseTest;
import helper.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserDashboardPage;

public class DashboardPageTest extends BaseTest {

    private  LoginPage loginPage;
    private UserDashboardPage userDashboardPage;
    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
        userDashboardPage = new UserDashboardPage(driver);
    }
    @Test
    public void testUserDashboardPage(){
        loginPage.typeIntoUserNameField(ConfigReader.getProperty("username"));
        loginPage.typeIntoPasswordField(ConfigReader.getProperty("password"));
        loginPage.clickLoginBtn();
        userDashboardPage.verifyTitlesPresent();
        loginPage.clickLogoutBtn();
    }
}
