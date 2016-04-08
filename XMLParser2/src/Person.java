public class Person {

	public String nachname;
	public String vorname;
	public String fuehrerschein;
	public String personalnummer;

	// konstruktor
	public Person(String vorname, String nachname, String fuehrerschein, String personalnummer) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.fuehrerschein = fuehrerschein;
		this.personalnummer = personalnummer;
	}
	// getter und setter
	public String getPersNr() {
		return personalnummer;
	}

	public void setPersNr(String personalnummer) {
		this.personalnummer = personalnummer;
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