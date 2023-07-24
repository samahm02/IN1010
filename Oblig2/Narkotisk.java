class Narkotisk extends Legemiddel {
    protected int styrke;

    public Narkotisk(String navnPar, int prisPar, double virkestofferPar, int narkoStyrke){
        super(navnPar,prisPar,virkestofferPar); 
        styrke= narkoStyrke;
    }

    public int hentNarkoStyrke(){
        return styrke;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Type: Narkotisk. Styrke: " + styrke;
    }
}
