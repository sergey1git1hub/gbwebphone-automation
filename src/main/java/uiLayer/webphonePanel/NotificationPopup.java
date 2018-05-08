package uiLayer.webphonePanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class NotificationPopup {
    private WebDriver driver;

    public NotificationPopup(WebDriver dirver){
        this.driver = driver;
    }


    public void checkNotificationHasBeenSend(String notification) {
        WebDriverWait waitForGrowlSocketMessage_container = new WebDriverWait(driver, 10);
        waitForGrowlSocketMessage_container.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#growlSocketMessage_container")));
        WebElement growlSocketMessage_container = driver.findElement(By.cssSelector("#growlSocketMessage_container"));
        Assert.assertTrue(growlSocketMessage_container.isDisplayed());
        Assert.assertTrue(growlSocketMessage_container.getText().contains(notification));

    }

}
