package uiLayer.login.additionalWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.JSExecutor;


/**
 * Created by SChubuk on 19.04.2018.
 */
public class UserLogoutPanel {
    private WebDriver driver;
    private JSExecutor jsExecutor;
    private int waitForLogoutWindowTimeout = Integer.getInteger("waitForLogoutWindowTimeout");

    public UserLogoutPanel(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = new JSExecutor(driver);
    }

    public WebDriver handleLogoutWindow() {
        WebDriverWait waitForLogoutWindow = new WebDriverWait(driver, waitForLogoutWindowTimeout);
        waitForLogoutWindow.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#userLogoutForm\\3a btn_userlogout_yes > span.ui-button-text.ui-c")));
        WebElement button_Yes = driver.findElement(By.cssSelector("#userLogoutForm\\3a btn_userlogout_yes > span.ui-button-text.ui-c"));
        jsExecutor.executeJavaScriptOrClick(button_Yes);
        return driver;
    }

}
