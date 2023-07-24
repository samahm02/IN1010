class TestLegemiddel {
    public static void main(String[] args) {
        //oppretter narkotisk legemiddel
        Narkotisk nar = new Narkotisk("narTest", 200, 1100, 4);
        //oppretter vanedannende legemiddel
        Vanedannende vane = new Vanedannende("vaneTest", 400, 2200, 7);
        //oppretter vanlig legemiddel
        Vanlig vanlig = new Vanlig("vanligTest", 100, 4000);

        //test av narkotisk legemiddel
        if (testId(nar, 1)) {
            System.out.println("Narkotisk id riktig");
        } else {
            System.out.println("Narkotisk id feil");
        }
        if (testNavn(nar, "narTest")) {
            System.out.println("Narkotisk navn riktig");
        } else {
            System.out.println("Narkotisk navn feil");
        }
        if (testPris(nar, 200)) {
            System.out.println("Narkotisk pris riktig");
        } else {
            System.out.println("Narkotisk pris feil");
        }
        nar.settNyPris(150);
        if (testPris(nar, 150)) {
            System.out.println("Narkotisk settNyPris riktig");
        } else {
            System.out.println("Narkotisk settNyPris feil");
        }
        if (testVirkestoff(nar, 1100)) {
            System.out.println("Narkotisk virkestoff riktig");
        } else {
            System.out.println("Narkotisk virkestoff feil");
        }
        if (testStyrke(nar, 4)) {
            System.out.println("Narkotisk styrke riktig");
        } else {
            System.out.println("Narkotisk styrke feil");
        }
         //test av vanedannende legemiddel
         if (testId(vane, 2)) {
            System.out.println("Vanedannende id riktig");
        } else {
            System.out.println("Vanedannende id feil");
        }
        if (testNavn(vane, "vaneTest")) {
            System.out.println("Vanedannende navn riktig");
        } else {
            System.out.println("Vanedannende navn feil");
        }
        if (testPris(vane, 400)) {
            System.out.println("Vanedannende pris riktig");
        } else {
            System.out.println("Vanedannende pris feil");
        }
        vane.settNyPris(500);
        if (testPris(vane, 500)) {
            System.out.println("Vanedannende settNyPris riktig");
        } else {
            System.out.println("Vanedannende settNyPris feil");
        }
        if (testVirkestoff(vane, 2200)) {
            System.out.println("Vanedannende virkestoff riktig");
        } else {
            System.out.println("Vanedannende virkestoff feil");
        }
        if (testStyrke(vane, 7)) {
            System.out.println("Vanedannende styrke riktig");
        } else {
            System.out.println("Vanedannende styrke feil");
        }

        //test av vanlig legemiddel
        if (testId(vanlig, 3)) {
            System.out.println("Vanlig id riktig");
        } else {
            System.out.println("Vanlig id feil");
        }
        if (testNavn(vanlig, "vanligTest")) {
            System.out.println("Vanlig navn riktig");
        } else {
            System.out.println("Vanlig navn feil");
        }
        if (testPris(vanlig, 100)) {
            System.out.println("Vanlig pris riktig");
        } else {
            System.out.println("Vanlig pris feil");
        }
        vanlig.settNyPris(250);
        if (testPris(vanlig, 250)) {
            System.out.println("Vanlig settNyPris riktig");
        } else {
            System.out.println("Vanlig settNyPris feil");
        }
        if (testVirkestoff(vanlig, 4000)) {
            System.out.println("Vanlig virkestoff riktig");
        } else {
            System.out.println("Vanlig virkestoff feil");
        }
    }

    //id test
    static boolean testId(Legemiddel legemiddel, int forventetId) {
        return legemiddel.hentId() == forventetId;
    }
    //navn test
    static boolean testNavn(Legemiddel legemiddel, String forventetNavn) {
        return legemiddel.hentNavn().equals(forventetNavn);
    }
    //pris test
    static boolean testPris(Legemiddel legemiddel, int forventetPris) {
        return legemiddel.hentPris() == forventetPris;
    }
    //virkestoff test
    static boolean testVirkestoff(Legemiddel legemiddel, double forventetVirkestoff) {
        return legemiddel.hentVirkestoff() == forventetVirkestoff;
    }
    //stryke test
    static boolean testStyrke(Legemiddel legemiddel, int forventetStyrke) {
        if (legemiddel instanceof Narkotisk) {
            return ((Narkotisk) legemiddel).hentNarkoStyrke() == forventetStyrke;
        } else {
            return ((Vanedannende) legemiddel).hentVaneStyrke() == forventetStyrke;
        }
    }
}
