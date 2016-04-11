package ch.makery.address.view;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import defaultxml.ConnectXMLPerson;
import javafx.stage.Stage;
import javafx.fxml.FXML;
public class BuchenController {
	 @FXML
	    private TableView<Person> personBookingTable;
	 @FXML
	 private TableView<Person> vehicleBookingTable;
	 @FXML
	 private TableColumn<Person,String>vorNameColumn;
	 @FXML
	 private TableColumn<Person,String>nachNameColumn;
	 @FXML
	 private TableColumn<Person,String>personalnummerColumn;
	 @FXML
	 private TableColumn<Person,String>fuehrerscheinColumn;
	 //Fahrzeug @FXML Columns
	 @FXML
	 private TableColumn<Vehicle,String>zweckColumn;
	 @FXML
	 private TableColumn<Vehicle,String>kennzeichenColumn;
	 @FXML
	 private TableColumn<Vehicle,String>typColumn;
	 @FXML
	 private TableColumn<Vehicle,String>geliehenColumn;
	 
	 private MainApp mainApp;
	 
	 public  BuchenController() {	    	
	    }
	 @FXML
	    private void initialize() {
	        // Initialize the person table with the two columns.
	        vorNameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
	        nachNameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
	        personalnummerColumn.setCellValueFactory(cellData -> cellData.getValue().personalnummerProperty());
	        fuehrerscheinColumn.setCellValueFactory(cellData -> cellData.getValue().fuehrerscheinProperty());
	        typColumn.setCellValueFactory(cellData -> cellData.getValue().typProperty());
	        zweckColumn.setCellValueFactory(cellData -> cellData.getValue().zweckProperty());
	        geliehenColumn.setCellValueFactory(cellData -> cellData.getValue().geliehenProperty());
	      kennzeichenColumn.setCellValueFactory(cellData -> cellData.getValue().kennzeichenProperty());

	        // Clear person details.
	     //   showPersonDetails(null);

	            // Clear person details.
	          //  showVehicleDetails(null);

	            // Listen for selection changes and show the person details when changed.
	         /*   personBookingTable.getSelectionModel().selectedItemProperty().addListener(
	                    (observable, oldValue, newValue) -> showPersonDetails(newValue));
	            // Listen for selection changes and show the person details when changed.
	            vehicleBookingTable.getSelectionModel().selectedItemProperty().addListener(
	                    (observable, oldValue, newValue) -> showVehicleDetails(newValue));
	            */
	           
	        }

}
