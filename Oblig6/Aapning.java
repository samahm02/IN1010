import java.util.ArrayList;

public class Aapning extends HvitRute {

    public Aapning(int rad, int kolonne, Labyrint lab) {
        super(rad, kolonne, lab);
    }

    public char tegn = '.';

    @Override
    public char hentChar() {
        return tegn;
    }

    @Override
    public void finn(Rute fra) {
        if (fra == null) {
            labyrint.utgang.add(this);
            Rute denne = this;
            ArrayList<Rute> hvite = hviteRuter(denne);
            for (Rute rute : hvite) {
                rute.finn(this);
            }
        } else {
            System.out.println(this);
            labyrint.utgang.add(this);
        }

    }

    @Override
    public String toString() {
        String print = "Aapning at: (" + x + ", " + y + ")";
        return print;
    }

}