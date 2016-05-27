package trello.business;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ScreenShotMaker {
    private WebDriver driver;

    public ScreenShotMaker(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot() {
        String timeStamp = LocalDateTime.now().toString();
        timeStamp = timeStamp.replace(":", "-").replace("T", "_T").substring(0, timeStamp.length() - 3);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File newFile = new File("d:\\screenshots\\screenshot_" + timeStamp + ".png");
            FileUtils.copyFile(scrFile, newFile);
            System.out.println("Screenshot taken: " + newFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
