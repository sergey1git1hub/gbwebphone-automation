package entityLayer;

import org.testng.annotations.Test;

/**
 * Created by SChubuk on 26.04.2018.
 */
public class AgentLoginTest {

    private static final String username = "81016";
    private static final String group = "!test_group5_5220";
    private static final String agentInitialStatus = "Available";


    private void loginTest(String browserName, String webphoneVersion, boolean remote) throws Exception {
        System.setProperty("browserName", browserName);
        System.setProperty("webphoneVersion", webphoneVersion);
        Agent agent = new Agent(username, group, agentInitialStatus, remote);
        agent.login();
        agent.getDriver().quit();
    }

    /*@Test
    private void loginTestChromeLocalVersion2() throws Exception {
        loginTest("chrome", "2", false);
    }


    @Test
    private void loginTestChromeRemoteVersion2() throws Exception {
        loginTest("chrome", "2", true);
    }*/


    //Language not changed on remote PC
    @Test
    private void loginTestIeLocalVersion2() throws Exception {
        loginTest("ie", "2", false);
    }


    //Do you want to run this application prompt
    @Test
    private void loginTestIeLocalVersion1() throws Exception {
        loginTest("ie", "1", false);
    }



    //Button continue does not work
    @Test
    private void loginTestIeRemoteVersion2() throws Exception {
        loginTest("ie", "2", true);
    }

    //Button continue does not work
    //Do you want to run this application prompt
    @Test
    private void loginTestIeRemoteVersion1() throws Exception {
        loginTest("ie", "1", true);
    }

    @Test
    private void loginTestOperaLocalVersion2() throws Exception {
        loginTest("opera", "2", false);
    }







}
