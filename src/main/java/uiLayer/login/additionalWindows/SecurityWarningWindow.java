package uiLayer.login.additionalWindows;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import utils.LoggingService;
import utils.SikuliAction;
import utils.SystemInfo;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class SecurityWarningWindow {

    private WebDriver driver;
    private SikuliAction sikuliAction = new SikuliAction();
    private SystemInfo systemInfo = new SystemInfo();
    private LoggingService loggingService = new LoggingService();

    public SecurityWarningWindow(WebDriver driver){
        this.driver = driver;
    }

    public void acceptTheRisk() throws Exception {
        if (systemInfo.isIe()) {
            try {
                int timeout;
                if (systemInfo.isLocal()) {
                    timeout = 2;
                } else {
                    timeout = 5;
                }
                sikuliAction.sikuliClickElement("checkbox_acceptTheRisk", timeout);
                sikuliAction.sikuliClickElement("checkbox_acceptTheRisk", 2);

            } catch (FindFailed findFailed) {
                loggingService.log("There is no do you want to run this application window!", "DEBUG");
            }
        }
    }


}
