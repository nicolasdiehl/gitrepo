import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.jdom2.*;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ConnectXMLFahrzeug extends ConnectXML{

	public static List<Element> readFahrzeugListe(){
		List<Element> fahrzeugList = null;
		try {
			File inputFile = new File("FahrzeugListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			System.out.println("Root element :" + document.getRootElement().getName());
			Element classElement = document.getRootElement();

			fahrzeugList = classElement.getChildren();
			System.out.println("----------------------------");

			for (int temp = 0; temp < fahrzeugList.size(); temp++) {    
				Element fahrzeug = fahrzeugList.get(temp);
				System.out.println("\n Current Element :" + fahrzeug.getName());
				Attribute attribute =  fahrzeug.getAttribute("ID");
				System.out.println("Zeile Nummer :\t" + attribute.getValue() );
				System.out.println("Fahrzeugtyp:\t"+ fahrzeug.getChild("Fahrzeugtyp").getText());
				System.out.println("Geliehen:\t"+ fahrzeug.getChild("Geliehen").getText());
				System.out.println("Zweck:\t"+ fahrzeug.getChild("Zweck").getText());	            
				System.out.println("Kennzeichen:\t"+ fahrzeug.getChild("Kennzeichen").getText());
			}
		}catch(JDOMException e){
			e.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return fahrzeugList;
	}

	public static void einfügenFahrzeug(String fahrzeugtyp, boolean geliehen, String zweck, String kennzeichen)
			throws JDOMException{
		try {
			File inputFile = new File("FahrzeugListe.xml"); // Zugriff auf
			// XML Datei
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);

			String tempID= createUniqueID("FahrzeugListe.xml");
			Element neuesFahrzeug= new Element ("Fahrzeug");
			neuesFahrzeug.setAttribute(new Attribute ("ID", tempID));
			Element neuerFahrzeugtyp= new Element ("Fahrzeugtyp");
			neuerFahrzeugtyp.setText(fahrzeugtyp);
			Element neuesGeliehen= new Element ("Geliehen");
			neuesGeliehen.setText(Objects.toString(geliehen));
			Element neuerZweck= new Element ("Zweck");
			neuerZweck.setText(zweck);
			Element neuesKennzeichen= new Element ("Kennzeichen");
			neuesKennzeichen.setText(kennzeichen);

			neuesFahrzeug.addContent(neuerFahrzeugtyp);
			neuesFahrzeug.addContent(neuesGeliehen);
			neuesFahrzeug.addContent(neuerZweck);
			neuesFahrzeug.addContent(neuesKennzeichen);		         

			document.getRootElement().addContent(neuesFahrzeug);

			XMLOutputter xmlOutput = new XMLOutputter();

			// display ml
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(inputFile)); 
		}catch(IOException e){
			e.printStackTrace();
		}	
	}  
}