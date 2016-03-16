public class Ausleihe {
	public String getMitarbeiterID() {
		return MitarbeiterID;
	}

	public void setMitarbeiterID(String mitarbeiterID) {
		MitarbeiterID = mitarbeiterID;
	}

	public String getFahrzeugID() {
		return FahrzeugID;
	}

	public void setFahrzeugID(String fahrzeugID) {
		FahrzeugID = fahrzeugID;
	}

	public String MitarbeiterID;
	public String FahrzeugID;
	
	public Ausleihe(String mID, String fID){
		this.MitarbeiterID=mID;
		this.FahrzeugID=fID;
	}

}