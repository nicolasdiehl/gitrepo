public class Mitarbeiter {

	public String nachname;
	public String vorname;
	public String fuehrerschein;
	public long persNr;

	// konstruktor
	public Mitarbeiter(String nachname, String vorname, String fuehrerschein, long persNr) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.fuehrerschein = fuehrerschein;
		this.persNr = persNr;
	}
	// getter und setter
	public long getPersNr() {
		return persNr;
	}

	public void setPersNr(long persNr) {
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