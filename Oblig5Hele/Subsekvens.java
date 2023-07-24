public class Subsekvens {
    public final String subsekvens;
    public int antall = 0;

    public Subsekvens(String s) {
        subsekvens = s;
    }

    public int hentAntall() {
        return antall;
    }

    public void settNyttAntall(int nyAntall) {
        antall = nyAntall;
    }

    public void leggTilAntall(int nyAntall) {
        antall += nyAntall;
    }
    public String hentSubsekvens(){
        return subsekvens;
    }

    public String toString() {
        return "(" + subsekvens + "," + antall + ")";
    }
}