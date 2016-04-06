/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.terrarium.items;

/**
 *
 * @author arne.simons
 */
public class Plant extends Ding {

    public Plant() {
        super.setLevenswaarde(0);
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