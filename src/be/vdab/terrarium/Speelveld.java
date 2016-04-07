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
        if (!nietsen.isEmpty()) {
            Coordinaat coord = (Coordinaat) nietsen.get(coordinaatRandom.nextInt(nietsen.size()));
            nietsen.remove(coord);
            if (ding instanceof Plant) {
                speelveld[coord.x][coord.y] = ding;
                plant.add(coord);
            }
            if (ding instanceof Herbivoor) {
                speelveld[coord.x][coord.y] = ding;
                herbivoor.add(coord);
            }
            if (ding instanceof Carnivoor) {
                speelveld[coord.x][coord.y] = ding;
                carnivoor.add(coord);
            }
        }
    }

    public Ding[][] getSpeelveld() {
        return speelveld;
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
        return plaats;
    }

    public List<Coordinaat> getVrijePlaatsen(Ding ding, Coordinaat plaats) {
        List<Coordinaat> vrijePlaatsen = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (speelveld[plaats.x + i][plaats.y + j] instanceof Niets) {
                    vrijePlaatsen.add(new Coordinaat(plaats.x + i, plaats.y + j));
                }
            }
        }
        return vrijePlaatsen;
    }

    public void verwijder(Ding ding, Coordinaat coord) {
        if (ding instanceof Carnivoor) {
            carnivoor.remove(coord);
            speelveld[coord.x][coord.y] = niets;
        }
    }

    public void wandel(Ding ding) {

        List<Coordinaat> vrijePlaatsen;
        vrijePlaatsen = getVrijePlaatsen(ding, getPlaats(ding));
        if (!vrijePlaatsen.isEmpty()) {
            speelveld[getPlaats(ding).x][getPlaats(ding).y] = niets;
            Coordinaat naarPlaats = vrijePlaatsen.get(coordinaatRandom.nextInt(vrijePlaatsen.size()));
            speelveld[naarPlaats.x][naarPlaats.y] = ding;
        }
    }

    void startBewegingen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
