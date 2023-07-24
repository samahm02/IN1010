public class Slange extends Rute {
    private boolean Hode;

    public Slange(int rad, int kolonne, boolean Hode) {
        super(rad, kolonne);
        this.Hode = Hode;
    }

    public void settHode(){
        Hode = true;
    }

    public void fjernHode(){
        Hode = false;
    }

    public boolean Hode(){
        return Hode;
    }
}