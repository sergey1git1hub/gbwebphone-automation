package uiLayer.login.additionalWindows;

import org.openqa.selenium.WebDriver;
import utils.SikuliAction;
import utils.SystemInfo;

import java.beans.ExceptionListener;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class SecurityWarningWindow {

    private SikuliAction sikuliAction = new SikuliAction();
    private SystemInfo systemInfo = new SystemInfo();

    public void acceptTheRisk() throws Exception {

                if (systemInfo.isIe()) {
                    int timeout;
                    if (systemInfo.isLocal()) {
                        timeout = 2;
                    } else {
                        timeout = 5;
                    }

                    sikuliAction.sikuliClickElement("checkbox_acceptTheRisk", timeout);
                    sikuliAction.sikuliClickElement("button_Run", timeout);
                }
            }

}
