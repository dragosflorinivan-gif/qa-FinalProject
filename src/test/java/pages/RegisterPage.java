package pages;

import org.openqa.selenium.By;
import com.codeborne.selenide.Condition;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

    // CORECTAT: Toți locatorii din RegisterSteps au fost mutați aici conform bazei arhitecturale POM
    private final By registerLink = By.id("signup");
    private final By registerFormContainer = By.id("second");
    private final By firstNameInput = By.cssSelector("#second input[name='reg_fname']");
    private final By lastNameInput = By.cssSelector("#second input[name='reg_lname']");
    private final By regEmailInput = By.cssSelector("#second input[name='reg_email']");
    private final By regEmailConfirmInput = By.cssSelector("#second input[name='reg_email2']");
    private final By regPasswordInput = By.cssSelector("#second input[name='reg_password']");
    private final By regPasswordConfirmInput = By.cssSelector("#second input[name='reg_password2']");
    private final By registerButton = By.cssSelector("#second input[name='register_button']");

    public void clickRegisterLink() {
        $(registerLink).shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        $(registerFormContainer).shouldBe(Condition.visible);
    }

    public void fillAndSubmitRegisterForm(String fName, String lName, String email, String password) {
        $(firstNameInput).shouldBe(Condition.visible).setValue(fName);
        $(lastNameInput).setValue(lName);
        $(regEmailInput).setValue(email);
        if ($(regEmailConfirmInput).exists()) {
            $(regEmailConfirmInput).setValue(email);
        }
        $(regPasswordInput).setValue(password);
        if ($(regPasswordConfirmInput).exists()) {
            $(regPasswordConfirmInput).setValue(password);
        }
        $(registerButton).shouldBe(Condition.visible).scrollTo().click();
    }

    public void verifyRegistrationSuccess() {
        // CORECTAT: Mutat aserțiunea din RegisterSteps în Page Object
        $("body").shouldHave(Condition.text("You're all set!"));
    }
}
