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
 * @author Arne Simons & Omer Aldur
 */
public class Herbivoor extends LevendWezen {

    public Herbivoor(Terrarium terrarium) {
        super(terrarium);
        Random lwRandom = new Random();
        super.setLevenswaarde((long) (lwRandom.nextInt(7) + 4));
    }

    @Override
    public void interageerMetEenDing(Ding ding) {
        ding.interageerMetEenHerbivoor(this);
    }

    @Override
    public void interageerMetEenCarnivoor(Carnivoor carnivoor) {
        carnivoor.interageerMetEenHerbivoor(this);
    }

    @Override
    public void interageerMetEenHerbivoor(Herbivoor herbivoor) {
        this.boemboem(herbivoor);
    }

    @Override
    public void interageerMetEenPlant(Plant plant) {
        this.eet(plant);
    }

    @Override
    public void interageerMetNiets(Niets niets) {
        this.wandel();
    }

    private void boemboem(Herbivoor herbivoor) {
        terrarium.maakNieuweHerbivoor();
    }

    private void eet(Plant plant) {
        setLevenswaarde(this.getLevenswaarde() + plant.getLevenswaarde());
        terrarium.sterf(plant, terrarium.getPlaats(plant));

    }

    private void wandel() {
        /*if (getLevenswaarde() == 0 && vrijeplaatsen.size() < 8) {
            terrarium.sterf(this, huidigePlaats);
        } else {
            terrarium.wandel(this);
        }*/
        Coordinaat huidigePlaats = terrarium.getPlaats(this);

        List<Coordinaat> vrijeplaatsen = terrarium.getVrijePlaatsen(this, huidigePlaats);

        if (getLevenswaarde() == 0 && vrijeplaatsen.size() < 8) {
            terrarium.sterf(this, huidigePlaats);
        } else if (vrijeplaatsen.size() < 8) {
            Random coordinaatRandom = new Random();
            Coordinaat plaats = (Coordinaat) vrijeplaatsen.get(coordinaatRandom.nextInt(vrijeplaatsen.size()));
            terrarium.wandel(this);
            this.setLevenswaarde(this.getLevenswaarde() - 1);
        }
    }
}
