import java.util.Iterator;

public class Koe<T> implements Iterable<T> {
    class Node {
        public Node neste = null;
        public Node forrige = null;
        T data;
        Node (T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    class KoeIterator implements Iterator<T> {
        private int pos = 0;

        @Override
        public T next() {
            pos++;
            return hent(pos - 1);
        }

        @Override
        public boolean hasNext() { return pos < storrelse(); }
    }

    protected Node start = null;
    protected Node slutt = null;
    protected int storrelse = 0;

    
    @Override
    public Iterator<T> iterator(){
        return new KoeIterator();
    }

   
    public int storrelse(){
        return storrelse;
    }


    public T hent(int pos) {
        Node node = start;

        if (pos < 0 || pos > storrelse - 1) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < pos; i++) {
            node = node.neste;
        }

        return node.data;
    }

    public void leggTil(T data) {
        Node ny = new Node(data);

        if (start == null) {
            start = ny;
            slutt = ny;
            start.neste = ny;
            slutt.forrige = ny;
        } 
        else if (storrelse() == 1) {
            start.neste = null;
            ny.neste = slutt;
            slutt.forrige = ny;
            start = ny;
        }
        else {
            start.forrige = ny;
            ny.neste = start;
            start = ny;
        }

        storrelse++;
    }

    
    public void fjern() {
        if (storrelse == 0){
            throw new IndexOutOfBoundsException(); }
        else if (storrelse == 1) {
            start.neste = null;
            slutt.forrige = null;
            start = null;
            slutt = null;
        } else {
            slutt.forrige.neste = null;
            slutt = slutt.forrige;
        }
        storrelse--;
    }

    @Override
    public String toString() { 
        String utskrift = " ";
        int teller = 0;
        for (T x : this) {
            if (teller != storrelse - 1) utskrift += x + " , ";
            else utskrift += x;
        }
        utskrift += ". ";
        return utskrift;
    }
}