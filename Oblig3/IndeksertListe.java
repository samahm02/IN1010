class IndeksertListe <T> extends LenkeListe<T> { 
    
    public void leggTil (int pos, T x) {
      Node ny = new Node(x);
      Node peker = peker(pos); //henter peker til posisjonen
      if (pos > stoerrelse || pos < 0) { //hvis det ikke mulig så sender vi posisjonen til ugyldigListeindeks
        throw new UgyldigListeindeks(pos);
      } else if (pos == stoerrelse) { //hvis det er ny slutt så bare legger vi til via lenkeliste
        leggTil(x);
        return;
      } else if (pos == 0) { //hvis det er ny start
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

    public void sett (int pos, T x) {
        Node ny = new Node(x);
        Node peker = peker(pos); //henter peker til posisjonen
        if (pos > stoerrelse - 1 || pos < 0) { //hvis ikke mulig
        throw new UgyldigListeindeks(pos);
      } else if (stoerrelse == 1) { //hvis elementet er både slutt og start
        start = ny;
        slutt = ny;
      } else if (peker.neste == null) { //hvis elementet er det siste i listen
        ny.forrige = slutt.forrige;
        slutt.forrige.neste = ny;
        slutt = ny;
      } else if (peker.forrige == null) { //hvis elementet er den første
        ny.neste = start.neste;
        start.neste.forrige = ny;
        start = ny;
      } else {
        ny.neste = peker.neste;
        ny.forrige = peker.forrige;
        peker.forrige.neste = ny;
        peker.neste.forrige = ny;
      }

    } 

    public T hent (int pos) {
        if (pos > stoerrelse - 1 || pos < 0) { //hvis ikke mulig
            throw new UgyldigListeindeks(pos);
          }
          Node peker = peker(pos); //henter peker til posisjonen
          return peker.data;
        }

    
    public T fjern (int pos) { 
        Node peker = peker(pos); //henter peker til posisjonen
      if (pos > (stoerrelse - 1) || pos < 0 || stoerrelse == 0) { //hvis ikke mulig
        throw new UgyldigListeindeks(pos);
      } else if (stoerrelse == 1) { //hvis elementet er det eneste i listen
        start = null;
        slutt = null;
      } else if (peker.neste == null) { //hvis elementet er det siste i listen
        slutt.forrige.neste = null;
        slutt = slutt.forrige;
      } else if (peker.forrige == null) { //hvis elementet er første i listen
        start.neste.forrige = null;
        start = start.neste;
      } else {
        peker.neste.forrige = peker.forrige;
        peker.forrige.neste = peker.neste;
      }
      stoerrelse --;
      return peker.data;
    }


    } 

