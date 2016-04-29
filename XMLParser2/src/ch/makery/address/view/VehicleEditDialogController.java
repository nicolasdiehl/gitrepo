package ch.makery.address.view;
//ein Dialogfenster soll geöffnet werden beim editieren oder neu anlegen
// eines Fahrzeuges
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
//import ch.makery.address.util.DateUtil;

import javafx.scene.control.TextField;

public class VehicleEditDialogController {
	// sämtliche für das VehicleEditDialog Fenster relevanten Referenzen
	@FXML
	private TextField typField;
	@FXML
	private TextField kennzeichenField;
	@FXML
	private TextField geliehenField;

	
	
	private Stage dialogStage;
    private Vehicle vehicle;
    private boolean okClicked = false;


    @FXML
    private void initialize() {
    }

    //setzt die Stage des Dialogfensters für Vehicle
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

   //Dialog ruft Information ab
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;

        typField.setText(vehicle.getTyp());
       kennzeichenField.setText(vehicle.getKennzeichen());
        geliehenField.setText(vehicle.getGeliehen());   
    }

 
    public boolean isOkClicked() {
        return okClicked;
    }

    //beim Clicken auf den OK button im Edit Vehicle Dialog
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            vehicle.setTyp(typField.getText());
            vehicle.setKennzeichen(kennzeichenField.getText());
            vehicle.setGeliehen(geliehenField.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    //bei Abbrechen soll das Dialogfenster geschlossen werden
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

   //Eingabe soll auf Richtigkeit geprüft werden
    private boolean isInputValid() {
        String errorMessage = "";

        if (typField.getText() == null || typField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (kennzeichenField.getText() == null || kennzeichenField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }

        if (geliehenField.getText() == null || geliehenField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
               
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}

