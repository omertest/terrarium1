/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.terrarium.items;

import be.vdab.terrarium.Coordinaat;
import be.vdab.terrarium.Terrarium;
import java.util.List;
import java.util.Random;

/**
 *
 * @author thomas.vandevyvere & nick.meeus
 */
public class Carnivoor extends LevendWezen {

    Random lwRandom = new Random();
    private final Terrarium terrarium;

    public Carnivoor(Terrarium terrarium) {
        super(terrarium);
        super.setLevenswaarde((long) (lwRandom.nextInt(7) + 4));
        this.terrarium = terrarium;
    }

    /*@Override
    public void verhoogLevenswaarde(long levenswaarde) {
        if (Long.MAX_VALUE - super.getLevenswaarde() < levenswaarde) {
            super.setLevenswaarde(Long.MAX_VALUE);
        } else {
            super.setLevenswaarde(super.getLevenswaarde() + levenswaarde);
        }
    }*/

    @Override
    public void interageerMetEenDing(Ding ding) {
        ding.interageerMetEenCarnivoor(this);
    }

    @Override
    public void interageerMetEenCarnivoor(Carnivoor carnivoor) {
        this.vecht(carnivoor);
    }

    @Override
    public void interageerMetEenHerbivoor(Herbivoor herbivoor) {
        this.eet(herbivoor);
    }

    @Override
    public void interageerMetEenPlant(Plant plant) {
        this.wandel();
    }

    @Override
    public void interageerMetNiets(Niets niets) {
        this.wandel();
    }

    private void vecht(Carnivoor carnivoor) {
        long verschil = this.getLevenswaarde() - carnivoor.getLevenswaarde();
        if (verschil > 0) {
            setLevenswaarde(this.getLevenswaarde() + carnivoor.getLevenswaarde());
            carnivoor.setLevenswaarde(0);
        } else if (verschil < 0) {
            setLevenswaarde(0);
            carnivoor.setLevenswaarde(this.getLevenswaarde() + carnivoor.getLevenswaarde());
        } else {
            //gelijkspel
        }
    }

    private void eet(Herbivoor herbivoor) {
        setLevenswaarde(this.getLevenswaarde() + herbivoor.getLevenswaarde());
        herbivoor.setLevenswaarde(0);
    }

    private void wandel() {
        Coordinaat huidigePlaats = terrarium.getPlaats(this);
        List<Coordinaat> vrijeplaatsen = terrarium.getVrijePlaatsen(this, huidigePlaats);
        
        if(getLevenswaarde() == 0 && vrijeplaatsen.size() < 8){
            terrarium.sterf(this, huidigePlaats);
        } else if(vrijeplaatsen.size() < 8){
            Random coordinaatRandom = new Random();
            Coordinaat plaats = (Coordinaat) vrijeplaatsen.get(coordinaatRandom.nextInt(vrijeplaatsen.size()));
            terrarium.wandel(this, plaats);
        }
            
        
    }
}
