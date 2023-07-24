abstract class Legemiddel{
    protected String navn;
    protected int pris;
    protected double virkestoffer;
    private static int idTeller;
    protected int id;

    public Legemiddel(String navnPar, int prisPar, double virkestofferPar){
        navn=navnPar;
        pris=prisPar;
        virkestoffer=virkestofferPar;
        idTeller++;
        id = idTeller;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn (){
        return navn;
    }

    public int hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoffer;
    }

    public void settNyPris(int nyPris){
        pris=nyPris;
    }


public String toString(){
    return "Legemiddel: \n Id: "+ id+" \n Navn: "+navn+ " \n Pris: "+ pris+"kr. \n Virkestoff: "+ virkestoffer +"mg.";
}


}