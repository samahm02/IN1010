class Vanlig extends Legemiddel {
    public Vanlig(String navnPar, int prisPar, double virkestofferPar){
        super(navnPar,prisPar,virkestofferPar); 
    }
    
    @Override
    public String toString() {
        return super.toString() + "Type: Vanlig";
}
}
