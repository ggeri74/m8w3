package trello.cucumberStepDefinition;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import trello.business.DriverFactory;
import trello.pageobjects.*;


import static trello.business.DriverFactory.WebDriverType.FIREFOXDRIVER;

import org.testng.*;

import java.util.concurrent.TimeUnit;


public class TrelloStepDefinitions {

    WebDriver driver;

    @Before
    public void before(Scenario scenario) throws Throwable {
        logger.info("Scenario started: " + scenario.getName());
        driver = DriverFactory.getDriver(FIREFOXDRIVER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void logAfter(Scenario scenario) {
        if (scenario.isFailed()) {
            driver = DriverFactory.getDriver(FIREFOXDRIVER);
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
            logger.info("Scenario ended with FAIL: " + scenario.getName());
        } else logger.info("Scenario ended with PASS: " + scenario.getName());
        driver.quit();
    }

    public final static Logger logger = Logger.getLogger(TrelloStepDefinitions.class);

    @Given("^User is on mentorship page not yet logged in$")
    public void userIsOnMentorshipPageNotYetLoggedIn() {
        logger.info("Step: User is on mentorship page not yet logged in");
        driver.get("https://trello.com/b/bg4pYhv2/mentorship-2016h1");
        TrelloMainPage trelloMainPage = new TrelloMainPage(driver);
        Assert.assertTrue(trelloMainPage.isLoginButtonDisplayed());
    }

    @When("^User clicks on login button on the main page$")
    public void userClicksOnLogInButtonOnTheMainPage() {
        logger.info("Step: User clicks on login button on the main page");
        TrelloMainPage trelloMainPage = new TrelloMainPage(driver);
        trelloMainPage.clickLoginButton();
    }

    @Then("^Login page appears$")
    public void loginPageAppears() {
        logger.info("Step: Login page appears");
        TrelloLoginPage trelloLoginPage = new TrelloLoginPage(driver);
        Assert.assertTrue(trelloLoginPage.isEmailFieldDisplayed());
    }

    @Given("^User is on the login page with proper credentials entered$")
    public void userIsOnTheLoginPageWithProperCredentialsEntered() {
        logger.info("Step: User is on the login page with proper credentials entered");
        userIsOnMentorshipPageNotYetLoggedIn();
        userClicksOnLogInButtonOnTheMainPage();
        TrelloLoginPage trelloLoginPage = new TrelloLoginPage(driver);
        trelloLoginPage.fillEmailField("gergely_glosz@epam.com");
        trelloLoginPage.fillPasswordField("gggggggg");
    }

    @When("^User clicks on login button on the login page$")
    public void userClicksOnLoginButtonOnTheLoginPage() {
        logger.info("Step: User clicks on login button on the login page");
        TrelloLoginPage trelloLoginPage = new TrelloLoginPage(driver);
        trelloLoginPage.clickLoginButton();
    }

    @Then("^Main page appears with logged in user$")
    public void mainPageAppearsWithLoggedInUser() {
        logger.info("Step: Main page appears with logged in user");
        TrelloMainPage trelloMainPage = new TrelloMainPage(driver);
        Assert.assertTrue(trelloMainPage.isMemberNameButtonDisplayed());
    }

    @Given("^User is on the login page with wrong (.+) and (.+) entered$")
    public void userIsOnTheLoginPageWithWrongEmailAndPasswordEntered(String email, String password) {
        logger.info("Step: User is on the login page with wrong username and password");
        userIsOnMentorshipPageNotYetLoggedIn();
        userClicksOnLogInButtonOnTheMainPage();
        TrelloLoginPage trelloLoginPage = new TrelloLoginPage(driver);
        trelloLoginPage.fillEmailField(email);
        trelloLoginPage.fillPasswordField(password);
    }

    @Then("^Error message (.+) appears on the login page$")
    public void errorMessageErrorMessageAppearsOnTheLoginPage(String errorMessage) throws InterruptedException {
        logger.info("Step: Error message appears on the login page");
        TrelloLoginPage trelloLoginPage = new TrelloLoginPage(driver);
        Assert.assertEquals(trelloLoginPage.getErrorMessage(), errorMessage);
    }

    @Given("^User is on mentorship page logged in$")
    public void userIsOnMentorshipPageLoggedIn() throws Throwable {
        logger.info("Step: User is on mentorship page logged in");
        userIsOnTheLoginPageWithProperCredentialsEntered();
        userClicksOnLoginButtonOnTheLoginPage();
        mainPageAppearsWithLoggedInUser();
    }

    @When("^User entered a (.+)$")
    public void userEnteredASearchString(String searchString) {
        logger.info("Step: User entered a search string");
        TrelloMainPage trelloMainPage = new TrelloMainPage(driver);
        trelloMainPage.fillSearchField(searchString);
    }

    @Then("^(.+) for (.+) given back$")
    public void searchResultGivenBack(String searchResult, String searchString) throws InterruptedException {
        logger.info("Step: Search result is given back");
        TrelloMainPage trelloMainPage = new TrelloMainPage(driver);
        String searchResultAsText = trelloMainPage.searchResultAsText();

        String actualFoundOrNot = null;
        if (searchResultAsText.contains("We couldn't find any cards")) {
            actualFoundOrNot = "not found";
        } else if (searchResultAsText.contains(searchString)) {
            actualFoundOrNot = "found";
        }
        //System.out.println("Search result for " + searchString + ": " + actualFoundOrNot + "\n");
        Assert.assertEquals(actualFoundOrNot, searchResult);
    }
}
