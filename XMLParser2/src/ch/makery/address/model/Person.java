package ch.makery.address.model;

import java.io.File;
import java.io.IOException;
import org.jdom2.JDOMException;
import defaultxml.HandleXML;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * 
 */
public class Person {
	private final StringProperty id;
	private final StringProperty vorname;
	private final StringProperty nachname;
	private final StringProperty personalnummer;
	private final StringProperty fuehrerschein;

	/**
	 * Default constructor.
	 */
	public Person() {
		this(null, null, null, null, null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String id, String nachname, String vorname, String fuehrerschein, String personalnummer) {

		this.id = new SimpleStringProperty(id);
		this.vorname = new SimpleStringProperty(vorname);
		this.nachname = new SimpleStringProperty(nachname);
		this.personalnummer = new SimpleStringProperty(personalnummer);
		this.fuehrerschein = new SimpleStringProperty(fuehrerschein);

		// SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}

	public Person(String vorname, String nachname, String fuehrerschein, String personalnummer) {
		SimpleStringProperty tempid = null;
		try {
			File file = new File("PersonListe.xml");
			tempid = new SimpleStringProperty(HandleXML.createUniqueID(file));
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.id = tempid;
		this.nachname = new SimpleStringProperty(nachname);
		this.vorname = new SimpleStringProperty(vorname);
		this.personalnummer = new SimpleStringProperty(personalnummer);
		this.fuehrerschein = new SimpleStringProperty(fuehrerschein);

		// SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}
	
	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public StringProperty idProperty() {
		return id;
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

	public String getVorname() {
		return vorname.get();
	}

	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}

	public StringProperty vornameProperty() {
		return vorname;
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
 