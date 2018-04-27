package configs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by SChubuk on 27.04.2018.
 */
public class ProcessPrinter {
    public void printProcessStream(Process p) throws Exception {
        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        input.close();
    }
}
