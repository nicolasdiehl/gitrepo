package ch.makery.address.view;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;

import ch.makery.address.MainApp;
import ch.makery.address.model.Buchen;
import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import defaultxml.HandleXML;

//hier wird das Hauptfenster erzeugt, in der man auf verschiedene andere Fenster wie Buchen und Suchen
// zugegriffen werden kann. Hier wird auch eine Tableview von Personen und Fahrzeuge erzeugt. 
// Neue Personen/Fahrzeuge sollen anlegbar, löschbar und editierbar sein.
//Einbau eines Sortierbuttons, welches das Sortieren der PersonTable ermöglicht
public class MainOverviewController {
	// sämtliche für das MainOverview Fenster relevanten Referenzen
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> vorNameColumn;
	@FXML
	private TableColumn<Person, String> nachNameColumn;
	@FXML
	private TableView<Vehicle> vehicleTable;
	@FXML
	private TableColumn<Vehicle, String> typColumn;
	@FXML
	private TableColumn<Vehicle, String> kennzeichenColumn;
	@FXML
	private TableColumn<Vehicle, String> geliehenColumn;
	@FXML
	private TableView<Vehicle> statsTable;
	@FXML
	private TableColumn<Vehicle, String> statsFahrzeug;
	@FXML
	private TableColumn<Vehicle, String> statsTyp;
	@FXML
	private TableColumn<Vehicle, String> statsAnzahl;
	@FXML
	private Label vorNameLabel;
	@FXML
	private Label nachNameLabel;
	@FXML
	private Label personalnummerLabel;
	@FXML
	private Label fuehrerscheinLabel;
	@FXML
	private TableView<Buchen> buchenTable;
	@FXML
	private TableColumn<Buchen, String> BnachnameColumn;
	@FXML
	private TableColumn<Buchen, String> BvornameColumn;
	@FXML
	private TableColumn<Buchen, String> BpersonalnummerColumn;
	@FXML
	private TableColumn<Buchen, String> Bfuehrerschein;
	@FXML
	private TableColumn<Buchen, String> Bkennzeichen;
	@FXML
	private TableColumn<Buchen, String> Btyp;
	@FXML
	private TableColumn<Buchen, String> Bzweck;
	@FXML
	private TableColumn<Buchen, String> Bvon;
	@FXML
	private TableColumn<Buchen, String> Bbis;
	@FXML
	private TableColumn<Buchen, String> Bdauer;

	//Zustände der Button buchen und abbrechen
	private boolean suchenClicked = false;
	// Reference zur Mainapp
	private MainApp mainApp;


	public MainOverviewController() {

	}

	//Initialisieren der Controller Klasse
	@FXML
	private void initialize() {
		// Initialisieren der Tableview (befüllen)
		vorNameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
		nachNameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
		
		
		typColumn.setCellValueFactory(cellData -> cellData.getValue().typProperty());
		kennzeichenColumn.setCellValueFactory(cellData -> cellData.getValue().kennzeichenProperty());
		geliehenColumn.setCellValueFactory(cellData -> cellData.getValue().geliehenProperty());
		
		BnachnameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
		BvornameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
		BpersonalnummerColumn.setCellValueFactory(cellData -> cellData.getValue().personalnummerProperty());
		Bfuehrerschein.setCellValueFactory(cellData -> cellData.getValue().fuehrerscheinProperty());
		Bkennzeichen.setCellValueFactory(cellData -> cellData.getValue().kennzeichenProperty());
		Btyp.setCellValueFactory(cellData -> cellData.getValue().typProperty());
		Bzweck.setCellValueFactory(cellData -> cellData.getValue().zweckProperty());
		Bvon.setCellValueFactory(cellData -> cellData.getValue().vonProperty());
		Bbis.setCellValueFactory(cellData -> cellData.getValue().bisProperty());
		Bdauer.setCellValueFactory(cellData -> cellData.getValue().dauerProperty());

		// Person details leeren
		showPersonDetails(null);

		// Listener bei Auswahl. Gab es veränderungen
		
		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
		// Listen for selection changes and show the person details when
		// changed.
		vehicleTable.getSelectionModel().selectedItemProperty();
		
		buchenTable.getSelectionModel().selectedItemProperty();

	}
	
