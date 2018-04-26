package uiLayer.login.additionalWindows;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import utils.SikuliAction;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class UpdateJavaLaterWindow {
    SikuliAction sikuliAction = new SikuliAction();

    public void updateLater() {
        Thread thread2 = new Thread() {
            public void run() {
                try {

                    sikuliAction.sikuliClickElement("option_updateJavaLater",2);
                    sikuliAction.sikuliClickElement("checkbox_doNotAskAgain",2);

                } catch (Exception e) {
                }
            }

        };
    }
}
