package regression.login_test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.BaseTest;
import helper.ConfigReader;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(groups = {"regression"})
    public void testLoginPage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.verifyLoginPage(softAssert);
        loginPage.typeIntoUserNameField(ConfigReader.getProperty("username"));
        loginPage.typeIntoPasswordField(ConfigReader.getProperty("password"));
        loginPage.clickLoginBtn();
        loginPage.verifyUserDashboard(getDriver(), softAssert);
        loginPage.clickLogoutBtn();
        softAssert.assertAll();
    }
}
