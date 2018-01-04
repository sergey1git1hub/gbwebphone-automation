package data;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by SChubuk on 04.01.2018.
 */
public class Flags {
    public static boolean isLocal;

    static {

        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            isLocal = hostName.equalsIgnoreCase(Data.localhostName);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public static boolean isIE(WebDriver driver) {
        System.out.println("isIE");
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        System.out.println(browserName);
        if (browserName.equals("internet explorer"))
            return true;
        else return false;
    }

}
