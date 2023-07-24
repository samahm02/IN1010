public class Stabel<T> extends Lenkeliste<T> {

    //Lagrer (T x) i / "som" første node
    public void leggTil (T x) {
        Node nyNode = new Node(x);
        if (start == null) {
          //hvis start == null eller storrelse oppretter jeg listen slik. 
          start = nyNode;
          slutt = nyNode;
        } else if (storrelse == 1) {
            start = nyNode;
            nyNode.neste = slutt;
            slutt.forrige = nyNode;
        } else {
            start.forrige = nyNode;
            nyNode.neste = start;
            start = nyNode;
        }
        storrelse ++;
    }
}
