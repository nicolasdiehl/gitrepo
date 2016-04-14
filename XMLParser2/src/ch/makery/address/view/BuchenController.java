package ch.makery.address.view;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.model.Buchen;
import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import ch.makery.address.MainApp;
import ch.makery.address.model.Buchen;
//
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
	 // die buch Tabelle///////////////////////////////////////////////////////////////////////////////////////
	 //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 @FXML
	 private TableView<Person> bookingTable;
	 @FXML
	 private TableColumn<Person,String>vorNameBookingColumn;
	 @FXML
	 private TableColumn<Person,String>nachNameBookingColumn;
	 @FXML
	 private TableColumn<Person,String>personalnummerBookingColumn;
	 @FXML
	 private TableColumn<Person,String>fuehrerscheinBookingColumn;
	 //Fahrzeug @FXML Columns
	 @FXML
	 private TableColumn<Vehicle,String>zweckBookingColumn;
	 @FXML
	 private TableColumn<Vehicle,String>kennzeichenBookingColumn;
	 @FXML
	 private TableColumn<Vehicle,String>typBookingColumn;
	 @FXML
	 private TableColumn<Vehicle,String>vonBookingColumn;
	 @FXML
	 private TableColumn<Vehicle,String>bisBookingColumn;
	 @FXML
	 private DatePicker vonDatePicker;
	 @FXML
	 private DatePicker bisDatePicker;
	 @FXML
	 private Label idBooking;
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
	 
	 private Buchen buchen;
	 private MainApp mainApp;
	 private boolean buchenClicked=false;
	 
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


	        //LISTENER HINZUFÜGEN////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
	        personBookingTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showPersonDetails(newValue));
            // Listen for selection changes and show the person details when changed.
            vehicleBookingTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showVehicleDetails(newValue));
	        
	        
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            //BuchenTable befüllen
            ////////////////////////////////////////////////////////////////////////
      
            
	        }
	    private void showPersonDetails(Person person) {
	        if (person != null) {
	            // Fill the labels with info from the person object.
	            vorNameBooking.setText(person.getVorname());
	            nachNameBooking.setText(person.getNachname());
	            fuehrerscheinBooking.setText(person.getFuehrerschein());
	            personalnummerBooking.setText(person.getPersonalnummer());
	            

	            // TODO: We need a way to convert the birthday into a String! 
	            // birthdayLabel.setText(...);
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
	            zweckBooking.setText(vehicle.getZweck());
	            typBooking.setText(vehicle.getTyp());
	            kennzeichenBooking.setText(vehicle.getKennzeichen());
	            
	            

	            // TODO: We need a way to convert the birthday into a String! 
	            // birthdayLabel.setText(...);
	        } else {
	            // Person is null, remove all the text.
	        	  zweckBooking.setText("");
		            typBooking.setText("");
		            kennzeichenBooking.setText("");
	          
	        }
	    }
	    //WICHTIG!!!!!!!!!!!!!! KLASSE BUCHEN ERSTELLEN WIE MIT PERSON UND VEHICLE
	    
	/*    @FXML
	    private void handleBuchen() {
	        Buchen tempBuchen = new Buchen();
	        boolean okClicked = mainApp.showBuchenDialog(tempBuchen);
	        if (okClicked) {
	            mainApp.getBuchenData().add(tempBuchen);
	        }
	    }
	    */
	    // wenn man buchen drückt, sollte das in der Übersicht Tableview eingefügt werden
	    public void setPerson(Buchen buchen) {
	        this.buchen = buchen;

	        vorNameBooking.setText(buchen.getVorname());
	        nachNameBooking.setText(buchen.getNachname());
	        personalnummerBooking.setText(buchen.getPersonalnummer());
	        fuehrerscheinBooking.setText(buchen.getFuehrerschein());
	        idBooking.setText(buchen.getID());
	        zweckBooking.setText(buchen.getZweck());
	        typBooking.setText(buchen.getTyp());
	        kennzeichenBooking.setText(buchen.getKennzeichen());
	        vonBooking.setText(buchen.getVon());
	        bisBooking.setText(buchen.getBis());
	        verbleibendBooking.setText(buchen.getDauer());
	        
	        
	    
	    }
	    @FXML
	    private void handleBuchen() {
	       
	            buchen.setNachname(vorNameBooking.getText());
	            buchen.setVorname(nachNameBooking.getText());
	            buchen.setPersonalnummer(personalnummerBooking.getText());
	            buchen.setFuehrerschein(fuehrerscheinBooking.getText());
	            buchen.setID(idBooking.getText());
	            buchen.setZweck(zweckBooking.getText());
	            buchen.setTyp(typBooking.getText());
	            buchen.setKennzeichen(kennzeichenBooking.getText());
	            buchen.setVon(vonBooking.getText());
	            buchen.setBis(bisBooking.getText());
	            buchen.setDauer(verbleibendBooking.getText());
	          

	            buchenClicked = true;
	          //  dialogStage.close();
	        
	    }
	   
	    
	    
	
	 public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // Add observable list data to the table
	        personBookingTable.setItems(mainApp.getPersonData());
	     vehicleBookingTable.setItems(mainApp.getVehicleData());
	    
	    }
}
