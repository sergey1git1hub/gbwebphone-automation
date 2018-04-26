package utils;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by SChubuk on 26.04.2018.
 */
public class SystemInfoTest {

    SystemInfo systemInfo = new SystemInfo();

    @Test
    private void testIsIe(){
        System.setProperty("browserName", "ie");
        Assert.assertTrue(systemInfo.isIe());

    }

    @Test
    private void testIsChrome(){
        System.setProperty("browserName", "chrome");
        Assert.assertTrue(systemInfo.isChrome());
    }

    @Test
    private void testIsOpera(){
        System.setProperty("browserName", "opera");
        Assert.assertTrue(systemInfo.isOpera());
    }

    @Test
    private void testIsLocal(){
        Assert.assertTrue(systemInfo.isLocal());
    }
}
