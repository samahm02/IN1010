import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

class FletteTrad implements Runnable{
    Monitor2 monitor;
    CountDownLatch latch;
    HashMap<String, Subsekvens> fletter;


    public FletteTrad(Monitor2 mon, CountDownLatch lat){
        monitor=mon;
        latch=lat;
    }

    public HashMap<String, Subsekvens> fletteTo(HashMap<String, Subsekvens> map1, HashMap<String, Subsekvens> map2) {
        HashMap<String, Subsekvens> returnMap = monitor.samleMap(map1,map2);
        return returnMap;
    }

    

    HashMap<String, Subsekvens> en = null; 
    HashMap<String, Subsekvens> to = null;


    @Override
    public void run() {
        while(monitor.Storrelse()>1 && latch.getCount() > 0){
            ArrayList<HashMap<String, Subsekvens>> dele = monitor.HentUtTo();
            try {
                en = dele.get(0);
                to = dele.get(1);
                fletter = fletteTo(en, to);
                monitor.SettInnHash(fletter);
                latch.countDown();
            } catch (NullPointerException e) {
            }
        }

    }




    
}
