package uiLayer.cxPhone;

import org.sikuli.script.App;
import utils.SikuliAction;

/**
 * Created by SChubuk on 19.04.2018.
 */


public class IdleScreen {
    private SikuliAction sikuliAction = new SikuliAction();
    //should be the same on all machines
    private String pathTo3CXPhoneExecutable;
    public void call(String phoneNubmer) throws Exception {

        pathTo3CXPhoneExecutable = System.getProperty("pathTo3CXPhoneExecutable");
        App cxphone = App.open(pathTo3CXPhoneExecutable);
        if(phoneNubmer.equals("490")){
            sikuliAction.sikuliClickElement("numberFour");
            sikuliAction.sikuliClickElement("numberNine");
            sikuliAction.sikuliClickElement("numberZero");
            sikuliAction.sikuliClickElement("button3CXCall");
        } else {
            throw new Exception("Functionality not implemented yet.");
        }

    }
}
