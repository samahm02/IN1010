class Pasient {
    private String navn, fodselsnr;
    private int id;
    private Stabel<Resept> resepter = new Stabel<>();
    private static int idTeller=1;

    public Pasient(String navn, String fodselsnr){
        this.navn=navn;
        this.fodselsnr=fodselsnr;
        id=idTeller;
        idTeller++;
        
    }
    public void nyResept (Resept resept){
        resepter.leggTil(resept);

    }
    public Stabel<Resept> hentResepter(){
        return resepter;
    }

    public int hentId(){
        return id;
    }

    public String hentNavn(){
        return navn;
    }

    public String hentFodselsnr(){
        return fodselsnr;
    }

    public String toString(){
        String s = navn + "," + fodselsnr;
        return s;
    }
}
