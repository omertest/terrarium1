package be.vdab.terrarium.userinterfaces;

import be.vdab.terrarium.Terrarium;
import be.vdab.terrarium.items.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Swing {

    //terrarium met speeldveld
    private static Terrarium terrarium = new Terrarium(60,30,10,20,20);
    
    // hard coded test stuff
    private static Ding[][] terrariumVeldHard = new Ding[4][4];
    private static Ding[][] terrariumVeldHard2 = new Ding[4][4];  

    static void initHard() {
        
        terrariumVeldHard[0][0] = new Herbivoor();
        terrariumVeldHard[0][1] = new Plant();
        //terrariumVeld[0][2] = new Herbivoor();
        terrariumVeldHard[0][3] = new Herbivoor();
        // terrariumVeld[1][0] = new Herbivoor();
        terrariumVeldHard[1][1] = new Herbivoor();
        terrariumVeldHard[1][2] = new Herbivoor();
        //terrariumVeld[1][3] = new Plant();
        terrariumVeldHard[2][0] = new Herbivoor();
        terrariumVeldHard[2][1] = new Herbivoor();
        //terrariumVeld[2][2] = new Herbivoor();
        terrariumVeldHard[2][3] = new Carnivoor();
        terrariumVeldHard[3][0] = new Plant();
        terrariumVeldHard[3][1] = new Herbivoor();
        //terrariumVeld[3][2] = new Carnivoor();
        terrariumVeldHard[3][3] = new Herbivoor();
        
        terrariumVeldHard2[0][0] = new Carnivoor();
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
        if (mode.equals("hard")) {
            return beginhtml + htmlRowsInstance(terrariumVeldHard) + endhtml;
        } else {
            return null; //beginhtml + htmlRowsInstance(terrarium.getSpeelveld().) + endhtml ;
        }
    }

    static String htmlTable() {
        return htmlTable("default");
    }

    static String htmlRowsString(Ding[][] veld) {
        String table = "";
        for (int row = 0; row < terrariumVeldHard.length; row++) {
            table += "<tr>";
            for (int col = 0; col < terrariumVeldHard[row].length; col++) {
                if (terrariumVeldHard[row][col] == null) {
                    table += "<td>" + "." + "</td>";
                }
                table += "<td>" + terrariumVeldHard[row][col] + "</td>";
            }
            table += "</tr>";
        }
        return table;
    }

    static String htmlRowsInstance(Ding[][] veld) {
        String table = "";
        for (int row = 0; row < terrariumVeldHard.length; row++) {
            table += "<tr>";
            for (int col = 0; col < terrariumVeldHard[row].length; col++) {
                if (terrariumVeldHard[row][col] instanceof Plant) {
                    table += "<td>" + "Plant" + "</td>";
                } else if (terrariumVeldHard[row][col] instanceof Herbivoor) {
                    table += "<td>" + "Herbi" + "</td>";
                } else if (terrariumVeldHard[row][col] instanceof Carnivoor) {
                    table += "<td>" + "Carni" + "</td>";
                } else {
                    table += "<td>" + "." + "</td>";
                }
            }
            table += "</tr>";
        }
        return table;
    }

    private static void createAndShowGUI() {
        initHard();

        //Create and set up the window.
        JFrame frame = new JFrame("Terrarium");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(htmlTable("hard"));
        frame.getContentPane().add(label);
        
        //volgende dag stuff
        JButton buttonNext = new JButton("volgende dag");
        buttonNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // terrarium.start 
                terrariumVeldHard = terrariumVeldHard2;
                label.setText(htmlTable("hard"));
            }
        });   
        
        frame.getContentPane().add(buttonNext);
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
