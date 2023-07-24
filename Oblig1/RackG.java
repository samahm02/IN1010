class RackG {
    private int maksNoder =12;
    private Node[] noder;
    private int antallNoder=0;
    private boolean full = false;

    public RackG (int plasser){
        noder = new Node[maksNoder];

    }

    public void leggtil(Node nynode){
        boolean settertil = false;
        int indeks = 0;

        while (indeks<maksNoder && !settertil){
            if (noder[indeks] == null){
                noder[indeks]= nynode;
                settertil = true;
            }
        
            indeks++;
        }
            if(!settertil){
                noder= new Node[maksNoder];
            }
        
    }


    public void full(){
        if (antallNoder >= maksNoder){
            full= true;
        }
    }

    public int antProsessor(){
        int antProsessor = 0;

        for(Node node: noder){

            if(node != null){
                antProsessor += node.prosessorder(); 

            }

        }

        return antProsessor;

    }

    public int noderMedNokMinne(int paakrevdMinne){

        int antallNoder = 0;



        for(Node node: noder){

            if(node != null && node.minne()>=paakrevdMinne){

                antallNoder+=1;

            }

        }

        return antallNoder;

    }



    
    

}
