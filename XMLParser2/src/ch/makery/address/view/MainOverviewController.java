package ch.makery.address.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;

public class MainOverviewController {

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
	private TableColumn<Vehicle, String> zweckColumn;
	@FXML
	private TableView<Vehicle> statsTable; 
	@FXML
	private TableColumn<Vehicle, String> statsFahrzeug;
	@FXML
	private TableColumn<Vehicle, String> statsTyp;
	@FXML
	private TableColumn<Vehicle, String> statsAnzahl;
	@FXML
	private Label typLabel;
	@FXML
	private Label zweckLabel;
	@FXML
	private Label geliehenLabel;
	@FXML
	private Label kennzeichenLabel;

	@FXML
	private Label vorNameLabel;
	@FXML
	private Label nachNameLabel;
	@FXML
	private Label personalnummerLabel;
	@FXML
	private Label fuehrerscheinLabel;

	private boolean suchenClicked = false;
	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public MainOverviewController() {

	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		vorNameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
		nachNameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
		typColumn.setCellValueFactory(cellData -> cellData.getValue().typProperty());
		zweckColumn.setCellValueFactory(cellData -> cellData.getValue().zweckProperty());

		// Clear person details.
		showPersonDetails(null);

		// Clear person details.
		showVehicleDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
		// Listen for selection changes and show the person details when
		// changed.
		vehicleTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showVehicleDetails(newValue));

	}

	private void showPersonDetails(Person person) {
		if (person != null) {
			// Fill the labels with info from the person object.
			vorNameLabel.setText(person.getVorname());
			nachNameLabel.setText(person.getNachname());
			fuehrerscheinLabel.setText(person.getFuehrerschein());
			personalnummerLabel.setText(person.getPersonalnummer());

			// TODO: We need a way to convert the birthday into a String!
			// birthdayLabel.setText(...);
		} else {
			// Person is null, remove all the text.
			vorNameLabel.setText("");
			nachNameLabel.setText("");
			fuehrerscheinLabel.setText("");
			personalnummerLabel.setText("");

		}
	}

	private void showVehicleDetails(Vehicle vehicle) {
		if (vehicle != null) {
			// Fill the labels with info from the person object.
			typLabel.setText(vehicle.getTyp());
			zweckLabel.setText(vehicle.getZweck());
			kennzeichenLabel.setText(vehicle.getKennzeichen());
			geliehenLabel.setText(vehicle.getGeliehen());

			// TODO: We need a way to convert the birthday into a String!
			// birthdayLabel.setText(...);
		} else {
			// Person is null, remove all the text.
			typLabel.setText("");
			zweckLabel.setText("");
			kennzeichenLabel.setText("");
			geliehenLabel.setText("");

		}
	}

	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person or Vehicle Selected");
			alert.setContentText("Please select a person or vehicle in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleDeleteVehicle() {
		int selectedIndex = vehicleTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			vehicleTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person or Vehicle Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			mainApp.getPersonData().add(tempPerson);
		}
	}

	@FXML
	private void handleNewVehicle() {
		Vehicle tempVehicle = new Vehicle();
		boolean okClicked = mainApp.showVehicleEditDialog(tempVehicle);
		if (okClicked) {
			mainApp.getVehicleData().add(tempVehicle);
		}
	}

	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleEditVehicle() {
		Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
		if (selectedVehicle != null) {
			boolean okClicked = mainApp.showVehicleEditDialog(selectedVehicle);
			if (okClicked) {
				showVehicleDetails(selectedVehicle);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("Nothing Selected");
			alert.setContentText("Please select a vehicle in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleBuchenButton(ActionEvent event) throws IOException {
		mainApp.Buchendialog(); // für die Hilfsklasse
	}

	public boolean isSuchenClicked() {
		return suchenClicked;
	}

	@FXML

	private void handleSuchenButton(ActionEvent event) throws IOException {
		mainApp.Suchendialog(); // für die Hilfsklasse
		suchenClicked = true;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		personTable.setItems(mainApp.getPersonData());
		vehicleTable.setItems(mainApp.getVehicleData());

	}

}