package de.lette.mensaplan.server;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Representiert deinen Eintrag aus der Tabelle zusatzstoffe.
 *
 * @author Tommy Schmidt
 */
public class Zusatzstoff implements Serializable {
    private static final long serialVersionUID = -4233149297103159036L;
    private int id;
    private int nummer;
    private String name;

    public Zusatzstoff(int id, int nummer, String name) {
        this.id = id;
        this.nummer = nummer;
        this.name = name;
    }

    /**
     * Gibt die Datenbank-Id dieses Zusatzstoffs zurück.
     *
     * @return Datenbank-Id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gibt die Id zurück, die diesen Zusatzstoff im Speiseplan representiert.
     *
     * @return die Id, die diesen Zusatzstoff im Speiseplan representiert
     */
    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Zusatzstoff) {
            Zusatzstoff zusatzstoff = (Zusatzstoff) obj;
            if (getId() != zusatzstoff.getId()) return false;
            if (!getName().equals(zusatzstoff.getName())) return false;
            if (getNummer() != zusatzstoff.getNummer()) return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (41 * (41 + getId()) + getNummer());
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}