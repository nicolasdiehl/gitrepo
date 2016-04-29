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

//
public class BuchenController {
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
	// Fahrzeug @FXML Columns

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
	private boolean buchenClicked = false;
	private boolean abbrechenClicked = false;

	public BuchenController() {
	}

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		vorNameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
		nachNameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
		personalnummerColumn.setCellValueFactory(cellData -> cellData.getValue().personalnummerProperty());
		fuehrerscheinColumn.setCellValueFactory(cellData -> cellData.getValue().fuehrerscheinProperty());


		// LISTENER
		// HINZUF�GEN////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
		personBookingTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
		// Listen for selection changes and show the person details when
		// changed.
		

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////
		// BuchenTable bef�llen
		////////////////////////////////////////////////////////////////////////

	}

	private void showPersonDetails(Person person) {
		if (person != null) {
			// Person zwischenspeichern
			Person personTemp = person;
			//Labels bef�llen
			vorNameBooking.setText(person.getVorname());
			nachNameBooking.setText(person.getNachname());
			fuehrerscheinBooking.setText(person.getFuehrerschein());
			personalnummerBooking.setText(person.getPersonalnummer());

		} else {
			// Person is null, remove all the text.
			vorNameBooking.setText("");
			nachNameBooking.setText("");
			fuehrerscheinBooking.setText("");
			personalnummerBooking.setText("");
		}
	}

	private void showVehicleDetails(Vehicle vehicle) {
		if (vehicle != null) {

			// Fill the labels with info from the person object.
			typBooking.setText(vehicle.getTyp());
			kennzeichenBooking.setText(vehicle.getKennzeichen());

		} else {
			// Person is null, remove all the text.
			zweckBooking.setText("");
			typBooking.setText("");
			kennzeichenBooking.setText("");

		}
	}

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


	@FXML
	private void handleAbbrechen() {
		abbrechenClicked = true;
		mainApp.showMainOverview();
	}

	@FXML
	private void handleVon(){
		
	}
	public void setMainApp(MainApp mainApp) throws JDOMException, IOException {
		this.mainApp = mainApp;

		// Add observable list data to the table
		personBookingTable.setItems(mainApp.getPersonData());
		buchen = new Buchen();
	}
}
