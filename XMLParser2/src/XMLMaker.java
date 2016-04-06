import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

class XMLMaker {
	public static void createXML() throws JDOMException, IOException{
	Element fahrzeugtypListe = new Element("FahrzeugtypListe");  

    Document document = new Document(fahrzeugtypListe);  

    Element fahrzeug = new Element("Fahrzeug");  

	String tempID= ConnectXML.createUniqueID("FahrzeugListe.xml");

    fahrzeug.setAttribute(new Attribute("ID", tempID));
    fahrzeug.addContent(new Element("Fahrzeugtyp").setText("Transporter"));  // gefundene Ergebnisse müssen hier rein
    fahrzeug.addContent(new Element("Geliehen").setText("false"));  		 // for-Schleife, FahrzeugListe.xml in
    fahrzeug.addContent(new Element("Zweck").setText("Transport"));  		 // Array speichern?, danach durchsuchen?
    fahrzeug.addContent(new Element("Kennzeichen").setText("DA-TR101"));  
    
    document.getRootElement().addContent(fahrzeug);  

    XMLOutputter xmlOutput = new XMLOutputter();  

    xmlOutput.output(document, System.out);  

    xmlOutput.setFormat(Format.getPrettyFormat());  
    xmlOutput.output(document, new FileWriter("D:\\Test.xml",true));
	}
}
