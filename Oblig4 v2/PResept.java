public class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient, reit);
    }

    @Override
    public int prisAaBetale(int pris) {
        pris -= 108;
        if (pris < 0){
            return 0;
        } else return pris;
    }

    @Override
    public String toString() {
        return legemiddel.hentId() + "," + utskrivendeLege.hentLegeNavn() + "," + pasient.hentId() + "," + "p" + "," + reit;
    }
}
