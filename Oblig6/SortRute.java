public class SortRute extends Rute {

    public SortRute(int y, int x, Labyrint lab) {
        super(y, x, lab);
    }

    public char tegn = '#';
    
    @Override
    public char hentChar() {
        return tegn;
    }

    @Override
    public void finn(Rute fra) {
        System.out.println("Kan ikke starte i en sort rute!");
    }

    @Override
    public String toString() {
        String print = "Svart rute at: (" + x + ", " + y + ")";
        return print;
    }
}