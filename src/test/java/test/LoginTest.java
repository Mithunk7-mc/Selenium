package test;

import BaseHealth.BaseTest;
import environment.DriverFactory;
import Pages.LoginPage;
import data.TestData;
import org.testng.annotations.Test;
import io.qameta.allure.*;

@Epic("SwagLabs")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(description = "Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginSwagLabs() {
        DriverFactory.getDriver().get("https://www.saucedemo.com/v1/");
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);
    }
}
