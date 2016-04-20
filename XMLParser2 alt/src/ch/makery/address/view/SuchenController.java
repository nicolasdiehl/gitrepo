package ch.makery.address.view;

import ch.makery.address.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ch.makery.address.MainApp;
import defaultxml.SuchBaumBSP;
<<<<<<< HEAD
=======
import ch.makery.address.view.*;
import defaultxml.SuchBaumBSP;
>>>>>>> branch 'ohne_fxml' of https://github.com/nicolasdiehl/gitrepo.git
//
public class SuchenController {
	@FXML
	private TableView<Person> personBookingTable;
	@FXML
	private TableView<Vehicle> vehicleBookingTable;
	@FXML
	private TextField searchTableFound;
	@FXML
	private TextField searchTablePersNr;
<<<<<<< HEAD
=======
	@FXML
	private Label suchenLabel;
	private Suchen suchen;
>>>>>>> branch 'ohne_fxml' of https://github.com/nicolasdiehl/gitrepo.git
	private MainApp mainApp;
	private boolean zurueckClicked = false;

	
	
	public SuchenController() {
	}

	@FXML
	private void handleSuchen() {
		SuchBaumBSP suchbaum = new SuchBaumBSP();
		int suchzahl = Integer.parseInt(searchTablePersNr.getText());
		suchbaum.Suche(suchzahl);
<<<<<<< HEAD
		
		searchTableFound.setText("Name: " + suchbaum.getTempNachname()+ ", " +suchbaum.getTempVorname()+"    Fuehrerschein: "
		+suchbaum.getTempFuehrerschein()+"    Personalnummer: "+suchbaum.getTempPersonalnummer());
=======
		String temp="" +suchzahl;
		suchenLabel.setText(SuchBaumBSP.person1.getChild("Nachname"));// label setzen geht nicht!!!!!!!! ARRAY LIST????? NICO WAY!!!
		
>>>>>>> branch 'ohne_fxml' of https://github.com/nicolasdiehl/gitrepo.git
	}

	@FXML
	private void handleZurueck() {
		zurueckClicked = true;
		mainApp.showMainOverview();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		personBookingTable.setItems(mainApp.getPersonData());
		vehicleBookingTable.setItems(mainApp.getVehicleData());
	}

    public boolean isZurueckClicked(){
    	return zurueckClicked();
    }
    private boolean zurueckClicked() {
		// TODO Auto-generated method stub
		return zurueckClicked;
	}
}