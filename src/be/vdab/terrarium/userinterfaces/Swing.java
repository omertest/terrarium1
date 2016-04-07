package be.vdab.terrarium.userinterfaces;

import be.vdab.terrarium.Terrarium;
import be.vdab.terrarium.items.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Swing {

    //terrarium met speeldveld
    private static Terrarium terrarium = new Terrarium(60, 30, 10, 20, 20);

    private static boolean continu = false;
    // hard coded test stuff
    private static Ding[][] terrariumVeldHard = new Ding[4][4];
    private static Ding[][] terrariumVeldHard2 = new Ding[4][4];

    static void initHard() {

        terrariumVeldHard[0][0] = new Herbivoor(terrarium);
        terrariumVeldHard[0][1] = new Plant(terrarium);
        //terrariumVeld[0][2] = new Herbivoor(terrarium);
        terrariumVeldHard[0][3] = new Herbivoor(terrarium);
        // terrariumVeld[1][0] = new Herbivoor(terrarium);
        terrariumVeldHard[1][1] = new Herbivoor(terrarium);
        terrariumVeldHard[1][2] = new Herbivoor(terrarium);
        //terrariumVeld[1][3] = new Plant(terrarium);
        terrariumVeldHard[2][0] = new Herbivoor(terrarium);
        terrariumVeldHard[2][1] = new Herbivoor(terrarium);
        //terrariumVeld[2][2] = new Herbivoor(terrarium);
        terrariumVeldHard[2][3] = new Carnivoor(terrarium);
        terrariumVeldHard[3][0] = new Plant(terrarium);
        terrariumVeldHard[3][1] = new Herbivoor(terrarium);
        //terrariumVeld[3][2] = new Carnivoor(terrarium);
        terrariumVeldHard[3][3] = new Herbivoor(terrarium);

        terrariumVeldHard2[0][0] = new Carnivoor(terrarium);
        terrariumVeldHard2[0][1] = null;
        terrariumVeldHard2[1][0] = null;
        terrariumVeldHard2[1][1] = null;
    }

    static String beginhtml = "<html>"
            + "<body>"
            + "<table border=2>";

    static String endhtml = "<html>"
            + "</table>"
            + "</body>"
            + "</html>";

    // werken instanceof /tostring - hard coded / terrarium
    static String htmlTable(String mode) {
        if (mode.equals("terrarium")) {
            return beginhtml + htmlRowsInstance(terrarium.getSpeelveld().getSpeelveld()) + endhtml;
        } else {
            return beginhtml + htmlRowsInstance(terrariumVeldHard) + endhtml;
        }
    }

    static String htmlTable() {
        return htmlTable("default");
    }

    static String htmlRowsString(Ding[][] veld) {
        String table = "";
        for (int row = 0; row < veld.length; row++) {
            table += "<tr>";
            for (int col = 0; col < veld[row].length; col++) {
                if (veld[row][col] == null) {
                    table += "<td>" + "." + "</td>";
                }
                table += "<td>" + veld[row][col] + "</td>";
            }
            table += "</tr>";
        }
        return table;
    }

    static String htmlRowsInstance(Ding[][] veld) {
        String table = "";
        for (int row = 0; row < veld.length; row++) {
            table += "<tr>";
            for (int col = 0; col < veld[row].length; col++) {
                if (veld[row][col] instanceof Plant) {
                    table += "<td>" + "Plant" + "</td>";
                } else if (veld[row][col] instanceof Herbivoor) {
                    table += "<td>" + "Herbi" + "</td>";
                } else if (veld[row][col] instanceof Carnivoor) {
                    table += "<td>" + "Carni" + "</td>";
                } else {
                    table += "<td>" + " " + "</td>";
                }
            }
            table += "</tr>";
        }
        return table;
    }

    private static void createAndShowGUI() {
       // initHard();

        //Create and set up the window.
        JFrame frame = new JFrame("Terrarium");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(htmlTable("terrarium"));
        frame.getContentPane().add(label);

        //volgende dag stuff
        JButton knopVolgende = new JButton("volgende dag");
        knopVolgende.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // terrarium.start(); 
                terrariumVeldHard = terrariumVeldHard2;
                label.setText(htmlTable("hard"));
            }
        });

        //continu stuff
        JButton knopContinu = new JButton("simulatie");
        knopContinu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continu = true;
                //todo get seconds from input
                final Timer timer = new Timer(500, null);
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (continu) {
                            //terrarium.start();
                            label.setText(htmlTable("hard"));
                        } else {
                            timer.stop();
                        }
                    }
                };
                timer.addActionListener(listener);
                timer.start();
            }
        });

        // stop continu
        JButton knopStopContinu = new JButton("stop");
        knopStopContinu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continu = false;
            }
        });
        
        frame.getContentPane().add(knopVolgende);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
