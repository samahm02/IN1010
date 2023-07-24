import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

class Oblig5Hele {


    public static void main(String[] args) {
        Monitor2 HattSykdom = new Monitor2();
        Monitor2 ikkeSykdom = new Monitor2();
        ArrayList<String> SykListe = new ArrayList<>();
        ArrayList<String> FriskListe = new ArrayList<>();
        String mappen = args[0];
        File filen = new File(mappen + "//metadata.csv");
        Scanner scanner= null;
        

        try{
            scanner = new Scanner(filen);
        } catch (FileNotFoundException e){
            System.out.println("Fant ikke filen");
            System.exit(-1);
        }

        while (scanner.hasNextLine()){
            String linje = scanner.nextLine();
            String[] biter= linje.split(",");
            if (biter[1].equals("True")){
                SykListe.add(biter[0]);
            } else if (biter[1].equals("False")){
                FriskListe.add(biter[0]);
            } else{
                System.out.println("Fant ikke boolean i "+ biter[0]);
            }
        }

        CountDownLatch Latch = new CountDownLatch(SykListe.size());
        for (int i = 0; i< SykListe.size(); i++){
            Thread test = new Thread(new LeseTrad(HattSykdom, Latch, SykListe.get(i)));
            test.start();
        }

        CountDownLatch Latch2 = new CountDownLatch(FriskListe.size());
        for (int i = 0; i< FriskListe.size(); i++){
            Thread test = new Thread(new LeseTrad(ikkeSykdom, Latch2, SykListe.get(i)));
            test.start();
        }

        try {
            Latch.await();
            Latch2.await();
        } catch (InterruptedException e) {
            System.out.println("Avbrutt...");
            System.exit(-1);
        }

        CountDownLatch Latch3 = new CountDownLatch(HattSykdom.Storrelse() - 1);
        for (int i = 0; i < 8; i++) {
            Thread test2 = new Thread(new FletteTrad(HattSykdom, Latch3));
            test2.start();
        }

        CountDownLatch Latch4 = new CountDownLatch(ikkeSykdom.Storrelse() - 1);
        for (int i = 0; i < 8; i++) {
            Thread test2 = new Thread(new FletteTrad(ikkeSykdom, Latch4));
            test2.start();
        }
        
        try {
            Latch3.await();
            Latch4.await();
        } catch (InterruptedException e) {
            System.out.println("Avbrutt...");
            System.exit(-1);
        }



        HashMap<String, Subsekvens> HattSykdomMap = HattSykdom.HentSubsekvens(0);
        HashMap<String, Subsekvens> IkkeSykdomMap = ikkeSykdom.HentSubsekvens(0);

        int tall = 0;
        String storrelse = "";
        String print = "";
        for(Entry<String, Subsekvens> entry: HattSykdomMap.entrySet()) {
            Subsekvens syk = entry.getValue();
            String navn = entry.getKey();
            int antallSyk = syk.hentAntall();
            if (IkkeSykdomMap.get(navn) != null){
            Subsekvens frisk = IkkeSykdomMap.get(navn);
            int antallFrisk = frisk.hentAntall();
            if (antallSyk > antallFrisk) {
                int forskjell = antallSyk- antallFrisk;
                if (forskjell >= 7) {
                    tall = forskjell;
                    storrelse = syk.hentSubsekvens();
                    print += "Navn: " + storrelse + ", flere forekomster: " + tall +". ";
                }
            }
        } else {
            if (antallSyk >= 7) {
                tall = antallSyk;
                storrelse = syk.hentSubsekvens();
                print += tall + "," + storrelse;
            }
        }
    }
    System.out.println(print);


    }
    
}
