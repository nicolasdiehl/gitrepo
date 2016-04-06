import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Calendar;
import java.io.IOException;
import java.util.List;
import java.lang.Integer;

import org.jdom2.*;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ConnectXMLAusleihe extends ConnectXML{
	   public static void readAusleihe() throws JDOMException{
		      try {
		         File inputFile = new File("AusleiheListe.xml");
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);

		         System.out.println("Root element :" + document.getRootElement().getName());
		         Element classElement = document.getRootElement();

		         List<Element> AusleihListe = classElement.getChildren();

		         for (int temp = 0; temp < AusleihListe.size(); temp++) {    
		            Element Vorgang = AusleihListe.get(temp);
		            //System.out.println("Liste der Ausleihvorgänge :" + Vorgang.getName());
		            System.out.println("-------------------");
		            Attribute attribute =  Vorgang.getAttribute("ID");
		            System.out.println("Vorgang:\t" + attribute.getValue() );
		            System.out.println("Kennzeichen:\t" + Vorgang.getChild("Kennzeichen").getText());
		            System.out.println("MitarbeiterID:\t" + Vorgang.getChild("MitarbeiterID").getText());
		            System.out.println("Leihbeginn:\t" + Vorgang.getChild("Leihbeginn").getText());
		            System.out.println("Leihende:\t" + Vorgang.getChild("Leihende").getText());
		         }
		      }catch(JDOMException e){
		         e.printStackTrace();
		      }catch(IOException ioe){
		         ioe.printStackTrace();
		      }
		   }  
	   public static void einfügenAusleihvorgang(String mitarbeiterID, String kennzeichen, String leihbeginn, String leihende) throws JDOMException{
		   try {
		         File inputFile = new File("AusleiheListe.xml");
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);
		         // erstellte Elemente entsprechen Attributen in der XML
		         Element neueAusleihe = new Element ("Vorgang");
		         String newID = createUniqueID("AusleiheListe.xml");
		         neueAusleihe.setAttribute(new Attribute ("ID", newID));
		         Element avKennzeichen = new Element ("Kennzeichen");
		         avKennzeichen.setText(kennzeichen);
		         Element avMitarbeiter = new Element ("MitarbeiterID");
		         avMitarbeiter.setText(mitarbeiterID);
		         Element avLeihbeginn = new Element ("Leihbeginn");
		         avLeihbeginn.setText(leihbeginn);
		         Element avLeihende = new Element ("Leihende");
		         avLeihende.setText(leihende);
		         
		         neueAusleihe.addContent(avKennzeichen);
		         neueAusleihe.addContent(avMitarbeiter);
		         neueAusleihe.addContent(avLeihbeginn);
		         neueAusleihe.addContent(avLeihende);
		         
		         document.getRootElement().addContent(neueAusleihe);
		         XMLOutputter xmlOutput = new XMLOutputter();

		         xmlOutput.setFormat(Format.getPrettyFormat());
		         xmlOutput.output(document, new FileWriter(inputFile)); 
		      }catch(IOException e){
		         e.printStackTrace();
		      }	
		   }
}