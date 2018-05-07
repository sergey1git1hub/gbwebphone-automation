package utils;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

/**
 * Created by SChubuk on 20.04.2018.
 */
public class SikuliAction {
    private String sikuliImagesLocation;

    public void sikuliClickElement(String elementName) throws Exception {
        sikuliClickElement(elementName, 10);
    }

    public void sikuliClickElement(String elementName, int timeout) throws Exception {
        sikuliImagesLocation = System.getProperty("sikuliImagesLocation");
        Screen screen = new Screen();
        org.sikuli.script.Pattern element = new org.sikuli.script.Pattern(sikuliImagesLocation + "\\" + elementName + ".png");
        screen.wait(element, timeout);
        screen.click(element);

    }

}
