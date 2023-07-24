import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Labyrint {
    Rute[][] ruter;
    private int y = 0;
    private int x = 0;
    public ArrayList<Rute> utgang = new ArrayList<>();


    public void LesInnfil(String fil) {
        String Filbane = "labyrinter/" + fil;
        try {
            Scanner scanner = new Scanner(new File(Filbane));
            int linjer = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (linjer == 0) {
                    String[] deler = nextLine.split(" ");
                    try {
                        y = Integer.parseInt(deler[0]);
                    } catch (Exception e) {
                        System.out.println("Feil med Raden");
                    }
                    try {
                        x = Integer.parseInt(deler[1]);
                    } catch (Exception e) {
                        System.out.println("Feil med kolonnen");
                    }
                    ruter = new Rute[y][x];
                } else {
                    char[] splitter = nextLine.toCharArray();
                    int kolonne = 1;
                    for (char x : splitter) {
                        if (x == '#') {
                            try {
                                settInnRute(linjer, kolonne, '#');
                            } catch (Exception e) {
                                System.out.println("Kunne ikke lage svart rute på (" + kolonne + ","
                                        + linjer + ") ");
                            }
                        } else if (x == '.') {
                            try {
                                settInnRute(linjer, kolonne, '.');
                            } catch (Exception e) {
                                System.out.println("Kunne ikke lage hvit rute på (" + kolonne + ","
                                        + linjer + ") ");
                            }
                        }
                        kolonne++;
                    }
                }
                linjer++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen");
        }
        System.out.println(this);
    }

    public int hentRad() {
        return y;
    }

    public int hentKolonne() {
        return x;
    }

    public void settInnRute(int y, int x, char rute) {
        if (rute == '#') {
            ruter[y - 1][x - 1] = new SortRute(y, x, this);
        } else if (rute == '.') {
            if (y == 1 || x == 1 || x == this.x || y == this.y) {
                ruter[y - 1][x - 1] = new Aapning(y, x, this);
            } else {
                ruter[y - 1][x - 1] = new HvitRute(y, x, this);
            }
        }
    }

    public Rute hentRute(int x, int y) { 
        try {
            Rute retur = ruter[y - 1][x - 1];
            return retur;
        } catch (Exception e) {
            System.out.println("Feil i hentRute!");
            return null;
        }
    }

    public Rute naboerOst(Rute rute) {
        Rute en = rute;
        Rute Siste;
        if (hentKolonne() == rute.x) {
            Siste = rute;
            naboerVest(Siste);
            return rute;
        } else {
            Rute sendeInn = hentRute(en.x + 1, en.y);
            en.ost = naboerOst(sendeInn);
            return en;
        }
    }

    public Rute naboerSor(Rute rute) {
        Rute en = rute;
        Rute Siste;
        if (hentRad() == rute.y) {
            Siste = rute;
            naboerNord(Siste);
            return rute;
        } else {
            Rute sendeInn = hentRute(en.x, en.y + 1);
            en.sor = naboerSor(sendeInn);
            return en;
        }
    }

    public Rute naboerVest(Rute rute) {
        Rute en = rute;
        if (1 == rute.x) {
            return rute;
        } else {
            Rute sendeInn = hentRute(en.x - 1, en.y);
            en.vest = naboerVest(sendeInn);
            return en;
        }
    }

    public Rute naboerNord(Rute rute) {
        Rute en = rute;
        if (1 == rute.y) {
            return rute;
        } else {
            Rute sendeInn = hentRute(en.x, en.y - 1);
            en.nord = naboerNord(sendeInn);
            return en;
        }
    }

    public void finnAlleNaboer() {
        for (int i = 0; i < hentKolonne(); i++) {
            naboerSor(ruter[0][i]);
        }
        for (int i = 0; i < hentRad(); i++) {
            naboerOst(ruter[i][0]);
        }
    }

    public void finnUtveiFra(int x, int y) {
        hentRute(x, y).finn(null);
        
    }


    @Override
    public String toString() {
        String print = "";
        for (int x = 0; x < hentRad(); x++) {
            for (int i = 0; i < hentKolonne(); i++) {
                print = print + ruter[x][i].hentChar();
            }
            print = print + "\n";
        }
        return print;
    }
}