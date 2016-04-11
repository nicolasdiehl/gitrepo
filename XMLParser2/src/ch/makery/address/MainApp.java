package ch.makery.address;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import ch.makery.address.view.MainOverviewController;
import ch.makery.address.view.PersonEditController;
import ch.makery.address.view.VehicleEditDialogController;
import defaultxml.ConnectXML;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import defaultxml.ConnectXMLPerson;

public class MainApp extends Application {
	 private ObservableList<Person> personData = FXCollections.observableArrayList();
	 private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();

	    /**
	     * Constructor
	     */
	    public MainApp() {
    		try {
    			List<Element> liste;
    			File inputFile = new File("PersonListe.xml");
    			SAXBuilder saxBuilder = new SAXBuilder();
    			Document document = saxBuilder.build(inputFile);
    			Element classElement = document.getRootElement();

    			liste = classElement.getChildren();
    			for (int temp = 0; temp < liste.size(); temp++) {
    				Element person = liste.get(temp);
    				String vorn = person.getChild("Vorname").getText();
    				String nachn = person.getChild("Nachname").getText();
    				String fuesch = person.getChild("Fuehrerschein").getText();
    				if (!fuesch.equals("B")&&!fuesch.equals("C")) fuesch = "nein";
    				String pen = person.getChild("Personalnummer").getText();
    				personData.add(new Person(vorn, nachn, fuesch, pen));
    			}
    		} catch (JDOMException e) {
    			e.printStackTrace();
    		} catch (IOException ioe) {
    			ioe.printStackTrace();
    		}
    		
    		try {
    			List<Element> liste;
    			File inputFile = new File("VehicleListe.xml");
    			SAXBuilder saxBuilder = new SAXBuilder();
    			Document document = saxBuilder.build(inputFile);
    			Element classElement = document.getRootElement();

    			liste = classElement.getChildren();
    			for (int temp = 0; temp < liste.size(); temp++) {
    				Element vehicle = liste.get(temp);
    				String tp = vehicle.getChild("Typ").getText();
    				String zw = vehicle.getChild("Zweck").getText();
    				String gl = vehicle.getChild("Geliehen").getText();
    				if (gl.equals("false")) gl = "nein";
    				else gl = "ja";
    				String ke = vehicle.getChild("Kennzeichen").getText();
    				vehicleData.add(new Vehicle(tp, zw, gl, ke));
    			}
    		} catch (JDOMException e) {
    			e.printStackTrace();
    		} catch (IOException ioe) {
    			ioe.printStackTrace();
    		}
	    }

	    /**
	     * Returns the data as an observable list of Persons. 
	     * @return
	     */
	    public ObservableList<Person> getPersonData() {
	        return personData;
	    }
	    public ObservableList<Vehicle> getVehicleData() {
	        return vehicleData;
	    }
	    
	  
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showMainOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showMainOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(MainApp.class.getResource("view/MainOverview.fxml"));
            AnchorPane mainOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainOverview);

            // Give the controller access to the main app.
            MainOverviewController controller = loader.getController();
          controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showVehicleEditDialog(Vehicle vehicle) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/VehicleEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Vehicle");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            VehicleEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVehicle(vehicle);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}