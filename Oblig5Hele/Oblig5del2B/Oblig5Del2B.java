import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class Oblig5Del2B {
    private static ArrayList<String> filer;
    static CountDownLatch Latch = new CountDownLatch(9);
    
    public static void main(String[] args) {
        Monitor2 monitor = new Monitor2();
        filer =monitor.FaaFilnavn(args);


        for (int i = 0; i< filer.size(); i++){
            Thread test = new Thread(new LeseTrad(monitor, Latch, filer.get(i)));
            test.start();
        }

        try {
            Latch.await();
        } catch (InterruptedException e) {
            System.out.println("Avbrutt...");
            System.exit(-1);
        }

        CountDownLatch latch2 = new CountDownLatch(monitor.Storrelse() - 1);
        for (int i = 0; i < 8; i++) {
            Thread test2 = new Thread(new FletteTrad(monitor, latch2));
            test2.start();
        }

        try {
            latch2.await();
        } catch (InterruptedException e) {
            System.out.println("Avbrutt...");
            System.exit(-1);
        }


       //System.out.println(monitor.Storrelse());
       monitor.hoyesteAntall(monitor.HentSubsekvens(0));


    
    }
}
