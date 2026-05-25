package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.ByteArrayInputStream;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Hooks {

    @Before
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://apps.qualiadept.eu/hapifyme";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Extragere screenshot brut și injectare nativă în Allure
            byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot_Eroare_Allure", new ByteArrayInputStream(screenshot));
        }
        closeWebDriver();
    }
}
