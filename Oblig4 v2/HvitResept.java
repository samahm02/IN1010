public class HvitResept extends Resept{
    String farge = "Hvit";

    public HvitResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
      super(legemiddel, lege, pasient, reit);
    }

    @Override
    public String farge() {
        return farge;
    }

    @Override
    public int prisAaBetale(int pris) {
        return pris;
    }

    @Override
    public String toString() {
        return legemiddel.hentId() + "," + utskrivendeLege.hentLegeNavn() + "," + pasient.hentId() + "," + farge + "," + reit;
    }

}
