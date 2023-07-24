
class Hovedprogram{
    public static void main(String[] args){
    Dataklynge test = new Dataklynge("dataklynge2.txt");
    System.out.println();
    System.out.println("Antall noder med minst 128GB: " + test.noderMedNokMinne(128));
    System.out.println("Antall noder med minst 512GB: " + test.noderMedNokMinne(512));
    System.out.println("Antall noder med minst 1024GB: " + test.noderMedNokMinne(1024));
    System.out.println();
    System.out.println("Antall prosessorer: " + test.antProsessorer());
    System.out.println("Antall racks: " + test.antRacks());
  }
}
