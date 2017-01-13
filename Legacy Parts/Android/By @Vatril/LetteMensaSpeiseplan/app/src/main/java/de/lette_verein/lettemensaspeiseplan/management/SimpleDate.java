package de.lette_verein.lettemensaspeiseplan.management;

/**
 * Created by NikolaiJay on 26.05.2016.
 */
public class SimpleDate {

    private int day;
    private int month;
    private  int year;

    public SimpleDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return ((day<10)?"0" + day:day) + "." + ((month<10)?"0" + month:month) + "." + year;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SimpleDate){
            SimpleDate simpleDate = (SimpleDate) o;
            if (simpleDate.getDay() == getDay() && simpleDate.getMonth() == getMonth() && simpleDate.getYear() == getYear()){
                return true;
            }
        }
        return false;
    }
}
