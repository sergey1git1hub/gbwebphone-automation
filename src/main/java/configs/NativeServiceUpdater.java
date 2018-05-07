package configs;

import org.testng.annotations.Test;
import utils.PropertiesLoader;
import utils.SystemInfo;
import java.io.File;


public class NativeServiceUpdater {

    ProcessPrinter processPrinter = new ProcessPrinter();
    private String updateServiceHubScriptLocation;
    private String updateServiceNodeScriptLocation;
    private String basePathInsideProject = "src/main/resources/batchScripts";
    private String location;
    SystemInfo systemInfo = new SystemInfo();


    public void updateNativeService() throws Exception {
        if (Boolean.getBoolean("updateService")) {
            updateNativeService(true);
        }

    }


    public void updateNativeService(Boolean argument) throws Exception {
        if(systemInfo.isLocal()){
            location = "local";
        } else {
            location = "jenkins";
        }
        updateServiceHubScriptLocation = new File(basePathInsideProject +"/updatens.bat").getCanonicalPath();
        updateServiceNodeScriptLocation = new File(basePathInsideProject +
                "/" + location + "/node/updatens.bat").getCanonicalPath();

        Process updateServiceHubProcess = Runtime.getRuntime().exec(new String[]{updateServiceHubScriptLocation});
        processPrinter.printProcessStream(updateServiceHubProcess);
        Process updateServiceNodeProcess = Runtime.getRuntime().exec(new String[]{updateServiceNodeScriptLocation});
        processPrinter.printProcessStream(updateServiceNodeProcess);
        System.out.println("Native service updated before running autotests.");
    }

    //independent of launching node and hub
    @Test
    private void updateNativeServiceTest() throws Exception {
        NativeServiceUpdater nativeServiceUpdater = new NativeServiceUpdater();
        nativeServiceUpdater.updateNativeService();
    }




}
