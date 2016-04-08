/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.terrarium;

import be.vdab.terrarium.items.Carnivoor;
import be.vdab.terrarium.items.Ding;
import be.vdab.terrarium.items.Herbivoor;
import be.vdab.terrarium.items.Plant;
import java.util.List;

/**
 *
 * @author arne.simons
 */
public class Terrarium {

    private final int aantalPlanten; //default 60
    private final int aantalHerbivoren; //default 30
    private final int aantalCarnivoren; //default 10    
    private final int x; //horizontaal, default 20
    private final int y; //verticaal, default 20
    private final Speelveld speelveld;

    public Terrarium(int aantalPlanten, int aantalHerbivoren, int aantalCarnivoren, int x, int y) {
        this.aantalPlanten = aantalPlanten;
        this.aantalHerbivoren = aantalHerbivoren;
        this.aantalCarnivoren = aantalCarnivoren;
        this.x = x;
        this.y = y;

        speelveld = new Speelveld(x, y, this);

        for (int i = 1; i <= aantalPlanten; i++) {
            speelveld.dingToevoegen((Ding) new Plant(this));
        }

        for (int i = 1; i <= aantalHerbivoren; i++) {
            speelveld.dingToevoegen((Ding) new Herbivoor(this));
        }

        for (int i = 1; i <= aantalCarnivoren; i++) {
            speelveld.dingToevoegen((Ding) new Carnivoor(this));
        }
    }

    public Speelveld getSpeelveld() {
        return speelveld;
    }

    // 1 actie
    public void start() {
        // alle planten + levenswaarde
        for (Coordinaat c : speelveld.plant) {
            Plant p = (Plant) speelveld.getDing(c);
            p.setLevenswaarde(p.getLevenswaarde() + 1);
        }

        // Per actie komen er 0 tot 10 nieuwe planten bij
        int aantalNieuwePlanten = (int) (Math.random() * 11);
        for (int i = 1; i <= aantalNieuwePlanten; i++) {
            speelveld.dingToevoegen((Ding) new Plant(this));
        }

        // alle herbivoren doen hun ding
        for (Coordinaat c : speelveld.herbivoor) {
            Herbivoor h = (Herbivoor) speelveld.getDing(c);
            // h.interageerMetEenDing();  - ding rechts ervan vandoen + check c.x = 20
        }
        // alle carnivoren doen hun ding
        for (Coordinaat c : speelveld.carnivoor) {
            Carnivoor carni = (Carnivoor) speelveld.getDing(c);
            // carni.interageerMetEenDing();
        }
    }

    public Coordinaat getPlaats(Ding ding) {
        return speelveld.getPlaats(ding);
    }

    public List<Coordinaat> getVrijePlaatsen(Ding ding) {
        return speelveld.getVrijePlaatsen(ding);
    }

    public void maakNieuweHerbivoor() {
        speelveld.dingToevoegen((Ding) new Herbivoor(this));
    }

    public void sterf(Ding ding) {
        speelveld.verwijder(ding);
    }

    public void wandel(Ding ding) {
        speelveld.wandel(ding);
    }

}
