package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by SChubuk on 19.04.2018.
 */
public class JSExecutor {
    private WebDriver driver;
    private LoggingService loggingService = new LoggingService();
    private SystemInfo systemInfo = new SystemInfo();

    public JSExecutor( WebDriver driver){
        this.driver = driver;
    }
    //@Deprecated
    public void executeJavaScriptOrClick(WebElement element, String script) {
        executeJavaScriptOrClick(element, script, false);
    }

    public  void executeJavaScriptOrClick(WebElement element, String script, Boolean inAllBrowsers) {
        if (systemInfo.isIe() || inAllBrowsers) {
            try {
                if (driver instanceof JavascriptExecutor) {
                    ((JavascriptExecutor) driver)
                            .executeScript(script, element);
                    //System.out.println("Button " + element.toString() + " pressed by javascript.");
                }
            } catch (Exception e) {
                loggingService.log("JavaScript execution error!", "INFO");
            }
        } else {
            element.click();
        }
    }

    //@Deprecated
    public  void executeJavaScriptOrClick(WebElement element, Boolean inAllBrowsers) {
        if ((systemInfo.isIe() || inAllBrowsers)) {
            try {
                if (driver instanceof JavascriptExecutor) {
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", element);
                    //System.out.println("Button " + element.toString() + " pressed by javascript.");
                }
            } catch (Exception e) {

               loggingService.log("JavaScript execution error!", "INFO");
            }
        } else {
            element.click();
        }


    }

    public void executeJavaScriptOrClick(WebElement element) {
        executeJavaScriptOrClick(element, true);
    }
}
