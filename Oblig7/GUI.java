import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GUI{
    private int rutestorrelse=12;
    private int SpillHoyde = 600;
    private int SpillBredde = 400;
    private int Kontrollhoyde = 100;
    private int RutenettHoyde = 400;

    JFrame vindu;
    JPanel panel, konsoll, rutenett, test, toppvindu;
    JLabel Lengde;
    JButton stoppknapp, opp, ned, venste,hoyre;
    Kontroll kontroll;
    private JLabel ruter[][] = new JLabel[rutestorrelse][rutestorrelse];
    
    public GUI(Kontroll k) {
        kontroll = k;
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { System.exit(9); }
        vindu = new JFrame("SlangeSpillet");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(SpillBredde, Kontrollhoyde));
        vindu.add(panel);

        toppvindu = new JPanel();
        toppvindu.setLayout(new BorderLayout());
        panel.add(toppvindu, BorderLayout.NORTH);
        

        


        konsoll = new JPanel();
        konsoll.setLayout(new BorderLayout());
        toppvindu.add(konsoll, BorderLayout.CENTER);
        konsoll.setPreferredSize(new Dimension(SpillBredde / 2, Kontrollhoyde));

        test = new JPanel();
        test.setLayout(new BorderLayout());
        toppvindu.add(test, BorderLayout.WEST);
        test.setPreferredSize(new Dimension(SpillBredde / 4, Kontrollhoyde));

        konsoll.setLayout(new BorderLayout());
        opp= new JButton("Opp");
        konsoll.add(opp, BorderLayout.NORTH);

        ned= new JButton("Ned");
        konsoll.add(ned, BorderLayout.SOUTH);

        venste= new JButton("Venstre");
        konsoll.add(venste, BorderLayout.WEST);

        hoyre= new JButton("Hoyre");
        konsoll.add(hoyre, BorderLayout.EAST);


        Lengde = new JLabel(" ");
        test.add(Lengde, BorderLayout.WEST);

        stoppknapp = new JButton("Exit");
        
        class Stoppbehandler implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.avsluttSpillet();
            }
        }
        stoppknapp.addActionListener(new Stoppbehandler());
        toppvindu.add(stoppknapp, BorderLayout.EAST);
      

        class Oppbehandler implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
            kontroll.bevegelse("opp");
            }
        }
        opp.addActionListener(new Oppbehandler());

        class Nedbehandler implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
            kontroll.bevegelse("ned");
            }
        }
        ned.addActionListener(new Nedbehandler());

        class Hoyrebehandler implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
            kontroll.bevegelse("hoyre");
            }
        }
        hoyre.addActionListener(new Hoyrebehandler());

        class Venstrebehandler implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
            kontroll.bevegelse("venstre");
            }
        }
        venste.addActionListener(new Venstrebehandler());


        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(rutestorrelse,rutestorrelse));
        rutenett.setPreferredSize(new Dimension(SpillBredde, RutenettHoyde));
        panel.add(rutenett, BorderLayout.SOUTH);

        for(int rad=0; rad<rutestorrelse; ++rad){
            for(int kol=0; kol<rutestorrelse; ++kol){
                JLabel rute = new JLabel("", SwingConstants.CENTER);
                rute.setBorder(BorderFactory.createLineBorder(Color.RED));
                rutenett.add(rute);
                rute.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
                ruter[rad][kol] = rute;

                rutenett.add(rute);
            } 
        }
    
        vindu.pack();
        vindu.setSize(SpillBredde, SpillHoyde);
        vindu.setVisible(true);
    }

    public void tegnBrett() {
        for (int rad = 0; rad < rutestorrelse; rad++) {
            for (int kol = 0; kol < rutestorrelse; kol++) {
                ruter[rad][kol].setText(" ");
            }
        }

ruter[6][6].setText("+");
kontroll.forlengSlange(new Slange(6, 6, true));

for (int i = 0; i < 10; i++) {
    int spesial_rad = 0;
    int spesial_kolonne = 0;
    while ((spesial_rad != 6 && spesial_kolonne != 6) || ruter[spesial_rad][spesial_kolonne].getText().equals("$")) {
        spesial_rad = SpesialRute.trekk(0, rutestorrelse - 1);
        spesial_kolonne = SpesialRute.trekk(0, rutestorrelse - 1);
        if ((spesial_rad != 6 && spesial_kolonne != 6) || ruter[spesial_rad][spesial_kolonne].getText().equals("$")) break;
    }
    ruter[spesial_rad][spesial_kolonne].setText("$");
    SpesialRute ny = new SpesialRute(spesial_rad, spesial_kolonne);
    kontroll.leggTilSpesialRuter(i, ny);
}
}


public void ResetBrett() {
    Koe<Slange> slange = kontroll.hentSlange();
    SpesialRute[] spesial = kontroll.hentSpesialRuter();

    for (int rad = 0; rad < rutestorrelse; rad++) {
        for (int kol = 0; kol < rutestorrelse; kol++) {
            ruter[rad][kol].setText("");
        }
    }
    
    for (int i = 0; i < spesial.length; i++) {
        if (spesial[i] != null) ruter[spesial[i].hentRad()][spesial[i].hentKolonne()].setText("$");
    }

    for (Slange del : slange) {
        int slange_rad = del.hentRad();
        int slange_kolonne = del.hentKolonne();
        if (del.Hode()) ruter[slange_rad][slange_kolonne].setText("+");
        else ruter[slange_rad][slange_kolonne].setText("+");
    }
    
    Lengde.setText(" " + kontroll.hentHalelengde() + " ");
}
}
