package entityLayer;

import configs.BrowserFactory;
import org.openqa.selenium.WebDriver;
import uiLayer.login.SelectGroupPage;
import uiLayer.login.WebphoneLoginPage;
import uiLayer.webphonePanel.WebphonePanel;
import utils.LoggingService;
import utils.SystemInfo;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class Agent {

    private WebDriver driver;
    private String username;
    private String group;
    private String language;
    private String initialStatus;
    private LoggingService loggingService = new LoggingService();
    private SystemInfo systemInfo = new SystemInfo();

    //username and password passed to constructor and not to login method
    //intentionally, so it's not possible to call any method of an agent without
    //username or group

    public Agent(String username, String group, String agentInitialStatus) throws Exception {
        this(username, group, agentInitialStatus, false, "English");
    }

    public Agent(String username, String group, String agentInitialStatus, Boolean remote) throws Exception {
        this(username, group, agentInitialStatus, remote, "English");
    }

    public Agent(String username, String group, String agentInitialStatus, Boolean remote, String language) throws Exception {
        BrowserFactory browserFactory = new BrowserFactory();
        this.driver = browserFactory.getBrowser(remote);
        this.username = username;
        this.group = group;
        this.language = language;
        this.initialStatus = agentInitialStatus;
    }

    public void login() throws Exception {
        WebphoneLoginPage webphoneLoginPage = new WebphoneLoginPage(driver);
        SelectGroupPage selectGroupPage = new SelectGroupPage(driver);
        WebphonePanel webphonePanel = new WebphonePanel(driver);

        webphoneLoginPage.openWebphone();
        webphoneLoginPage.changeLanguage(language);
        webphoneLoginPage.login(username);
        selectGroupPage.selectGroup(group);
        webphonePanel.checkStatus(initialStatus, 30);

        loggingService.log("Login to webphone as angent" + username + "/" + group +
                ".\n Check that initial status is " + initialStatus + ".", "INFO");
    }



}
