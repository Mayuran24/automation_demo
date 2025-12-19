package pages;

import base.BasePage;
import helper.HelperClass;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({AllureTestNg.class})
public class LoginPage extends BasePage {
    private HelperClass helper;

    @FindBy(xpath = "//h5[text() = 'Login']")
    private WebElement loginPageTitle;

    @FindBy(name = "username")
    private WebElement userNameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement loginBtn;

    @FindBy(xpath = "//h6[text()= 'Dashboard']")
    private WebElement userDashboardPage;

    public LoginPage(WebDriver driver) {
        super(driver, LoginPage.class);
        PageFactory.initElements(driver, this);
        helper = new HelperClass(driver);
    }

    @Step("Verify the user landed on the correct login page")
    public void verifyLoginPage(SoftAssert softAssert) {
        helper.waitingTime(30, loginPageTitle);
        logger.info("Verifying the {}", loginPageTitle.getText());
        softAssert.assertEquals(loginPageTitle.getText(), "Login", "Your are in wrong page");
        softAssert.assertAll();
    }

    @Step("User enter the value for the user name field {username}")
    public void typeIntoUserNameField(String username) {
        logger.info("Entering the username: {}", username);
        typeIntoTheTextField(userNameField, username);
    }

    @Step("User enter the value for the password field {password}")
    public void typeIntoPasswordField(String password) {
        logger.info("Entering the password: {}", password);
        typeIntoTheTextField(passwordField, password);
    }

    @Step("User clicked on the login button")
    public void clickLoginBtn() {
        logger.info("Clicking on login button");
        clickOnButton(loginBtn, "Login Button");
    }

    @Step("Verify the user logged into the system and navigates to the default Dashboard page test")
    public UserDashboardPage verifyUserDashboard(WebDriver driver, SoftAssert softAssert) {
        helper.waitingTime(10, userDashboardPage);
        logger.info("Verifying on the : {}", "DashboardPage");
        softAssert.assertEquals(userDashboardPage.getText(), "Dashboard", "Your are in wrong pageYour Login Attempt not Success");
        softAssert.assertAll();
        return new UserDashboardPage(driver);
    }
}
