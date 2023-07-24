import java.io.*;
import java.util.*;

class Legesystem {
  private static Lenkeliste<Pasient> pasientListe = new Lenkeliste<>();
  private static Lenkeliste<Legemiddel> legemiddelListe = new Lenkeliste<>();
  private static IndeksertListe<Lege> legeListe = new IndeksertListe<>();
  private static Lenkeliste<Resept> reseptListe = new Lenkeliste<>();

  // definerer static meode for aa lese frs fil og finne seksjoner
  private static void lesFraFil(String filnavn) {
    HashMap<Integer, Pasient> pasienterMap = new HashMap<>();
    HashMap<Integer, Legemiddel> legemidlerMap = new HashMap<>();
    HashMap<String, Lege> legerMap = new HashMap<>();

    File fil = new File(filnavn);
    String objType = "tom";
    Scanner scannerInst;

    try {
      scannerInst = new Scanner(fil, "UTF-8");
    } catch (FileNotFoundException e) {
      System.out.println("Filen kunne ikke leses av..");
      return;
    }

    while (scannerInst.hasNextLine()) {
      String linje = scannerInst.nextLine();
      // Sjekker om det er en objekt type..
      if (linje.startsWith("#")) {
        //Sjekker hvilken objekttype det er..
        if (linje.startsWith("# Pasienter")) {
          objType = "pasienter";
        } else if (linje.startsWith("# Legemidler")) {
          objType = "legemidler";
        } else if (linje.startsWith("# Leger")) {
          objType = "leger";
        } else if (linje.startsWith("# Resepter")) {
          objType = "resepter";
        } else {
          //feilmelding hvis ingen av if testene teffer
          System.out.println("Kunne ikke lose i linje " + linje);
        }
      }
      // ellers
      else {
        switch (objType) {
          case "pasienter":
            try {
              String[] data = linje.strip().split(",");
              String navn = data[0];
              String foedselsnr = data[1];
              Double sjekk = Double.valueOf(foedselsnr);
              if (foedselsnr.length() == 11) {
                Pasient pasient = new Pasient(navn, foedselsnr);
                pasientListe.leggTil(pasient);
                pasienterMap.put(pasient.hentId(), pasient);
              } else {
                System.out.println("Feil lengde paa foedselsnummer: " + linje);
              }
            } catch (Exception e) {
              System.out.println("Kunne ikke lose i linje " + linje);
            }

            break;
          case "legemidler":
            // sorger for at riktig format har blitt brukt
            try {
              String[] data = linje.strip().split(",");
              String navn = data[0];
              String type = data[1];
              int pris = (int) Math.round(Double.valueOf(data[2]));
              double virkestoff = Double.valueOf(data[3]);
              int styrke = 0;
              // enda en sjekk for riktig format
              int sjekk = 1;
              Legemiddel legemiddel = null;
              switch (type) {
                case "vanedannende" -> {
                  styrke = Integer.valueOf(data[4]);
                  legemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
                }
                case "narkotisk" -> {
                  styrke = Integer.valueOf(data[4]);
                  legemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
                }
                case "vanlig" -> legemiddel = new Vanlig(navn, pris, virkestoff);
                default -> sjekk = 0;
              }
              if (sjekk == 1) {
                // legger endelig til
                legemiddelListe.leggTil(legemiddel);
                legemidlerMap.put(legemiddel.hentId(), legemiddel);
              } else {
                System.out.println("Feil type legemiddel: " + linje);
              }
            } catch (Exception e) {
              System.out.println("Kunne ikke lose i linje " + linje);
            }

            break;
          case "leger":
            // sorger for at riktig format har blitt brukt
            try {
              String[] data = linje.strip().split(",");
              String navn = data[0];
              String kontrollId = data[1];
              Lege lege;
              if (Integer.valueOf(kontrollId) == 0) {
                lege = new Lege(navn);
              } else {
                lege = new Spesialist(navn, kontrollId);
              }
              legeListe.leggTil(lege);
              legerMap.put(lege.hentLegeNavn(), lege);
            } catch (Exception e) {
              System.out.println("Kunne ikke lose i linje " + linje);
            }

            break;
          case "resepter":
            // sorger for at riktig format har blitt brukt
            try {
              String[] data = linje.strip().split(",");
              Legemiddel legemiddel = legemidlerMap.get(Integer.valueOf(data[0]));
              Lege lege = legerMap.get(data[1]);
              Pasient pasient = pasienterMap.get(Integer.valueOf(data[2]));
              String type = data[3];
              int reit = 0; // plassholder
              // soerger for at legemiddel, leger og pasienter finnes
              if (legemiddel == null || lege == null || pasient == null) {
                throw new Exception();
              }
              // enda en sjekk for riktig format
              int sjekk = 1;
              Resept resept = null;
              switch (type) {
                case "hvit" -> {
                  reit = Integer.valueOf(data[4]);
                  resept = lege.skrivHvitResept(legemiddel, pasient, reit);
                }
                case "millitaer" -> {
                  reit = Integer.valueOf(data[4]);
                  resept = lege.skrivMilitaerresept(legemiddel, pasient);
                }
                case "p" -> resept = lege.skrivPResept(legemiddel, pasient, reit);
                case "blaa" -> {
                  reit = Integer.valueOf(data[4]);
                  resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                }
                default -> sjekk = 0;
              }
              if (sjekk == 1) {
                reseptListe.leggTil(resept);
              }
            } catch (Exception e) {
              System.out.println("Kunne ikke lose i linje " + linje);
            }
            break;
          default:
            System.out.println("Kunne ikke lose i linje " + linje);
            break;
        }

      }
    }

  }

