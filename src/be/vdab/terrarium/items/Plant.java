/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.terrarium.items;

import be.vdab.terrarium.Terrarium;

/**
 *
 * @author arne.simons
 */
public class Plant extends LevendWezen {

    public Plant(Terrarium terrarium) {
        super(terrarium);
        super.setLevenswaarde(0);
    }

    @Override
    public void interageerMetEenDing(Ding ding) {
        ding.interageerMetEenPlant(this);
    }

    @Override
    public void interageerMetEenCarnivoor(Carnivoor carnivoor) {
        carnivoor.interageerMetEenPlant(this);
    }

    @Override
    public void interageerMetEenHerbivoor(Herbivoor herbivoor) {
        herbivoor.interageerMetEenPlant(this);
    }

    @Override
    public void interageerMetEenPlant(Plant plant) {
        // doet niks
    }

    @Override
    public void interageerMetNiets(Niets niets) {
        // doet niks
    }
    
}