package configs;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.SystemInfo;

import java.io.File;


/**
 * Created by SChubuk on 19.04.2018.
 */
public class BeforeAfterSuiteTest {

    SystemInfo systemInfo = new SystemInfo();
    private String basePathInsideProject = "src/main/resources/batchScripts";
    private String location;
    private String killDriversHubScriptLocation;
    private String killDriversNodeScriptLocation;
    private String killBrowsersHubScriptLocation;
    private String killBrowsersNodeScriptLocation;


    private ProcessPrinter processPrinter = new ProcessPrinter();


    public BeforeAfterSuiteTest() throws Exception {
        if(systemInfo.isLocal()){
            location = "local";
        } else {
            location = "jenkins";
        }
    }

    @BeforeSuite(alwaysRun=true)
    private void setup() throws Exception {
        System.out.println("BEFORE SUITE.");
        killDrivers();
        killBrowsers();
    }

    @AfterSuite(alwaysRun=true)
    private void teardown() throws Exception {
        System.out.println("AFTER SUITE.");
        killDrivers();
        killBrowsers();

    }

    private void killDrivers() throws Exception {

        killDriversHubScriptLocation = new File(basePathInsideProject +"/kill-drivers.bat").getCanonicalPath();
        killDriversNodeScriptLocation = new File(basePathInsideProject +
                "/" + location + "/node/kill-drivers.bat").getCanonicalPath();

        Process killDriversHubProcess = Runtime.getRuntime().exec(new String[]{killDriversHubScriptLocation});
        processPrinter.printProcessStream(killDriversHubProcess);
        Process killDriversNodeProcess = Runtime.getRuntime().exec(new String[]{killDriversNodeScriptLocation});
        processPrinter.printProcessStream(killDriversNodeProcess);
    }

    private void killBrowsers() throws Exception {

        killBrowsersHubScriptLocation = new File(basePathInsideProject +"/kill-browsers.bat").getCanonicalPath();
        killBrowsersNodeScriptLocation = new File(basePathInsideProject +
                "/" + location + "/node/kill-browsers.bat").getCanonicalPath();

        Process killDriversHubProcess = Runtime.getRuntime().exec(new String[]{killBrowsersHubScriptLocation});
        processPrinter.printProcessStream(killDriversHubProcess);
        Process killDriversNodeProcess = Runtime.getRuntime().exec(new String[]{killBrowsersNodeScriptLocation});
        processPrinter.printProcessStream(killDriversNodeProcess);

    }

}
