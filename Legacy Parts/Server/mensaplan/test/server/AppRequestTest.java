package de.lette.mensaplan.test.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import com.google.gson.Gson;

import de.lette.mensaplan.server.ClientData;
import de.lette.mensaplan.server.Speise;
import de.lette.mensaplan.server.Termin;
import de.lette.mensaplan.server.Zusatzstoff;

/**
 * Testet die Funktionalität des AppRequestHandler-Servlets.
 * 
 * @author Tommy Schmidt
 *
 */
public class AppRequestTest {
	public AppRequestTest() throws IOException {
		//URL url = new URL("http://localhost:8080/MensaPlan/AppRequestHandler?startDate=2015-01-13&daysToSelect=1");
		//URL url = new URL("http://192.168.50.30:8080/MensaPlanOhneLogger/AppRequestHandler?startDate=2015-01-01");
		URL url = new URL("http://lebensart.lette-verein.de:8080/MensaPlan/AppRequestHandler?startDate=2015-01-01");
		
		BufferedInputStream bis = new BufferedInputStream(url.openConnection().getInputStream());
		int charInt = -1;
		String result = "";
		while((charInt = bis.read()) != -1) {
			result += (char) charInt;
		}
		Gson gson = new Gson();
		ClientData cd = gson.fromJson(result, ClientData.class);
		for(Termin t : cd.getTermine()) {
			System.out.println("Termin: " + t.getDatum());
		}
		System.out.println("");
		for(Speise s : cd.getSpeisen()) {
			System.out.println("Speise: " + s.getName());
			System.out.println("\n");
		}
		System.out.print("Zusatzstoffe:");
		for(Zusatzstoff zusatzstoff : cd.getZusatzstoffe()) {
			System.out.print(" " + zusatzstoff.getName());
		}
		System.out.println("\nTest succeed!");
	}

	public static void main(String[] args) {
		try {
			new AppRequestTest();
		} catch(IOException e) {
			System.out.println("Test failed!");
			e.printStackTrace();
		}
	}
}