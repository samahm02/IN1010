class Bil3 {
    static String bilnummer;


    public Bil3 (String bn){
        bilnummer= bn;


    }
    public String navn(){ 
        return("Jeg er en Bil og nummeret mitt er "+ bilnummer);

    }
    public static String hentNummer(){
        return bilnummer;
    
    }

}
    

