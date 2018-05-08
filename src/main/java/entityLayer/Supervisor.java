package entityLayer;


import org.openqa.selenium.WebElement;
import utils.SikuliAction;
import utils.Waiter;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class Supervisor extends Agent{

    private SikuliAction sikuliAction = new SikuliAction();
    private Waiter waiter = new Waiter();

    public Supervisor(String username, String group, String agentInitialStatus) throws Exception {
        super(username, group, agentInitialStatus, false, "English");
    }

    public Supervisor(String username, String group, String agentInitialStatus, Boolean remote) throws Exception {
        super(username, group, agentInitialStatus, remote, "English");
    }

    public Supervisor(String username, String group, String agentInitialStatus, Boolean remote, String language) throws Exception {
        super(username, group, agentInitialStatus, remote, language);
    }


    public void listen() throws Exception {
        sikuliAction.sikuliClickElement("button_Listen");
    }


    //Whispering
    public void talkToUser() throws Exception {
        sikuliAction.sikuliClickElement("button_TalkToUser");
    }

    //Barged
    public void bargeIn() throws Exception {
        sikuliAction.sikuliClickElement("button_BargeIn");
    }

    public void logUserOut() throws Exception {
        sikuliAction.sikuliClickElement("button_LogUserOut");
    }


}
