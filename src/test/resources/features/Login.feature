Feature: Login

  Scenario: Login succes
    Given utilizatorul deschide pagina de login
    When utilizatorul se logheaza cu email "dragosflorin.ivan@gmail.com" si parola "Pass123!!"
    Then utilizatorul ajunge in pagina principala

  Scenario Outline: Login esuat
    Given utilizatorul deschide pagina de login
    When utilizatorul se logheaza cu email "<email>" si parola "<parola>"
    Then utilizatorul vede ca nu ajunge in pagina principala
    And pagina de login arunca o eroare relevanta

    Examples:

      | email                    | parola            |
      | george.datcu@hotmail.com | ParolaMeaInvalida |
      | invalid@email.com        | ParolaMeaSecreta  |
