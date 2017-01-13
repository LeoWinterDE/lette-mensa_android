package de.lette.mensaplan;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.lette.mensaplan.server.ClientData;
import de.lette.mensaplan.server.Termin;

// TODO: CONNECTION-ERROR HANDLING!
public class ConnectionHandler {
    // URL für Netzzugriff
    public static String urlToServer = "https://api.myjson.com/bins/4i63n";
    private static List<Tagesplan> tagesPläne;

    public static List<Tagesplan> getClientData() throws IOException, URISyntaxException, ParseException {
        if (tagesPläne != null)
            return tagesPläne;
        Log.i("CONNECTION", "GET");


        // TODO: last edit 3.dez. daten kommen an, doch ClientData und co. muss noch kompltt umgebaut werden. damit das akutelle json modeell verarbeitet werden kann.

        Gson gson = new Gson();
        ClientData data = getClientDataFromServer();
        //ClientData data = gson.fromJson(json);
        String name, beschreibung, beachte, preis, kcal, fette, eiweisse, kolenhydrate, zusatzstoffe;
        try {
            // mensa
            for (int i = 0; i < c.length("mensa0", "mensa1"); i++) {
                // datum, gerichte
                for (int i = 0; i < data.length(); i++) {
                    //JSONObject c = data.getJSONObject(i);
                    //String datum = c.getString();

                    String r_name = c.getString(name);
                    String r_beschreibung = c.getString(beschreibung);
                    String r_beachte = c.getString(beachte);
                    String r_preis = c.getString(preis);
                    String r_kcal = c.getString(kcal);
                    String r_fette = c.getString(fette);
                    String r_eiweisse = c.getString(eiweisse);
                    String r_kolenhydrate = c.getString(kolenhydrate);
                    ArrayList<String> r_zusatzstoffe = Arrays.asList(zusatzstoffe.split(","));

                    HashMap<String, String> map = new HashMap<String, String>();

                    //map.put(VTYPE, vtype);
                    //map.put(VCOLOR, vcolor);
                    //map.put(FUEL, vfuel);
                    //map.put(TREAD, vtread);
                    //jsonlist.add(map);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("CONNECTION", "Daten Geladen");
        // TODO: Speise to Termin (speise.setTermin(termin))
        tagesPläne = new ArrayList<Tagesplan>();
        for (Entry<java.util.Date, Map<de.lette.mensaplan.server.Speise, Termin>> dateMap : data.getSpeisenForDate().entrySet()) {
            // Normalerweise ist jedes Date-Objekt ein Tag, es kann jedoch auch
            // vorkommen, dass 2 Date-Objekte 1 Tag sind.
            Tagesplan tagesPlan = new Tagesplan(dateMap.getKey());
            for (Entry<de.lette.mensaplan.server.Speise, Termin> dataMap : dateMap.getValue().entrySet()) {
                de.lette.mensaplan.server.Speise s = dataMap.getKey();
                Speise speise = new Speise(s.getId(), s.getName(), s.getArt(), dataMap.getValue().isDiät(), dataMap.getValue().getPreis(), s.getBeachte(), s.getKcal(), s.getEiweiß(), s.getFett(), s.getKohlenhydrate(), data.getZusatzstoffe(s), s.getLikes(), s.getDislikes());
                tagesPlan.addSpeise(speise);
                Log.i("CONNECTION", speise.getName());
            }
            tagesPläne.add(tagesPlan);
        }
        return tagesPläne;
    }

    /**
     * Diese Methode stellt eine Verbindung zum MensaplanServer her, lädt die gewünschten Daten herunter und gibt sie als ClientData-Objekt zurück.
     *
     * @return das ClientData-Objekt
     * @throws IOException        wenn die Verbindung mit dem Server nicht aufgebaut werden konnte oder Fehler beim Lesen auftreten.
     * @throws URISyntaxException
     */
    @SuppressLint("NewApi")
    private static ClientData getClientDataFromServer() throws IOException, URISyntaxException {
        URL url = new URL(urlToServer);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("charset", "UTF-8");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestMethod("GET");

        BufferedReader result = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

        Log.d("GET", "" + result);
        result.close();

        Gson gson = new Gson();
        ClientData data = gson.fromJson(result, ClientData.class);
        if (data == null) {
            new ClientData();
        }
        return data;
    }

    /**
     * @param userId   die Id vom Gerät des Nutzers
     * @param speiseId die Id der Speise, die zu bewerten ist
     * @param action   entweder "like", "dislike" oder "reset" (siehe Action im Server)
     * @throws IOException TODO: Speise to Termin (speiseId)
     */
    // ToDo and Outdated - Currently NO API endpoint for meal ratings.
    public static boolean rateFoodWithAction(String userId, int speiseId, String action) throws IOException {
        String requestParams = "user=" + URLEncoder.encode(userId, "UTF-8") + "&" + "speise=" + URLEncoder.encode("" + speiseId, "UTF-8") + "&" + "action=" + URLEncoder.encode(action, "UTF-8");

        URL url = new URL(urlToServer);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("charset", "UTF-8");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestMethod("POST");

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(requestParams);
        writer.flush();

        // Read Stream //
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String result = "";
        String currentLine = "";
        while ((currentLine = br.readLine()) != null) {
            result += currentLine;
        }
        Log.d("POST", "" + result);

        writer.close();
        br.close();
        return Boolean.parseBoolean(result);
    }
}