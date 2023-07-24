class TestResepter{
    public static void main(String[] args) {
    Legemiddel nar = new Narkotisk("Ritalin", 500, 100.0, 40);
    Lege lege = new Lege("Ali");

    // Instansierer nye reseptobjekter

    HvitResept hvitResept = new HvitResept(nar, lege, 1, 3);
    BlaaResept blaaResept = new BlaaResept(nar, lege, 2, 5);
    HvitResept hvitR = new HvitResept(nar, lege, 3, 3);
    MilResept militaerresept = new MilResept(nar, lege, 4);
    PResept pResept = new PResept(nar, lege, 5, 5);
    BlaaResept blaaR = new BlaaResept(nar, lege, 6, 5);

    //tester Resept klassen.
    if (hvitResept.hentId() == 1) {
        System.out.println("Resept id er riktig");
    } else {
        System.out.println("Resept id er feil");
    }
    if (blaaResept.hentId() == 2) {
        System.out.println("Resept id er riktig");
    } else {
        System.out.println("Resept id er feil");
    }
    if (hvitResept.hentLegemiddel() == nar) {
        System.out.println("Resept legemiddel er riktig");
    } else {
        System.out.println("Resept legemiddel er feil");
    }
    if (hvitResept.hentLege() == lege) {
        System.out.println("Resept lege er riktig");
    } else {
         System.out.println("Resept lege er feil");
    }

    if (hvitResept.hentPasientId() == 1) {
        System.out.println("Resept pasientId er riktig");
    } else {
        System.out.println("Resept pasientId er feil");
    }
    if (hvitResept.hentReit() == 3) {
        System.out.println("Resept reit er riktig");
    } else {
        System.out.println("Resept reit er feil");
    }
    hvitResept.bruk();
    if (hvitResept.hentReit() == 2 ) {
        System.out.println("Resept bruk er riktig");
    } else {
        System.out.println("Resept bruk er feil");
    }

    //tester HvitResept
    if (hvitR.farge().equals("Hvit")) {
        System.out.println("HvitResept farge er riktig");
    } else {
        System.out.println("HvitResept farge er feil");
    }
    if (hvitR.prisAaBetale(100) == 100) {
        System.out.println("HvitResept prisAaBetale er riktig");
    } else {
        System.out.println("HvitResept prisAaBetale er feil");
    }

    //tester Militaerresept
    if (militaerresept.hentReit() == 3) {
        System.out.println("Militaerresept reit fungerer riktig");
    } else {
        System.out.println("Militaerresept reit fungerer feil");
    }
    if (militaerresept.prisAaBetale(4567) == 0) {
        System.out.println("Militaerresept prisAaBetale er riktig");
    } else {
        System.out.println("Militaerresept prisAaBetale er feil");
    }
    //tester PResept
    if (pResept.prisAaBetale(208) == 100 && pResept.prisAaBetale(34) == 0) {
        System.out.println("PReseppt prisAaBetale fungerer riktig");
    } else {
        System.out.println("PResept prisAaBetale fungerer feil");
    }

    //tester BlaaResept
        if (blaaR.prisAaBetale(100) == 25) {
            System.out.println("BlaaResept prisAaBetale fungerer riktig");
        } else {
            System.out.println("BlaaResept prisAaBetale fungerer feil");
        }

    // henter informasjon fra tostringene
    System.out.println(hvitResept.toString());
    System.out.println();
    System.out.println(blaaResept.toString());
    System.out.println();
    System.out.println(militaerresept.toString());
    System.out.println();
    System.out.println(pResept.toString());
    }
    
}