  // E3 lager en oversikt i terminalen
  private static void skrivUtOversikt() {
    System.out.println("Oversikt over pasienter:");
    pasientUtskrift();
    System.out.println();

    System.out.println("Oversikt over legemidler:");
    legemiddelUtskrift();
    System.out.println();

    System.out.println("Oversikt over leger:");
    legeUtskrift(false);
    System.out.println();

    System.out.println("Oversikt over resepter:");
    reseptUtskrift();
    System.out.println();
  }

  private static void pasientUtskrift() {
    // oversikt
    System.out.println("id" + " , " + "navn" + " , " + "foedselsnummer");
    // linjeskift
    System.out.println();
    for (Pasient pasient : pasientListe) {
      System.out.println(pasient.hentId() + " , " + pasient.hentNavn() + " , " + pasient.hentFodselsnr());
      // linjeskift
      System.out.println();
    }
  }

  private static void legemiddelUtskrift() {
    // oversikt
    System.out.println(
            "id" + " , " + "navn" + " , " + "pris" + " , " + "virkestoff" + " , " + "type" + " , " + "styrke");
    System.out.println();
    // bygger opp ulik utskrift for hver av de forskjellige subklassene / type
    for (Legemiddel legemiddel : legemiddelListe) {
      if (legemiddel instanceof Vanlig) {
        System.out.println(legemiddel.hentId() + " , " + legemiddel.hentNavn() + " , " + legemiddel.hentPris() + " , " + legemiddel.hentVirkestoff()
                + " , " + "vanlig" + " , " + "");
        System.out.println();
      } else if (legemiddel instanceof Narkotisk nark) {
        System.out.println(legemiddel.hentId() + " , " + legemiddel.hentNavn() + " , " + legemiddel.hentPris() + " , " + legemiddel.hentVirkestoff()
                + " , " + "narkotisk" + " , " +
                nark.hentNarkotiskStyrke());
        System.out.println();
      } else if (legemiddel instanceof Vanedannende vane) {
        System.out.println(legemiddel.hentId() + " , " + legemiddel.hentNavn() + " , " + legemiddel.hentPris() + " , " + legemiddel.hentVirkestoff()
                + " , " + "vanedannende" + " , " +
                vane.hentVanedannendeStyrke());
        System.out.println();
        ;
      }
    }
  }

  private static void legeUtskrift(boolean spesialistUtskrft) {
    System.out.println("navn" + " , " + "type" + " , " + "kontroll-id");
    System.out.println();
    for (Lege lege : legeListe) {
      if (lege instanceof Spesialist spesial) {
        System.out.println(lege.hentLegeNavn() + " , " + "spesialist" + " , " + spesial.hentKontrollID());
        System.out.println();
        //implementerer ekstra funksjonalitet for aa kun skrive ut spesialister.
      } else if (!spesialistUtskrft) {
        System.out.println(lege.hentLegeNavn() + " , " + "vanlig" + " , " + "");
        System.out.println();
      }
    }
  }

