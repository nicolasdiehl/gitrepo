package ch.makery.address.view;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import ch.makery.address.MainApp;

public class RootLayoutController {
	boolean saved = false;
	// Reference to the main application
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

		//Vorgang Speichern über File Speichern. Falls noch nicht gespeichert dann Speichern unter
	@FXML
	private void handleSave() {
		// File personFile = mainApp.getPersonFilePath();
		// File vehicleFile = mainApp.getVehicleFilePath();
		File personFile = new File("PersonListe.xml");
		File vehicleFile = new File("VehicleListe.xml");
		if (personFile != null && vehicleFile != null) {
			mainApp.savePersonDataToFile(personFile);
			mainApp.saveVehicleDataToFile(vehicleFile);
			saved = true;
		} else {

			handleSaveAs();
			saved = true;
		}
	}


	// Nutzer kann entscheiden bei Speichern unter wo hin gespeichert wird

	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
			mainApp.saveVehicleDataToFile(file);
			saved = true;
		}
	}

	//der Button about gibt kleine Informationen wer am Projekt beteiligt war
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Fuhrpark");
		alert.setHeaderText("About");
		alert.setContentText("Erstellet und Programmiert von:  Marie, Laleh, Dominik, Nico, Charles");

		alert.showAndWait();
	}


	//schließen über File. Erinnerung wenn man schließt und vorher nicht gespeichert hat
	@FXML
	private void handleExit() {
		if (saved != true) {
			Alert speichernvergessen = new Alert(AlertType.WARNING);
			speichernvergessen.setTitle("Bitte Speichern");
			speichernvergessen.setHeaderText("Speichern");
			speichernvergessen.setContentText("Sicher ohne Speichern fortfahren?");
			speichernvergessen.showAndWait();
		} else {
			System.exit(0);
		}
	}

	@FXML
	public void exitApplication(ActionEvent event) {
		if (saved != true) {
			Alert speichernvergessen = new Alert(AlertType.WARNING);
			speichernvergessen.setTitle("Bitte Speichern");
			speichernvergessen.setContentText("Sicher ohne Speichern fortfahren?");
			speichernvergessen.showAndWait();
		} else {
			Platform.exit();
		}
	}
}