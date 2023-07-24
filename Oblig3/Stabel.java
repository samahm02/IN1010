class Stabel<T> extends LenkeListe<T> {
    
    public void leggTil(T x){
        Node ny= new Node(x);
        if (stoerrelse == 0) {
            start=ny;
            slutt=ny;
        }if (stoerrelse == 1) {
              start = ny;
              ny.neste=slutt;
              slutt.forrige=ny;
            } else {
            start.forrige=ny;
            ny.neste=start;
            start=ny;
            }
            stoerrelse ++;
          
    }
}
