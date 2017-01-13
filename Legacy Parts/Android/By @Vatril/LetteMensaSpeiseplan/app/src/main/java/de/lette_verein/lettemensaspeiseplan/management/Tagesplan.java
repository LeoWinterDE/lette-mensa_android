package de.lette_verein.lettemensaspeiseplan.management;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by NikolaiJay on 28.05.2016.
 */
public class Tagesplan {
    private SimpleDate date;
    private ArrayList<Speise> speisen;

    public Tagesplan(SimpleDate date, Collection<Speise> tagesspeisen) {
        this.date = date;
        speisen = new ArrayList<>();

        for(Speise sp:tagesspeisen){
            speisen.add(sp);
        }
    }

    public SimpleDate getDate() {
        return date;
    }

    public ArrayList<Speise> getSpeisen() {
        return speisen;
    }

    public void sort(){
        ArrayList<Speise> list = new ArrayList();

        while (speisen.size() > 0){
            list.add(speisen.get(0));
            speisen.remove(0);
        }

        for (Speise speise: list){
            if (speise.get_art() == Kategorie.VORSPEISE);
            speisen.add(speise);
        }

        for (Speise speise: list){
            if (speise.get_art() == Kategorie.LEICHTEVORKOST);
            speisen.add(speise);
        }

        for (Speise speise: list){
            if (speise.get_art() == Kategorie.VEGETARISCH);
            speisen.add(speise);
        }

        for (Speise speise: list){
            if (speise.get_art() == Kategorie.VOLLKOST);
            speisen.add(speise);
        }

        for (Speise speise: list){
            if (speise.get_art() == Kategorie.GEMUESETELLER);
            speisen.add(speise);
        }

        for (Speise speise: list){
            if (speise.get_art() == Kategorie.BEILAGE);
            speisen.add(speise);
        }

        for (Speise speise: list){
            if (speise.get_art() == Kategorie.DESSERT);
            speisen.add(speise);
        }
    }
}
