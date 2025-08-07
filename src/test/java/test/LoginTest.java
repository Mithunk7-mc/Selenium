package test;

import BaseHealth.BaseTest;
import environment.DriverFactory;
import Pages.LoginPage;
import utils.JsonUtils;
import org.testng.annotations.Test;
import io.qameta.allure.*;

@Epic("SwagLabs")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(description = "Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginSwagLabs() {
        // Read test data from JSON file
        String baseUrl =JsonUtils.get("environment","baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new RuntimeException("Base URL from JSON is null or empty!");
        }
        DriverFactory.getDriver().get(baseUrl);
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        // Read test data from JSON file
        String username = JsonUtils.get("validLogin", "username");
        String password = JsonUtils.get("validLogin", "password");

        loginPage.login(username, password);
    }
}
