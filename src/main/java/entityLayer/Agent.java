package entityLayer;

import configs.BrowserFactory;
import org.openqa.selenium.WebDriver;
import uiLayer.login.SelectGroupPage;
import uiLayer.login.WebphoneLoginPage;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class Agent {

    private WebDriver driver;
    private String username;
    private String group;
    private String language;

    //username and password passed to constructor and not to login method
    //intentionally, so it's not possible to call any method of an agent without
    //username or group

    public Agent(String username, String group) throws Exception {
        this(username, group, false, "English");
    }

    public Agent(String username, String group, Boolean remote) throws Exception {
        this(username, group, remote, "English");
    }

    public Agent(String username, String group, Boolean remote, String language) throws Exception {
        BrowserFactory browserFactory = new BrowserFactory();
        this.driver = browserFactory.getBrowser(remote);
        this.username = username;
        this.group = group; 
        this.language = language;
    }

    public void login() throws Exception{
        WebphoneLoginPage webphoneLoginPage = new WebphoneLoginPage();
        SelectGroupPage selectGroupPage = new SelectGroupPage();
        webphoneLoginPage.openWebphone(driver);
        webphoneLoginPage.changeLanguage(driver, language);
        webphoneLoginPage.login(driver, username);
        selectGroupPage.selectGroup(driver, group);
    }

}
