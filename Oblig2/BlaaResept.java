class BlaaResept extends Resept{
    String farge = "Blaa";

    public BlaaResept(Legemiddel legemiddel,Lege lege, int paisentId,int reit){
        super(legemiddel, lege, pasientId, reit);
    }

    @Override
    public String farge() {
        return farge;
    }

    @Override
    public int prisAaBetale(int pris) {
        return Math.round((int)(pris*0.25));
    }

    @Override
    public String toString() {
        return super.toString() + " Farge: " + farge + ". Rabatt: 75%.";
    }


}