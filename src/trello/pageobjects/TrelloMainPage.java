package trello.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TrelloMainPage extends Page {
    public TrelloMainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginButton = By.cssSelector(".header-btn.header-login");
    private By memberNameButton = By.cssSelector(".header-btn-text.js-member-name");
    private By addCardLink = By.cssSelector(".list-wrapper:first-child .open-card-composer");
    private By textAreaBox = By.className("list-card-composer-textarea");
    private By addCardButton = By.className("confirm");
    private By cardWithMyName = By.cssSelector(".list-wrapper:first-child [href$=glosz]");
    private By doneColumn = By.cssSelector(".list-wrapper:nth-child(3)");
    private By searchField = By.className("header-search-input");
    private By searchResultsPopOver = By.className("js-pop-over-content");

    public boolean isMemberNameButtonDisplayed() {
        return driver.findElement(memberNameButton).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public TrelloLoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new TrelloLoginPage(driver);
    }

    public void addCard() {
        driver.findElement(addCardLink).click();
        driver.findElement(textAreaBox).sendKeys("Gergely Glosz");
        driver.findElement(addCardButton).click();
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();
    }

    public void moveCardToDoneColumn() {
        WebElement cardToBeMoved = driver.findElement(cardWithMyName);
        WebElement columnWhereRelease = driver.findElement(doneColumn);
        new Actions(driver).clickAndHold(cardToBeMoved).moveToElement(columnWhereRelease).release(cardToBeMoved).perform();
    }

    public void fillSearchField(String searchString) {
        driver.findElement(searchField).sendKeys(searchString);
    }

    public String searchResultAsText() throws InterruptedException {
        Thread.sleep(3000);
        return driver.findElement(searchResultsPopOver).getText();
    }

}
