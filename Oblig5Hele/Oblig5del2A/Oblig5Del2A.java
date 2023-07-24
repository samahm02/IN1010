import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class Oblig5Del2A {
    private static ArrayList<String> filer;
    static CountDownLatch Latch = new CountDownLatch(9);
    
    public static void main(String[] args) {
        Monitor1 monitor = new Monitor1();
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

        monitor.Storrelse();
        monitor.HentRegister();
        monitor.settSammenHashmap();

    }
}
