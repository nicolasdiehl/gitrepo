import java.io.File;
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
		         File inputFile = new File("Ausleihe.xml");
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);

		         System.out.println("Root element :" + document.getRootElement().getName());
		         Element classElement = document.getRootElement();

		         List<Element> AusleihListe = classElement.getChildren();
		         System.out.println("----------------------------");

		         for (int temp = 0; temp < AusleihListe.size(); temp++) {    
		            Element Vorgang = AusleihListe.get(temp);
		            System.out.println("Liste der Ausleihvorgänge :" + Vorgang.getName());
		            Attribute attribute =  Vorgang.getAttribute("ID");
		            System.out.println("Vorgang   : "  + attribute.getValue() );
		            System.out.println("FahrzeugID :         " + Vorgang.getChild("FahrzeugID").getText());
		            System.out.println("MitarbeiterID:       " + Vorgang.getChild("MitarbeiterID").getText());
		         }
		      }catch(JDOMException e){
		         e.printStackTrace();
		      }catch(IOException ioe){
		         ioe.printStackTrace();
		      }
		   }  
	   public static void einfügenAusleihvorgang(String MitarbeiterID, String FahrzeugID) throws JDOMException{
		   try {
		         File inputFile = new File("Ausleihe.xml");
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);

		         Element ausleihV= new Element ("Vorgang");
		         String newID= createUniqueID("Ausleihe.xml");
		         ausleihV.setAttribute(new Attribute ("ID", newID));
		         Element nvFahrzeug= new Element ("FahrzeugID");
		         nvFahrzeug.setText(FahrzeugID);
		         Element nvMitarbeiter= new Element ("MitarbeiterID");
		         nvMitarbeiter.setText(MitarbeiterID);
		            
		         ausleihV.addContent(nvFahrzeug);
		         ausleihV.addContent(nvMitarbeiter);
		         
		         document.getRootElement().addContent(ausleihV);
		         XMLOutputter xmlOutput = new XMLOutputter();

		         // display xml
		         xmlOutput.setFormat(Format.getPrettyFormat());
		         xmlOutput.output(document, System.out); 
		      }catch(IOException e){
		         e.printStackTrace();
		      }	
		   }//schließt Methode
}