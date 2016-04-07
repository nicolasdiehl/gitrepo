package ch.makery.address.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * 
 */
public class Person {

	private final StringProperty vorname;
	private final StringProperty nachname;
	private final StringProperty personalnummer;
	private final StringProperty fuehrerschein;

	/**
	 * Default constructor.
	 */
	public Person() {
		this(null, null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String vorname, String nachname) {
		this.vorname = new SimpleStringProperty(vorname);
		this.nachname = new SimpleStringProperty(nachname);

		// Some initial dummy data, just for convenient testing.
		this.personalnummer = new SimpleStringProperty("111");
		this.fuehrerschein = new SimpleStringProperty("B");

		// SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
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

	public StringProperty personalnummer() {
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
