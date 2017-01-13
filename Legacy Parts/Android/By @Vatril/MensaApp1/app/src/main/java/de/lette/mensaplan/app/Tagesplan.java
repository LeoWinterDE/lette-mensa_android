package de.lette.mensaplan.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import de.lette.mensaplan.server.SpeiseArt;
import de.lette.mensaplan.server.Zusatzstoff;

/**
 * Diese Klasse representiert im Normalfall einen Tag im Speiseplan und enthällt Informationen zu den Speisen an diesem Tag.<br>
 * Um genau zu seien repräsentiert diese Klasse ein Date-Objekt und dessen Speisen.<br>
 * Das bedeutet, dass ein Tag aus meheren Objekten dieser Klasse bestehen kann.<br><br>
 * 
 * Tagesplan t1 und Tagesplan t2 können also beide Montag repräsentieren.
 * 
 * @author Tommy Schmidt
 */
public class Tagesplan {
	private Date datum;
	private List<Speise> speisen;
	
	public Tagesplan(Date datum) {
		this.datum = datum;
		speisen = new ArrayList<Speise>();
	}
	
	public Tagesplan(Date datum, List<Speise> speisen) {
		this.datum = datum;
		this.speisen = speisen;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public List<Speise> getSpeisen() {
		return speisen;
	}

	public void setSpeisen(List<Speise> speisen) {
		this.speisen = speisen;
	}
	
	public boolean addSpeise(Speise s) {
		return speisen.add(s);
	}
}

class Speise {
	private int id;
	private String name;
	private SpeiseArt art;
	private boolean isDiät;
	private BigDecimal preis;
	private String beachte;
	private int kcal;
	private int eiweiß;
	private int fett;
	private int kohlenhydrate;
	private Set<Zusatzstoff> zusatzstoffe;
	private int likes;
	private int dislikes;

	public Speise(int id, String name, SpeiseArt art, boolean isDiät, BigDecimal preis, String beachte, int kcal, int eiweiß, int fett, int kohlenhydrate,
			Set<Zusatzstoff> zusatzstoffe, int likes, int dislikes) {
		this.id = id;
		this.name = name;
		this.art = art;
		this.isDiät = isDiät;
		this.preis = preis;
		this.beachte = beachte;
		this.kcal = kcal;
		this.eiweiß = eiweiß;
		this.fett = fett;
		this.kohlenhydrate = kohlenhydrate;
		this.zusatzstoffe = zusatzstoffe;
		this.likes = likes;
		this.dislikes = dislikes;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpeiseArt getArt() {
		return art;
	}

	public void setArt(SpeiseArt art) {
		this.art = art;
	}

	public boolean isDiät() {
		return isDiät;
	}

	public void setDiät(boolean isDiät) {
		this.isDiät = isDiät;
	}

	public BigDecimal getPreis() {
		return preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	public String getBeachte() {
		return beachte;
	}

	public void setBeachte(String beachte) {
		this.beachte = beachte;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getEiweiß() {
		return eiweiß;
	}

	public void setEiweiß(int eiweiß) {
		this.eiweiß = eiweiß;
	}

	public int getFett() {
		return fett;
	}

	public void setFett(int fett) {
		this.fett = fett;
	}

	public int getKohlenhydrate() {
		return kohlenhydrate;
	}

	public void setKohlenhydrate(int kohlenhydrate) {
		this.kohlenhydrate = kohlenhydrate;
	}

	public Set<Zusatzstoff> getZusatzstoffe() {
		return zusatzstoffe;
	}

	public void setZusatzstoffe(Set<Zusatzstoff> zusatzstoffe) {
		this.zusatzstoffe = zusatzstoffe;
	}

	public int getLikes() {
		return likes;
	}

	public void addLikes() {
		likes += 1;
	}

	public void remLikes() {
		likes -= 1;
	}
	
	public int getDislikes() {
		return dislikes;
	}

	public void addDislikes() {
		dislikes += 1;
	}

	public void remDislikes() {
		dislikes -= 1;
	}
}