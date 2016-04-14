package ch.makery.address.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Vehicle {

    private final StringProperty typ;
    private final StringProperty geliehen;
    private final StringProperty kennzeichen;
  

    /**
     * Default constructor.
     */
    public Vehicle() {
        this(null, null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Vehicle(String typ, String geliehen, String kennzeichen) {
        this.typ = new SimpleStringProperty(typ);
        this.geliehen = new SimpleStringProperty(geliehen);
        this.kennzeichen = new SimpleStringProperty(kennzeichen);
       
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

    public String getGeliehen() {
        return geliehen.get();
    }

    public void setGeliehen(String geliehen) {
        this.geliehen.set(geliehen);
    }

    public StringProperty geliehenProperty() {
        return geliehen;
    }
}