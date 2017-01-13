package de.lette.mensaplan.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;
//import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Behandelt jeden Request aus der Android App und liefert die angeforderten Daten im JSON-Format. Request Parameter: startDate - gibt an ab welchem
 * Datum die termine ausgewählt werden sollen. Im Format YYYY-MM-DD. daysToSelect - gibt an wie viele Tage ab dem Startdatum ausgewählt werden sollen.
 * Wenn startDate=2000-01-01 ist und daysToSelect=0 ist so wird nur der startTag ausgewählt also der 1. Jan. 2000. Wenn startDate=2000-01-01 ist und
 * daysToSelect=1 ist so wird der startTag und der folgende Tag ausgewählt also 1. Jan. 2000 und 2. Jan. 2000.
 * 
 * @author Tommy Schmidt
 * 
 */
@WebServlet("/")
public class AppRequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private Logger logger = Logger.getLogger("" + getClass());

	/**
	 * Request Parameter: startDate - gibt an ab welchem Datum die termine ausgewählt werden sollen. Im Format YYYY-MM-DD. daysToSelect - gibt an wie
	 * viele Tage ab dem Startdatum ausgewählt werden sollen. Wenn startDate=2000-01-01 ist und daysToSelect=0 ist so wird nur der startTag ausgewählt
	 * also der 1. Jan. 2000. Wenn startDate=2000-01-01 ist und daysToSelect=1 ist so wird der startTag und der folgende Tag ausgewählt also 1. Jan.
	 * 2000 und 2. Jan. 2000.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//logger.info("Request from: " + request.getRemoteAddr());
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		// Auffangen der Request Parameter
		String startDate = null;
		Integer daysToSelect = null;
		for(Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			if(entry.getKey().equalsIgnoreCase("startdate")) {
				startDate = entry.getValue()[0];
			} else if(entry.getKey().equalsIgnoreCase("daystoselect")) {
				try {
					daysToSelect = Integer.parseInt(entry.getValue()[0]);
				} catch(NumberFormatException ex) {
					//logger.warning("daysToSelect was no Integer.");
				}
			}
		}

		try {
			// Verbindungsaufbau zur Datenbank via JDBC (siehe de.lette.mensaplan.server.MySQLConnector.java)
			Connection connection = MySQLConnector.getConnection();
			// Zusammenstellung der SQL-Abfrage
			String sql = "select * from termine where datum >=";
			String orderSQL = " order by datum";
			PreparedStatement ps;
			// Unterscheidung der Angeforderten Daten:
			if(Validator.validateDate(startDate) || Validator.validateDateTime(startDate)) {
				// ?startDate ist gesetzt und valide
				sql += " ?";
				if(daysToSelect != null) {
					sql += " and datum <= date_add(?, interval ? day)" + orderSQL;
					//logger.info(sql);
					ps = connection.prepareStatement(sql);
					ps.setString(1, startDate);
					ps.setString(2, startDate);
					ps.setInt(3, daysToSelect);
					//logger.info("startDate is set: " + startDate);
					//logger.info("daysToSelect is set: " + daysToSelect);
				} else {
					sql += orderSQL;
					ps = connection.prepareStatement(sql);
					ps.setString(1, startDate);
					//logger.info("startDate is set: " + startDate);
				}
			} else {
				// kein Parameter ist valide oder vorhanden
				sql += " curdate()";
				if(daysToSelect != null) {
					sql += " and datum <= date_add(curdate(), interval ? day)" + orderSQL;
					ps = connection.prepareStatement(sql);
					ps.setInt(1, daysToSelect);
					//logger.info("daysToSelect is set: " + daysToSelect);
				} else {
					sql += orderSQL;
					ps = connection.prepareStatement(sql);
				}
				//logger.info("using curdate()");
			}
			// Ausführung der SQL-Abfrage
			ResultSet terminResult = ps.executeQuery();
			PrintWriter pw = response.getWriter();
			ClientData clientData = new ClientData();

			// Auswertung der Daten aus der Datenbank
			while(terminResult.next()) {
				Termin termin = new Termin();
				termin.setId(terminResult.getInt("id"));
				termin.setPreis(terminResult.getBigDecimal("preis"));
				termin.setDatum(terminResult.getDate("datum"));
				termin.setDiät(terminResult.getBoolean("isDiaet"));
				termin.setSpeiseId(terminResult.getInt("speise"));

				clientData.getTermine().add(termin);

				sql = "select * from speisen where id = ?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, termin.getSpeiseId());
				ResultSet speiseResult = ps.executeQuery();
				speiseResult.next();

				// Erstellen der Speise //
				Speise speise = new Speise();
				speise.setId(speiseResult.getInt("id"));
				speise.setName(speiseResult.getString("name"));
				for(SpeiseArt st : SpeiseArt.values()) {
					if(st.getTyp().equals(speiseResult.getString("art"))) {
						speise.setArt(st);
						break;
					}
				}
				speise.setBeachte(speiseResult.getString("beachte"));
				speise.setKcal(speiseResult.getInt("kcal"));
				speise.setEiweiß(speiseResult.getInt("eiweisse"));
				speise.setFett(speiseResult.getInt("fette"));
				speise.setKohlenhydrate(speiseResult.getInt("kohlenhydrate"));

				// Likes für die Speise
				sql = "select count(*) as likes from bewertungen where speiseId = ? and action = 'like'";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, speise.getId());
				ResultSet likeResult = ps.executeQuery();
				while(likeResult.next()) {
					speise.setLikes(likeResult.getInt("likes"));
				}

				// Dislikes für die Speise
				sql = "select count(*) as dislikes from bewertungen where speiseId = ? and action = 'dislike'";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, speise.getId());
				ResultSet dislikeResult = ps.executeQuery();
				while(dislikeResult.next()) {
					speise.setDislikes(dislikeResult.getInt("dislikes"));
				}

				// Zusatzstoffe zu der Speise suchen und zum Speise Objekt hinzufügen
				sql = "select zusatzstoffe.id, zusatzstoffe.nummer, zusatzstoffe.name from zusatzstoffe join zusatzstoffelink on zusatzstoffe.id = zusatzstoffelink.zusatzstoff where zusatzstoffelink.speise = ? order by zusatzstoffe.nummer";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, speise.getId());
				ResultSet zusatzstoffeResult = ps.executeQuery();
				while(zusatzstoffeResult.next()) {
					speise.getZusatzstoffe().add(zusatzstoffeResult.getInt("nummer"));
					Zusatzstoff zusatzstoff = new Zusatzstoff(zusatzstoffeResult.getInt("id"), zusatzstoffeResult.getInt("nummer"), zusatzstoffeResult.getString("name"));
					clientData.getZusatzstoffe().add(zusatzstoff);
				}

				clientData.getSpeisen().add(speise);
			}
			// Antwort auf den Request in JSON-Format
			pw.write(clientData.serialize());
		} catch(Exception ex) {
			//logger.info("Verbindung zur Datenbank fehlgeschlagen oder SQLException! Beende die Bearbeitung des Request.");
			//logger.severe(ex.getMessage());
			return;
		}

	}

	/**
	 * Bewertet eine Speise mit Like oder Dislike oder entfernt eine vorhandene Bewertung, von einem Benutzer.<br>
	 * Request Parameter:<br>
	 * speise (int) - die SpeiseId der Speise, die bewertet werden soll.<br>
	 * userId (String) - die Id vom Benutzer, der die Speise bewerten möchte.<br>
	 * action (Stringrepresentation einer Action) - die Aktion (Like/Dislike/Reset).
	 * 
	 * TODO: Speise to Termin (speiseId)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = null;
		Integer speiseId = null;
		String userId = null;
		for(Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			//logger.info("" + entry.getKey());
			if(entry.getKey().equalsIgnoreCase("speise")) {
				try {
					speiseId = Integer.parseInt(entry.getValue()[0]);
				} catch(NumberFormatException e) {
					//logger.warning("speise was no Integer.");
				}
			} else if(entry.getKey().equalsIgnoreCase("action")) {
				action = Action.fromString(entry.getValue()[0]);
				//if(action == null) logger.warning("action was no valid Action.");
			} else if(entry.getKey().equalsIgnoreCase("user")) {
				userId = entry.getValue()[0];
			}
		}
		//logger.info("" + speiseId);
		//logger.info("" + userId);
		//logger.info("" + action);
		if(speiseId != null && userId != null && action != null) {
			// Verbindungsaufbau zur Datenbank via JDBC (siehe de.lette.mensaplan.server.MySQLConnector.java)
			Connection connection = MySQLConnector.getConnection();
			PreparedStatement ps = null;
			try {
				String sql = "insert into bewertungen (speiseId, userId, action) values (?, ?, ?)";
				if(action == Action.LIKE) {
					ps = connection.prepareStatement(sql);
					ps.setInt(1, speiseId);
					ps.setString(2, userId);
					ps.setString(3, action.toString());
				} else if(action == Action.DISLIKE) {
					ps = connection.prepareStatement(sql);
					ps.setInt(1, speiseId);
					ps.setString(2, userId);
					ps.setString(3, action.toString());
				} else if(action == Action.RESET) {
					sql = "delete from bewertungen where speiseId=? and userId=?";
					ps = connection.prepareStatement(sql);
					ps.setInt(1, speiseId);
					ps.setString(2, userId);
				}
				ps.execute();
				//logger.info("Abfrage erfolgreich!");
				response.getWriter().write("true");
			} catch(SQLException ex) {
				//logger.info("Verbindung zur Datenbank fehlgeschlagen oder SQLException! Beende die Bearbeitung des Request.");
				//logger.severe(ex.getMessage());
				//logger.info("" + false);
				response.getWriter().write("false");
			}
		} else {
			//logger.info("" + false);
			response.getWriter().write("false");
		}
	}
}