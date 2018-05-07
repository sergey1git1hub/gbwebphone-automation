package utils;

/**
 * Created by SChubuk on 20.04.2018.
 */
public class Waiter {
    //Thread.sleep used where pause is necessary and could not be skipped by
    //setting run.slow to false

    public void wait(int timeMilliseconds)  {
        wait(timeMilliseconds,0);
    }

    public void wait(int optionalTimeMilliseconds, int requiredTimeMilliseconds){
        Boolean slow = Boolean.getBoolean("runFast");
        try {
        if(slow){
                Thread.sleep(optionalTimeMilliseconds);
        }
            Thread.sleep(requiredTimeMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
