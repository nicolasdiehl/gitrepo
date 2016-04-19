package ch.makery.address.model;


import java.io.File;
import java.io.IOException;

import org.jdom2.JDOMException;

import defaultxml.ConnectXML;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Booking {

    private final StringProperty von;
    private final StringProperty bis;
    private final StringProperty personalnummer;
    private final StringProperty kennzeichen;
    private final StringProperty zweck;
	private final StringProperty id;
    
  

    /**
     * Default constructor.
     */
    public Booking() {
        this(null, null, null, null, null, null);
    }

    public Booking(String id, String von, String bis, String personalnummer, String kennzeichen, String zweck) {
		SimpleStringProperty tempid = null;
		try {
			File file = new File("BookingListe.xml");
			tempid = new SimpleStringProperty(ConnectXML.createUniqueID(file));
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.id = tempid;
    	this.von = new SimpleStringProperty(von);
        this.bis = new SimpleStringProperty(bis);
        this.personalnummer = new SimpleStringProperty(personalnummer);
        this.kennzeichen = new SimpleStringProperty(kennzeichen);
        this.zweck = new SimpleStringProperty(zweck);
       
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

    public String getPersonalnummer() {
        return personalnummer.get();
    }

    public void setPersonalnummer(String personalnummer) {
        this.personalnummer.set(personalnummer);
    }

    public StringProperty personalnummerProperty() {
        return personalnummer;
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
    
    public String getZweck() {
        return zweck.get();
    }

    public void setZweck(String zweck) {
        this.zweck.set(zweck);
    }

    public StringProperty zweckProperty() {
        return zweck;
    }
}