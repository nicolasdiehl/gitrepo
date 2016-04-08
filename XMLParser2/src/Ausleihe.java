import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class Ausleihe {
	// Attribute
	public String kennzeichen;
	public String personID;
	public String leihzweck;
	public String leihbeginn;
	public String leihende;

	// Konstruktor
	public Ausleihe(String kennz, String mID, String leihzweck, String leihbeginn, String leihende) {
		this.kennzeichen = kennz;
		this.personID = mID;
		this.leihzweck = leihzweck;
		this.leihbeginn = leihbeginn;
		this.leihende = leihende;
	}

	// Getter und Setter
	public String getLeihzweck() {
		return leihzweck;
	}

	public void setLeihzweck(String leihzweck) {
		this.leihzweck = leihzweck;
	}

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

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public String getKennzeichen() {
		return kennzeichen;
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

}