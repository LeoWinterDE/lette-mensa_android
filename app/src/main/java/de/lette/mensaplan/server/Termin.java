package de.lette.mensaplan.server;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * BITTE NICHT NUTZEN! Diese Klasse dient deserialisierungszwecken.
 * Representiert deinen Eintrag aus der Tabelle termine.
 *
 * @author Tommy Schmidt
 */
public class Termin {
    private int id; // id
    private Date datum; // datum
    private int speiseId; // speise
    private BigDecimal preis; // preis
    private boolean isDiät; // isDiaet

    public Termin() {
    }

    public Termin(int id, Date datum, int speiseId, BigDecimal preis, boolean isDiät) {
        this.id = id;
        this.datum = datum;
        this.speiseId = speiseId;
        this.preis = preis;
        this.isDiät = isDiät;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public java.util.Date getJavaDatum() {
        java.util.Date returnDate = new java.util.Date(datum.getTime());
        return returnDate;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getSpeiseId() {
        return speiseId;
    }

    public void setSpeiseId(int speiseId) {
        this.speiseId = speiseId;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal bigDecimal) {
        this.preis = bigDecimal;
    }

    public boolean isDiät() {
        return isDiät;
    }

    public void setDiät(boolean isDiät) {
        this.isDiät = isDiät;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Termin) {
            Termin termin = (Termin) obj;
            if (getId() != termin.getId()) return false;
            if (!getDatum().equals(termin.getDatum())) return false;
            if (getSpeiseId() != termin.getSpeiseId()) return false;
            if (!getPreis().equals(termin.getPreis())) return false;
            if (isDiät() != termin.isDiät()) return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (41 * (41 + getId()) + getSpeiseId());
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}