  private static void reseptUtskrift() {
    //oversikt
    System.out.println("id" + " , " + "legemiddel-id" + " , " + "legenavn" + " , " + "pasient-id" + " , " + "type"
            + " , " + "reit");
    System.out.println();

    for (Resept resept : reseptListe) {
      String type;
      if (resept instanceof HvitResept) {
        type = "HvitResept";
      } else if (resept instanceof BlaaResept) {
        type = "BlaaResept";
      } else if (resept instanceof MilResept) {
        type = "MilResept";
      } else if (resept instanceof PResept) {
        type = "PResept";
      } else {
        type = " ";
      }
      System.out.println(
              resept.hentId() + " , " + resept.hentLegemiddel().hentId() + " , " + resept.hentLege().hentLegeNavn() + " , " +
                      resept.hentPasient().hentId() + " , " + type + " , " + resept.hentReit());
    }
  }

  private static void leggTilNyeElementer() throws UlovligUtskrift {
    // spor om hvilken objekter brukeren vil legge til, og sender dem videre til riktig metode.
    String[] valgalternativer = {
            "Ny pasient", "Nytt legemiddel", "Ny lege", "Ny resept"
    };
    System.out.println("Du kan legge til nye elementer.");
    Scanner scElement = new Scanner(System.in);
    String linje = "start"; // Har linje for aa holde styr paa input
    while (!linje.equals("s")) {
      System.out.println("\nVelg et alternativ: ");
      for (int i = 0; i < valgalternativer.length; i++) {
        System.out.println((i + 1) + ": " + valgalternativer[i]);
      }
      System.out.println("s: tilbake");
      System.out.print("Ditt valg: ");
      linje = scElement.next().strip();
      switch (linje) {
        case "1" -> {
          System.out.println(valgalternativer[0] + " valgt\n");
          leggTilNyPasient();
        }
        case "2" -> {
          System.out.println(valgalternativer[1] + " valgt\n");
          leggTilNyttLegemiddel();
        }
        case "3" -> {
          System.out.println(valgalternativer[2] + " valgt\n");
          leggTilNyLege();
        }
        case "4" -> {
          System.out.println(valgalternativer[3] + " valgt\n");
          leggTilNyResept();
        }
      }
    }
    System.out.println("Du har lagt til nye elementer!");
  }

  private static void leggTilNyResept() throws UlovligUtskrift {

    int id; //for aa hente legemiddel eller pasient
    String format = ""; //sjekkeer formaten paa input
    String linje = ""; // holder styr paa input i flervalg
    Pasient pasient = null;
    Legemiddel legemiddel = null;
    Lege lege = null;
    int reit = 0;

    Scanner scannerResept = new Scanner(System.in);
    String[] typer = { "hvit", "milresept", "p", "blaa" };
    String type = "";
    // spor om type og ser om den er gyldig
    System.out.print("Typer aa velge imellom er: (Mulige typer: hvit, milresept, p, blaa) ");
    format = scannerResept.next().strip().toLowerCase();
    if (Arrays.asList(typer).contains(format)) {
      type = format;
    } else {
      System.out.println("Ugyldig type: " + format);
      return;
    }
    System.out.println();

    // velg legemiddel med test
    System.out.println("Vi har disse legemidlene: ");
    legemiddelUtskrift();
    System.out.print("LegemiddelID: ");
    linje = scannerResept.next().strip();
    try {
      id = Integer.valueOf(linje);
    } catch (Exception e) {
      System.out.println("Ugyldig valg: " + linje);
      return;
    }
    // ser om legemiddelet finnes
    legemiddel = hentLegemiddel(id);
    if (legemiddel == null) {
      System.out.println("Legemiddel finnes ikke. Id: " + linje);
      return;
    }
    System.out.println();

    
    boolean bareSpesialister;
    bareSpesialister = legemiddel instanceof Narkotisk;
    System.out.println("foelgende leger kan skrive ut denne resepten: ");
    legeUtskrift(bareSpesialister);
    System.out.print("Navn: ");
    scannerResept.nextLine();
    linje = scannerResept.nextLine().strip();
    // ser om legen finnes
    lege = hentLege(linje);
    if (lege == null) {
      System.out.println("Lege " + linje+ " finnes ikke");
      return;
    }
    System.out.println();

    // velg pasient med sjekker
    System.out.println("Vi har disse pasientene: ");
    pasientUtskrift();
    System.out.print("Pasient-id: ");
    linje = scannerResept.next().strip();
    try {
      id = Integer.valueOf(linje);
    } catch (Exception e) {
      System.out.println("Ugyldig id: " + linje);
      return;
    }
    // finnes pasienten?
    pasient = hentPasient(id);
    if (pasient == null) {
      System.out.println("Pasienten med Id: " + linje + " finnes ikke");
      return;
    }

    // legger til resept
    switch (type) {
      case "hvit" -> lege.skrivHvitResept(legemiddel, pasient, reit);
      case "militaer" -> lege.skrivMilitaerresept(legemiddel, pasient);
      case "p" -> lege.skrivPResept(legemiddel, pasient, reit);
      case "blaa" -> lege.skrivBlaaResept(legemiddel, pasient, reit);
    }
    System.out.println("Resepen har blitt lagt til.");
  }

