public class Prioritetskoe<T extends Comparable<T>> extends  Lenkeliste<T> {

  @Override
  public void leggTil(T x) {
      if (storrelse == 0) {
          super.leggTil(x);
          return;
      }
      Node peker = start;
      for (int i = 0; i < storrelse ; i++) {
        if (i != 0) {
          peker = peker.neste;
        }
        if (peker.verdi.compareTo(x) > 0) {
          leggTil(i, x);
          return;
        }
      }
      super.leggTil(x);
  }

    //bruker leggTil metoden fra IndeksertListe for å utfylle logikken
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
}