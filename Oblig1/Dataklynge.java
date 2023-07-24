import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

class Dataklynge {
    private ArrayList<Rack> racks = new ArrayList<Rack>();

public Dataklynge (String filnavn) {
    Scanner sc =null;
    try{
        sc= new Scanner(new File(filnavn));
        while (sc.hasNext() == true) {
            nyeNoder(sc.nextInt(), sc.nextInt(), sc.nextInt());
          }
    } catch (Exception e){
        System.out.println("Fant ikke filen, endre filnavnet og prøv igjen!");
        System.exit(1);
    }
    
    }

      public void nyeNoder(int antallNoder, int prosessorer, int minne) {
       if(minne>4096 || prosessorer>16 ){
        System.out.println("Desverre så har filen din feil i dataen. Hå en fin dag!");
        System.exit(1);
        }
        int teller = 0;
        while (teller < antallNoder) {
          nyNode(minne, prosessorer);
          teller += 1;
        }
      }

      public void nyNode(int minne, int prosessorer) {
        for (Rack rack : racks) {
          if (rack.full() == false) {
            rack.leggTil(new Node(minne, prosessorer));
            return;
          }
        }
        Rack nyRack = new Rack();
        nyRack.leggTil(new Node(minne, prosessorer));
        racks.add(nyRack);
      }

      public int antNoder() {
        int teller = 0;
        for (Rack rack : racks) {
          for (Node node : rack.hentNoder()) {
            if (node != null) {
            teller += 1;
            }
          }
        }
        return teller;
      }

      public int antProsessorer() {
        int teller = 0;
        for (Rack rack : racks) {
          for (Node node : rack.hentNoder()) {
            if (node != null) {
            teller += node.prosessorder();
            }
          }
        }
        return teller;
      }
    
      public int antRacks() {
        return racks.size();
      }
    
      public int noderMedNokMinne(int paakrevdMinne) {
        int teller = 0;
        for (Rack rack : racks) {
          for (Node node : rack.hentNoder()) {
            if (node != null) {
              if (node.minne() >= paakrevdMinne) {
                teller += 1;
              }
            }
          }
        }
        return teller;
      }
    
    }

