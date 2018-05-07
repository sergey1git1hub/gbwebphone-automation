package uiLayer.login;

import configs.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import uiLayer.login.additionalWindows.SecurityWarningWindow;
import uiLayer.login.additionalWindows.UserLogoutPanel;
import utils.LoggingService;

/**
 * Created by SChubuk on 25.04.2018.
 */
public class WebphoneLoginPageTest {
    private WebDriver driver;
    private LoggingService loggingService = new LoggingService();
    private static final String testUsername = "81016";


    private void testWebphoneLoginPage(String browserName, String webphoneVersion, boolean remote) throws Exception {
        //only for testing purposes
        System.setProperty("browserName", browserName);
        System.setProperty("webphoneVersion", webphoneVersion);

        BrowserFactory browserFactory = new BrowserFactory();
        driver = browserFactory.getBrowser(remote);
        WebphoneLoginPage webphoneLoginPage = new WebphoneLoginPage(driver);
        SecurityWarningWindow securityWarningWindow = new SecurityWarningWindow();
        UserLogoutPanel userLogoutPanel = new UserLogoutPanel(driver);
        webphoneLoginPage.openWebphone();
        webphoneLoginPage.changeLanguage("English");
        webphoneLoginPage.login(testUsername);
        try {
            userLogoutPanel.handleLogoutWindow();
        } catch (Exception e) {
            loggingService.log("Handle logout window.", "DEBUG");
        }

        Thread.sleep(3000);
        driver.quit();
    }


    @Test
    private void testChromeLoginLocalVersion2() throws Exception {
        testWebphoneLoginPage("chrome", "2", false);
    }


   //Plugin not supported by chrome
/* @Test
    private void testChromeLoginLocalVersion1() throws Exception {
        testWebphoneLoginPage("chrome", "1", false);
    }*/


    @Test
    private void testChromeLoginRemoteVersion2() throws Exception {
        testWebphoneLoginPage("chrome", "2", true);
    }

    //Plugin not supported by chrome
/*@Test
    private void testChromeLoginRemoteVersion1() throws Exception {
        testWebphoneLoginPage("chrome", "1", true);
    }*/


    //Language not changed on remote PC
    @Test
    private void testIeLoginLocalVersion2() throws Exception {
        testWebphoneLoginPage("ie", "2", false);
    }


   //Do you want to run this application prompt
    @Test
    private void testIeLoginLocalVersion1() throws Exception {
        testWebphoneLoginPage("ie", "1", false);
    }



    //Button continue does not work
    @Test
    private void testIeLoginRemoteVersion2() throws Exception {
        testWebphoneLoginPage("ie", "2", true);
    }

    //Button continue does not work
    //Do you want to run this application prompt
    @Test
    private void testIeLoginRemoteVersion1() throws Exception {
        testWebphoneLoginPage("ie", "1", true);
    }

    @Test
    private void testOperaLoginLocalVersion2() throws Exception {
        testWebphoneLoginPage("opera", "2", false);
    }


    //Plugin not supported by opera
   /* @Test
    private void testOperaLoginLocalVersion1() throws Exception {
        testWebphoneLoginPage("opera", "1", false);
    }*/

    /*@Test
    private void testOperaLoginRemoteVersion2() throws Exception {
        testWebphoneLoginPage("opera", "2", true);
    }*/

    //plugin not supported by Opera
    /* @Test
    private void testOperaLoginRemoteVersion1() throws Exception {
        testWebphoneLoginPage("opera", "1", true);
    }*/

    @AfterTest
    private void teardown(){
        driver.quit();
    }
}
