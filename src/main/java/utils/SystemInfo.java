package utils;

import org.openqa.selenium.WebDriver;

import java.net.InetAddress;

/**
 * Created by SChubuk on 04.01.2018.
 */

public class SystemInfo {

    private String localhostName = System.getProperty("localhostName");

    public boolean isLocal() {
        String hostName = "";
        try{
            hostName= InetAddress.getLocalHost().getHostName();
        } catch (Exception e){
            e.printStackTrace();
        }
        boolean isLocal = hostName.equalsIgnoreCase(localhostName);
        return isLocal;

    }

    public static boolean isThisBrowser(String browser){
        String browserName = System.getProperty("browserName");
        if (browserName.equals(browser))
            return true;
        else return false;
    }


    public static boolean isIe() {
        return isThisBrowser("ie");
    }
    public static boolean isChrome(){
        return isThisBrowser("chrome");
    }
    public static boolean isOpera(){
        return isThisBrowser("opera");
    }


}
