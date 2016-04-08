/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.terrarium;

import be.vdab.terrarium.items.Carnivoor;
import be.vdab.terrarium.items.Ding;
import be.vdab.terrarium.items.Herbivoor;
import be.vdab.terrarium.items.Niets;
import be.vdab.terrarium.items.Plant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author thomas.vandevyvere
 */
public class Speelveld {

    private static final long serialVersionUID = 1L;
    Terrarium t;
    Niets niets = new Niets(t);
    Random coordinaatRandom = new Random();

    Ding[][] speelveld;

    List<Coordinaat> carnivoor = new ArrayList();
    List<Coordinaat> plant = new ArrayList();
    List<Coordinaat> herbivoor = new ArrayList();
    List<Coordinaat> nietsen = new ArrayList();

    public Speelveld(int breedte, int hoogte, Terrarium terrarium) {
        //de 2d array wordt aangemaakt.
        //elke positie krijgt een niets object.
        //al deze nietsen worden aan een verzameling toegevoegd.
        t = terrarium;
        speelveld = new Ding[breedte][hoogte];
        for (int x = 0; x < breedte; x++) {
            for (int y = 0; y < hoogte; y++) {
                speelveld[x][y] = niets;
                nietsen.add(new Coordinaat(x, y));
            }
        }
    }

    public void dingToevoegen(Ding ding) {
        //als er nog vrije plaatsen zijn, 
        //kies je een random vrije plaats uit deze verzameling. 
        //Je verwijdert deze coordinaat uit nietsen, 
        //en voegt hem toe aan de correcte verzameling objecten.
        if (!nietsen.isEmpty()) {
            Coordinaat coord = (Coordinaat) nietsen.get(coordinaatRandom.nextInt(nietsen.size()));
            nietsen.remove(coord);
            speelveld[coord.x][coord.y] = ding;
            if (ding instanceof Plant) {
                plant.add(coord);
            }
            if (ding instanceof Herbivoor) {
                herbivoor.add(coord);
            }
            if (ding instanceof Carnivoor) {
                carnivoor.add(coord);
            }
        }
    }

    public Ding[][] getSpeelveld() {
        return speelveld;
    }
    
    public Ding getDing(Coordinaat c){
        return speelveld[c.x][c.y];
    }

    public void interactieMetRechts(Ding ding) {

        if (ding instanceof Plant) {

            for (Coordinaat coord : plant) {
                if (speelveld[coord.x][coord.y].equals(ding)) {
                    if (speelveld[coord.x - 1][coord.y] instanceof Herbivoor) {
                        plant.remove(coord);
                        herbivoor.remove(new Coordinaat(coord.x - 1, coord.y));
                        herbivoor.add(coord);
                        speelveld[coord.x - 1][coord.y] = niets;
                    }
                }
            }
        }
    }

    public Coordinaat getPlaats(Ding ding) {
        Coordinaat plaats = new Coordinaat(0, 0);
        if (ding instanceof Herbivoor) {
            for (Coordinaat coord : herbivoor) {
                if (speelveld[coord.x][coord.y].equals(ding)) {
                    plaats.setCoordinaat(coord.x, coord.y);
                }
            }
        }
        if (ding instanceof Carnivoor) {
            for (Coordinaat coord : carnivoor) {
                if (speelveld[coord.x][coord.y].equals(ding)) {
                    plaats.setCoordinaat(coord.x, coord.y);
                }
            }
        }
        if (ding instanceof Plant) {
            for (Coordinaat coord : plant) {
                if (speelveld[coord.x][coord.y].equals(ding)) {
                    plaats.setCoordinaat(coord.x, coord.y);
                }
            }
        } else {
            for (Coordinaat coord : nietsen) {
                if (speelveld[coord.x][coord.y].equals(ding)) {
                    plaats.setCoordinaat(coord.x, coord.y);
                }
            }
        }
        return plaats;
    }

    public List<Coordinaat> getVrijePlaatsen(Ding ding) {
        List<Coordinaat> vrijePlaatsen = new ArrayList<>();
        Coordinaat plaats = getPlaats(ding);

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (speelveld[plaats.x + i][plaats.y + j] instanceof Niets) {
                    vrijePlaatsen.add(new Coordinaat(plaats.x + i, plaats.y + j));
                }
            }
        }
        return vrijePlaatsen;
    }

    public void verwijder(Ding ding) {
        Coordinaat coord = getPlaats(ding);
        if (ding instanceof Carnivoor) {
            carnivoor.remove(coord);
        }
        if (ding instanceof Herbivoor) {
            herbivoor.remove(coord);
        }
        if (ding instanceof Plant) {
            plant.remove(coord);
        }
        speelveld[coord.x][coord.y] = niets;
        nietsen.add(coord);
    }

    public void wandel(Ding ding) {

        Coordinaat vanPlaats = getPlaats(ding);
        List<Coordinaat> vrijePlaatsen = getVrijePlaatsen(ding);
        if (!vrijePlaatsen.isEmpty()) {
            Coordinaat naarPlaats = vrijePlaatsen.get(coordinaatRandom.nextInt(vrijePlaatsen.size()));
            speelveld[vanPlaats.x][vanPlaats.y] = niets;
            speelveld[naarPlaats.x][naarPlaats.y] = ding;
        }
    }

    void startBewegingen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
