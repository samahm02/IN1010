import java.util.*;

public class Lenkeliste<T> implements Liste<T> {

  public class Node {
    // peker på nodene i listen
    public Node neste;
    public Node forrige;

    public T verdi;

    // konstruktør som legger til innhold

    public Node(T verdi) {
      this.verdi = verdi;
    }

    public String toString() {
      return verdi.toString();
    }

  }

  public class LenkelisteIterator implements Iterator<T> {
    private Node peker = start;

    public boolean hasNext() {
      if (peker != null) {
        return true;
      } else {
        return false;
      }
    }

    @Override
    public T next() {
      T data = peker.verdi;
      peker = peker.neste;
      return data;

    }

  }

  // Lager strukturen for lenkelisten

  protected Node start;
  protected Node slutt;
  int storrelse;

  // returenerer storrelse som teller objekter i listen

  @Override

  public int stoerrelse() {

    return storrelse;

  }

  // legger til node i riktig posisjon

  @Override

  public void leggTil(T x) {

    Node nyNode = new Node(x);

    // sjekker om start er et null-objekt

    if (start == null) {

      start = nyNode;

    } else {

      nyNode.forrige = slutt;

      slutt.neste = nyNode;

    }

    slutt = nyNode;

    storrelse++;

  }

  // returnerer start objektet hvis mulig

  @Override

  public T hent() {

    if (storrelse != 0) {

      return start.verdi;

    } else {

      throw new UgyldigListeindeks(0);

    }

  }

  // Fjerner start objektet hvis mulig

  @Override

  public T fjern() {

    if (storrelse == 0) {

      throw new UgyldigListeindeks(0);

    }

    T verdien = start.verdi;

    if (storrelse == 1) {

      start = null;

      slutt = null;

    } else {

      start = start.neste;

    }

    storrelse--;

    return verdien;

  }

  // definerer metode for å returnere objekt i bestemt posisjon

  public Node peker(int posisjon) {

    if (posisjon > storrelse - 1 || posisjon < 0) {

      return null;

    } else {

      Node nodePeker = start;

      for (int i = 0; i < posisjon; i++) {

        nodePeker = nodePeker.neste;

      }

      return nodePeker;

    }

  }

  public Iterator<T> iterator() {

    // LenkelisteIterator iterator = new LenkelisteIterator();

    return new LenkelisteIterator();

  }

  // Bygger opp String med innholdet av listen

  public String toString() {
    String s = "";
    s += storrelse + " noder:";
    Node peker = start;
    for (int i = 0; i < storrelse; i++) {
      s += "\nNode " + String.valueOf(i) + " har verdi ";
      if (i != 0) {
        peker = peker.neste;
      }
      s += peker.toString();
    }

    return s;

  }

  public String Utskrift() {
    String streng = "";
    Node node = start;
    // Itererer over
    while (node != null) {
      if (node.toString() == null) {
        streng += "null" + "\n";
      }
      // Legger til elementene
      streng += node + "\n";
      node = node.neste;
    }
    return streng;
  }

}
