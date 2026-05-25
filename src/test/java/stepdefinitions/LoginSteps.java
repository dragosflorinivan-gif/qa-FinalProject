package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
import pages.DashboardPage;
import static com.codeborne.selenide.Selenide.open;

public class LoginSteps {

    private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);
    private final LoginPage loginPage = new LoginPage();
    private final DashboardPage dashboardPage = new DashboardPage();

    @Given("utilizatorul deschide pagina de login")
    public void utilizatorulDeschidePaginaDeLogin() {
        log.info("Deschidere URL de bază prin POM");
        loginPage.openPage();
    }

    @Given("utilizatorul deschide pagina de login {string}")
    public void openLoginPage(String url) {
        log.info("Deschidere URL direct: {}", url);
        loginPage.openPageWithUrl(url);
    }

    @When("utilizatorul se logheaza cu email {string} si parola {string}")
    public void utilizatorulSeLogheazaCuEmailSiParola(String email, String parola) {
        log.info("Executare metodă încapsulată de login pentru: {}", email);
        loginPage.login(email, parola);
    }

    @Then("utilizatorul ajunge in pagina principala")
    public void utilizatorulAjungeInPaginaPrincipala() {
        log.info("Apelare metodă de aserțiune din DashboardPage");
        dashboardPage.verifyDashboardLoaded();
    }

    @Then("utilizatorul vede ca nu ajunge in pagina principala")
    public void utilizatorulVedeCaNuAjungeInPaginaPrincipala() {
        String currentUrl = WebDriverRunner.url();
        log.info("Validare eșec autentificare la nivel de URL: {}", currentUrl);
        Assert.assertTrue(currentUrl.contains("login") || !currentUrl.contains("page=feed"));
    }

    @And("pagina de login arunca o eroare relevanta")
    public void paginaDeLoginAruncaOEroareRelevanta() {
        log.info("Apelare metodă aserțiune eroare din LoginPage");
        loginPage.verifyErrorMessage();
    }

    @Then("fortam esuarea testului verificand un mesaj inexistent")
    public void fortamEsuareaTestului() {
        log.warn("Se execută pasul de eșec controlat pentru Allure Report...");
        loginPage.verifyIntentionallyFailedText();
    }
}
