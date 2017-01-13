package de.lette_verein.lettemensaspeiseplan.management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by NikolaiJay on 28.05.2016.
 */


public class Datamanager extends SQLiteOpenHelper {

    private static ArrayList<Tagesplan> tagesplane;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Speiseplan.db";

    private static final String SPEISETABLE = "speisen";
    private static final String SPEISENCOLUMN_ID = "_id";
    private static final String SPEISENCOLUMN_NAME = "_name";
    private static final String SPEISENCOLUMN_ART = "_art";
    private static final String SPEISENCOLUMN_BEACHTE = "_beachte";
    private static final String SPEISENCOLUMN_KCAL = "_kcal";
    private static final String SPEISENCOLUMN_EIWEISSE = "_eiweisse";
    private static final String SPEISENCOLUMN_KOHLENHYDRATE = "_kohlenhydrate";
    private static final String SPEISENCOLUMN_FETTE = "_fette";
    private static final String SPEISENCOLUMN_BESCHREIBUNG = "_beschreibung";
    private static final String SPEISENCOLUMN_PREIS = "_preis";

    private static final String TERMINTABLE = "termine";
    private static final String TERMINCOLUMN_ID = "_id";
    private static final String TERMINCOLUMN_DATUM = "_datum";
    private static final String TERMINCOLUMN_SPEISE = "_speise";
    private static final String TERMINCOLUMN_ISDIAT = "_isDiat";

    private static final String ZUSATZTABLE = "zusatz";
    private static final String ZUSATZCOLUMN_ID = "_id";
    private static final String ZUSATZCOLUMN_NAME = "_name";

    private static final String ZUSATZLINKTABLE = "zusatzlink";
    private static final String ZUSATZLINKCOLUMN_SPEISEID = "_speise";
    private static final String ZUSATZLINKCOLUMN_ZUSATZID = "_zusatz";

    private static ArrayList testlist;