  private static void leggTilNyLege() {
    String navn;
    String kontrollId = "";
    Scanner scannerLege = new Scanner(System.in);
    String input = ""; // For aa sjekke format paa input

    System.out.println("Legg til en ny lege");
    System.out.println();
    System.out.print("Navn: ");
    navn = scannerLege.nextLine().strip();
    // Spor om det skal vaere spesialist eller ikke
    Lege lege = null;
    System.out.println("Spesialist?: (Mulige alternativer: ja, nei) ");
    String linje = scannerLege.next().strip().toLowerCase();
    if (linje.equals("ja")) {
      System.out.print("Kontroll-id: ");
      input = scannerLege.next().strip();
      try {
        Integer sjekk = Integer.valueOf(input);
        kontrollId = input;
      } catch (Exception e) {
        System.out.println("Ugyldig id: " + input);
        return;
      }
      lege = new Spesialist(navn, kontrollId);
    } else if (linje.equals("nei")) {
      lege = new Lege(navn);
    } else {
      System.out.println("Ugyldig valg: " + linje);
      return;
    }
    // legger til i lege listen
    legeListe.leggTil(lege);
    System.out.println("Lege har blitt lagt til.");
  }
  private static void leggTilNyttLegemiddel() {
    String navn;
    int pris;
    double virkestoff;
    Scanner scnr = new Scanner(System.in);
    String input; // For aa sjekke format paa input
   
    String[] typer = {
            "vanlig", "narkotisk", "vanedannende"
    };
    int styrke = 0;
    System.out.println("Her kan du legge til et nytt legemiddel");
    System.out.print("Navn paa legemiddel: ");
    navn = scnr.nextLine().strip();
    System.out.print("Pris paa legemiddel: ");
    input = scnr.next().strip();
     // ser om pris har rett format
    try {
      pris = Integer.valueOf(input);
    } catch (Exception e) {
      System.out.println( input+ " er en ugydig pris.");
      return;
    }
    // ser om virkestoff har rett format
    System.out.print("Virkestoff paa legemiddel: ");
    input = scnr.next().strip();
    try {
      virkestoff = Double.valueOf(input);
    } catch (Exception e) {
      System.out.println( input + " er ikke gylding input for virkestoff.");
      return;
    }
    System.out.print("Typer aa velge imellom er: ( vanlig, narkotisk, vanedannende) ");
    // sjekker type og eventuell laging av vanlig legemiddel
    Legemiddel legemiddel;
    String scnrLinje = scnr.next().strip().toLowerCase();
    if (!Arrays.asList(typer).contains(scnrLinje)) {
      System.out.println( scnrLinje+ " er ikke en gyldig type.");
      return;
    } else if (scnrLinje.equals(typer[0])) {
    } else {
      // ser om styrke har riktig format
      System.out.print("Styrke paa legemiddel: ");
      input = scnr.next().strip();
      try {
        styrke = Integer.valueOf(input);
      } catch (Exception e) {
        System.out.println( input+ " er ikke et gyldig input");
        return;
      }
    }
    // laging av ikke-vanlige legemiddel
    if (scnrLinje.equals(typer[1])) {
      legemiddel = new Narkotisk(navn, pris, virkestoff, styrke);
    } else {
      legemiddel = new Vanedannende(navn, pris, virkestoff, styrke);
    }
    // legger til objektet
    legemiddelListe.leggTil(legemiddel);
    System.out.println("Legemiddel lagt til!");
  }
  

