package uiLayer.supervisor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SikuliAction;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class SupervisorTab {
    private WebDriver driver;
    private SikuliAction sikuliAction = new SikuliAction();

    public SupervisorTab(WebDriver driver){

    }

    public void clickUsername(String username){
        WebDriverWait waitForUsername = new WebDriverWait(driver, 10);
        By usernameSelector = By.xpath("//*[text()='" + username +"']");
        waitForUsername.until(ExpectedConditions.visibilityOfElementLocated(usernameSelector));
        WebElement userName = driver.findElement(usernameSelector);
        userName.click();
    }







}
