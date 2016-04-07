/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.util.ArrayList;

/**
 *
 * @author thomas.vandevyvere
 */
public class NewClass {
    public static void main(String[]args){
        ArrayList<String> al = new ArrayList<>(10);
        al.add("een");
        al.add("twee");
        al.add("drie");
        System.out.println(al);
        System.out.println(al.indexOf("een"));
        System.out.println(al.indexOf("twee"));
        System.out.println(al.indexOf("drie"));
        System.out.println(al.indexOf("vier"));
        al.remove("twee");
        System.out.println(al);
        System.out.println(al.indexOf("een"));
        System.out.println(al.indexOf("twee"));
        System.out.println(al.indexOf("drie"));
        System.out.println(al.indexOf("vier"));
        al.add(1,"vier");
        System.out.println(al);
        System.out.println(al.indexOf("een"));
        System.out.println(al.indexOf("twee"));
        System.out.println(al.indexOf("drie"));
        System.out.println(al.indexOf("vier"));
        
    }
}
