package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import org.jdom2.JDOMException;
import ch.makery.address.model.Person;
import defaultxml.HandleXML;
//ein Dialogfenster das beim Editieren oder Neuanlegen aufgerufen wird
public class PersonEditController {
	// sämtliche für das PersonEdit Fenster relevanten Referenzen
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

	    //Dialogstage wird hier erzeugt
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }


	     //Person editier Dialog
	    public void setPerson(Person person) {
	        this.person = person;
	        vorNameField.setText(person.getVorname());
	        nachNameField.setText(person.getNachname());
	        personalnummerField.setText(person.getPersonalnummer());
	        fuehrerscheinField.setText(person.getFuehrerschein());
	    }

	   
	    //setzt zustand true und wird übernommen bei ok, sonst auf falsch
	    public boolean isOkClicked() {
	        return okClicked;
	    }


	    //Vorgang wenn Ok gedrückt wird
	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	        	try {
					person.setId(HandleXML.createUniqueID(new File ("PersonListe.xml")));
				} catch (JDOMException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            person.setVorname(vorNameField.getText());
	            person.setNachname(nachNameField.getText());
	            person.setPersonalnummer(personalnummerField.getText());
	            person.setFuehrerschein(fuehrerscheinField.getText());
	            okClicked = true;
	            dialogStage.close();
	        }
	    }

	   //bei Abbrechen drücken
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }


	    //überprüft die Richtigkeit der Eingabe
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
