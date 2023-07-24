public class IndeksertListe < T > extends Lenkeliste < T > {

  public void leggTil(int pos, T x) {
      Node nyNode = new Node(x);
      Node peker = peker(pos);

      if (pos < 0 || pos > storrelse) {
          throw new UgyldigListeindeks(pos);
      } else if (pos == storrelse) {
          //gyldig for pos<=stoerrelse()
          super.leggTil(x);
          return;
      } else if (pos == 0) {
          //forskyver første element
          nyNode.neste = start;
          start.forrige = nyNode;
          start = nyNode;
      } else {
          //legges inn i pos
          nyNode.neste = peker;
          nyNode.forrige = peker.forrige;
          peker.forrige.neste = nyNode;
          peker.forrige = nyNode;
      }
      storrelse += 1;
  }

  public void sett(int pos, T x) {
      Node nyNode = new Node(x);
      Node peker = peker(pos);

      //Lovlig pos er 0<=pos<stoerrelse()
      if (pos < 0 || pos > storrelse - 1) {
          throw new UgyldigListeindeks(pos);
      } else if (peker == slutt) {
          //hvis peker er slutt elementet
          nyNode.forrige = slutt.forrige;
          slutt.forrige.neste = nyNode;
          slutt = nyNode;
      } else if (storrelse == 1) {
          //eksepsjonstilfelle samme element er start / slutt
          slutt = nyNode;
          start = nyNode;
      } else if (peker == start) {
          //hvis peker er start elementet
          nyNode.neste = start.neste;
          start.neste.forrige = nyNode;
          start = nyNode;
      } else {
          //inne i listen
          nyNode.neste = peker.neste;
          nyNode.forrige = peker.forrige;
          peker.forrige.neste = nyNode;
          peker.neste.forrige = nyNode;
      }
  }

  public T hent(int pos) {
      //0<=pos<stoerrelse()
      if (pos > storrelse - 1 || pos < 0) {
          //dersom pos ikke er i listen
          throw new UgyldigListeindeks(pos);
      }
      //henter noden og returnerer verdien
      Node peker = peker(pos);
      return peker.verdi;
  }

  public T fjern(int pos) {
    //0<=pos<stoerrelse())
    Node peker = peker(pos);
    //System.out.println("kazooops");
  
    if (pos < 0 || pos > storrelse - 1 || storrelse == 0) {
        throw new UgyldigListeindeks(pos);
    } else if (storrelse == 1) {
        //dersom kun et element i listen
        start = null;
        slutt = null;
    } else if (peker == start) {
      start = start.neste;
    } else if (peker == slutt) { 
      slutt = slutt.forrige;
    } else {
      peker.neste.forrige = peker.forrige;
      peker.forrige.neste = peker.neste;
    }
    storrelse --;
    return peker.verdi;
  }
}