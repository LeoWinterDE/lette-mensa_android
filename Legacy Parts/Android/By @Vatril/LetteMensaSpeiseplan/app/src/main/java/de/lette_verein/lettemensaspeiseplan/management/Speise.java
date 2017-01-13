package de.lette_verein.lettemensaspeiseplan.management;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by NikolaiJay on 28.05.2016.
 */
public class Speise {
    private int _id;
    private String _name;
    private Kategorie _art;
    private String _beachte;
    private int _kcal;
    private int _eiweisse;
    private int _fette;
    private int _kohlenhydrate;
    private String _beschreibung;
    private double _preis;


    public Speise(@NonNull int id, @NonNull String name, @NonNull Kategorie art, @Nullable String beachte, @Nullable int kcal,
                  @Nullable int eiweisse, @Nullable int fette, @Nullable int kohlenhydrate, @Nullable String beschreibung, @NonNull double preis) {
        this._id = id;
        this._name = name;
        this._art = art;
        this._beachte = beachte;
        this._kcal = kcal;
        this._eiweisse = eiweisse;
        this._fette = fette;
        this._kohlenhydrate = kohlenhydrate;
        this._beschreibung = beschreibung;
        this._preis = preis;
    }

    public int get_id() {
        return _id;
    }

    public String getPreisInEuro(){return _preis + "€";}


    public String get_name() {
        return _name;
    }

    public Kategorie get_art() {
        return _art;
    }

    public String get_beachte() {
        return _beachte;
    }

    public int get_kcal() {
        return _kcal;
    }

    public int get_eiweisse() {
        return _eiweisse;
    }

    public int get_fette() {
        return _fette;
    }

    public int get_kohlenhydrate() {
        return _kohlenhydrate;
    }

    public String get_beschreibung() {
        return _beschreibung;
    }

    public double get_preis() {
        return _preis;
    }

    @Override
    public String toString() {
        return _name;
    }
}
