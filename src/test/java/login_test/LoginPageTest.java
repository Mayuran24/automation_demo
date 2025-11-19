package login_test;

import base.BaseTest;
import helper.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }
    @Test
    public void testLoginPage(){
        loginPage.verifyLoginPage();
        loginPage.typeIntoUserNameField(ConfigReader.getProperty("username"));
        loginPage.typeIntoPasswordField(ConfigReader.getProperty("password"));
        loginPage.clickLoginBtn();
        loginPage.verifyUserDashboard(driver);
        loginPage.clickLogoutBtn();
    }
}
