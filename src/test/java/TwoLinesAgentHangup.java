import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import data.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import testMethods.CallOnTwoLines;
import testMethods.Methods;
import utils.RetryAnalyzer;

import static utils.TestSetup.setup;
import static utils.TestTeardown.teardown;


/**
 * Created by SChubuk on 04.10.2017.
 */
@Listeners(VideoListener.class)
public class TwoLinesAgentHangup {
    static WebDriver driver;
    static Data data;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void twoLinesAgentHangup() throws Exception {
        try {
            setup(driver);
            CallOnTwoLines.callOnTwoLines();
            driver = CallOnTwoLines.driver;
            data = CallOnTwoLines.data;
            Thread.sleep(1000);
            WebElement button_Hold = driver.findElement(By.cssSelector("#btn_hold"));
            button_Hold.click();
            Thread.sleep(1000);
            Methods.agentHangup(driver, 1);
            Thread.sleep(1000);
            Methods.agentHangup(driver, 2);
            Thread.sleep(1000);
            CallOnTwoLines.setResultCodeAndCheckAvailableStatus();
            teardown(driver, "twoLinesAgentHangup");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}

