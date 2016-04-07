/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.terrarium.items;

import be.vdab.terrarium.Terrarium;

/**
 *
 * @author thomas.vandevyvere
 */
public abstract class Ding {
    
    public final Terrarium terrarium;

    public Ding(Terrarium terrarium) {
        this.terrarium = terrarium;
    }
    
    public abstract void interageerMetEenDing( Ding ding );
    public abstract void interageerMetEenCarnivoor(Carnivoor carnivoor);
    public abstract void interageerMetEenHerbivoor(Herbivoor herbivoor);
    public abstract void interageerMetEenPlant(Plant plant);
    public abstract void interageerMetNiets(Niets niets);
}
