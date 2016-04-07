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
public abstract class LevendWezen extends Ding {
    private long levenswaarde;

    public LevendWezen(Terrarium terrarium, long levenswaarde) {
        super(terrarium);
        this.levenswaarde = levenswaarde;
    }
        
    public long getLevenswaarde(){
        return levenswaarde;
    }

    public void setLevenswaarde(long levenswaarde) {
        this.levenswaarde = levenswaarde;
    }
    
    public void verhoogLevenswaarde(long levenswaarde){}    
}
