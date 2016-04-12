package ch.makery.address.view;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Person;
//
public class PersonEditController {
	 @FXML
	    private TextField vorNameField;
	    @FXML
	    private TextField nachNameField;
	    @FXML
	    private TextField personalnummerField;
	    @FXML
	    private TextField fuehrerscheinField;
	
	 


	    private Stage dialogStage;
	    private Person person;
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
	    public void setPerson(Person person) {
	        this.person = person;

	        vorNameField.setText(person.getVorname());
	        nachNameField.setText(person.getNachname());
	        personalnummerField.setText(person.getPersonalnummer());
	        fuehrerscheinField.setText(person.getFuehrerschein());
	  
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
	            person.setVorname(vorNameField.getText());
	            person.setNachname(nachNameField.getText());
	            person.setPersonalnummer(personalnummerField.getText());
	            person.setFuehrerschein(fuehrerscheinField.getText());
	      

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

	        if (vorNameField.getText() == null || vorNameField.getText().length() == 0) {
	            errorMessage += "No valid first name!\n"; 
	        }
	        if (nachNameField.getText() == null || nachNameField.getText().length() == 0) {
	            errorMessage += "No valid last name!\n"; 
	        }
	        if (personalnummerField.getText() == null || personalnummerField.getText().length() == 0) {
	            errorMessage += "No pers.number !\n"; 
	        }

	        if (fuehrerscheinField.getText() == null || fuehrerscheinField.getText().length() == 0) {
	            errorMessage += "No valid fuehrerschein\n"; 
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
