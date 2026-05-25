package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import pages.LoginPage;
import pages.RegisterPage;

public class RegisterSteps {

    private static final Logger log = LoggerFactory.getLogger(RegisterSteps.class);
    private final LoginPage loginPage = new LoginPage();
    private final RegisterPage registerPage = new RegisterPage();

    @And("utilizatorul acceseaza formularul de inregistrare")
    public void accessRegisterForm() {
        log.info("Apelare metodă click link register din RegisterPage POM");
        registerPage.clickRegisterLink();
    }

    @When("completeaza formularul de inregistrare:")
    public void fillRegisterForm(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String fName = data.get(0).get("First Name");
        String lName = data.get(0).get("Last Name");
        String emailOriginal = data.get(0).get("Email");
        String pass = data.get(0).get("Password");

        String uniqueEmail = emailOriginal.replace("@", "_" + System.currentTimeMillis() + "@");
        log.info("Trimitere date către formularul POM. Email unic: {}", uniqueEmail);

        registerPage.fillAndSubmitRegisterForm(fName, lName, uniqueEmail, pass);
    }

    @Then("contul este creat cu succes")
    public void verifyRegistration() {
        log.info("Apelare validare succes din RegisterPage");
        registerPage.verifyRegistrationSuccess();
    }
}
