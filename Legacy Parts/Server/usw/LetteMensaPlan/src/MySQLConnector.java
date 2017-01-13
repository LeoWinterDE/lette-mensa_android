package de.lette.mensaplan.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Öffnet die Verbindung zur MySQL Datenbank. Enthällt die Konfiguration der Verbindungsdaten.
 * 
 * @author Tommy Schmidt
 *
 */
public class MySQLConnector extends MySQLConnectorData {
	private static Connection connection = null;

	private MySQLConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Verbindung zur JDBC-Datenbank herstellen.
			connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?" + "user=" + dbUser + "&password=" + dbPass);
			Statement s = connection.createStatement();
			s.execute("set names 'utf8';");
		} catch(ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		} catch(SQLException e) {
			System.out.println("Verbindung nicht moglich o.ä.");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	/**
	 * Gibt die Verbindung zur MySQL Datenbank. Die Initialisierung der Verbindung geschieht erst wenn sie benötigt wird.
	 * 
	 * @return gibt die Verbindung zur MySQL Datenbank zurück
	 */
	public static Connection getConnection() {
		if(connection == null) new MySQLConnector();
		return connection;
	}

	/**
	 * Testet die Verbindung der Datenbank
	 */
	public static void dbTest(Connection connection) {
		try {
			PreparedStatement ps = null;
			String sql = "select * from speisen";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt("id"));
			}
		} catch(SQLException ex) {
			System.out.println("Statement could'nt be prepared.");
		}
		System.out.println("Database works perfectly.");
	}

	public static void main(String[] args) {
		dbTest(MySQLConnector.getConnection());
	}
}