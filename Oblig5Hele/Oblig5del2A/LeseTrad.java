import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable {
    private Monitor1 monitor;
    private CountDownLatch latch;
    private String filnavn;
    private HashMap<String, Subsekvens> returntype;

    


    public LeseTrad(Monitor1 mon, CountDownLatch lat, String fil){
        monitor=mon;
        latch=lat;
        filnavn=fil;
    }

    @Override
    public void run(){
        //monitor.lesNavnFraFil(filnavn);
        returntype= monitor.lesFraFil(filnavn);
        monitor.SettInnHash(returntype);
        latch.countDown();
    }
    
}
