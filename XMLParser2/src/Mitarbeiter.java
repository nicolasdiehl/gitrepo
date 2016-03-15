import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Mitarbeiter {

	public String nachname;
	public String vorname;
	public String standort;
	public String fuehrerschein;
	public Calendar geburtstag;

	public Mitarbeiter(String nachname, String vorname, String standort, String fuehrerschein, Calendar geburtstag) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.standort = standort;
		this.fuehrerschein = fuehrerschein;
		this.geburtstag = geburtstag;
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

	public String getStandort() {
		return standort;
	}

	public void setStandort(String standort) {
		this.standort = standort;
	}

	public String getFuehrerschein() {
		return fuehrerschein;
	}

	public void setFuehrerschein(String fuehrerschein) {
		this.fuehrerschein = fuehrerschein;
	}

	public Calendar getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(Calendar geburtstag) {
		this.geburtstag = geburtstag;
	}

}