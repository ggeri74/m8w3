package trello;


import cucumber.api.CucumberOptions;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import trello.cucumberStepDefinition.TrelloStepDefinitions;


@CucumberOptions(plugin = {"html:target/site/cucumber-report", "json:target/cucumber-report/cucumber.json"})
public class StartTrelloCucumber extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void logBefore() {
        TrelloStepDefinitions.logger.info("Suite started");
    }

    @AfterClass
    public void logAfter() {
        TrelloStepDefinitions.logger.info("Suite ended");
    }

}
