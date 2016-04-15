
package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Uebersicht {
    private final StringProperty id;
    private final StringProperty nachName;
    private final StringProperty vorName;
    private final StringProperty fuehrerschein;
    private final StringProperty personalnummer;
    private final StringProperty zweck;
    private final StringProperty typ;
    private final StringProperty kennzeichen;
    private final StringProperty von;
    private final StringProperty bis;
    private final StringProperty dauer;
    
    public Uebersicht(){
    	this(null,null,null,null,null,null,null,null,null,null,null);
    }
    public Uebersicht(String id, String nachName, String vorName, String fuehrerschein, String personalnummer, String zweck, String typ,
    		String kennzeichen, String von, String bis, String dauer){
    	this.id=new SimpleStringProperty(id);
    	this.nachName=new SimpleStringProperty(nachName);
    	this.vorName=new SimpleStringProperty(vorName);
    	this.fuehrerschein=new SimpleStringProperty(fuehrerschein);
    	this.personalnummer=new SimpleStringProperty(zweck);
    	this.typ=new SimpleStringProperty(typ);
    	this.kennzeichen=new SimpleStringProperty(kennzeichen);
    	this.von=new SimpleStringProperty(von);
    	this.bis=new SimpleStringProperty(bis);
    	this.dauer=new SimpleStringProperty(dauer);
    	this.zweck=new SimpleStringProperty(zweck);
    }
    
    public String getID(){
    	return id.get();
    }
    public void setID(String id){
    	this.id.set(id);
    }
    public StringProperty idProperty(){
    	return id;
    }
    
    
    public String getNachname(){
    	return nachName.get();
    }
    public void setNachname(String nachName){
    	this.nachName.set(nachName);
    }
    public StringProperty nachNameProperty(){
    	return nachName;
    }
    
    public String getVorname(){
    	return vorName.get();
    }
    public void setVorname(String vorName){
    	this.vorName.set(vorName);
    }
    public StringProperty vorNameProperty(){
    	return vorName;
    }
    
    
    public String getPersonalnummer(){
    	return personalnummer.get();
    }
    public void setPersonalnummer(String personalnummer ){
    	this.personalnummer.set(personalnummer);
    }
    public StringProperty personalnummerProperty(){
    	return personalnummer;
    }
    
    
    
    
    public String getFuehrerschein(){
    	return fuehrerschein.get();
    }
    public void setFuehrerschein(String fuehrerschein){
    	this.fuehrerschein.set(fuehrerschein);
    }
    public StringProperty fuehrerscheinProperty(){
    	return fuehrerschein;
    }
    
    
    
    public String getTyp(){
    	return typ.get();
    }
    public void setTyp(String typ){
    	this.typ.set(typ);
    }
    public StringProperty typProperty(){
    	return typ;
    }
    
    
    public String getZweck(){
    	return zweck.get();
    }
    public void setZweck(String zweck){
    	this.zweck.set(zweck);
    }
    public StringProperty zweckProperty(){
    	return zweck;
    }
    
    
    public String getKennzeichen(){
    	return kennzeichen.get();
    }
    public void setKennzeichen(String kennzeichen){
    	this.kennzeichen.set(kennzeichen);
    }
    public StringProperty kennzeichenProperty(){
    	return kennzeichen;
    }
    
    
    
    public String getVon(){
    	return von.get();
    }
    public void setVon(String von){
    	this.von.set(von);
    }
    public StringProperty von(){
    	return von;
    }
    
    
    public String getBis(){
    	return bis.get();
    }
    public void setBis(String bis){
    	this.bis.set(bis);
    }
    public StringProperty bisProperty(){
    	return bis;
    }
    
    
    public String getDauer(){
    	return dauer.get();
    }
    public void setDauer(String dauer ){
    	this.dauer.set(dauer);
    }
    public StringProperty dauerProperty(){
    	return dauer;
    }
    
}
