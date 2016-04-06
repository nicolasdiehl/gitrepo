import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class Ausleihe {
	// Attribute
	public String kennzeichen;
	public String mitarbeiterID;
	public String leihbeginn;
	public String leihende;
	// Konstruktor
	public Ausleihe(String kennz, String mID, String leihbeginn, String leihende) {
		this.kennzeichen = kennz;
		this.mitarbeiterID = mID;
		this.leihbeginn = leihbeginn;
		this.leihende = leihende;
	}
	// Getter und Setter
	public String getLeihbeginn() {
		return leihbeginn;
	}

	public void setLeihbeginn(String leihbeginn) {
		this.leihbeginn = leihbeginn;
	}

	public String getLeihende() {
		return leihende;
	}

	public void setLeihende(String leihende) {
		this.leihende = leihende;
	}

	public String getMitarbeiterID() {
		return mitarbeiterID;
	}

	public void setMitarbeiterID(String mitarbeiterID) {
		this.mitarbeiterID = mitarbeiterID;
	}

	public String getKennzeichen() {
		return kennzeichen;
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

}