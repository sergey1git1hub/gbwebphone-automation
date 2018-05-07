package entityLayer;
import org.testng.annotations.Test;

/**
 * Created by SChubuk on 26.04.2018.
 */
public class AgentTest {

    private static final String username = "81016";
    private static final String group = "!test_group5_5220";
    private static final String agentInitialStatus = "Available";


    private void testWebphoneChangeStatusPage(String browserName, String webphoneVersion, boolean remote) throws Exception {
        System.setProperty("browserName", browserName);
        System.setProperty("webphoneVersion", webphoneVersion);
        Agent agent = new Agent(username, group, agentInitialStatus, remote);
        agent.login();
        agent.changeStatus("Meeting");
        agent.changeStatus("Webchat");
        agent.changeStatus("AUX");
        agent.getDriver().quit();
    }

    @Test
    private void testChromeChangeStatusLocalVersion2() throws Exception {
        testWebphoneChangeStatusPage("chrome", "2", false);
    }


    @Test
    private void testChromeChangeStatusRemoteVersion2() throws Exception {
        testWebphoneChangeStatusPage("chrome", "2", true);
    }

  /*  @Test
    private void testIeChangeStatusLocalVersion2() throws Exception {
        testWebphoneChangeStatusPage("ie", "2", false);
    }

    @Test
    private void testIeChangeStatusLocalVersion1() throws Exception {
        testWebphoneChangeStatusPage("ie", "1", false);
    }

    @Test
    private void testIeChangeStatusRemoteVersion2() throws Exception {
        testWebphoneChangeStatusPage("ie", "2", true);
    }


    @Test
    private void testIeChangeStatusRemoteVersion1() throws Exception {
        testWebphoneChangeStatusPage("ie", "1", true);
    }*/

    @Test
    private void testOperaChangeStatusLocalVersion2() throws Exception {
        testWebphoneChangeStatusPage("opera", "2", false);
    }


}
