public class MilResept extends HvitResept{

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    @Override
    public int prisAaBetale(int pris) {
        return 0;
    }

    @Override
    public String toString() {
        return legemiddel.hentId() + "," + utskrivendeLege.hentLegeNavn() + "," + pasient.hentId() + "," + "milResept" + "," + reit;
    }
}
