public class Kontroll {
    
    private GUI gui;
    private Modell modell;
    private boolean OPP;
    private boolean HOYRE;
    private boolean NED;
    private boolean VENSTRE;

    public Kontroll() {
        gui = new GUI(this);
        modell = new Modell(gui, this);
    }

    public void spill() throws InterruptedException {
        modell.startSpill();
        Spiller();
    }

    public void Spiller() throws InterruptedException {
        while (modell.spillKjorer()) {
            Thread.sleep(1000);
            oppdater();
            if (!modell.spillKjorer()) break;
            gui.ResetBrett();
        }
    }

    public SpesialRute[] hentSpesialRuter(){
        return modell.hentSpesialRuter();
    }

    public void tegnBrett() {
        gui.tegnBrett();
    }

    public Koe<Slange> hentSlange(){
        return modell.hentSlange();
    }


    public int hentHalelengde(){
        return modell.hentHalelengde();
    }


    public Slange hentHode(){
        return modell.hentHode();
    }

    public void forlengSlange(Slange del){
        modell.forlengSlange(del);
    }

    public void leggTilSpesialRuter(int pos, SpesialRute spesial){
        modell.leggTilSpesialRuter(pos, spesial);
    }


    public void avsluttSpillet() {
        System.exit(0);
    }

    public void oppdater() {
        bevege();
        selvKolisjon();
    }

    public void selvKolisjon() {
        modell.selvKolisjon();
    }

    public void bevege(){
        if (OPP){
            modell.opp();
        }
        if (NED){
            modell.ned();
        }
        if (HOYRE){
            modell.hoyre();
        }
        if (VENSTRE){
            modell.venstre();
        }
    }

    public void bevegelse(String retning) {
        if(retning=="opp"){
            OPP = true;
            HOYRE = false;
            NED = false;
            VENSTRE = false;
        } else if (retning=="ned"){
            OPP = false;
            HOYRE = false;
            NED = true;
            VENSTRE = false;
        }else if (retning=="hoyre"){
            OPP = false;
            HOYRE = true;
            NED = false;
            VENSTRE = false;
        }else if (retning=="venstre"){
            OPP = false;
            HOYRE = false;
            NED = false;
            VENSTRE = true;
        }
    }

   
}
