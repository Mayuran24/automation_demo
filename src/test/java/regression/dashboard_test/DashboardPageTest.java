package regression.dashboard_test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import helper.ConfigReader;
import pages.LoginPage;
import pages.UserDashboardPage;

public class DashboardPageTest extends BaseTest {

    private LoginPage loginPage;
    private UserDashboardPage userDashboardPage;
    //private ArrayList userCredentials;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPage = new LoginPage(getDriver());
        userDashboardPage = new UserDashboardPage(getDriver());
        //userCredentials = BaseAPI.createUser();
    }

    @Test(groups = {"regression"})
    public void testUserDashboardPage() {
        loginPage.typeIntoUserNameField(ConfigReader.getProperty("username"));
        loginPage.typeIntoPasswordField(ConfigReader.getProperty("password"));
//        loginPage.typeIntoUserNameField(userCredentials.get(0).toString());
//        loginPage.typeIntoPasswordField(userCredentials.get(1).toString());
        loginPage.clickLoginBtn();
        userDashboardPage.verifyTitlesPresent();
        loginPage.clickLogoutBtn();
    }
}
