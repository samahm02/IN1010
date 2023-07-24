import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Labyrint lab = new Labyrint();
        lab.LesInnfil(args[0]);
        System.out.println();
        lab.finnAlleNaboer();
        lab.finnUtveiFra(1,2);

    
    Scanner scanner = new Scanner(System.in);
    String valgt = "tom";
    while (!valgt.equals("s")) {
      System.out.println();
      System.out.println("Skriv inn kordinater <rad> <kolonne>");
      System.out.println("s for å avslutte");
      System.out.print("Input her: ");

      valgt = scanner.next();
      String[] biter= valgt.split(" ");
      int a = Integer.parseInt(biter[0]);
      int b = Integer.parseInt(biter[1]);

      lab.finnUtveiFra(a,b);

    }
    System.out.println("Systemet er avsluttet");
    scanner.close();
    System.exit(0);

     

    }

}