package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Buchen;
import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class UebersichtController {
	@FXML
    private TableView<Buchen> buchenTable;
	 private Buchen buchen;
	 private MainApp mainApp;
	@FXML
	private void handleBuchen() {
	    Buchen selectedBuchen = buchenTable.getSelectionModel().getSelectedItem();
	 
	       
	        
		}
}
