import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SubsekvensRegister {
    static ArrayList<HashMap<String, Subsekvens>> subsekvensRegister = new ArrayList<HashMap<String, Subsekvens>>();
    ArrayList<String> filernavn = new ArrayList<>();

    public void leggTilSubsekvens(HashMap<String, Subsekvens> map) {
        subsekvensRegister.add(map);
    }

    public void leggTilSubsekvensPlass(int Index, HashMap<String, Subsekvens> map2) {
        subsekvensRegister.add(Index, map2);
    }

    public HashMap<String, Subsekvens> hentSubsekvens(int Index) {
        return subsekvensRegister.get(Index);
    }

    public int Storrelse() {
        return subsekvensRegister.size();
    }

    public static HashMap<String, Subsekvens> lesFraFil(String filnavn) {
        //Subsekvens map = new Subsekvens();
        HashMap<String, Subsekvens> sekvensMap = new HashMap<String, Subsekvens>();
        try {
            File fil = new File(filnavn);
            Scanner sacnner = new Scanner(fil);
            while (sacnner.hasNextLine()) {
                String data = sacnner.nextLine();
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
                        if (!sekvensMap.containsKey(data.substring(start, slutt))) {
                            sekvensMap.put(data.substring(start, slutt), subsekvens);
                        }
                        start += 1;
                        slutt += 1;
                    }
                }
            }

            for (Map.Entry<String, Subsekvens> entry : sekvensMap.entrySet()) {
                Subsekvens value = entry.getValue();
                //System.out.println(value);
            }
            sacnner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke lese filen...");
            e.printStackTrace();

        }
        subsekvensRegister.add(sekvensMap);
        return sekvensMap;

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

    public void fjernFraArray(HashMap<String, Subsekvens> mapSomSkalFjernes) {
        boolean test = subsekvensRegister.remove(mapSomSkalFjernes);
        System.out.println(test);
    }

    public void leggTilArray(HashMap<String, Subsekvens> mapSomSkalLeggesTil) {
        subsekvensRegister.add(mapSomSkalLeggesTil);
    }

    public void lesArray() {
        for (HashMap<String, Subsekvens> map : subsekvensRegister) {
            System.out.println("Skriver ut hashMap!");
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

            File oversiktsfilen = new File(OversiktsFilBane);
            Scanner scanner = new Scanner(oversiktsfilen);
            while (scanner.hasNextLine()) {
                String linje = scanner.nextLine();
                if (args.length > 0) {
                    linje = args[0] + "//" + linje;
                }
                filernavn.add(linje);
                System.out.println(linje);
                lesFraFil(linje);
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



    
    
    



}
