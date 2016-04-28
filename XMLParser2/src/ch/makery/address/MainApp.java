package ch.makery.address;

import java.io.File;
import java.io.FileWriter;

import javafx.event.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
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

import ch.makery.address.model.*;
import ch.makery.address.model.Vehicle;
import ch.makery.address.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import defaultxml.*;

public class MainApp extends Application {
	private ObservableList<Uebersicht> uebersichtData = FXCollections.observableArrayList();
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();
	private ObservableList<Buchen> buchenData = FXCollections.observableArrayList();
	public Buchen booking;

	public MainApp() throws JDOMException, IOException {
		Searchtree searchtree;
		searchtree = HandleXML.xmlZuSearchtree(new File("PersonListe.xml"), false);
		ArrayList<Object> persons = searchtree.zuArrayList();
		for (int i = 0; i < persons.size(); i++) {
			String id = ((Person) persons.get(i)).getId();
			String nachn = ((Person) persons.get(i)).getNachname();
			String vorn = ((Person) persons.get(i)).getVorname();
			String fuesch = ((Person) persons.get(i)).getFuehrerschein();
			String pen = ((Person) persons.get(i)).getPersonalnummer();
			personData.add(new Person(id, nachn, vorn, fuesch, pen));
		}
		ArrayList<Object> vehicles;
		vehicles = HandleXML.xmlZuArrayList(new File("VehicleListe.xml"), false);
		for (int i = 0; i < vehicles.size(); i++) {
			String id = ((Vehicle) vehicles.get(i)).getId();
			String typ = ((Vehicle) vehicles.get(i)).getTyp();
			String geliehen = ((Vehicle) vehicles.get(i)).getGeliehen();
			String kennzeichen = ((Vehicle) vehicles.get(i)).getKennzeichen();
			vehicleData.add(new Vehicle(id, typ, geliehen, kennzeichen));
		}
		ArrayList<Object> buchen;
		buchen = HandleXML.xmlZuArrayList(new File("BuchenListe.xml"), false);
		for (int i = 0; i < buchen.size(); i++) {
			String id = ((Buchen) buchen.get(i)).getId();
			String na = ((Buchen) buchen.get(i)).getNachname();
			String vor = ((Buchen) buchen.get(i)).getVorname();
			String pe = ((Buchen) buchen.get(i)).getPersonalnummer();
			String fu = ((Buchen) buchen.get(i)).getFuehrerschein();
			String ke = ((Buchen) buchen.get(i)).getKennzeichen();
			String ty = ((Buchen) buchen.get(i)).getTyp();
			String zw = ((Buchen) buchen.get(i)).getZweck();
			String von = ((Buchen) buchen.get(i)).getVon();
			String bi = ((Buchen) buchen.get(i)).getBis();
			String da = ((Buchen) buchen.get(i)).getDauer();
			buchenData.add(new Buchen(id, na, vor, pe, fu, ke, ty, zw, von, bi, da));
		}
	}

	public static void buchen(String id, String nachname, String vorname, String personalnummer, String fuehrerschein,
			String kennzeichen, String typ, String zweck, String von, String bis, String dauer) throws JDOMException, IOException {
		Buchen buchen = new Buchen();
		try {
			File inputFile = new File("Buchen.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			// erstellte Elemente entsprechen Attributen in der XML
			Element nbuchen = new Element("Buchen");
			String newID = HandleXML.createUniqueID(new File("Buchen.xml"));
			nbuchen.setAttribute(new Attribute("ID", newID));
			Element avNachname = new Element("Nachname");
			avNachname.setText(nachname);
			Element avVorname = new Element("Vorname");
			avVorname.setText(vorname);
			Element avPersonalnummer = new Element("Personalnummer");
			avPersonalnummer.setText(personalnummer);
			Element avFuehrerschein = new Element("Fuehrerschein");
			avFuehrerschein.setText(fuehrerschein);
			Element avKennzeichen = new Element("Kennzeichen");
			avKennzeichen.setText(kennzeichen);
			Element avTyp = new Element("Typ");
			avTyp.setText(typ);
			Element avZweck = new Element("Zweck");
			avZweck.setText(zweck);
			Element avVon = new Element("Von");
			avVon.setText(von);
			Element avBis = new Element("Bis");
			avBis.setText(bis);
			Element avDauer = new Element("Dauer");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Person> getPersonData() {
		return personData;
	}

	public ObservableList<Vehicle> getVehicleData() {
		return vehicleData;
	}

	public ObservableList<Buchen> getBuchenData() {
		return buchenData;
	}

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws JDOMException, IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		booking = new Buchen();

		initRootLayout();

		showMainOverview();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
				}
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
			JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

			// ***** persondata load from file geht nicht
			// ***** personData.clear();
			// ***** personData.addAll(wrapper.getPersons());

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

	public void savePersonDataToFile(File file) {// file identisch mit vehicle
													// save???
		try {
			JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
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
			JAXBContext context = JAXBContext.newInstance(VehicleListWrapper.class);
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

	public void saveVehicleDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(VehicleListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			VehicleListWrapper wrapper = new VehicleListWrapper();
			wrapper.setVehicles(vehicleData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			setVehicleFilePath(file);
		} catch (Exception e) { // catches ANY exception
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + file.getPath());

			alert.showAndWait();
		}
	}

	public void Buchendialog() throws JDOMException {
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

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Suchendialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Suchen.fxml"));
			AnchorPane suchen = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(suchen);

			// Give the controller access to the main app.
			SuchenController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}