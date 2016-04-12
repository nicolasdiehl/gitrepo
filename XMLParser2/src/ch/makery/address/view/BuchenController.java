package ch.makery.address.view;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import ch.makery.address.MainApp;

public class BuchenController {
	 @FXML
	 private TableView<Person> personBookingTable;
	 @FXML
	 private TableView<Vehicle> vehicleBookingTable;
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
	        kennzeichenColumn.setCellValueFactory(cellData -> cellData.getValue().kennzeichenProperty());
	
	        }
	
	 public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // Add observable list data to the table
	        personBookingTable.setItems(mainApp.getPersonData());
	     vehicleBookingTable.setItems(mainApp.getVehicleData());
	   
	    }
}
