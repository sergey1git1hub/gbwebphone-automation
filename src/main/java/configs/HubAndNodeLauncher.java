package configs;

import org.testng.annotations.Test;
import utils.PropertiesLoader;
import utils.SystemInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by SChubuk on 23.04.2018.
 */
public class HubAndNodeLauncher {

    SystemInfo systemInfo = new SystemInfo();

    //don't forget to update cd {{launchHubScriptLocation}} in batch script
    private String launchHubScriptLocation;
    private String launchNodeScriptLocation;
    private String exportFilesScriptLocation;

    private boolean launchHubAndNode = Boolean.getBoolean("launchHubAndNode");
    private ProcessPrinter processPrinter = new ProcessPrinter();


    public HubAndNodeLauncher() throws Exception {
    }


    public void launchHubAndNode() throws Exception {
        if (launchHubAndNode) {
            String basePathInsideProject = "src/main/resources/batchScriptsRefactored";
            String location;
            if(systemInfo.isLocal()){
                location = "local";
            } else {
                location = "jenkins";
            }

                launchHubScriptLocation = new File(basePathInsideProject +
                        "/" + location + "/hub/launch-selenium-hub.bat").getCanonicalPath();
                exportFilesScriptLocation = new File(basePathInsideProject +
                        "/" + location + "/node/export-files.bat").getCanonicalPath();
                launchNodeScriptLocation = new File(basePathInsideProject +
                        "/" + location + "/node/launch-selenium-node.bat").getCanonicalPath();

            Process launchHubProcess = Runtime.getRuntime().exec(new String[]{launchHubScriptLocation});
            /*processPrinter.printProcessStream(launchHubProcess);*/
            Thread.sleep(100000);
            System.out.println("Hub launched.");
            Thread.sleep(2000); //waiting for hub to launch

            //export node sctipt

            Process launchNodeProcess = Runtime.getRuntime().exec(new String[]{launchNodeScriptLocation});
            processPrinter.printProcessStream(launchNodeProcess);
            Thread.sleep(1000);  //waiting for node to register on hub
            System.out.println("Hub and node has been launched.");
        }
    }


    //Test should be written to detect any errors in process

    @Test
    private void hubAndNodeLauncherTest() throws Exception {
        PropertiesLoader propertiesLoader  = new PropertiesLoader();
        propertiesLoader.loadProperties();
        HubAndNodeLauncher hubAndNodeLauncher = new HubAndNodeLauncher();
        hubAndNodeLauncher.launchHubAndNode();

    }


}
