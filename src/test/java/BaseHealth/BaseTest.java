package BaseHealth;

import environment.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {

    protected Logger log;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        log = LogManager.getLogger(method.getDeclaringClass());
        log.info("=========== STARTING TEST: " + method.getName() + " ===========");
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("❌ Test Failed: " + result.getName());

            // Take screenshot & attach to Allure
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            log.info("✅ Test Passed: " + result.getName());
        } else {
            log.warn("⚠️ Test Skipped: " + result.getName());
        }

        DriverFactory.quitDriver();
        log.info("=========== END TEST: " + result.getName() + " ===========\n");
    }

    @AfterSuite
    public void attachLogFileToAllure() throws IOException {
        Allure.addAttachment("Log Output", new FileInputStream("logs/execution.log"));
    }
}
