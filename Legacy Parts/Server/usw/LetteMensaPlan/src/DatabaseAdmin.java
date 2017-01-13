package de.lette.mensaplan.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseAdmin
 */
// @WebServlet("/DatabaseAdmin")
public class DatabaseAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = null;
		for(Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			if(entry.getKey().equalsIgnoreCase("sql")) {
				sql = entry.getValue()[0];
			}
		}
		PrintWriter pw = response.getWriter();
		pw.write("<p>Result:</p>");
		if(sql != null) try {
			Connection c = MySQLConnector.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();

			pw.write("<table><tr>");
			int numberOfColumns = rsmd.getColumnCount();
			for(int i = 1; i <= numberOfColumns; i++) {
				String name = rsmd.getColumnName(i);
				pw.write("<th>" + name + "</th>");
			}
			pw.write("</tr>");
			while(result.next()) {
				pw.write("<tr>");
				for(int i = 1; i <= numberOfColumns; i++) {
					String columnValue = result.getString(i);
					pw.write("<td>" + columnValue + "</td>");
				}
				pw.write("</tr>");
			}
		} catch(SQLException e) {
			try {
				Connection c = MySQLConnector.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ps.execute();
			} catch(SQLException ex) {
				pw.write(e.getMessage());
				pw.write(ex.getMessage());
			}
		}
	}
}