abstract class Resept{
    protected int Id;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected static int pasientId;
    protected int reit;
    protected static int idTeller;

    public Resept(Legemiddel legemiddelPar,Lege legePar, int paisentIdPar,int reitpar){
        legemiddel=legemiddelPar;
        lege=legePar;
        pasientId=paisentIdPar;
        reit=reitpar;
        idTeller++;
        Id = idTeller;
    }
    public int hentId() {
        return Id;
    }
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }
    public Lege hentLege() {
        return lege;
    }
    public int hentPasientId() {
        return pasientId;
    }
    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit == 0) {
            return false;
        } else {
            reit -- ;
            return true;
        }
    }
    
    abstract public String farge();
    
    abstract public int prisAaBetale(int pris);

    public String toString() {
        return "Resept: \nID: " + Id + ". \nLegemiddel: " + legemiddel + ". \nLege: " + lege + ". \nPasientId: " + pasientId + ". Reit: " + reit + ".";
    }



}