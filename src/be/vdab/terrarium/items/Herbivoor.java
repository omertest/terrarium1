/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.terrarium.items;

import java.util.Random;

/**
 *
 * @author thomas.vandevyvere & nick.meeus
 */
public class Herbivoor extends Ding{
    
    Random lwRandom = new Random();

    public Herbivoor() {
        super.setLevenswaarde((long) (lwRandom.nextInt(7) + 4));
    }

    
    @Override
    public void verhoogLevenswaarde(long levenswaarde){
        if (Long.MAX_VALUE - super.getLevenswaarde() > levenswaarde) {
            super.setLevenswaarde(Long.MAX_VALUE);
        } else {
            super.setLevenswaarde(super.getLevenswaarde() + levenswaarde);
        }
    }
}
