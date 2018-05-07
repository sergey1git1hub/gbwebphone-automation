package uiLayer.webphonePanel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.*;

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

    public void changeStatus(String status) throws Exception {

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

    public WebDriver clickButtonAccept(int waitTime, boolean isPreview) throws InterruptedException {

        WebDriverWait waitForButtonAccept = new WebDriverWait(driver, waitTime);
        String idValue;
        if (isPreview) {
            idValue = "btn_preview_accept";
        } else {
            idValue = "btn_accept";
        }
        driver.switchTo().defaultContent();
        //OR condition in css selector might be used
        By byIdAccept = By.cssSelector("[id = '" + idValue + "']");
        waitForButtonAccept.until(ExpectedConditions.elementToBeClickable(byIdAccept));
        WebElement button_Accept = driver.findElement(byIdAccept);
        //if not wait, CRM card not opened
        Thread.sleep(500);
        if (systemInfo.isIe()) {
            jsExecutor.executeJavaScriptOrClick(button_Accept);
        } else {
            button_Accept.click();
        }
        return driver;
    }

    public void agentHangup(int line) throws Exception {
        switchLine(line);
        waiter.wait(1000, 500);
        WebElement button_Hangup = driver.findElement(By.cssSelector("#btn_hangup"));
        jsExecutor.executeJavaScriptOrClick(button_Hangup, "arguments[0].click();");
    }

    public void switchLine(int line) throws Exception {
        if (driver.equals("chrome") && systemInfo.isLocal()) {
            WebDriverWait waitForLineElement = new WebDriverWait(driver, 2);
            waitForLineElement.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[id = 'btn_line_" + line + "_span']")));
            WebElement lineElement = driver.findElement(By.cssSelector("[id = 'btn_line_" + line + "_span']"));
            Thread.sleep(1000);
            System.out.println("Slept 1000 ms.");
            lineElement.click();
        } else {
            /*System.out.println("Browser is not chrome or running on Jenkns.");
            if (!isLocal()) {
                WebElement lineElement = driver.findElement(By.cssSelector("[id = 'btn_line_" + line + "_span']"));
                lineElement.sendKeys(Keys.ENTER);
            } else*/

            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        .executeScript("wp_common.wp_ChangeLine(" + line + "); log(event);");
                System.out.println("Line switched by javascript.");
            }
        }
    }

    public  void hold(WebDriver driver) throws Exception {

        WebElement button_Hold = driver.findElement(By.cssSelector("#btn_hold"));
        jsExecutor.executeJavaScriptOrClick(button_Hold, "wp_common.wp_HoldOrVoicemail();log(event);" +
                "PrimeFaces.ab({source:'btn_hold'});return false;");
        checkStatus("Onhold", 6);

    }

    public  void unhold(WebDriver driver) throws Exception {
        WebElement button_Hold = driver.findElement(By.cssSelector("#btn_hold"));
        jsExecutor.executeJavaScriptOrClick(button_Hold, "wp_common.wp_HoldOrVoicemail();log(event);" +
                "PrimeFaces.ab({source:'btn_hold'});return false;");
        checkStatus("Incall", 6);
    }

    public  void mute(WebDriver driver) throws Exception {
        WebElement button_Mute = driver.findElement(By.cssSelector("#btn_mute"));
        jsExecutor.executeJavaScriptOrClick(button_Mute, "wp_common.wp_HoldOrVoicemail();log(event);" +
                "PrimeFaces.ab({source:'btn_hold'});return false;");
        checkStatus("Muted", 6);
    }

    public  void unmute(WebDriver driver) throws Exception {
        WebElement button_Mute = driver.findElement(By.cssSelector("#btn_mute"));
        jsExecutor.executeJavaScriptOrClick(button_Mute, "wp_common.wp_HoldOrVoicemail();log(event);" +
                "PrimeFaces.ab({source:'btn_hold'});return false;");
        checkStatus("Incall", 6);

    }

}




