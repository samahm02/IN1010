
abstract class LenkeListe <T> implements Liste <T> {

    // leger node klassen for å holde styr på elementene
    class Node {
        Node neste = null; //peker til neste node
        Node forrige = null; // peker til forrige node
        T data; //peker til objektet som lagres

        public Node (T data) {
          this.data = data;
        }

        public String toString() {
          return data.toString();
        }
      }
    
    protected Node start= null;
    protected Node slutt= null;
    protected int stoerrelse=0;


    @Override
    public int stoerrelse(){
        return stoerrelse;
    }

    @Override
    public void leggTil(T x){
        Node ny= new Node(x);
        if(start==null){
            start=ny;
        } else {
            ny.forrige=slutt;
            slutt.neste= ny;
        }
        slutt=ny;
        stoerrelse++;
    }

    @Override
    public T hent(){
        if (stoerrelse !=0){
            return start.data;
        } else {
          throw new UgyldigListeindeks(0);
        }

    }

    @Override
    public T fjern(){
        if (stoerrelse == 0) {
          throw new UgyldigListeindeks(0);
        }
        T res = start.data;
        if (stoerrelse == 1) {
          slutt = null;
          start = null;
        } else {
          start.neste.forrige = null;
          start = start.neste;
        }
        stoerrelse --;
        return res;
      }

    public String toString() {
        String str = "";
        str += "Antall noder: " + stoerrelse;
        str += "\nInnhold:";
        Node peker = start;
        for(int i = 0; i < stoerrelse; i++) {
          str += "\n  pos = " + String.valueOf(i) + ", data = ";
          if (i != 0) {
            peker = peker.neste;
          }
          str += peker.toString();
        }
        return str;
      }

     
    //lager denne metoden for å returnere peker til Noden på posisjonen
    //denne blir brukt for å gjøre Proiritetskoe og IndeksertListe enklere
    public Node peker(int pos) {
      if (pos > stoerrelse - 1 || pos < 0) { //hvis ikke mulig
        return null;
      } else if (pos == 0) {
        return start;
      } else if (pos == stoerrelse) {
        return slutt;
      } else {
        Node peker = start;
        for (int i = 0; i < pos; i ++) {
          peker = peker.neste;
        }
        return peker;
      }
    } 


}
