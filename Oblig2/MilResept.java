class MilResept extends HvitResept {
    
    public MilResept(Legemiddel legemiddel,Lege lege, int paisentId){
        super(legemiddel, lege, pasientId, 3);
}
@Override
    public int prisAaBetale(int pris) {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + "Type: Militaerresept" + ". Rabatt: 100%";
    }
}
