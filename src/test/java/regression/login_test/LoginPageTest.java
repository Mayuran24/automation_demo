package regression.login_test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.BaseTest;
import helper.ConfigReader;
import pages.LoginPage;
import pages.UserDashboardPage;

public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;
    private UserDashboardPage userDashboardPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPage = new LoginPage(getDriver());
        userDashboardPage = new UserDashboardPage(getDriver());
    }

    @Test(groups = {"regression"})
    public void testLoginPage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.verifyLoginPage(softAssert);
        loginPage.typeIntoUserNameField(ConfigReader.getProperty("username"));
        loginPage.typeIntoPasswordField(ConfigReader.getProperty("password"));
        loginPage.clickLoginBtn();
        loginPage.verifyUserDashboard(getDriver(), softAssert);
        userDashboardPage.clickLogoutBtn();
    }
}
