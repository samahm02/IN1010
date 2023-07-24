class Spesialist extends Lege implements Godkjenningsfritak{
    
    String kontrollID;

    public Spesialist(String navn, String kontrollIDPar){
        super(navn);
        kontrollID = kontrollIDPar;
    }

    @Override
    public String hentKontrollID(){
        return kontrollID;
    }

    @Override
    public String toString(){
        return super.toString() + "Type: Spesialist. KontrollID: " + kontrollID;
    }
}
