abstract class Resept {
    int id;
    Legemiddel legemiddel;
    Lege utskrivendeLege;
    Pasient pasient;
    int reit;
    static int idInt;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;

        idInt ++;
        id = idInt;
    }

    public int hentId(){
        return id;
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege(){
        return utskrivendeLege;
    }

    public Pasient hentPasient(){
        return pasient;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        if (reit > 0){
            reit --;
            return true;
        } else return false;
    }

    abstract public String farge();
    
    abstract public int prisAaBetale(int pris);

    public String toString() {
        return legemiddel.hentId() + "," + utskrivendeLege.hentLegeNavn() + "," + pasient.hentId() + "," + "typen" + "," + reit;
    }
    
}
