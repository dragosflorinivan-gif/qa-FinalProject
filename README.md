# 🚀 Sistem Enterprise BDD Framework: hapifyMe Regression Suite

Suita finală de testare automatizată robustă, axată pe modulele critice de **Autentificare (Login)** și **Înregistrare (Register)** ale platformei **hapifyMe** (`https://apps.qualiadept.eu/hapifyme`). 
Proiectul este construit pe un model arhitectural 100% pur de tip **Page Object Model (POM)** cu management automat de logs și generare de rapoarte grafice avansate prin Allure.

---

## 🛠️ Stack Tehnologic și Componente Integrate
*   **Limbaj & Management dependințe:** Java 24 + Maven
*   **BDD Engine:** Cucumber JVM 7 + JUnit 4 Core Runner
*   **WebDriver Wrapper:** Selenide 6.19.1 (Asigură așteptări implicite asincrone)
*   **Logging Engine:** SLF4J Interface + Log4j2 Appenders (`log4j-slf4j2-impl`)
*   **Advanced Reports:** Allure Framework Cucumber Adaptor

---

## 📂 Structura Arhitecturală a Proiectului

Proiectul decuplează complet scenariile text de logica codului Java și elimină orice instanță de locator din pași:

```text
qa_FinalProject/
│
├── .github/workflows/
│   └── test.yml                  # Configurare Pipeline CI (GitHub Actions)
├── pom.xml                           # Definire dependințe proiect (Allure, Slf4j, Allure-Maven Plugin)
└── src/
    └── test/
        ├── java/
        │   ├── pages/                    # --- PAGE OBJECT MODEL (100% PUR) ---
        │   │   ├── LoginPage.java        # Capsulează elementele și aserțiunile formei de login
        │   │   ├── RegisterPage.java     # Mutat toți locatorii din RegisterSteps conform POM
        │   │   └── DashboardPage.java    # Mutat verificările din LoginSteps (Dashboard UI validation)
        │   │
        │   ├── stepdefinitions/          # --- GLUE CODE (ZERO LOCATORI / ZERO WEB ASERȚIUNI) ---
        │   │   ├── Hooks.java            # Setup de browser, timeout-uri și atașamente screenshot în Allure
        │   │   ├── LoginSteps.java       # Mapează pașii text pe metode logice POM din pagini
        │   │   └── RegisterSteps.java    # Mapează pașii text pe metode logice POM din pagini
        │   │
        │   └── runners/
        │       └── TestRunner.java       # Executor JUnit centralizat cu adaptor Allure
        │
        └── resources/
            ├── log4j2.xml            # Configurare profile de scriere logs (Console & File)
            └── features/                 # --- SCENARII GHERKIN ---
                ├── Login.feature         # Scenarii pozitive/negative (Scenario Outline)
                └── Register.feature      # Scenariu Register (DataTable) + Test de eșec controlat
```

---

## 💡 Bune Practici Arhitecturale și Corecții Implementate

1. **Eliminarea încălcării POM din RegisterSteps:** Toți locatorii nativi Selenium (`By.name`, `By.id`) folosiți în procesul de înregistrare au fost eliminați complet din clasa `RegisterSteps.java` și mutați în clasa dedicată `RegisterPage.java`. Pașii acum doar consumă metode logice încapsulate.
2. **Eliminarea Aserțiunilor Hardcodate din LoginSteps:** Liniile de verificare directă a interfeței prin aserțiuni rulate în interiorul pașilor (ex: `$(".posts_area").shouldBe(visible)`) au fost eliminate. Acestea au fost relocate sub formă de metode specifice în clasele `DashboardPage.java` (`verifyDashboardLoaded()`) și `LoginPage.java` (`verifyErrorMessage()`), lăsând clasele de Steps complet curate de orice legătură cu framework-ul de UI.
3. **Generare de Date Unice (Prevenire Data Pollution):** Înregistrarea folosește concatenarea timestamp-ului curent UNIX în interiorul adresei de email pentru a genera conturi unice la fiecare execuție automată din pipeline.
4. **Test de Eșec Intenționat (Allure Proof):** S-a introdus un scenariu secundar în `Register.feature` conceput să caute un text inexistent. Acest test forțează eșecul controlat, permițând metodei `@After` din `Hooks.java` să captureze starea ecranului ca octeți și să injecteze automat un screenshot de debug direct în interiorul raportului Allure.

