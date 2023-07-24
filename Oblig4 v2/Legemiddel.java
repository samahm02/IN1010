abstract class Legemiddel {
    int id;
    String navn;
    int pris;
    double virkestoff;
    static int idInt;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        idInt ++;
        id = idInt;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(int nyPris) {
        pris = nyPris;
    }

    public String toString() {
        return "Legemiddel med ID " + id + ", navnet: " + navn + ", prisen: " + pris + " kr og virkestoffet: " + virkestoff + " mg.";
    }
}
