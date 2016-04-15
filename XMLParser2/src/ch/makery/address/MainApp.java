package ch.makery.address;


import java.io.File;
import java.io.FileWriter;

import javafx.event.EventHandler ;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import ch.makery.address.model.Buchen;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.model.Vehicle;
import ch.makery.address.model.VehicleListWrapper;
import ch.makery.address.view.*;
import ch.makery.address.model.Uebersicht;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException; 
import defaultxml.*;


// testo
public class MainApp extends Application {
	private ObservableList<Uebersicht> uebersichtData = FXCollections.observableArrayList();
	 private ObservableList<Person> personData = FXCollections.observableArrayList();
	 private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();
	 private ObservableList<Buchen>buchenData = FXCollections.observableArrayList();
		public Buchen booking;
		
		
	   
		
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
	    
	    // versuch Buchen XML////////////////////////////////////////////////////////////////////////////////////////////////////
	   /* try {
			List<Element> liste;
			File inputFile = new File("Buchen.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			Element classElement = document.getRootElement();

			liste = classElement.getChildren();
			for (int temp = 0; temp < liste.size(); temp++) {
				Element buchen = liste.get(temp);
				String tp = buchen.getChild("Typ").getText();
				String zw = buchen.getChild("Zweck").getText();
				String id = buchen.getChild("ID").getText();
				String vorn = buchen.getChild("Vorname").getText();
				String nachn = buchen.getChild("Nachname").getText();
				String fuesch =buchen.getChild("Fuehrerschein").getText();
				String von = buchen.getChild("Von").getText();
				String bis=buchen.getChild("Bis").getText();
				String dauer=buchen.getChild("Dauer").getText();
				String kennzeichen=buchen.getChild("Kennzeichen").getText();
				
				String ke = buchen.getChild("Kennzeichen").getText();
				buchenData.add(new Buchen(tp, zw, id, ke, vorn, nachn, fuesch, von, bis, dauer,kennzeichen));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
    }
	*/
    		

	    public static void buchen(String id,String nachname, String vorname, String personalnummer, String fuehrerschein,
	    		String kennzeichen, String typ, String zweck, String von,String bis, String dauer)throws JDOMException{
	    	Buchen buchen=new Buchen();
	    	try {
		         File inputFile = new File("Buchen.xml");
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);
		         // erstellte Elemente entsprechen Attributen in der XML
		         Element nbuchen = new Element ("Buchen");
		         String newID = ConnectXML.createUniqueID("Buchen.xml");
		         nbuchen.setAttribute(new Attribute ("ID", id));
		         Element avNachname = new Element ("Nachname");
		         avNachname.setText(nachname);
		         Element avVorname = new Element ("Vorname");
		         avVorname.setText(vorname);
		         Element avPersonalnummer = new Element ("Personalnummer");
		         avPersonalnummer.setText(personalnummer);
		         Element avFuehrerschein = new Element ("Fuehrerschein");
		         avFuehrerschein.setText(fuehrerschein);
		         Element avKennzeichen = new Element ("Kennzeichen");
		         avKennzeichen.setText(kennzeichen);
		         Element avTyp = new Element ("Typ");
		         avTyp.setText(typ);
		         Element avZweck = new Element ("Zweck");
		         avZweck.setText(zweck);
		         Element avVon = new Element ("Von");
		         avVon.setText(von);
		         Element avBis = new Element ("Bis");
		         avBis.setText(bis);
		         Element avDauer = new Element ("Dauer");
		         avDauer.setText(dauer);
		         
		         
		         
		         
		         nbuchen.addContent(avNachname);
		        nbuchen.addContent(avVorname);
		         nbuchen.addContent(avPersonalnummer);
		         nbuchen.addContent(avFuehrerschein);
		         nbuchen.addContent(avKennzeichen);
		         nbuchen.addContent(avTyp);
		         nbuchen.addContent(avZweck);
		         nbuchen.addContent(avVon);
		         nbuchen.addContent(avBis);
		         nbuchen.addContent(avDauer);
		         
		         document.getRootElement().addContent(nbuchen);
		         XMLOutputter xmlOutput = new XMLOutputter();

		         xmlOutput.setFormat(Format.getPrettyFormat());
		         xmlOutput.output(document, new FileWriter(inputFile)); 
		      }catch(IOException e){
		         e.printStackTrace();
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
	    public ObservableList<Buchen> getBuchenData(){
	    	return buchenData;
	    }
	      
	  
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        booking = new Buchen();// meiselbach way!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        initRootLayout();

        showMainOverview();
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            
            @Override
            public void handle(WindowEvent event){}
            }); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
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
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //hier versuch BuchenController!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
   
 
    	
   
    
    
    
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
    
    //Speichern, Laden //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
//VEHICLE SPEICHERN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public File getVehicleFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    public void setVehicleFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }
    public void loadVehicleDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(VehicleListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            VehicleListWrapper wrapper = (VehicleListWrapper) um.unmarshal(file);

           vehicleData.clear();
            vehicleData.addAll(wrapper.getVehicles());

            // Save the file path to the registry.
            setVehicleFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     */
    public void saveVehicleDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(VehicleListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            VehicleListWrapper wrapper = new VehicleListWrapper();
            wrapper.setVehicles(vehicleData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
//Erstellen eines neuen Fensters: BuchenFenster
	public void Buchendialog() {
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Buchen.fxml"));
            AnchorPane buchen = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(buchen);

            // Give the controller access to the main app.
            BuchenController controller = loader.getController();
          controller.setMainApp(this);
          controller.setBuchen(booking);// meiselbachway!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         
		} catch (IOException e) {
            e.printStackTrace();
        }
		
	}	

}