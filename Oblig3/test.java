class test {
    public static void main(String[] args) {
        IndeksertListe<String> koe = new IndeksertListe<>();
        Prioritetskoe<String> pkoe = new Prioritetskoe<>();
        pkoe.leggTil("A");
        koe.leggTil("A");
        pkoe.leggTil("A");
        // pkoe.leggTil("C");

        // pkoe.fjern();
        // pkoe.hent();
        System.out.println(pkoe.stoerrelse());
        System.out.println(koe.stoerrelse());



    }
}
