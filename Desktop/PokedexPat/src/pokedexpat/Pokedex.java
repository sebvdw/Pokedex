/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexpat;
import java.util.*;
import java.util.logging.Logger;

/**
 *
 * @author SeaBassCarstens
 */



public class Pokedex {
    private String name = "Unknown";
    private String type = "Uknown";
    private double weight = 0.0;
    private double height = 0.0;
    private String gender = "Uknown";
    private int catchrate = 0;
    private boolean finalevo = false;
    private String pokenum = "0";
    static final int num = 000;

    public Pokedex() {
    }


    public Pokedex(String n, String t, double w, double h, String g, int c, boolean f, String p) {
        setName(n);
        setType(t);
        setWeight(w);
        setHeight(h);
        setGender(g);
        setCatchrate(c);
        setFinalevo(f);
        setPokenum(p);
        
    }

    public String getPokenum() {
        return pokenum;
    }

    public void setPokenum(String newpokenum) {
        String equ = newpokenum + "";
        if (equ.length() < 3) {
            equ = "000" +equ;
        }
        String eq = "";
        for (int i = 0; i < 3; i++) {
           eq = equ.charAt((equ.length()-1)-i)+eq;
       }
        pokenum = "#"+eq;
    }
   
    public String getName() {
        return name;
    }

    public void setName(String newname) {
        if (newname.equals("")) {
            name = "Unown";
        }
        else{
            name = newname;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String newtype) {
        if (newtype.equals("")) {
            type = "Unknown";
        }
        else {
            type = newtype;
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double newweight) {
        if (newweight < 0.0) {
            weight = 0.0;
        }
        else {
            weight = newweight;
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double newheight) {
        if (newheight < 0.0) {
            height = 0.0;
        }
        else {
            height = newheight;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String newgender) {
        if (newgender.equals("")) {
            gender = "Uknown";
        }
        else{
            gender = newgender;
        }
    }

    public int getCatchrate() {
        return catchrate;
    }

    public void setCatchrate(int newcatchrate) {
        if (newcatchrate < 0) {
            catchrate = 45;
        }
        else {
            catchrate = newcatchrate;
        }
    }

    public boolean isFinalevo() {
        return finalevo;
    }

    public void setFinalevo(boolean newfinalevo) {
        
            finalevo = newfinalevo;
        
    }

    @Override
    public String toString() {
        return name;
                }
   
    
    
}
