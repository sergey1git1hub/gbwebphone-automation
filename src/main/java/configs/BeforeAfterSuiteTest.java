package configs;

import org.testng.annotations.AfterSuite;
import java.io.File;


/**
 * Created by SChubuk on 19.04.2018.
 */
public class BeforeAfterSuiteTest {

    private String killDriversHubScriptLocation = new File("src\\main\\resources\\batchScripts\\drivers\\kill-drivers-hub.bat").getCanonicalPath();
    private String killDriversNodeScriptLocation = new File("src\\main\\resources\\batchScripts\\drivers\\kill-drivers-node.bat").getCanonicalPath();
    private ProcessPrinter processPrinter = new ProcessPrinter();


    public BeforeAfterSuiteTest() throws Exception {
    }

    @AfterSuite(alwaysRun=true)
    private void teardown() throws Exception {
        System.out.println("AFTER SUITE.");
        Process killDriversHubProcess = Runtime.getRuntime().exec(new String[]{killDriversHubScriptLocation});
        processPrinter.printProcessStream(killDriversHubProcess);
        Process killDriversNodeProcess = Runtime.getRuntime().exec(new String[]{killDriversNodeScriptLocation});
        processPrinter.printProcessStream(killDriversNodeProcess);
    }

}