---

## 🏃‍♂️ Instrucțiuni de Rulare Locală și Generare Raport Grafic

### Pasul 1: Execuția Testelor din Interfața Grafică Maven
Pentru a lansa suita local și a genera datele brute pentru Allure fără erori de terminal în Windows:
1. Priviște în marginea din **dreapta-sus** a ecranului IntelliJ IDEA și dă click pe tab-ul vertical **Maven** (pictograma cu un cerc și litera M).
2. În panoul lateral deschis, extinde structura proiectului: `qa_BDD_Cucumber` ➔ `Lifecycle`.
3. Ține apăsată tasta **`Ctrl`** din tastatură și execută dublu-click mai întâi pe opțiunea **`clean`**, apoi dublu-click pe opțiunea **`test`**.
4. Browserul Chrome se va deschide automat și va rula toate scenariile, populând folderul `target/allure-results`.

### Pasul 2: Vizualizarea Raportului Grafic Avansat Allure (Fără cod în Terminal)
Datorită pluginului `allure-maven` integrat în `pom.xml`, poți porni serverul de vizualizare dashboard direct din butoanele grafice ale IntelliJ IDEA:
1. În același panou lateral vertical din dreapta (**Maven**), extinde directorul numit **`Plugins`**.
2. Extinde subdirectorul numit **`allure`**.
3. Vei observa o listă de comenzi dedicate. Execută **dublu-click pe comanda `allure:serve`**.
4. În consola IntelliJ va apărea mesajul `Generating report...`, iar după câteva secunde se va deschide automat o filă de browser cu dashboard-ul interactiv Allure. 
5. În interiorul raportului, selectează testul picat intenționat pentru a vizualiza captura de ecran (**Screenshot**) atașată automat în momentul eșecului.

---

## ☁️ Pipeline Integrare Continuă (CI) - GitHub Actions

Proiectul conține un workflow complet funcțional de integrare continuă în fișierul `.github/workflows/test.yml`. La fiecare push sau pull request, GitHub instanțiază o mașină curată de Linux, activează cache-ul de Maven, instalează stabil Google Chrome și rulează testele în fundal (`Headless Mode`).

### 📸 Execute E2E Tests (Rosu)

Testele vor pica la fiecare push pe git intrucat text-ul ce trebuie verificat, nu exista pe hapifyMe.

Daca testul Failed controlat este scos, atunci prin CI/CD vom avea testele verzi.

```
Error:  Failures: 
Error:    Element not found {by text: Acest text nu exista pe site-ul hapifyMe!}
Expected: visible
Screenshot: file:/home/runner/work/qa-FinalProject/qa-FinalProject/build/reports/tests/1779717403460.0.png
Page source: file:/home/runner/work/qa-FinalProject/qa-FinalProject/build/reports/tests/1779717403460.0.html
Timeout: 10 s.
Caused by: NoSuchElementException: no such element: Unable to locate element: {"method":"xpath","selector":".//*/text()[normalize-space(translate(string(.), '
[INFO] 
Error:  Tests run: 5, Failures: 1, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  45.001 s
[INFO] Finished at: 2026-05-25T13:56:44Z
[INFO] ------------------------------------------------------------------------
Error:  Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.1.2:test (default-test) on project qa_BDD_Cucumber: There are test failures.
Error:  
Error:  Please refer to /home/runner/work/qa-FinalProject/qa-FinalProject/target/surefire-reports for the individual test results.
Error:  Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
Error:  -> [Help 1]
Error:  
Error:  To see the full stack trace of the errors, re-run Maven with the -e switch.
Error:  Re-run Maven using the -X switch to enable full debug logging.
Error:  
Error:  For more information about the errors and possible solutions, please read the following articles:
Error:  [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
Error: Process completed with exit code 1.


```
