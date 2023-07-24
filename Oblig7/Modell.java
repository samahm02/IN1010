class Modell {
    private GUI gui;
    private Kontroll kontroll;
    private Koe<Slange> slange = new Koe<>();
    private Slange ny;
    private boolean SpillStatus;
    private SpesialRute[] spesial = new SpesialRute[10];

    public Modell(GUI gui, Kontroll kontroller) {
        this.gui = gui;
        this.kontroll = kontroller;
    }

    public void startSpill() { 
        SpillStatus = true;
        kontroll.tegnBrett();
    }

      public Koe<Slange> hentSlange(){
          return slange;
        }

      public int hentHalelengde(){
          return slange.storrelse();
        }
  
      public Slange hentHode(){
          for (Slange del : slange){
              if (del.Hode()) return del;
            }
  
          return null;
      }


    public void avsluttSpill(){
        SpillStatus = false;
    }

    public boolean spillKjorer(){
        return SpillStatus;
    }

    public SpesialRute[] hentSpesialRuter(){
        return spesial;
    }
    public void leggTilSpesialRuter(int pos, SpesialRute skatt){
        spesial[pos] = skatt;
    }


    public void fjernSpesialRuter(int pos){
        spesial[pos] = null;
    }


    public void forlengSlange(Slange del){
        slange.leggTil(del);
    }

     
     public void forkortSlange(){
         slange.fjern();
        }
 
        public void leggTilSpesialRuter(int pos) {
            int skatt_rad = 0;
            int skatt_kolonne = 0;
            boolean ingen_treff = false;
    
            while (true) {
                for (Slange del : slange) {
                    for (SpesialRute skatt : spesial) {
                        if (skatt != null) {
                            skatt_rad = SpesialRute.trekk(0, 11);
                            skatt_kolonne = SpesialRute.trekk(0, 11);
                            if (skatt_rad != del.hentRad() && skatt_rad != skatt.hentRad() &&
                            skatt_kolonne != del.hentKolonne() && skatt_kolonne != skatt.hentKolonne()){
                                 ingen_treff = true;
                            }
                            else ingen_treff = false;
                        }
                    }
                }
    
                if (ingen_treff) break;
            }
    
            spesial[pos] = new SpesialRute(skatt_rad, skatt_kolonne); 
        }
    
        public boolean kollisjon() {
            for (int i = 0; i < spesial.length; i++) {
                for (Slange del : slange) {
                    if (spesial[i] != null) {
                        if (spesial[i].hentRad() == del.hentRad() && spesial[i].hentKolonne() == del.hentKolonne()) {
                            fjernSpesialRuter(i);
                            leggTilSpesialRuter(i);
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public void selvKolisjon() {
            if (slange.storrelse() > 1) {
                for (Slange del : slange) {
                    if (hentHode().hentRad() == del.hentRad() && hentHode().hentKolonne() == del.hentKolonne() && !del.Hode()){
                     avsluttSpill();
                    }
                }
            }
        }


    public void opp() {
        if (hentHode().hentRad() - 1 < 0){
            avsluttSpill();}
                if (slange.storrelse() == 1) {
                    ny = new Slange(hentHode().hentRad() - 1, hentHode().hentKolonne(), true);
                    if (!kollisjon()){
                        slange.fjern();}
                    for (Slange del : slange){
                        del.fjernHode();}
                    slange.leggTil(ny);
                    return;
                }

                ny = new Slange(hentHode().hentRad() - 1, hentHode().hentKolonne(), true);
                for (Slange del : slange){
                    del.fjernHode();}
                if (!kollisjon()){
                    slange.fjern();}
                slange.leggTil(ny); 
       
    }
    
    public void ned() {
        if (hentHode().hentRad() + 1 > 11){
            avsluttSpill();}
        
        if (slange.storrelse() == 1) {
                    ny = new Slange(hentHode().hentRad() + 1, hentHode().hentKolonne(), true);
                    if (!kollisjon()){
                        slange.fjern();}
                    for (Slange del : slange){
                        del.fjernHode();}
                    slange.leggTil(ny);
                    return;
                }

                ny = new Slange(hentHode().hentRad() + 1, hentHode().hentKolonne(), true); 
                for (Slange del : slange){
                    del.fjernHode();}
                if (!kollisjon()){
                    slange.fjern();}
                slange.leggTil(ny);
       
    }

    public void venstre() {
        if (hentHode().hentKolonne() - 1 < 0){
            avsluttSpill();}

                if (slange.storrelse() == 1) {
                    ny = new Slange(hentHode().hentRad(), hentHode().hentKolonne() - 1, true);
                    if (!kollisjon()){
                        slange.fjern();}
                    for (Slange del : slange){
                        del.fjernHode();}
                    slange.leggTil(ny);
                    return;
                }

                ny = new Slange(hentHode().hentRad(), hentHode().hentKolonne() - 1, true);
                for (Slange del : slange){
                    del.fjernHode();}
                if (!kollisjon()){
                    slange.fjern();}
                slange.leggTil(ny);
       
    }

    public void hoyre() {
        if (hentHode().hentKolonne() + 1 > 11){
            avsluttSpill();}
                
        if (slange.storrelse() == 1) {
            ny = new Slange(hentHode().hentRad(), hentHode().hentKolonne() + 1, true);
            if (!kollisjon()){
                slange.fjern();}
            for (Slange del : slange){
                del.fjernHode();}
            slange.leggTil(ny);
            return;
        }

        ny = new Slange(hentHode().hentRad(), hentHode().hentKolonne() + 1, true);
        for (Slange del : slange) del.fjernHode();
        if (!kollisjon()){
            slange.fjern();}
        slange.leggTil(ny);
        
    }

}

