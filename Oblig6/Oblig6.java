import java.util.Scanner;
public class Oblig6 {
    
public static void main(String[] args) {
    Labyrint lab = new Labyrint();
    lab.LesInnfil(args[0]);
    System.out.println();
    lab.finnAlleNaboer();
    Scanner scanner = new Scanner(System.in);
    
        String valg = "";
        while (!valg.equals("-1")) {
            System.out.print("Velg posisjon <rad> <kolonne> ('-1' for aa avslutte) ");
            valg = scanner.nextLine();
            if (valg.equals("-1")) {
                continue;
            } 
            String[] line_split = valg.split(" ");
            try {
                int y = Integer.parseInt(line_split[0]);
                int x = Integer.parseInt(line_split[1]);
                lab.finnUtveiFra(y, x);
            }
            catch (NumberFormatException e) {
                System.out.println("input krever ha 2 kordinater");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("kordinat finnes ikke i labyrinten");
            }
        }
        scanner.close();
    }

    
}

