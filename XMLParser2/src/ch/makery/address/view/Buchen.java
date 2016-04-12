package ch.makery.address.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


////////////////////////////////////////////////////////////////////////
//Hilfsklasse für das Fenser Buchen!!!!!!!!!!!!!!!!!!!!!!!!!//
public class Buchen extends Stage {
	
	    Stage stage;
	    public Buchen() throws IOException{
	        stage = this;
	         Parent root = FXMLLoader.load(getClass().getResource("Buchen.fxml"));// Buchen FXML holen
	        
	        Scene scene = new Scene(root);
	        
	        stage.setScene(scene);
	        stage.show();
	    }
	    }

