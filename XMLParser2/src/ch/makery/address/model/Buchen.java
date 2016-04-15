package ch.makery.address.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Buchen {

	private final StringProperty vorname;
	private final StringProperty nachname;
	private final StringProperty personalnummer;
	private final StringProperty fuehrerschein;
	private final StringProperty ID;
	private final StringProperty kennzeichen;
	private final StringProperty typ;
	private final StringProperty zweck;
	private final StringProperty von;
	private final StringProperty bis;
	private final StringProperty dauer;

	/**
	 * Default constructor.
	 */
	public Buchen() {
		this(null, null, null, null, null, null, null, null, null, null, null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Buchen(String ID, String nachname, String vorname, String personalnummer, String fuehrerschein,
			String kennzeichen, String typ, String zweck, String von, String bis, String dauer) {
		this.vorname = new SimpleStringProperty(vorname);
		this.nachname = new SimpleStringProperty(nachname);
		this.personalnummer = new SimpleStringProperty(personalnummer);
		this.fuehrerschein = new SimpleStringProperty(fuehrerschein);
		this.ID = new SimpleStringProperty(ID);
		this.kennzeichen = new SimpleStringProperty(kennzeichen);
		this.typ = new SimpleStringProperty(typ);
		this.zweck = new SimpleStringProperty(zweck);
		this.von = new SimpleStringProperty(von);
		this.bis = new SimpleStringProperty(bis);
		this.dauer = new SimpleStringProperty(dauer);
	}

	public String getID() {
		return ID.get();
	}

	public void setID(String ID) {
		this.ID.set(ID);
	}

	public StringProperty IDProperty() {
		return ID;
	}
	public String getDauer() {
		return dauer.get();
	}

	public void setDauer(String dauer) {
		this.dauer.set(dauer);
	}

	public StringProperty dauerProperty() {
		return dauer;
	}

	public String getKennzeichen() {
		return kennzeichen.get();
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen.set(kennzeichen);
	}

	public StringProperty kennzeichenProperty() {
		return kennzeichen;
	}

	public String getTyp() {
		return typ.get();
	}

	public void setTyp(String typ) {
		this.typ.set(typ);
	}

	public StringProperty typProperty() {
		return typ;
	}

	public String getZweck() {
		return zweck.get();
	}

	public void setZweck(String zweck) {
		this.zweck.set(zweck);
	}

	public StringProperty zweckProperty() {
		return zweck;
	}

	public String getVon() {
		return von.get();
	}

	public void setVon(String von) {
		this.von.set(von);
	}

	public StringProperty vonProperty() {
		return von;
	}

	public String getBis() {
		return bis.get();
	}

	public void setBis(String bis) {
		this.bis.set(bis);
	}

	public StringProperty bisProperty() {
		return bis;
	}

	public String getVorname() {
		return vorname.get();
	}

	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}

	public StringProperty vornameProperty() {
		return vorname;
	}

	public String getNachname() {
		return nachname.get();
	}

	public void setNachname(String name) {
		this.nachname.set(name);
	}

	public StringProperty nachnameProperty() {
		return nachname;
	}

	public String getPersonalnummer() {
		return personalnummer.get();
	}

	public void setPersonalnummer(String personalnummer) {
		this.personalnummer.set(personalnummer);
	}

	public StringProperty personalnummerProperty() {
		return personalnummer;
	}

	public String getFuehrerschein() {
		return fuehrerschein.get();
	}

	public void setFuehrerschein(String fuehrerschein) {
		this.fuehrerschein.set(fuehrerschein);
	}

	public StringProperty fuehrerscheinProperty() {
		return fuehrerschein;
	}

}
