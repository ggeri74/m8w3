package trello.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import trello.business.Credentials;

public class TrelloLoginPage extends Page {

    public TrelloLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.id("user");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");
    private By errorMessage = By.id("error");

    public boolean isEmailFieldDisplayed() {
        return driver.findElement(emailField).isDisplayed();
    }

    public void fillEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElement(errorMessage).getText();
    }

    public void loginToTrello() {
        Credentials credentials = Credentials.readFromConsole();
        driver.findElement(emailField).sendKeys(credentials.getUsername());
        driver.findElement(passwordField).sendKeys(credentials.getPassword());
        driver.findElement(loginButton).click();
    }
}
