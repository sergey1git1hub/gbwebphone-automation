package entityLayer;

import org.testng.annotations.Test;

/**
 * Created by SChubuk on 26.04.2018.
 */
public class AgentTest {

    private static final String username = "81016";
    private static final String group = "!test_group5_5220";
    private static final String agentInitialStatus = "Available";

    @Test
    public void loginTest() throws Exception {
        Agent agent = new Agent(username, group, agentInitialStatus);
        agent.login();
    }





}
