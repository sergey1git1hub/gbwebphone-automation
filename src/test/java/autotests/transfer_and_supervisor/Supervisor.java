package autotests.transfer_and_supervisor;

import actions.AgentAbstractionLayer;
import actions.webphonePanel.WebphonePanel;
import autotests.calls.callOnTwoLines.CallOnTwoLinesBaseClass;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import data.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.RetryAnalyzer;

import java.io.IOException;

import static actions.client.Client.cxAnswer;
import static actions.client.Client.openCXphone;
import static actions.login.Logout.logOut;
import static actions.webphonePanel.WebphonePanel.agentHangup;
import static actions.webphonePanel.WebphonePanel.checkStatus;
import static callsMethods.Methods.*;
import static callsMethods.STMethods.*;
import static utils.Flags.isLocal;


/**
 * Created by SChubuk on 10.11.2017.
 */

@Listeners(VideoListener.class)
public class Supervisor {
    static Data data;
    static WebDriver agent;
    static WebDriver supervisor;
    static boolean slow = true;
    static int delay = 2;
    static String supervisorUsername;
    static String agentUsername;
    static WebDriver dummiDriver;
    static String group = "!test_group5_5220";
    AgentAbstractionLayer agentEntity = new AgentAbstractionLayer();

    static {

        try {
            if (isLocal()) {
                supervisorUsername = "81046";
                agentUsername = "81047";

            } else {
                supervisorUsername = "81048";
                agentUsername = "81049";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void supervisorAction(String sikuliElement, String statusAfterAction, String testName) throws Exception {
        try {

            supervisor = login(supervisor, supervisorUsername, group, false);
            //OPEN CHROME
            System.setProperty("browserName", "chrome");
            agent = login(agent, agentUsername, group, true);

            openCXphone(5000);
            WebphonePanel.call(agent, 1, "94949");
            cxAnswer();
            Thread.sleep(1000);

            //LISTEN FROM IE, CALL IN CHROME
            clickUsername(supervisor, agentUsername);
            sikuliClickElement(sikuliElement);  //can't do it on remote PC!
            Thread.sleep(1000);
            sikuliClickElement(sikuliElement);
            //END LISTENING IN IE
            checkStatus(supervisor, statusAfterAction, 5);
            Thread.sleep(5000);
            agentHangup(supervisor, 1);
            checkStatus(supervisor, "Available", 5);

            //END CALL IN CHROME

            agentHangup(agent, 1);
            agentEntity.setResultCodeAndCheckAvailableStatus();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void call() throws Exception {
        try {
            log("This is a log message.", "DEBUG");
            supervisor = loginReceiver(supervisor, supervisorUsername, false);

            //OPEN CHROME
            System.setProperty("browserName", "chrome");
            agent = loginInitiator(agent, agentUsername, true);

            clickUsername(supervisor, agentUsername);
            sikuliClickElement("button_Call");//can't do it on remote PC!
            Thread.sleep(5000);
            checkStatus(supervisor, "Incall", 5);
            Thread.sleep(5000);
            agentHangup(supervisor, 1);
            checkStatus(supervisor, "Wrapup", 5);


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public  void listen() throws Exception {
        supervisorAction("button_Listen", "Listening", "Supervisor Listen");
    }


    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    //Whispering
    public  void talkToUser() throws Exception {
        supervisorAction("button_TalkToUser", "Whispering", "Supervisor Talk to user");
    }


    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    //Barged
    public  void bargeIn() throws Exception {
        supervisorAction("button_BargeIn", "Barged", "Supervisor Talk to user");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void logUserOut() throws Exception {
        try {

            supervisor = loginReceiver(supervisor, supervisorUsername, false);

            //OPEN CHROME
            System.setProperty("browserName", "chrome");
            agent = loginInitiator(agent, agentUsername, true);

            clickUsername(supervisor, agentUsername);
            sikuliClickElement("button_LogUserOut");  //can't do it on remote PC!
            Thread.sleep(1000);
            sikuliClickElement("button_LogUserOut");

            Thread.sleep(5000);

            WebDriverWait wait = new WebDriverWait(agent, 10);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn_connect")));


        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void sendNotification() throws Exception {
        try {
            //OPEN IE
            supervisor = login(supervisor, supervisorUsername, "\\!test_group5_5220", false);
            agent = login(agent, agentUsername, "\\!test_group5_5220", true);

            clickUsername(supervisor, agentUsername);



            sikuliClickElement("button_SendNotification");
            Thread.sleep(2000);

            //ENTER MESSAGE IN NOTIFICATION BOX
            WebElement notificationBox = supervisor.findElement(By.cssSelector("#tabView\\:notificationMessage"));
            notificationBox.sendKeys("This is notification.");

            WebElement button_Send = supervisor.findElement(By.cssSelector("#tabView\\:supervisorNotifyButton"));
            button_Send.click();

            System.out.println("Notification has been sent.");

            WebDriverWait waitForGrowl_container = new WebDriverWait(supervisor, 5);
            waitForGrowl_container.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#growl_container")));
            WebElement growl_container = supervisor.findElement(By.cssSelector("#growl_container"));
            Assert.assertTrue(growl_container.isDisplayed());
            Assert.assertTrue(growl_container.getText().contains("Sending notification(s)"));

            //Check that message received in chrome.

            WebDriverWait waitForGrowlSocketMessage_container = new WebDriverWait(agent, 10);
            waitForGrowlSocketMessage_container.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#growlSocketMessage_container")));
            WebElement growlSocketMessage_container = agent.findElement(By.cssSelector("#growlSocketMessage_container"));
            Assert.assertTrue(growlSocketMessage_container.isDisplayed());
            Assert.assertTrue(growlSocketMessage_container.getText().contains("This is notification."));
            System.out.println("Notification arrived.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void assist() throws Exception {
        try {
            supervisor = login(supervisor, supervisorUsername, "\\!test_group5_5220",  false);
            agent = login(agent, agentUsername, "\\!test_group5_5220",  true);
            clickUsername(supervisor, agentUsername);

            WebDriverWait waitForButton_Assist = new WebDriverWait(supervisor, 10);
            waitForButton_Assist.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tabView\\:supervisorAssist")));

            sikuliClickElement("button_Assist");
            Thread.sleep(2000);

            By assistanceBoxSelector = By.xpath("//*[contains(@id,'message_assistanceDialog_" + supervisorUsername + "_" + agentUsername+ "')]");
            WebElement supervisorAssistanceBox = supervisor.findElement(assistanceBoxSelector);
            supervisorAssistanceBox.sendKeys("This is assistance message.");

            sikuliClickElement("button_Send");

            String supervisorAssistanceBoxText = supervisorAssistanceBox.getText();
            System.out.println(supervisorAssistanceBoxText);
            Assert.assertTrue(supervisorAssistanceBoxText.equals(""));

            WebDriverWait waitForAgentAssistanceBox = new WebDriverWait(agent, 10);
            waitForAgentAssistanceBox.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='This is assistance message.']")));

        System.out.println("Notification arrived.");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void changeStatus() throws InterruptedException, IOException, FindFailed {
       // setup(dummiDriver, "Change status");
        supervisor = login(supervisor, supervisorUsername, "\\!test_group5_5220",  false);
        agent = login(agent, agentUsername, "\\!test_group5_5220",  true);
        clickUsername(supervisor, agentUsername);

        WebDriverWait waitForButton_Assist = new WebDriverWait(supervisor, 10);
        waitForButton_Assist.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tabView\\:supervisorChangeStatus")));
        sikuliClickElement("button_ChangeStatus");
        sleep(2000);
        WebElement changeStatusAux = supervisor.findElement(By.cssSelector("#tabView\\3a supervisorChangeStatus_menu > ul > li:nth-child(5) > a > span"));
        changeStatusAux.click();
        checkStatus(agent, "AUX", 10);
        Thread.sleep(3000);
        //TestTeardown.teardown(dummiDriver, "Change status");
    }


    @BeforeMethod
    public void open() throws InterruptedException, FindFailed, IOException {
        openCXphone(30);
        //before groups to launch ie browser
    }


    @AfterMethod(alwaysRun = true)
    public void teardown() throws IOException {
        try {
            logOut(agent);
            logOut(supervisor);
            agent.quit();
            supervisor.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().exec("taskkill /F /IM 3CXPhone.exe");
    }


}