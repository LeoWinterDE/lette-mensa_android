package de.lette.mensaplan.server;

/**
 * Diese Klasse representiert die Enum Speise.typ.
 *
 * @author Tommy Schmidt
 */
public enum SpeiseArt {
    VORSPEISE("Vorspeise"), VEGETARISCH("Vegetarisch"), VOLLKOST("Vollkost"), BEILAGEN("Beilagen"), DESSERT("Dessert"), LEICHTEVOLLKOST("Leichte Vollkost"), GEMÜSETELLER("Gemüseteller");

    private String typ;

    private SpeiseArt(String typ) {
        this.typ = typ;
    }

    public String getTyp() {
        return typ;
    }
}