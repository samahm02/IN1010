class Lege {
        
    String navn;

    public Lege(String na){
        navn = na;
    }

    public String hentLege(){
        return navn;
    }

    public String toString(){
        return "Lege: Navn- " + navn;
    }

}
