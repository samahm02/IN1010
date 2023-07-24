import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SubsekvensRegister {
    static ArrayList<HashMap<String, Subsekvens>> subsekvensRegister = new ArrayList<HashMap<String, Subsekvens>>();
    private ArrayList<String> filernavn = new ArrayList<>();

    public void leggTilSubsekvens(HashMap<String, Subsekvens> e) {
        subsekvensRegister.add(e);
    }

    public void leggTilSubsekvensPlass(int plassIndex, HashMap<String, Subsekvens> e) {
        subsekvensRegister.add(plassIndex, e);
    }

    public HashMap<String, Subsekvens> hentSubsekvens(int Index) {
        return subsekvensRegister.get(Index);
    }

    public int Storrelse() {
        return subsekvensRegister.size();
    }

    public static HashMap<String, Subsekvens> lesFraFil(String filnavn) {
        HashMap<String, Subsekvens> sekvenser = new HashMap<String, Subsekvens>();
        try {
            File fil = new File(filnavn);
            Scanner scanner = new Scanner(fil);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                int lengde = data.length();
                if (lengde >= 3) {
                    int start = 0;
                    int slutt = 3;
                    lengde -= 2;
                    while (lengde >= 1) {
                        lengde -= 1;
                        //System.out.println(data.substring(start, slutt));
                        Subsekvens subsekvens = new Subsekvens(data.substring(start, slutt));
                        subsekvens.settNyttAntall(1);
                        if (!sekvenser.containsKey(data.substring(start, slutt))) {
                            sekvenser.put(data.substring(start, slutt), subsekvens);
                        }
                        start += 1;
                        slutt += 1;
                    }
                }
            }

            for (Map.Entry<String, Subsekvens> entry : sekvenser.entrySet()) {
                Subsekvens value = entry.getValue();
                //System.out.println(value);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke lese " + filnavn);
            e.printStackTrace();

        }
        //subsekvensRegister.add(sekvenser);
        return sekvenser;

    }

    public static HashMap<String, Subsekvens> samleMap(HashMap<String, Subsekvens> map1, HashMap<String, Subsekvens> map2){
        HashMap<String,Subsekvens> Nymap = new HashMap<>();
        Nymap = map1; 
        for(String entry: map2.keySet()) {
            if (Nymap.containsKey(entry)) {
                Nymap.get(entry).leggTilAntall(map2.get(entry).hentAntall());
        } else {
          Nymap.put(entry, map2.get(entry));
        }
    }
    return Nymap;

}

    public void fjernFraArray(HashMap<String, Subsekvens> map) {
        boolean test = subsekvensRegister.remove(map);
        System.out.println(test);
    }

    public void leggTilArray(HashMap<String, Subsekvens> map) {
        subsekvensRegister.add(map);
    }

    public void lesArray() {
        for (HashMap<String, Subsekvens> map : subsekvensRegister) {
            System.out.println("Skriver ut hashMap: ");
            int indeks = 0;
            for (Map.Entry<String, Subsekvens> entry : map.entrySet()) {
                Subsekvens value = entry.getValue();
                System.out.println(indeks + "," + value);
                indeks++;
            }
        }
    }

    public void lesMap(HashMap<String, Subsekvens> mapet) {
        int indeks = 0;
        for (Map.Entry<String, Subsekvens> entry : mapet.entrySet()) {
            Subsekvens value = entry.getValue();
            System.out.println(indeks + "," + value);
            indeks++;
        }
    }

    public void settSammenHashmap() {
        int arrayLengde = subsekvensRegister.size();
        while (arrayLengde > 1) {
            HashMap<String, Subsekvens> temp = samleMap(subsekvensRegister.get(0), subsekvensRegister.get(1));
            fjernFraArray(subsekvensRegister.get(1));
            fjernFraArray(subsekvensRegister.get(0));
            leggTilArray(temp);

            arrayLengde--;
        }
        hoyesteAntall(subsekvensRegister.get(0));
    }

    public ArrayList <String> FaaFilnavn(String[] args) {
        try {
            String OversiktsFilBane = "metadata.csv";
            if (args.length > 0) {
                OversiktsFilBane = args[0] + "//metadata.csv";
            }

            //ArrayList<String> filernavn = new ArrayList<>();
            File oversiktsfilen = new File(OversiktsFilBane);
            Scanner scanner = new Scanner(oversiktsfilen);
            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine();
                if (args.length > 0) {
                    linje = args[0] + "//" + linje;
                }
                filernavn.add(linje);
                System.out.println(linje);
                //lesFraFil(linje);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen i FaaFilnavn");
            e.printStackTrace();
        }
        return filernavn;

    }
    
    public void hoyesteAntall(HashMap<String, Subsekvens> mapet){
        String hoyesteIndeks = " ";
        int hoyesteValue = 0;
        for(Map.Entry<String, Subsekvens> entry: mapet.entrySet()) {
            Subsekvens value = entry.getValue();
            int verdien = value.hentAntall();
            String navnet = value.hentSubsekvens();
            if (verdien > hoyesteValue){
                hoyesteValue = verdien;
                hoyesteIndeks = navnet;
            }
        }
        System.out.println(hoyesteIndeks + " hadde flest forekomster med " + hoyesteValue);

    }

    public void VisFiler(){
        for (int i = 0; i< filernavn.size(); i++){
            System.out.println(filernavn.get(i));
        }


    }

    public ArrayList<HashMap<String, Subsekvens>> HentRegister(){
        return subsekvensRegister;
    }
    
    



}
