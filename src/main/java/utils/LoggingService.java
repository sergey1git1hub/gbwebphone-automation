package utils;

/**
 * Created by SChubuk on 19.04.2018.
 */
public class LoggingService {
    //Will be used on Entity level only!

    public void log(String text, String logLevel) { //INFO, DEBUG, ERROR

        String LOGLEVEL = System.getProperty("LOGLEVEL");
        if (logLevel.equalsIgnoreCase(LOGLEVEL) || logLevel.equalsIgnoreCase("CONSOLE")) {
            System.out.println(text);
        }

    }

}
