package ch.makery.address.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

import org.jdom2.JDOMException;

import ch.makery.address.MainApp;
import ch.makery.address.model.Booking;
import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import defaultxml.*;


public class MainOverviewController {
	
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> personTableVorname;
    @FXML
    private TableColumn<Person, String> personTableNachname;
    @FXML
    private TableColumn<Person, String> personTablePersonalnummer;
    @FXML
    private TableColumn<Person, String> personTableFuehrerschein;
    
    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> vehicleTableTyp;
    @FXML
    private TableColumn<Vehicle, String> vehicleTableKennzeichen;
    @FXML
    private TableColumn<Vehicle, String> vehicleTableGeliehen;
    
    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, String> bookingTableVon;
    @FXML
    private TableColumn<Booking, String> bookingTableBis;
    @FXML
    private TableColumn<Booking, String> bookingTablePersonalnummer;
    @FXML
    private TableColumn<Booking, String> bookingTableKennzeichen;
    @FXML
    private TableColumn<Booking, String> bookingTableZweck;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public  MainOverviewController() {
    	
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table
        personTableVorname.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
        personTableNachname.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
        personTablePersonalnummer.setCellValueFactory(cellData -> cellData.getValue().personalnummerProperty());
        personTableFuehrerschein.setCellValueFactory(cellData -> cellData.getValue().fuehrerscheinProperty());
        
        vehicleTableTyp.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        vehicleTableKennzeichen.setCellValueFactory(cellData -> cellData.getValue().kennzeichenProperty());
        vehicleTableGeliehen.setCellValueFactory(cellData -> cellData.getValue().geliehenProperty());
        
        bookingTableVon.setCellValueFactory(cellData -> cellData.getValue().vonProperty());
        bookingTableBis.setCellValueFactory(cellData -> cellData.getValue().bisProperty());
        bookingTablePersonalnummer.setCellValueFactory(cellData -> cellData.getValue().personalnummerProperty());
        bookingTableKennzeichen.setCellValueFactory(cellData -> cellData.getValue().kennzeichenProperty());
        bookingTableZweck.setCellValueFactory(cellData -> cellData.getValue().zweckProperty());        
      
/*
        // Clear person details.
        showPersonDetails(null);
        showVehicleDetails(null);
        showBookingDetails(null);

            // Listen for selection changes and show the person details when changed.
            personTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showPersonDetails(newValue));
            vehicleTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showVehicleDetails(newValue));
            bookingTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showBookingDetails(newValue));
            */
           
        }
    
    /*
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
        	personTableVorname.setText(person.getVorname());
        	personTableNachname.setText(person.getNachname());
        	personTablePersonalnummer.setText(person.getFuehrerschein());
        	personTableFuehrerschein.setText(person.getPersonalnummer());
        } else {
            // Person is null, remove all the text.
        	personTableVorname.setText("");
        	personTableNachname.setText("");
        	personTablePersonalnummer.setText("");
        	personTableFuehrerschein.setText("");
          
        }
    }
    private void showVehicleDetails(Vehicle vehicle) {
        if (vehicle != null) {
        	vehicleTableTyp.setText(vehicle.getTyp());
        	vehicleTableKennzeichen.setText(vehicle.getZweck());
        	vehicleTableGeliehen.setText(vehicle.getKennzeichen());
        } else {
        	vehicleTableTyp.setText("");
        	vehicleTableKennzeichen.setText("");
        	vehicleTableGeliehen.setText("");
         
        }
    }
    private void showBookingDetails(Booking booking) {
        if (booking != null) {
            bookingTableVon.setText(booking.getVon());
            bookingTableBis.setText(booking.getBis());
            bookingTablePersonalnummer.setText(booking.getPersonalnummer());
            bookingTableKennzeichen.setText(booking.getKennzeichen());
        } else {
        	bookingTableVon.setText("");
        	bookingTableBis.setText("");
        	bookingTablePersonalnummer.setText("");
        	bookingTableKennzeichen.setText("");
         
        }
    }
    */

    @FXML
    private void handleDeletePerson() {
    	  int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    	    if (selectedIndex >= 0) {
    	        personTable.getItems().remove(selectedIndex);
    	    } else {
    	        // Nothing selected.
            	showNotSelectedAlert("Person");
    	    }
    }
    @FXML
    private void handleDeleteVehicle() {
        int selectedIndex = vehicleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            vehicleTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
        	showNotSelectedAlert("Vehicle");
        }
    }

    @FXML
    private void handleDeleteBooking() {
        int selectedIndex = bookingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            bookingTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
        	showNotSelectedAlert("Booking");
        }
    }

    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEdit(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
            try {
				ConnectXMLPerson.einfügenPerson(tempPerson);
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    @FXML
    private void handleNewVehicle() {
        Vehicle tempVehicle = new Vehicle();
        boolean okClicked = mainApp.showVehicleEdit(tempVehicle);
        if (okClicked) {
            mainApp.getVehicleData().add(tempVehicle);
            try {
				ConnectXMLVehicle.einfügenVehicle(tempVehicle);
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    @FXML
    private void handleNewBooking() {
        Booking tempBooking = new Booking();
        boolean okClicked = mainApp.showBookingEdit(tempBooking);
        if (okClicked) {
            mainApp.getBookingData().add(tempBooking);
            try {
				ConnectXMLBooking.einfügenBooking(tempBooking);
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
  
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEdit(selectedPerson);
            if (okClicked) {
                //showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
        	showNotSelectedAlert("Person");
        }
    }
    @FXML
    private void handleEditVehicle() {
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            boolean okClicked = mainApp.showVehicleEdit(selectedVehicle);
            if (okClicked) {
                //showVehicleDetails(selectedVehicle);
            }

        } else {
            // Nothing selected.
        	showNotSelectedAlert("Vehicle");
        }
    }
    @FXML
    private void handleEditBooking() {
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            boolean okClicked = mainApp.showBookingEdit(selectedBooking);
            if (okClicked) {
                //showBookingDetails(selectedBooking);
            }

        } else {
            // Nothing selected.
            showNotSelectedAlert("Booking");
        }
    }

    @FXML
    private void handleXmlPersonLoad() {
    	// **einfügen xml load code
    }
    @FXML
    private void handleXmlVehicleLoad() {
    	// **einfügen xml load code
    }
    @FXML
    private void handleXmlBookingLoad() {
    	// **einfügen xml load code
    }
    
    @FXML
    private void handleXmlPersonSave() {
    	// **einfügen xml load code
    }
    @FXML
    private void handleXmlVehicleSave() {
    	// **einfügen xml load code
    }
    @FXML
    private void handleXmlBookingSave() {
    	// **einfügen xml load code
    }

    private void showNotSelectedAlert(String type) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("Nothing Selected");
        alert.setContentText("Please select a "+type+" in the table.");
        alert.showAndWait();
    }
    
    @FXML
    private void handleBuchenButton(ActionEvent event)throws IOException{
    new Buchen(); // für die Hilfsklasse
    }
   

    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
        vehicleTable.setItems(mainApp.getVehicleData());
        bookingTable.setItems(mainApp.getBookingData());
   
    }
    
    
}