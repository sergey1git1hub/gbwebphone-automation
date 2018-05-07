package uiLayer.webphonePanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import utils.*;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class WebphonePanel {
    private WebDriver driver;
    private JSExecutor jsExecutor;
    private SystemInfo systemInfo = new SystemInfo();
    private SikuliAction sikuliAction = new SikuliAction();
    private Waiter waiter = new Waiter();


    public WebphonePanel(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = new JSExecutor(driver);
    }


    public void checkStatus(String status, int waitTime) {

        WebDriverWait waitForStatus = new WebDriverWait(driver, waitTime);
        waitForStatus.until(ExpectedConditions.textMatches(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"), Pattern.compile(".*\\b" + status + "\\b.*")));

        WebElement currentStatus = driver.findElement(By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c"));
        Assert.assertTrue(currentStatus.getText().contains(status));


    }

    public void changeStatus(String status) throws Exception{

        By currentStatusSelector = By.cssSelector(
                "#statusButton > span.ui-button-text.ui-c");
        WebDriverWait waitForCurrentStatus = new WebDriverWait(driver, 5);
        waitForCurrentStatus.until(ExpectedConditions.elementToBeClickable(currentStatusSelector));

        WebElement currentStatus = driver.findElement(currentStatusSelector);
        if (!systemInfo.isLocal() && systemInfo.isIe()) {
            changeStatusNewDontWork(status);
            //System.out.println("Host is: kv1-it-pc-jtest and browser is not Chrome.");
        } else if (systemInfo.isChrome()) {
            currentStatus.click();
            WebElement desirableStatus;
            if (!status.equals("AUX")) {
                desirableStatus = driver.findElement(By.xpath(
                        "/*//*[contains(text(),'" + status + "')]"));
            } else {
                desirableStatus = driver.findElement(By.xpath(
                        "//*[contains(text(),'AUX') and not(contains(text(),'Доступен'))]"));
            }
            desirableStatus.click();
            // System.out.println("Browser is Chrome.");
        } else {
            jsExecutor.executeJavaScriptOrClick(currentStatus);
            WebElement desirableStatus;
            if (!status.equals("AUX")) {
                desirableStatus = driver.findElement(By.xpath(
                        "/*//*[contains(text(),'" + status + "')]"));
            } else {
                desirableStatus = driver.findElement(By.xpath(
                        "//*[contains(text(),'AUX') and not(contains(text(),'Доступен'))]"));
            }
            jsExecutor.executeJavaScriptOrClick(desirableStatus);
        }
        checkStatus(status, 2);
    }

    public void changeStatusNewDontWork(String status) throws Exception {

        if (status.equalsIgnoreCase("Available")) {
            sikuliAction.sikuliClickElement("currentStatus");
            waiter.wait(1000);
            sikuliAction.sikuliClickElement("availableStatus");

        }
        if (status.equalsIgnoreCase("AUX")) {
            sikuliAction.sikuliClickElement("currentStatus");
            waiter.wait(1000);
            sikuliAction.sikuliClickElement("auxStatus");
            waiter.wait(1000);
        }


    }




}



