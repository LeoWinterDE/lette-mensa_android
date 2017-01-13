package de.lette_verein.lettemensaspeiseplan.management;

/**
 * Created by NikolaiJay on 28.05.2016.
 */
public enum Kategorie {
    VORSPEISE,LEICHTEVORKOST,DESSERT,GEMUESETELLER,VEGETARISCH,VOLLKOST,BEILAGE,UNKNOWN;

    public Kategorie getKategoryFromSQLEnum(String sqlEnum){
        switch (sqlEnum){
            case "Vorspeise":return VORSPEISE;
            case "Leichte Vorkost":return LEICHTEVORKOST;
            case "Dessert":return DESSERT;
            case "Gemüseteller":return GEMUESETELLER;
            case "Vegetarisch":return VEGETARISCH;
            case "Vollkost":return VOLLKOST;
            case "Beilagen":return BEILAGE;
            default:return UNKNOWN;
        }
    }
}
