import java.util.ArrayList;

public class HvitRute extends Rute {

    public HvitRute(int rad, int kolonne, Labyrint lab) {
        super(rad, kolonne, lab);
    }

    public char tegn = '.';

    @Override
    public char hentChar() {
        return tegn;
    }

    @Override
    public void finn(Rute fra) {
        Rute start = fra;
        Rute denne = this;
        ArrayList<Rute> hvite = hviteRuter(denne);
        if (hvite.size() == 0) { 
            System.out.println("Ingen veier ut!");
        }
        for (Rute rute : hvite) {
            if (hvite.size() == 1) {
                if (fra == null) {
                    rute.finn(this);
                } 
            } else {
                if (rute == start) {  
                } else {
                    rute.finn(this);
                }

            }

        }
    }

    @Override
    public String toString() {
        String print = "Hvit rute at: (" + x + ", " + y + ")";
        return print;
    }
}