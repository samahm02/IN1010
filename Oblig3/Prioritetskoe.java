class Prioritetskoe<T extends Comparable <T>> extends LenkeListe <T>{
    
    @Override
    public void leggTil(T x){ 
        if (stoerrelse==0){ //hvis den er tom så bare setter vi den inn som vanlig
            super.leggTil(x);
            return;
        } Node peker = start;
        for (int i = 0; i < stoerrelse ; i++) {
          if (i != 0) {
            peker = peker.neste;
          }
          if (peker.data.compareTo(x) > 0) { //hvis data på posisjonen har større prioritet enn x
            leggTilFraIndeksertListe(i, x);
            return;
          }
          if ((stoerrelse!=0 && peker.data.compareTo(x) < 0)){
            super.leggTil(x);
            return;
          }
        }
    }



      // kopierer inn fra IndeksertListe for bruk i denne klassen
      private void leggTilFraIndeksertListe (int pos, T x) { 
        Node ny = new Node(x);
        Node peker = peker(pos); //henter peker til posisjonen
        if (pos > stoerrelse || pos < 0) { //hvis ikke mulig
          throw new UgyldigListeindeks(pos);
        } else if (pos == stoerrelse) { //hvis ny slutt
          leggTil(x);
          return;
        } else if (pos == 0) { //hvis ny start
          ny.neste = start;
          start.forrige = ny;
          start = ny;
        } else {
          ny.neste = peker;
          ny.forrige = peker.forrige;
          peker.forrige.neste = ny;
          peker.forrige = ny;
        }
        stoerrelse ++;
      }
}
