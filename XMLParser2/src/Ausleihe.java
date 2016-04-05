import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
public class Ausleihe {

	public String mitarbeiterID;
	public String kennzeichen;
	public Date leihbeginn;
	public Date leihende;

	public Ausleihe(String mID, String kennz) {
		this.mitarbeiterID = mID;
		this.kennzeichen = kennz;
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