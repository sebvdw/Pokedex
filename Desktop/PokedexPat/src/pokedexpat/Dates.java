/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokedexpat;

/**
 *
 * @author SeaBassCarstens
 */
public class Dates {
    public int year = 2000;
    public int month = 0;
    public int day = 0;


    public Dates(int m, int d, int y) {
        setMonth(m);
        setDay(d);
        setYear(y);
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int newyear) {
        
        year = newyear;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int newmonth) {
        month = newmonth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int newday) {
        day = newday;
    }

    @Override
    public String toString() {
        return "" +month+"/"+day+"/"+ year;
    }
    
    
    
}
