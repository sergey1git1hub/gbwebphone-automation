package uiLayer.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import uiLayer.login.additionalWindows.SecurityWarningWindow;
import uiLayer.login.additionalWindows.UserLogoutPanel;
import utils.LoggingService;
import utils.Waiter;
import java.io.IOException;

/**
 * Created by SChubuk on 19.04.2018.
 */

public class WebphoneLoginPage {

    private static final String passwordValue = "1"; //newer changes
    private String webphoneVersion = System.getProperty("webphoneVersion");
    private String webphone1Url = System.getProperty("webphone1Url");
    private String webphone2Url = System.getProperty("webphone2Url");
    private WebDriver driver;
    private LoggingService loggingService = new LoggingService();

    public WebphoneLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openWebphone() throws Exception {
        driver.manage().window().maximize();
        Thread thread1 = new Thread() {

            public void run() {
                if (webphoneVersion.equalsIgnoreCase("1")) {
                    driver.get(webphone1Url);
                }
                if (webphoneVersion.equalsIgnoreCase("2")) {
                    driver.get(webphone2Url);
                }

            }
        };
        Thread thread2 = new Thread() {

            public void run() {
                SecurityWarningWindow securityWarningWindow = new SecurityWarningWindow();
                try {
                    securityWarningWindow.acceptTheRisk();
                } catch (Exception e) {
                    //implement listener to listen for exception in main thread

                }
            }

        };


        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        WebDriverWait waitForTitle = new WebDriverWait(driver, 10);
        waitForTitle.until(ExpectedConditions.titleIs("gbwebphone"));
        Assert.assertEquals(driver.getTitle(), "gbwebphone");
    }

    public void changeLanguage(String language) {
        By buttonConnectLable = By.cssSelector("#btn_connect > span.ui-button-text.ui-c");
        WebElement buttonConnectLableElement = driver.findElement(buttonConnectLable);
        if (buttonConnectLableElement.getText().equalsIgnoreCase("connect")) {
            return;
        }

        WebElement languageDropdown = driver.findElement(By.cssSelector("#lang_input_label"));
        languageDropdown.click();

        By languageSelector = By.xpath("//li[text() = '" + language + "']");

        WebElement language_en = driver.findElement(languageSelector);
        language_en.click();
    }

    public void login(String usernameValue) throws InterruptedException, IOException {
        Waiter waiter = new Waiter();
        UserLogoutPanel userLogoutPanel = new UserLogoutPanel(driver);

        By byNameU = By.cssSelector("[name=username_input]");
        WebDriverWait waitForUsername = new WebDriverWait(driver, 5);
        waitForUsername.until(ExpectedConditions.presenceOfElementLocated(byNameU));
        WebElement userName = driver.findElement(byNameU);
        userName.clear();
        userName.sendKeys(usernameValue);

        waiter.wait(500);

        By byNameP = By.cssSelector("[name=password_input]");
        WebElement password = driver.findElement(byNameP);
        password.clear();
        password.sendKeys(passwordValue);

        waiter.wait(1000);

        WebElement button_Connect = driver.findElement(By.cssSelector("[name='btn_connect']"));
        button_Connect.click();

        try {
            userLogoutPanel.handleLogoutWindow();
        } catch (Exception e) {
            loggingService.log("Handle logout window.", "DEBUG");
        }

        //Verification point
        By button_BackSelector = By.cssSelector("[name='btn_power_group']");
        WebDriverWait waitForButton_Back = new WebDriverWait(driver, 3);
        waitForButton_Back.until(ExpectedConditions.elementToBeClickable(button_BackSelector));
    }

    private void ssoLogin() {
        //not refactored yet
    }


}
