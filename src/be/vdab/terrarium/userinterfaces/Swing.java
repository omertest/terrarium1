/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.terrarium.userinterfaces;

import java.awt.FlowLayout;
import javax.swing.*;

public class Swing {

    private static String[][] terrariumVeld = new String[4][4];

    static void init() {
        terrariumVeld[0][0] = ".";
        terrariumVeld[0][1] = ".";
        terrariumVeld[0][2] = "carni";
        terrariumVeld[0][3] = "herbi";
        terrariumVeld[1][0] = "herbi";
        terrariumVeld[1][1] = ".";
        terrariumVeld[1][2] = ".";
        terrariumVeld[1][3] = "herbi";
        terrariumVeld[2][0] = "herbi";
        terrariumVeld[2][1] = "herbi";
        terrariumVeld[2][2] = ".";
        terrariumVeld[2][3] = "herbi";
        terrariumVeld[3][0] = "herbi";
        terrariumVeld[3][1] = "carni";
        terrariumVeld[3][2] = ".";
        terrariumVeld[3][3] = "herbi";
    }

    static String beginhtml = "<html>"
            + "<body>"
            + "<p>Terrarium scum</p>"
            + "<table border=2>";

    static String endhtml = "<html>"
            + "</table>"
            + "</body>"
            + "</html>";

    static String htmlTable() {
        return beginhtml + htmlRows(terrariumVeld) + endhtml;
    }

    static String htmlRows(String[][] veld) {
        String table = "";
        for (int row = 0; row < terrariumVeld.length; row++) {
            table += "<tr>";
            for (int col = 0; col < terrariumVeld[row].length; col++) {
                table += "<td>" + terrariumVeld[row][col] + "</td>";
            }
            table += "</tr>";
        }
        return table;
    }

    private static void createAndShowGUI() {
        init();

        //Create and set up the window.
        JFrame frame = new JFrame("Terrarium");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(htmlTable());
        frame.getContentPane().add(label);
        JButton buttonNext = new JButton("volgende dag");
        frame.getContentPane().add(buttonNext);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
