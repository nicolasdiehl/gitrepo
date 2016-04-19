package ch.makery.address.view;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Person;
import ch.makery.address.model.Booking;
//import ch.makery.address.util.DateUtil;

import javafx.scene.control.TextField;

public class BookingEditController {
	@FXML
	private TextField vonField;
	@FXML
	private TextField bisField;
	@FXML
	private TextField personalnummerField;
	@FXML
	private TextField kennzeichenField;

	
	
	private Stage dialogStage;
    private Booking booking;
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
    public void setBooking(Booking booking) {
        this.booking = booking;

        vonField.setText(booking.getVon());
        bisField.setText(booking.getBis());
        personalnummerField.setText(booking.getPersonalnummer());
        kennzeichenField.setText(booking.getKennzeichen());
       
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
            booking.setVon(vonField.getText());
            booking.setBis(bisField.getText());
            booking.setPersonalnummer(personalnummerField.getText());
            booking.setKennzeichen(kennzeichenField.getText());
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

        if (vonField.getText() == null || vonField.getText().length() == 0) {
            errorMessage += "No valid beginning date!\n"; 
        }
        if (bisField.getText() == null || bisField.getText().length() == 0) {
            errorMessage += "No valid return date!\n"; 
        }
        if (personalnummerField.getText() == null || personalnummerField.getText().length() == 0) {
            errorMessage += "No valid number!\n"; 
        }
        if (kennzeichenField.getText() == null || kennzeichenField.getText().length() == 0) {
            errorMessage += "No valid platenumber!\n"; 
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

