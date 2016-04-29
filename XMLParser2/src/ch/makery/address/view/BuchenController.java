package ch.makery.address.view;

import ch.makery.address.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import ch.makery.address.MainApp;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.model.Buchen;
import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import defaultxml.Verwaltung;
import java.io.IOException;
import java.util.Calendar;
import org.jdom2.JDOMException;
import ch.makery.address.MainApp;

	//Buchen soll es ermöglichen, mit dem Klicken in der Tableview in der gewünschten Spalte und dem 
	//der Eingabe "von", "bis" "zweck" eine Buchen der gewünschten Daten vorzunehmen und in der Mainoverview
	//booking Tabelle übernehmen. Auch ist ein zurück knopf eingebaut, der das Fenster MainOverview wieder aufruft
public class BuchenController {
	// sämtliche für das Buchenfenster relevanten Referenzen
	@FXML
	private TableView<Person> personBookingTable;
	@FXML
	private TableView<Vehicle> vehicleBookingTable;
	@FXML
	private TableView<Person> bookingTable;
	@FXML
	private TableColumn<Person, String> vorNameColumn;
	@FXML
	private TableColumn<Person, String> nachNameColumn;
	@FXML
	private TableColumn<Person, String> personalnummerColumn;
	@FXML
	private TableColumn<Person, String> fuehrerscheinColumn;
	@FXML
	private TextField testBis;
	@FXML
	private TextField testVon;
	@FXML
	private TextField testZweck;
	@FXML
	private Label nachNameBooking;
	@FXML
	private Label vorNameBooking;
	@FXML
	private Label fuehrerscheinBooking;
	@FXML
	private Label personalnummerBooking;
	@FXML
	private Label typBooking;
	@FXML
	private Label zweckBooking;
	@FXML
	private Label vonBooking;
	@FXML
	private Label bisBooking;
	@FXML
	private Label verbleibendBooking;
	@FXML
	private Label kennzeichenBooking;
	@FXML
	private Label dauerBooking;
	
	
	private Buchen buchen;
	private MainApp mainApp;
	
	// Zustände der Button buchen und abbrechen
	private boolean buchenClicked = false;
	private boolean abbrechenClicked = false;

	public BuchenController() {
	}
	
	// Initialisieren der Tableview mit Personendaten
	@FXML
	private void initialize() {
		
		vorNameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
		nachNameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
		personalnummerColumn.setCellValueFactory(cellData -> cellData.getValue().personalnummerProperty());
		fuehrerscheinColumn.setCellValueFactory(cellData -> cellData.getValue().fuehrerscheinProperty());

	}

// Handle Button Klick
	public boolean isBuchenClicked() {
		return buchenClicked;
	}

	public boolean isAbbrechenClicked() {
		return abbrechenClicked();
	}

	private boolean abbrechenClicked() {
		// TODO Auto-generated method stub
		return abbrechenClicked;
	}

	//Vorgang bei dem Klicken des Buchen Buttons
	//Ausgewählte Zeile der Tableview soll übernommen werden und in die MainOverview buchenTable übernommen werden 
	//Mainoverview soll wieder geöffnet werden
	@FXML
	private void handleBuchen() {

		buchen.setNachname(nachNameBooking.getText());
		buchen.setVorname(vorNameBooking.getText());
		buchen.setPersonalnummer(personalnummerBooking.getText());
		buchen.setFuehrerschein(fuehrerscheinBooking.getText());
		buchen.setZweck(zweckBooking.getText());
		buchen.setTyp(typBooking.getText());
		buchen.setKennzeichen(kennzeichenBooking.getText());
		buchen.setVon(vonBooking.getText());
		buchen.setBis(bisBooking.getText());
		Calendar vona=Verwaltung.umrechnenZeit(vonBooking.getText());
		Calendar bisa=Verwaltung.umrechnenZeit(bisBooking.getText());
		buchen.setDauer(Verwaltung.vergleichenZeit(vona, bisa));
		buchenClicked = true;
		// dialogStage.close();
		mainApp.showMainOverview();
	}

	//beim Klicken des Abbrechen Buttons soll man wieder zurück auf MainOverview Window gelangen
	@FXML
	private void handleAbbrechen() {
		abbrechenClicked = true;
		mainApp.showMainOverview();
	}
	//Umrechen des eingegeben Strings im Textfeld "von" in Zeit
	@FXML
	private void handleVon(){
		
	}
	//erlaubt den Zugriff der mainApp auf Buchen um es aufrufen zu können
	public void setMainApp(MainApp mainApp) throws JDOMException, IOException {
		this.mainApp = mainApp;

		// Add observable list data to the table
		personBookingTable.setItems(mainApp.getPersonData());
		buchen = new Buchen();
	}
}
