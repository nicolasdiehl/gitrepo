package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Window;
import ch.makery.address.FXMLVerwaltung;
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
    private Label vornameLabel;
    @FXML
    private Label nachnameLabel;
    @FXML
    private Label fuehrerscheinLabel;
    @FXML
    private Label personalnummerLabel;
  


  @FXML
    private Label kennzeichenLabel;
    @FXML
    private Label geliehenLabel;
    @FXML
    private Label typLabel;
    @FXML
    private Label zweckLabel;


    // Reference to the main application.
    private FXMLVerwaltung fxmlVerwaltung;

   
    @FXML
   
   
    
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            vornameLabel.setText(person.getVorname());
            nachnameLabel.setText(person.getNachname());
            personalnummerLabel.setText(person.getPersonalnummer());
            fuehrerscheinLabel.setText(person.getFuehrerschein());
            
            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            nachnameLabel.setText("");
            nachnameLabel.setText("");
            fuehrerscheinLabel.setText("");
            personalnummerLabel.setText("");
        }
    }
    private void showFahrzeugDetails(Vehicle vehicle) {
        if (vehicle!= null) {
            // Fill the labels with info from the person object.
            typLabel.setText(vehicle.typ());
            geliehenLabel.setText(vehicle.getGeliehen());
            kennzeichenLabel.setText(vehicle.getKennzeichen());
            zweckLabel.setText(vehicle.getZweck());
            
            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            typLabel.setText("");
            geliehenLabel.setText("");
            kennzeichenLabel.setText("");
           zweckLabel.setText("");
        }
    }
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        // Initialize the person table with the two columns.
        vorNameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
        nachNameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
        vorNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nachnameProperty());
        nachNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nachnameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
   
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(MainOverviewController.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
	private static Window getPrimaryStage() {
		// TODO Auto-generated method stub
		return null;
	}

}


