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

	/**
	 * Opens a FileChooser to let the user select an address book to load.
	 */
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file != null) {
			mainApp.loadPersonDataFromFile(file);
			mainApp.loadVehicleDataFromFile(file);
		}
	}

	/**
	 * Saves the file to the person file that is currently open. If there is no
	 * open file, the "save as" dialog is shown.
	 */
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
		System.out.println("gespeichert.");
		System.out.println(personFile);
		System.out.println(vehicleFile);
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
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

	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Fuhrpark");
		alert.setHeaderText("About");
		alert.setContentText("Erstellet und Programmiert von:  Marie, Laleh, Dominik, Nico, Charles");

		alert.showAndWait();
	}

	/**
	 * Closes the application.
	 */
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