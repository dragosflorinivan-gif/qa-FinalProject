package pages;

import org.openqa.selenium.By;
import com.codeborne.selenide.Condition;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    // CORECTAT: Mutat locatorii hardcodați din LoginSteps în pagină dedicată de Dashboard
    private final By postTextArea = By.cssSelector("textarea[name='post_text'], .posts_area");

    public void verifyDashboardLoaded() {
        $(postTextArea).shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}
