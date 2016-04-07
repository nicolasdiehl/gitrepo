import java.util.Calendar;

public class Mitarbeiter {

	public String nachname;
	public String vorname;
	public boolean fuehrerschein;
	public long persNr;

	public long getPersNr() {
		return persNr;
	}

	public void setPersNr(long persNr) {
		this.persNr = persNr;
	}

	public Mitarbeiter(String nachname, String vorname, boolean fuehrerschein, long persNr) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.fuehrerschein = fuehrerschein;
		this.persNr = persNr;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public boolean isFuehrerschein() {
		return fuehrerschein;
	}

	public void setFuehrerschein(boolean fuehrerschein) {
		this.fuehrerschein = fuehrerschein;
	}

}