  private static void leggTilNyPasient() {
    String navn;
    String foedNr;
    Scanner scPasient = new Scanner(System.in);

    System.out.println("LEGG TIL NY PASIENT");
    System.out.print("NAVN? >: ");
    navn = scPasient.nextLine().strip();

    System.out.print("FOEDSELSNUMMER? >: ");
    foedNr = scPasient.next().strip();

    try {
      if (foedNr.length() != 11) {
        System.out.println("FEIL NUMMER! DET MAA VAERE 11 SIFFER!");
      } else {
        pasientListe.leggTil(new Pasient(navn, foedNr));
        System.out.println("FULLFOERT! PASIENT ER LAGT INN!");
      }
    } catch (Exception e) {
      System.out.println("KUNNE IKKE LEGGE TIL PASIENTEN");
    }
  }

  private static Legemiddel hentLegemiddel(int id) {
    for (Legemiddel legemiddel : legemiddelListe) {
      if (legemiddel.hentId() == id) {
        return legemiddel;
      }
    }
    return null;
  }

  private static Lege hentLege(String navn) {
    for (Lege lege : legeListe) {
      if (lege.hentLegeNavn().equals(navn)) {
        return lege;
      }
    }
    return null;
  }

  private static Pasient hentPasient(int id) {
    for (Pasient pasient : pasientListe) {
      if (pasient.hentId() == id) {
        return pasient;
      }
    }
    return null;
  }

  // E5
  private static void brukResept() {
    int intInp;
    Resept resept;
    Scanner scanner = new Scanner(System.in);
    String inp; // mellomledd for input

    System.out.println("Hvilken pasient vil du se resepter for");
    pasientUtskrift();
    System.out.print("Skriv id her: ");
    inp = scanner.next().strip();

    try {
      intInp = Integer.valueOf(inp);
    } catch (Exception e) {
      System.out.println("ID maa vaere et heltall!");
      return;
    }

    Pasient pasient = hentPasient(intInp);
    if (pasient == null) {
      System.out.println("Finner ikke pasient med id " + intInp);
      return;
    }

    System.out.println();
    System.out.println("Hvilken resept vil du bruke?");
    int teller = 1;
    HashMap<Integer, Resept> hashMap = new HashMap<>();
    for (Resept res : pasient.hentResepter()) {
      System.out.println(teller + ": " + res.hentLegemiddel().hentNavn() + " (" + res.hentReit() + " reit)");
      hashMap.put(teller, res);
      teller += 1;
    }

    System.out.print("valg: ");
    inp= scanner.next().strip();

    try {
      intInp = Integer.valueOf(inp);
    } catch (Exception e) {
      System.out.println("ID maa vaere et heltall!");
      return;
    }

    try {
      resept = hashMap.get(intInp);
    } catch (Exception e) {
      System.out.println("Finner ikke resept med id " + intInp);
      return;
    }

    if (resept.bruk()) {
      System.out.println("Brukte resept paa" + resept.hentLegemiddel().hentNavn() + ". Antall gjenvaerende reit:" + resept.hentReit() + ".");
    } else {
      System.out
              .println("Kunne ikke bruke resept paa " + resept.hentLegemiddel().hentNavn()
                      + ". Ingen gjenvaerende reit.");
    }

  }

  // E6
  private static void lagStatistikk() {
    int valigeResepter = 0;
    int narkotiskeResepter = 0;

    ArrayList<Lege> legeNark = new ArrayList<>();
    ArrayList<Integer> legeNarkResepter = new ArrayList<>();
    ArrayList<Pasient> pasientNark = new ArrayList<>();
    ArrayList<Integer> pasientNarkResepter = new ArrayList<>();

    // teller antallet vanlige- og narkotiskeresepter utskrevet
    for (Resept resept : reseptListe) {
      if (resept.hentLegemiddel() instanceof Vanedannende) {
        valigeResepter += 1;
      } else if (resept.hentLegemiddel() instanceof Narkotisk) {
        narkotiskeResepter += 1;
      }
    }

    // teller antall leger som har skrevet nark resepter
    int antallResepter = 0;
    boolean narkLege = false;
    for (Lege lege : legeListe) {
      antallResepter = 0;
      narkLege = false;
      for (Resept resept : lege.hentResepter()) {
        if (resept.hentLegemiddel() instanceof Narkotisk) {
          narkLege = true;
          antallResepter += 1;
        }
      }
      if (narkLege) {
        legeNark.add(lege);
        legeNarkResepter.add(antallResepter);
      }
    }

    // teller pasienter som har minst en gyldig resept paa narkotisk legemiddel
    int antallNarkResept = 0;
    boolean harNarkResept = false;
    for (Pasient pasient : pasientListe) {
      antallNarkResept = 0;
      harNarkResept = false;
      for (Resept resept : pasient.hentResepter()) {
        if (resept.hentLegemiddel() instanceof Narkotisk || resept.hentReit() > 0) {
          harNarkResept = true;
          antallNarkResept += 1;
        }
      }
      if (harNarkResept) {
        pasientNark.add(pasient);
        pasientNarkResepter.add(antallNarkResept);
      }
    }
    statistikkUtskrift(valigeResepter, narkotiskeResepter, pasientNarkResepter, legeNark, legeNarkResepter, pasientNark);
  }

