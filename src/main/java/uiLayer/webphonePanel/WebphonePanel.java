package uiLayer.webphonePanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import utils.SystemInfo;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class WebphonePanel {
    private WebDriver driver;

    public WebphonePanel(WebDriver driver) {
        this.driver = driver;
    }




    public void checkStatus(String status, int waitTime) {

        WebDriverWait waitForStatus = new WebDriverWait(driver, waitTime);
        waitForStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\b" + status + "\\b.*")));

        WebElement currentStatus = driver.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        Assert.assertTrue(currentStatus.getText().contains(status));
       // log("Check that status is " + status + ".", "INFO");

    }

}



