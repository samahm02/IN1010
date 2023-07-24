public class Rute {
    protected int rad, kolonne;
    protected final int rutestorrelse = 12;

    public Rute(int rad, int kolonne){
        this.rad = rad;
        this.kolonne = kolonne;
    }

    public int hentRad(){
        return rad; 
    }

    public int hentKolonne(){
        return kolonne; 
    }

    public void endreRad(int rad){
        this.rad = rad; 
    }

    public void endreKolonne(int kolonne){
        this.kolonne = kolonne;
    }

    @Override
    public String toString(){
        return "(" + rad + "," + kolonne + ")"; 
    }
}