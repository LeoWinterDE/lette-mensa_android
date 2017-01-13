package de.lette.mensaplan.server;

import com.google.gson.Gson;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Representiert einen Eintrag aus der Tabelle speisen.
 *
 * @author Tommy Schmidt
 */
public class Speise {
    private int id; // id
    private String name; // name
    private SpeiseArt art; // art
    private boolean isDiät; // isDiaet
    private String beachte; // beachte
    private int kcal; // kcal
    private int eiweiß; // eiweisse
    private int fett; // fette
    private int kohlenhydrate; // kohlenhydrate
    private Set<Integer> zusatzstoffe; // Tabelle zusatzstoffelink
    private int likes;
    private int dislikes;

    public Speise() {
        zusatzstoffe = new LinkedHashSet<Integer>();
    }

    public Speise(int id, String name, SpeiseArt typ, boolean isDiät, String beachte, int kcal, int eiweiß, int fett, int kohlenhydrate) {
        this();
        this.id = id;
        this.name = name;
        this.art = typ;
        this.isDiät = isDiät;
        this.beachte = beachte;
        this.kcal = kcal;
        this.eiweiß = eiweiß;
        this.fett = fett;
        this.kohlenhydrate = kohlenhydrate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpeiseArt getArt() {
        return art;
    }

    public void setArt(SpeiseArt art) {
        this.art = art;
    }

    public boolean isDiät() {
        return isDiät;
    }

    public void setDiät(boolean isDiät) {
        this.isDiät = isDiät;
    }

    public String getBeachte() {
        return beachte;
    }

    public void setBeachte(String beachte) {
        this.beachte = beachte;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getEiweiß() {
        return eiweiß;
    }

    public void setEiweiß(int eiweiß) {
        this.eiweiß = eiweiß;
    }

    public int getFett() {
        return fett;
    }

    public void setFett(int fett) {
        this.fett = fett;
    }

    public int getKohlenhydrate() {
        return kohlenhydrate;
    }

    public void setKohlenhydrate(int kohlenhydrate) {
        this.kohlenhydrate = kohlenhydrate;
    }

    public Set<Integer> getZusatzstoffe() {
        return zusatzstoffe;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Speise) {
            Speise speise = (Speise) obj;
            if (getId() != speise.getId()) return false;
            if (!getBeachte().equals(speise.getBeachte())) return false;
            if (getEiweiß() != speise.getEiweiß()) return false;
            if (getFett() != speise.getFett()) return false;
            if (getKcal() != speise.getKcal()) return false;
            if (getKohlenhydrate() != speise.getKohlenhydrate()) return false;
            if (getArt() != speise.getArt()) return false;
            if (isDiät() != speise.isDiät()) return false;
            if (getLikes() != speise.getLikes()) return false;
            if (getDislikes() != speise.getDislikes()) return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (41 * (41 + getId()));
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}