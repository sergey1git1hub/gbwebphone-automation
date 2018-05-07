package uiLayer.login.additionalWindows;

import utils.SikuliAction;
import utils.SystemInfo;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class SecurityWarningWindow {

    private SikuliAction sikuliAction = new SikuliAction();
    private SystemInfo systemInfo = new SystemInfo();
    private static int acceptTheRiskLocalTimeout;
    private static int acceptTheRiskJenkinsTimeout;

    public void acceptTheRisk() throws Exception {
        acceptTheRiskLocalTimeout = Integer.getInteger("acceptTheRiskLocalTimeout");
        acceptTheRiskJenkinsTimeout = Integer.getInteger("acceptTheRiskJenkinsTimeout");
                if (systemInfo.isIe()) {
                    int timeout;
                    if (systemInfo.isLocal()) {
                        timeout = acceptTheRiskLocalTimeout;
                    } else {
                        timeout = acceptTheRiskJenkinsTimeout;
                    }

                    sikuliAction.sikuliClickElement("checkbox_acceptTheRisk", timeout);
                    sikuliAction.sikuliClickElement("button_Run", timeout);
                }
            }

}
