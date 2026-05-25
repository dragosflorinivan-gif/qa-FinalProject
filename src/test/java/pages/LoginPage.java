package pages;

import org.openqa.selenium.By;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final By usernameField = By.cssSelector("input[type='email'], input[name*='email'], input[name*='user']");
    private final By passwordField = By.cssSelector("input[type='password'], input[name*='password']");
    private final By loginButton = By.cssSelector("input[type='submit'], button[type='submit'], input[name*='login']");

    public void openPage() {
        open("/");
    }

    public void openPageWithUrl(String url) {
        open(url);
    }

    public void login(String email, String password) {
        $(usernameField).shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(email);
        $(passwordField).shouldBe(Condition.visible).setValue(password);
        $(loginButton).shouldBe(Condition.visible).click();
    }

    public void verifyErrorMessage() {
        // CORECTAT: Mutat aserțiunea din LoginSteps în Page Object
        $("body").shouldHave(Condition.or("Eroare text", Condition.text("Email or password was incorrect"), Condition.text("Invalid")));
    }

    public void verifyIntentionallyFailedText() {
        // Element fictiv menit să pice garantat pentru a fi captat în Allure Report
        $(Selectors.byText("Acest text nu exista pe site-ul hapifyMe!")).shouldBe(Condition.visible);
    }
}
