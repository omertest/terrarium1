/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.terrarium;

/**
 *
 * @author thomas.vandevyvere
 */
public class Coordinaat {
    int x, y;
    public Coordinaat(int x, int y){
        setCoordinaat(x, y);
    }
    
    public void setCoordinaat(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
