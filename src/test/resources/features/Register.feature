Feature: Inregistrare Utilizator

  Scenario: Inregistrare cu succes
    Given utilizatorul deschide pagina de login "https://apps.qualiadept.eu/hapifyme/login_register.php"
    And utilizatorul acceseaza formularul de inregistrare
    When completeaza formularul de inregistrare:

      | First Name | Last Name | Email        | Password |
      | Ion        | Popescu   | ion@test.com | Pass@123 |
    Then contul este creat cu succes


  Scenario: Test esuat intentionat pentru Allure Report Screenshot
    Given utilizatorul deschide pagina de login "https://apps.qualiadept.eu/hapifyme"
    Then fortam esuarea testului verificand un mesaj inexistent