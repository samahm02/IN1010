public class Vanedannende extends Legemiddel {

    int styrke;

    public Vanedannende(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }

    @Override
      public String toString() {
        return navn + "," + "Vanedannende" + "," + pris + "," + virkestoff + "," + styrke;
      }
}