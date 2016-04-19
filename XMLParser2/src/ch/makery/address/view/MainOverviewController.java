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
<<<<<<< HEAD

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
=======
	
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
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch

	private boolean suchenClicked = false;
	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public MainOverviewController() {

<<<<<<< HEAD
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
=======
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
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch

<<<<<<< HEAD
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
=======
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
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch

<<<<<<< HEAD
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
=======
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
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch

<<<<<<< HEAD
	private void showVehicleDetails(Vehicle vehicle) {
		if (vehicle != null) {
			// Fill the labels with info from the person object.
			typLabel.setText(vehicle.getTyp());
			zweckLabel.setText(vehicle.getZweck());
			kennzeichenLabel.setText(vehicle.getKennzeichen());
			geliehenLabel.setText(vehicle.getGeliehen());
=======
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
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch

<<<<<<< HEAD
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
=======
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
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch

<<<<<<< HEAD
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
=======
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
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch

<<<<<<< HEAD
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
=======
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
   
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch

			alert.showAndWait();
		}
	}

<<<<<<< HEAD
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

=======
        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
        vehicleTable.setItems(mainApp.getVehicleData());
        bookingTable.setItems(mainApp.getBookingData());
   
    }
    
    
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch
}