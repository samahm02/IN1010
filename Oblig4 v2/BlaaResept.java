public class BlaaResept extends Resept {

    String farge = "Blaa";

    public BlaaResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    @Override
    public String farge() {
        return farge;
    }

    @Override
    public int prisAaBetale(int pris) {
        float prisen = pris;
        prisen = Math.round(prisen*0.25);
        pris = (int) prisen;
        return pris;
    }

    @Override
    public String toString() {
        return legemiddel.hentId() + "," + utskrivendeLege.hentLegeNavn() + "," + pasient.hentId() + "," + farge + "," + reit;
    }

}