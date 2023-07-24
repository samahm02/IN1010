class PResept extends HvitResept {
    
    public PResept(Legemiddel legemiddel,Lege lege, int paisentId,int reit){
        super(legemiddel, lege, pasientId, reit);
}
@Override
    public int prisAaBetale(int pris) {
        pris -=108;
        if (pris<0){
            return 0;
        } else {
            return pris;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Type: P-resept" + ".Rabatt: 108 kr.";
    }
}