    public Datamanager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);


        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SPEISETABLE + " WHERE 1",null);
        cursor.moveToFirst();

        testlist = new ArrayList();

        while (!cursor.isAfterLast()){
            if (cursor.getString(cursor.getColumnIndex(SPEISENCOLUMN_NAME))!= null){
                testlist.add(cursor.getColumnIndex(SPEISENCOLUMN_NAME));
            }
        }

        db.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {




        String querry = "CREATE TABLE IF NOT EXISTS " + SPEISETABLE + " (" + SPEISENCOLUMN_ID + " INTEGER PRIMARY KEY ," + SPEISENCOLUMN_NAME +
                " TEXT ," + SPEISENCOLUMN_ART + " TEXT ," + SPEISENCOLUMN_BEACHTE + " TEXT ," + SPEISENCOLUMN_KCAL + " INTEGER, " +
                 SPEISENCOLUMN_EIWEISSE + " INTEGER ," + SPEISENCOLUMN_KOHLENHYDRATE + " INTEGER ," + SPEISENCOLUMN_FETTE +
                " INTEGER ," + SPEISENCOLUMN_BESCHREIBUNG + " TEXT ," + SPEISENCOLUMN_PREIS + " REAL" + ");";

        db.execSQL(querry);

        querry = "CREATE TABLE IF NOT EXISTS " + TERMINTABLE + " (" + TERMINCOLUMN_ID + " INTEGER PRIMARY KEY ," + TERMINCOLUMN_DATUM +
                " TEXT ," + TERMINCOLUMN_SPEISE + " INTEGER ," + TERMINCOLUMN_ISDIAT + " INTEGER " +");";

        db.execSQL(querry);

        querry = "CREATE TABLE IF NOT EXISTS " + ZUSATZTABLE + " (" + ZUSATZCOLUMN_ID + " INTEGER PRIMARY KEY ," + ZUSATZCOLUMN_NAME +
                " TEXT " + ");";

        db.execSQL(querry);

        querry = "CREATE TABLE IF NOT EXISTS " + ZUSATZLINKTABLE + " (" + ZUSATZLINKCOLUMN_SPEISEID + " INTEGER ," + ZUSATZLINKCOLUMN_ZUSATZID + " INTEGER " + ");";

        db.execSQL(querry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ SPEISETABLE);
        db.execSQL("DROP TABLE IF EXIST "+ TERMINTABLE);
        db.execSQL("DROP TABLE IF EXIST "+ ZUSATZTABLE);
        db.execSQL("DROP TABLE IF EXIST "+ ZUSATZLINKTABLE);
        onCreate(db);
    }

    private void addSpeise(Speise speise){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SPEISENCOLUMN_ID, speise.get_id());
        values.put(SPEISENCOLUMN_NAME, speise.get_name());
        values.put(SPEISENCOLUMN_ART, speise.get_art().toString());
        values.put(SPEISENCOLUMN_BEACHTE, speise.get_beachte());
        values.put(SPEISENCOLUMN_KCAL, speise.get_kcal());
        values.put(SPEISENCOLUMN_EIWEISSE, speise.get_eiweisse());
        values.put(SPEISENCOLUMN_KOHLENHYDRATE, speise.get_kohlenhydrate());
        values.put(SPEISENCOLUMN_FETTE, speise.get_fette());
        values.put(SPEISENCOLUMN_BESCHREIBUNG, speise.get_beschreibung());
        values.put(SPEISENCOLUMN_PREIS, speise.get_preis());


        db.insert(SPEISETABLE,null,values);


        db.close();
    }

    private void deleteSpeise(int id){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + SPEISETABLE + " WHERE " + SPEISENCOLUMN_ID + " =\"" + id + "\"");

        db.close();
    }

    private void addTermin(int id, SimpleDate date, Speise speise, boolean isDiat){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TERMINCOLUMN_ID, id);
        values.put(TERMINCOLUMN_DATUM, date.toString());
        values.put(TERMINCOLUMN_SPEISE, speise.get_id());
        values.put(TERMINCOLUMN_ISDIAT, (isDiat)?"1":"0");

        db.insert(TERMINTABLE,null,values);


        db.close();
    }

    private void deleteTermin(SimpleDate date){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TERMINTABLE + " WHERE " + TERMINCOLUMN_DATUM + " =\"" + date.toString() + "\"");

        db.close();
    }

    private void addZusatz(int id, String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ZUSATZCOLUMN_ID, id);
        values.put(ZUSATZCOLUMN_NAME, name);

        db.insert(ZUSATZTABLE,null,values);

        db.close();
    }

    private void deleteZusatz(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + ZUSATZTABLE + " WHERE " + ZUSATZCOLUMN_ID + " =\"" + id + "\"");
        db.close();
    }

    private void addLinkZusatz(int zusatzID, int speiseID){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ZUSATZLINKCOLUMN_SPEISEID, speiseID);
        values.put(ZUSATZLINKCOLUMN_ZUSATZID, zusatzID);

        db.insert(ZUSATZLINKTABLE,null,values);

        db.close();
    }

    private void deleteLinkZusatz(int zusatzID){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + ZUSATZLINKCOLUMN_ZUSATZID + " WHERE " + ZUSATZLINKCOLUMN_ZUSATZID + " =\"" + zusatzID + "\"");
        db.close();
    }



    public static void init(Context context){
        Datamanager datamanager = new Datamanager(context,null,null,1);
        for (Object o:testlist){
            System.out.println(o);
        }
        tagesplane = new ArrayList<>();
        tagesplane.add(new Tagesplan(new SimpleDate(28, 5, 2016), Arrays.asList(new Speise(1,"test",Kategorie.BEILAGE,null,0,0,0,0,null,3),new Speise(1,"test2",Kategorie.LEICHTEVORKOST,null,0,0,0,0,null,3))));
    }



    public static void retriveSpeiseplanFromDatabase(SimpleDate date) throws MalformedURLException {


    }

    public static Tagesplan getTagesplanByDate(SimpleDate date){
        for (Tagesplan tagesplan:tagesplane) {
            if (tagesplan.getDate().equals(date)){return tagesplan;}
        }
        return null;
    }




}
