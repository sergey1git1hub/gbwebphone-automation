package uiLayer.supervisor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.SikuliAction;
import utils.Waiter;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class SendNotificationWindow {
    private WebDriver driver;
    private SikuliAction sikuliAction = new SikuliAction();
    private Waiter waiter = new Waiter();
    
    public SendNotificationWindow(WebDriver dirver) throws Exception {
        this.driver = driver;
    }

    public void sendNotification(String notification) throws Exception {
        sikuliAction.sikuliClickElement("button_SendNotification");
        waiter.wait(2000);

        //ENTER MESSAGE IN NOTIFICATION BOX
        WebElement notificationBox = driver.findElement(By.cssSelector("#tabView\\:notificationMessage"));
        notificationBox.sendKeys(notification);

        WebElement button_Send = driver.findElement(By.cssSelector("#tabView\\:driverNotifyButton"));
        button_Send.click();
    }

   public void checkNotificationHasBeenSend(){
       WebDriverWait waitForGrowl_container = new WebDriverWait(driver, 5);
       waitForGrowl_container.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#growl_container")));
       WebElement growl_container = driver.findElement(By.cssSelector("#growl_container"));
       Assert.assertTrue(growl_container.isDisplayed());
       Assert.assertTrue(growl_container.getText().contains("Sending notification(s)"));
   }

}
