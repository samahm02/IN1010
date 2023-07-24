class Node {
    private int minne;
    private int antallPros;

    public Node(int minneStorelse,int pros){
    minne=minneStorelse;
    antallPros=pros;
    }
    
    public int prosessorder(){
        return antallPros;

    }

    public int minne(){
        return minne;

    }
    
}
