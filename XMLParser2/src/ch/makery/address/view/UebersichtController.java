package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Buchen;
import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import ch.makery.address.view.MainOverviewController;

public class UebersichtController {

	@FXML
	private TableView<Buchen>bookingTable;
	@FXML
	private TableView<Buchen>vorNameColumn;
	@FXML
	private TableView<Buchen>nachNameColumn;
	@FXML
	private TableView<Buchen>idColumn;
	@FXML
	private TableView<Buchen>zweckColumn;
	@FXML
	private TableView<Buchen>vonColumn;
	@FXML
	private TableView<Buchen>bisColumn;
	@FXML
	private TableView<Buchen>dauerColumn;
	@FXML
	private TableView<Buchen>fuehrerscheinColumn;
	@FXML
	private TableView<Buchen>kennzeichenColumn;
	@FXML
	private TableView<Buchen>typColumn;
	
	
	
	
	
	Buchen buchen;
	private MainApp mainApp;
	
	
	  private void initialize() {
		   // Initialize the person table with the two columns.
	        vorNameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameroperty().asObject());
	        nachNameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
	        typColumn.setCellValueFactory(cellData -> cellData.getValue().typProperty());
	        zweckColumn.setCellValueFactory(cellData -> cellData.getValue().zweckProperty());
	        
	        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	        vonColumn.setCellValueFactory(cellData -> cellData.getValue().vonProperty());
	        bisColumn.setCellValueFactory(cellData -> cellData.getValue().bisProperty());
	        dauerColumn.setCellValueFactory(cellData -> cellData.getValue().dauerProperty());
	        
	        kennzeichenColumn.setCellValueFactory(cellData -> cellData.getValue().kennzeichenProperty());
	        fuehrerscheinColumn.setCellValueFactory(cellData -> cellData.getValue().fuehrerscheinProperty());
	        typColumn.setCellValueFactory(cellData -> cellData.getValue().typProperty());
	        zweckColumn.setCellValueFactory(cellData -> cellData.getValue().zweckProperty());
	  }
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		bookingTable.setItems(mainApp.getBuchenData());
		buchen=new Buchen();
	}


}
