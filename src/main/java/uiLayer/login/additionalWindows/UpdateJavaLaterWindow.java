package uiLayer.login.additionalWindows;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import utils.SikuliAction;
import utils.SystemInfo;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class UpdateJavaLaterWindow {
    SikuliAction sikuliAction = new SikuliAction();
    SystemInfo systemInfo = new SystemInfo();

    public void updateLater() {
        Thread thread2 = new Thread() {
            public void run() {
                try {

                    if (systemInfo.isIe()) {
                        int timeout;
                        if (systemInfo.isLocal()) {
                            timeout = 2;
                        } else {
                            timeout = 5;
                        }
                        sikuliAction.sikuliClickElement("checkbox_doNotAskAgain", timeout);
                        sikuliAction.sikuliClickElement("option_updateJavaLater", timeout);
                    }

                } catch (Exception e) {

                }
            }

        };
    }
}
