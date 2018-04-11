package autotests.transfer_and_supervisor; /**
 * Created by SChubuk on 15.11.2017.
 */

import callsMethods.Methods;
import callsMethods.STMethods;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Flags;
import utils.RetryAnalyzer;

import java.io.IOException;

import static actions.client.Client.cxAnswer;
import static actions.client.Client.focusCXphone;
import static actions.client.Client.hangup;
import static actions.login.Logout.logOut;
import static actions.webphonePanel.WebphonePanel.agentHangup;
import static callsMethods.Methods.log;
import static callsMethods.Methods.sleep;
import static utils.Flags.isLocal;
import static utils.TestSetup.setup;


/**
 * Created by SChubuk on 04.10.2017.
 */
@Listeners(VideoListener.class)
public class Transfer {

    static WebDriver transferInitiator;
    static WebDriver transferReceiver;
    static String callToNumber;
    static String transferToNumber;
    static String transferToAgent;
    static String transferFromAgent;
    static WebDriver dummiDriver;

    static boolean fast = false;
    static int delay = 2;

    static{
        try {
            if(isLocal()){
                callToNumber = "94949";
                transferToNumber = "94948";
                transferToAgent = "81046";
                transferFromAgent = "81047";
            } else{
                callToNumber = "94944";
                transferToNumber = "94947";
                transferToAgent = "81048";
                transferFromAgent = "81049";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void blindTransferToNumber() throws Exception {
         String testName = "Blind transfer to number";
        try {
            setup(testName);
            transferInitiator = STMethods.loginInitiator(transferInitiator, transferFromAgent);
            log("Transfer Initiator  is : " + transferFromAgent + ".", "DEBUG");
            Thread.sleep(5000);
            STMethods.call(transferInitiator, callToNumber);
            STMethods.makeTransfer(transferInitiator, "blind", transferToNumber);
            cxAnswer("Answer transfered call on behalf of transfer receiver.");
            log("Check that call finished on agent side.", "INFO");
            STMethods.setResultCodeAndCheckAvailableStatus(transferInitiator);
            focusCXphone(1);
            Thread.sleep(2500);
            hangup(1);
            utils.TestTeardown.teardown(transferInitiator, testName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void attendedTransferToNumber() throws Exception {
        String testName = "Attended transfer to number";
        try {
            setup(testName);
            transferInitiator = STMethods.loginInitiator(transferInitiator, transferFromAgent);
            STMethods.call(transferInitiator, callToNumber);
            STMethods.makeTransfer(transferInitiator, "attended", transferToNumber);
            cxAnswer();
            sleep(5000);
            agentHangup(transferInitiator, 1);
            STMethods.setResultCodeAndCheckAvailableStatus(transferInitiator);
            focusCXphone(1);
            Thread.sleep(2500);
            hangup(1);
            utils.TestTeardown.teardown(transferInitiator, testName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void blindTransferToAgent() throws Exception {
        try {
            String testName = "Blind transfer to Agent";
            setup(testName);
            transferReceiver = STMethods.loginReceiver(transferReceiver, transferToAgent, true);
            transferInitiator = STMethods.loginInitiator(transferInitiator, transferFromAgent);
            STMethods.call(transferInitiator, callToNumber);
            STMethods.makeTransfer(transferInitiator, "blind", transferToAgent);
            STMethods.acceptTransfer(transferReceiver);
            Thread.sleep(3000);
            STMethods.setResultCodeAndCheckAvailableStatus(transferInitiator);
            Thread.sleep(5000);
            agentHangup(transferReceiver, 1);
            STMethods.setResultCodeAndCheckAvailableStatus(transferReceiver);
            utils.TestTeardown.teardown(transferInitiator, testName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
       // Assert.assertTrue(false);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void attendedTransferToAgent() throws Exception {
        try {
            String testName = "Attended transfer to Agent";
            setup(testName);
            transferReceiver = STMethods.loginReceiver(transferReceiver, transferToAgent, true);
            transferInitiator = STMethods.loginInitiator(transferInitiator, transferFromAgent);
            STMethods.call(transferInitiator, callToNumber);
            STMethods.makeTransfer(transferInitiator, "attended", transferToAgent);
            STMethods.acceptTransfer(transferReceiver);
            Thread.sleep(5000);
            agentHangup(transferInitiator, 1);
            STMethods.setResultCodeAndCheckAvailableStatus(transferInitiator);
            Thread.sleep(5000);
            agentHangup(transferReceiver, 1);
            STMethods.setResultCodeAndCheckAvailableStatus(transferReceiver);
            utils.TestTeardown.teardown(transferInitiator, testName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Video
    public static void blindTransferToPoint() throws Exception {
        try {
            String testName = "Blind transfer to Point";
            setup(testName);
            transferInitiator = STMethods.loginInitiator(transferInitiator, transferFromAgent);
            STMethods.call(transferInitiator, callToNumber);
            STMethods.makeTransfer(transferInitiator, "point", transferToNumber);
            cxAnswer();
            STMethods.setResultCodeAndCheckAvailableStatus(transferInitiator);
            focusCXphone(1);
            Thread.sleep(5000);
            hangup(1);
            utils.TestTeardown.teardown(transferInitiator, testName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        }

        //Threre is no guarantee of transfer to othrer agent, not to yourself

        // @Test

    public static void attendedTransferToPoint() throws InterruptedException, IOException, FindFailed {


    }

   /* @BeforeMethod
    public void open() throws InterruptedException, FindFailed, IOException {
        Methods.openCXphone(100);
        //before groups to launch ie browser
    }*/

    //alailability schedule for transfer point - not really needed
    @AfterMethod(alwaysRun = true)
    public static void teardown() throws IOException {
        try {
            logOut(transferReceiver);
            logOut(transferInitiator);
            transferInitiator.quit();
            transferReceiver.quit();
            boolean isIE = Flags.isIE(transferInitiator) || Flags.isIE(transferReceiver);

            if (isIE) {
                Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
            }
        } catch (NullPointerException e) {

        } catch (Exception e){
            e.printStackTrace();
        }
        Runtime.getRuntime().exec("taskkill /F /IM 3CXPhone.exe");
    }


}


// time on each test 2h