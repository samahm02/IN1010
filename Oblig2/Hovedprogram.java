public class Hovedprogram {
    public static void main(String[] args) {
        //Lager instans av hver eneste klasse.
        //legemidler
        Vanlig vanlig = new Vanlig("Ibuprofen", 150, 200);
        Narkotisk narkotisk = new Narkotisk("Ritalin", 500, 100.0, 40);
        Vanedannende vanedannende = new Vanedannende("Nesespray", 75, 300, 60);
        //leger
        Lege lege = new Lege("Ali");
        Spesialist spesialist = new Spesialist("Jonas", "id424");
        //resepter
        HvitResept hvitResept = new HvitResept(vanlig, lege, 1, 4);
        MilResept militaerresept = new MilResept(narkotisk, spesialist, 2);
        PResept pResept = new PResept(vanlig, lege, 3,5);
        BlaaResept blaaResept = new BlaaResept(vanedannende, spesialist, 4, 10);

        //skriver ut informasjon om hvert objekt:
        //legemiddel
        System.out.println(vanlig);
        System.out.println();
        System.out.println(narkotisk);
        System.out.println();
        System.out.println(vanedannende);
        System.out.println();

        //leger
        System.out.println(lege);
        System.out.println();
        System.out.println(spesialist);
        System.out.println();
        
        
        //resepter

        System.out.println(hvitResept);
        System.out.println();
        System.out.println(militaerresept);
        System.out.println();
        System.out.println(pResept);
        System.out.println();
        System.out.println(blaaResept);

        
    }
}
