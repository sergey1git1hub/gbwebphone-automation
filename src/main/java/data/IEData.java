package data;

import java.net.UnknownHostException;

/**
 * Created by SChubuk on 04.10.2017.
 */

/*data.Data data = new data.Data();
data.username = "";
data.browser = "chrome";*/
public class IEData extends Data {


    public IEData() throws UnknownHostException {
        super();
        browser = "IE";
        webphoneUrl = "http://172.21.24.109/gbwebphone/";
        method = "usual";
    }
}
