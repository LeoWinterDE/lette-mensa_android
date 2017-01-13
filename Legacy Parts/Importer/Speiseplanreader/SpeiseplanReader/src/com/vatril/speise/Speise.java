package com.vatril.speise;


import java.util.Date;

/**
 * Created by vatril on 12.09.16.
 */
public class Speise {
    private String name,beachte;
    private double preis;
    private Art art;
    private int[] zusatsstoffe;
    private Date date;

    public Speise(String name, String beachte, double preis, Art art, int[] zusatsstoffe, Date date) {
        this.name = name;
        this.beachte = beachte;
        this.preis = preis;
        this.art = art;
        this.zusatsstoffe = zusatsstoffe;
        this.date = date;
    }




    public enum Art{
        VORSPEISE,VEGETARISCH,VOLLKOST,BEILAGE,DESSERT
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        if (zusatsstoffe != null)

        for (int i : zusatsstoffe) {
            builder.append(i + " ");
        }

        return "Speise: \n"+
                "  name: " + name + ",\n" +
                "  preis: " + preis + ",\n" +
                "  art: " + art + ",\n" +
                "  Datum: " + date + ",\n" +
                "  beachte: " + beachte + ",\n" +
                "  zusatzstoffe: " + builder.toString() + "\n";
    }
}