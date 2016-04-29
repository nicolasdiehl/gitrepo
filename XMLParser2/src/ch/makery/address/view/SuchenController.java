package ch.makery.address.view;

import ch.makery.address.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import ch.makery.address.MainApp;
import defaultxml.HandleArrayList;
import defaultxml.HandleXML;
import defaultxml.Searchtree;
import defaultxml.SuchBaumBSP;
//Es wird ein Suchfenster erstellt, welches es ermöglichtn mit der Hilfe von der Personalnummer
//nach der gewünschten Person zu suchen (linear und binär)
//das Suchen nach einem Fahrzeug mit der Hilfe von dem Kennzeichen soll mit dem drücken auf ein Button ermöglicht werden
public class SuchenController {
	@FXML
	private TableView<Person> personBookingTable;
	@FXML
	private TableView<Vehicle> vehicleBookingTable;
	@FXML
	private TextField searchTableFound;
	@FXML
	private TextField searchTableFound1;
	@FXML
	private TextField searchTablePersNr;
	@FXML
	private TextField searchTableFahrzKZLin;
	@FXML
	private TextField searchTablePersNrLin;
	
	private MainApp mainApp;
	private boolean zurueckClicked = false;

	
	
	public SuchenController() {
	}
	//Vorgang bei dem Klicken auf den Suchen Button mit Hilfe der Personalnummer (binäre Suche)
	@FXML
	private void handleSuchen() { // Person binär suchen
		// SuchBaumBSP suchbaum = new SuchBaumBSP();
		Searchtree searchtree = HandleXML.xmlZuSearchtree(new File("PersonListe.xml"), false);
		String suchzahl = searchTablePersNr.getText();
		// suchbaum.Suche(suchzahl);
		Person person = searchtree.search(suchzahl);
		searchTableFound.setText("Name: " + person.getNachname()+ ", " +person.getVorname()+"    Fuehrerschein: "
		+person.getFuehrerschein()+"    Personalnummer: "+person.getPersonalnummer());
	}
	//Vorgang bei dem Klicken auf den linearen suchen Button mit Hilfe der Personalnummer (lineare Suche)
	@FXML
	private void handleSuchen2() { // Person linear suchen
		String suchzahl = searchTablePersNrLin.getText();
		Searchtree searchtree = HandleXML.xmlZuSearchtree(new File("PersonListe.xml"), false);
		ArrayList<Object> arrayList = searchtree.zuArrayList();
		for (int i=0;i<arrayList.size();i++) {
			if (((Person)arrayList.get(i)).getPersonalnummer().equals(suchzahl)) {
				Person person = (Person)arrayList.get(i);
				searchTableFound.setText("Name: " + person.getNachname()+ ", " +person.getVorname()+"    Fuehrerschein: "
						+person.getFuehrerschein()+"    Personalnummer: "+person.getPersonalnummer());

			}
		}
	}
	//Vorgang bei dem Klicken auf den Suchen Button bei Fahrzeug nach Kennzeichen suchen
	@FXML
	private void handleSuchenKZ() {
		String suchwert = searchTableFahrzKZLin.getText();
		ArrayList<String> suchwertAsAL = new ArrayList<>(Arrays.asList(suchwert));
		ArrayList<Object> arrayList = HandleXML.xmlZuArrayList(new File("VehicleListe.xml"),false);
		ArrayList<Object> gefunden = HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayList, "Kennzeichen", suchwertAsAL);
		Vehicle vehicle = (Vehicle)gefunden.get(gefunden.size()-1);
		searchTableFound1.setText("Typ: " + vehicle.getTyp()+ ", Kennzeichen: " +vehicle.getKennzeichen());
	}
	//beim Klicken auf Zurück soll man zur MainOverview zurück gelangen
	@FXML
	private void handleZurueck() {
		zurueckClicked = true;
		mainApp.showMainOverview();
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}
	//Zustand beim Klicken auf den Zurück button
    public boolean isZurueckClicked(){
    	return zurueckClicked();
    }
    private boolean zurueckClicked() {
		// TODO Auto-generated method stub
		return zurueckClicked;
	}
}