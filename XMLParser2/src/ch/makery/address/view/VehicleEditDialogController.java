package ch.makery.address.view;

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
	@FXML
	private TextField typField;
	@FXML
	private TextField zweckField;
	@FXML
	private TextField kennzeichenField;
	@FXML
	private TextField geliehenField;

	
	
	private Stage dialogStage;
    private Vehicle vehicle;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;

        typField.setText(vehicle.getTyp());
       kennzeichenField.setText(vehicle.getKennzeichen());
        zweckField.setText(vehicle.getZweck());
        geliehenField.setText(vehicle.getGeliehen());
       
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            vehicle.setTyp(typField.getText());
            vehicle.setKennzeichen(kennzeichenField.getText());
            vehicle.setZweck(zweckField.getText());
            vehicle.setGeliehen(geliehenField.getText());
          

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (typField.getText() == null || typField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (kennzeichenField.getText() == null || kennzeichenField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (zweckField.getText() == null || zweckField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
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

