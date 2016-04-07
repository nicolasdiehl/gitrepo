public class Mitarbeiter {

	public String nachname;
	public String vorname;
	public String fuehrerschein;
	public String persNr;

	// konstruktor
	public Mitarbeiter(String nachname, String vorname, String fuehrerschein, String persNr) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.fuehrerschein = fuehrerschein;
		this.persNr = persNr;
	}
	// getter und setter
	public String getPersNr() {
		return persNr;
	}

	public void setPersNr(String persNr) {
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

	public String getFuehrerschein() {
		return fuehrerschein;
	}

	public void setFuehrerschein(String fuehrerschein) {
		this.fuehrerschein = fuehrerschein;
	}

}