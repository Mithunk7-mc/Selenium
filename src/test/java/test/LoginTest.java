package test;

import BaseHealth.BaseTest;
import environment.DriverFactory;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.qameta.allure.*;

@Epic("SwagLabs")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(description = "Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginSwagLabs() {
        DriverFactory.getDriver().get("https://www.saucedemo.com/v1/");
        DriverFactory.getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        DriverFactory.getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        DriverFactory.getDriver().findElement(By.id("login-button")).click();
    }
}

