class Vanedannende extends Legemiddel {
    protected int styrke;

    public Vanedannende(String navnPar, int prisPar, double virkestofferPar, int vaneStyrke){
        super(navnPar,prisPar,virkestofferPar); 
        styrke= vaneStyrke;
    }

    public int hentVaneStyrke(){
        return styrke;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Type: Vanedannende. Styrke: " + styrke;
    }
}
