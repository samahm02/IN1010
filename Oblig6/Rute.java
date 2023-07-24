import java.util.ArrayList;

abstract class Rute {

    int y;
    int x;
    Labyrint labyrint;
    Rute nord, sor,vest,ost ;
    public char tegn;
    

    public Rute(int y, int x, Labyrint lab) {
        this.y = y;
        this.x = x;
        labyrint = lab;
    }

    public char hentChar() {
        return tegn;
    }

    public ArrayList<Rute> hviteRuter(Rute rute) {
        ArrayList<Rute> apningsListe = new ArrayList<>();
        if (rute.nord instanceof HvitRute || rute.nord instanceof Aapning) {
            apningsListe.add(rute.nord);
        }
        if (rute.ost instanceof HvitRute || rute.ost instanceof Aapning) {
            apningsListe.add(rute.ost);
        }
        if (rute.sor instanceof HvitRute || rute.sor instanceof Aapning) {
            apningsListe.add(rute.sor);
        }
        if (rute.vest instanceof HvitRute || rute.vest instanceof Aapning) {
            apningsListe.add(rute.vest);
        }
        return apningsListe;
    }

    public Rute hentRute(int y, int x) {
        try {
            Rute retur = labyrint.ruter[y - 1][x - 1];
            return retur;
        } catch (Exception e) {
            System.out.println("feil i hentRute");
            return null;
        }
    }

    public void finn(Rute fra) {
    }

    @Override
    public String toString() {
        String print = "Rute at: (" + x + ", " + y + ")";
        return print;
    }

}