  private static void statistikkUtskrift(int vanlig, int nark, ArrayList<Integer> antallNarkPas,
                                         List<Lege> legeNarkotisk, List<Integer> antallRespeter, ArrayList<Pasient> pasNark) {
    // viser statistikk
    System.out.println("RESEPTER I SYSTEMET:");
    System.out.println("Type: Vanlig, Antall: " + vanlig);
    System.out.println("Type: Narkotisk, Antall: " + nark);
    System.out.println();

    System.out.println("NARKOTISKE RESEPTUTSKRIFTER I SYSTEMET:");
    System.out.println();
    for (int i = 0; i < legeNarkotisk.size(); i++) {
      System.out.println(legeNarkotisk.get(i).hentLegeNavn() + " har skrevet ut " + antallRespeter.get(i) + " narkotiske resepter i systemet.");
      System.out.println();
    }
    System.out.println();
    System.out.println("NARKOTISKE RESPTER I SYSTEMET:");
    System.out.println();
    for (int i = 0; i < pasNark.size(); i++) {
      System.out.println(pasNark.get(i).hentNavn()+ " har " + antallNarkPas.get(i) + " narkotiske resepter i systemet.");
      System.out.println();
    }
  }



  // E2 / main metode

  public static void main(String[] args) throws UlovligUtskrift {

    //legger inn filen som skal leses
    lesFraFil("Liteneksempelfil.txt");

    // bygger opp menyen
    Scanner scannr = new Scanner(System.in);
    String valgt = "tom";
    //mens vaglt.equsla("s") er false
    while (!valgt.equals("s")) {
      System.out.println();
      System.out.println("Valgmuligheter: ");

      //lager menyen
      System.out.println("0: Skriv ut fullstendig oversikt,");
      System.out.println("1: Opprett nye elementer til systemet,");
      System.out.println("2: Bruk en resept fra pasient listen,");
      System.out.println("3: Skriv ut statistikk,");
      System.out.println("4: Skriv til fil");
      System.out.println("s: slutt");
      System.out.print("Input her: ");

      valgt = scannr.next().strip();

      //sjekker input og kaller metoder. Trigges av scannr..
      switch (valgt) {
        case "0" -> {
          System.out.println("Skriver ut oversikt");
          System.out.println();
          skrivUtOversikt();
        }
        case "1" -> {
          System.out.println("Oppretter nye elementer til systemet");
          System.out.println();
          leggTilNyeElementer();
        }
        case "2" -> {
          System.out.println("Bruker en resept fra pasient listen");
          System.out.println();
          brukResept();
        }
        case "3" -> {
          System.out.println("Statistikk:");
          System.out.println();
          lagStatistikk();
        }
        case "4" -> {
          skrivTilFil("Informasjon.txt");
        }
      }

    }
    System.out.println("Legesystem er avsluttet");
  }

  private static void skrivTilFil(String navnFil) {
    try {
      File fil = new File(navnFil);
      if (fil.createNewFile()) {
        System.out.println("Navn paa filen: " + fil.getName());
      } else {
        System.out.println("Fil finnes allerede.");
      }
    } catch (IOException problem) {
      System.out.println("Fil kunne ikke lages.");
      problem.printStackTrace();
    }

    try {
      FileWriter skriver = new FileWriter(navnFil);
      System.out.println("Filen's skriver: " + navnFil);
      skriver.write("# Pasienter (navn, fnr)\n");
      skriver.write(pasientListe.Utskrift());
      skriver.write("# Legemidler (navn,type,pris,virkestoff,[styrke]))\n");
      skriver.write(legemiddelListe.Utskrift());
      skriver.write("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");
      skriver.write(legeListe.Utskrift());
      skriver.write("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");
      skriver.write(reseptListe.Utskrift());
      System.out.println("Velykket skriving til tekstfil.");
      skriver.close();
    } catch (IOException problems) {
      System.out.println("Ikke velykket skriving til tekstfil.");
      problems.printStackTrace();
    }

  }

}