	//befüllen der Label je nach Click in der Tableview von Personen
	private void showPersonDetails(Person person) {
		if (person != null) {
			// Fill the labels with info from the person object.
			vorNameLabel.setText(person.getVorname());
			nachNameLabel.setText(person.getNachname());
			fuehrerscheinLabel.setText(person.getFuehrerschein());
			personalnummerLabel.setText(person.getPersonalnummer());
		} else {
			System.out.println("******* person is null!");
			// Person is null, remove all the text.
			vorNameLabel.setText("");
			nachNameLabel.setText("");
			fuehrerscheinLabel.setText("");
			personalnummerLabel.setText("");

		}
	}
	//befüllen der Label je nach Click in der Tableview von Vehicle
	@FXML
	private void handleNewVehicle() {
		Vehicle tempVehicle = new Vehicle();
		boolean okClicked = mainApp.showVehicleEditDialog(tempVehicle);
		if (okClicked) {
			mainApp.getVehicleData().add(tempVehicle);
		}
	}
	
	//Löschen von Vehicle in der Tableview
	@FXML
	private void handleDeleteVehicle() {
		int selectedIndex = vehicleTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			vehicleTable.getItems().remove(selectedIndex);
		} else {
			// keine Auswahl erzeugt eine Warnung
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person or Vehicle Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}
	//Editieren von Vehicle in der Tableview
	@FXML
	private void handleEditVehicle() {
		Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
		if (selectedVehicle != null) {
			boolean okClicked = mainApp.showVehicleEditDialog(selectedVehicle);

		} else {
			// keine Auswahl erzeugt eine Warnung
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("Nothing Selected");
			alert.setContentText("Please select a vehicle in the table.");

			alert.showAndWait();
		}
	}
	//Anlegen neuer Personen in der Tableview
	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			ArrayList<Object> temp = HandleXML.xmlZuArrayList(new File("PersonListe.xml"), false);
			temp.add(tempPerson);
			HandleXML.arrayListZuXml(temp, new File("PersonListe.xml"),"");
			MainApp.clearPersonData();
			for (int i=0; i<temp.size();i++){
				Person persond=(Person)temp.get(i);
				MainApp.addToPersonData(persond);
			}
		}
	}

	//Löschen von Personen in der Tableview
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			// keine Auswahl erzeugt eine Warnung
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person or Vehicle Selected");
			alert.setContentText("Please select a person or vehicle in the table.");

			alert.showAndWait();
		}
	}
	//Editieren von Personen in der Tableview
	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

		} else {
			// keine Auswahl erzeugt eine Warnung
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}
	
	//beim Klicken des Buchenbuttons, soll das Buchenfenster geöffnet werden
	@FXML
	private void handleBuchenButton(ActionEvent event) throws IOException, JDOMException {
		mainApp.Buchendialog(); // für die Hilfsklasse
	}
	
	public boolean isSuchenClicked() {
		return suchenClicked;
	}
	//beim Klicken des Suchenbuttons, soll das Suchfenster geöffnet werden
	@FXML
	private void handleSuchenButton(ActionEvent event) throws IOException {
		mainApp.Suchendialog(); // für die Hilfsklasse
		suchenClicked = true;
	}
	
	//befüllen der verschiedenen Tableviews wird hier vorgenommen
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		//Fügt Daten der observable list hinzu
		personTable.setItems(mainApp.getPersonData());
		vehicleTable.setItems(mainApp.getVehicleData());
		buchenTable.setItems(mainApp.getBuchenData());
		

	}

}