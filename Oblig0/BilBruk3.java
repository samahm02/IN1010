class BilBruk3 {
    public static void main(String[] args){
        String bilnummer = "AS21235";
        Bil3 bil= new Bil3(bilnummer);
        Person person = new Person(bil);
        person.navn();

    }
}
