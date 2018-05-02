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
    private String killDriversHubScriptLocation;
    private String killDriversNodeScriptLocation;
    private ProcessPrinter processPrinter = new ProcessPrinter();


    public BeforeAfterSuiteTest() throws Exception {
    }

    @BeforeSuite(alwaysRun=true)
    private void setup() throws Exception {
        System.out.println("BEFORE SUITE.");
        killDrivers();
    }

    @AfterSuite(alwaysRun=true)
    private void teardown() throws Exception {
        System.out.println("AFTER SUITE.");
        killDrivers();

    }

    private void killDrivers() throws Exception {
        String basePathInsideProject = "src/main/resources/batchScriptsRefactored";
        String location;
        if(systemInfo.isLocal()){
            location = "local";
        } else {
            location = "jenkins";
        }
        killDriversHubScriptLocation = new File(basePathInsideProject +"/kill-drivers.bat").getCanonicalPath();
        killDriversNodeScriptLocation = new File(basePathInsideProject +
                "/" + location + "/node/kill-drivers.bat").getCanonicalPath();

        Process killDriversHubProcess = Runtime.getRuntime().exec(new String[]{killDriversHubScriptLocation});
        processPrinter.printProcessStream(killDriversHubProcess);
        Process killDriversNodeProcess = Runtime.getRuntime().exec(new String[]{killDriversNodeScriptLocation});
        processPrinter.printProcessStream(killDriversNodeProcess);
    }

}
