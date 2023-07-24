class Rack {
    private int maksNoder =12;
    private Node[] noder;
    private int antallNoder=0;
    private boolean full = false;

    public Rack(){
        noder = new Node[maksNoder];
    }

    public void leggTil(Node node){
        if (!full) {
          noder[antallNoder] = node;
          antallNoder += 1;
        }
        if (antallNoder == maksNoder) {
          full = true;
        }
      }

    public Node[] hentNoder() {
        return noder;
      }


    public boolean full(){
      return full;
    }

    
}


