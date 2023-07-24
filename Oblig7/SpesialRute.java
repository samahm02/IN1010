public class SpesialRute extends Rute {

    public SpesialRute(int rad, int kolonne) {
        super(rad, kolonne);
    }

    static int trekk(int min, int max){
        return (int)(Math.random()*(max - min + 1)) + min;
    } 
}