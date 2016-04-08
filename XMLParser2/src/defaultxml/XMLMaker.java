package defaultxml;
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
	Element typListe = new Element("TypListe");  

    Document document = new Document(typListe);  

    Element vehicle = new Element("Vehicle");  

	String tempID= ConnectXML.createUniqueID("VehicleListe.xml");

    vehicle.setAttribute(new Attribute("ID", tempID));
    vehicle.addContent(new Element("Typ").setText("Transporter"));  // gefundene Ergebnisse müssen hier rein
    vehicle.addContent(new Element("Geliehen").setText("false"));  		 // for-Schleife, VehicleListe.xml in
    vehicle.addContent(new Element("Zweck").setText("Transport"));  		 // Array speichern?, danach durchsuchen?
    vehicle.addContent(new Element("Kennzeichen").setText("DA-TR101"));  
    
    document.getRootElement().addContent(vehicle);  

    XMLOutputter xmlOutput = new XMLOutputter();  

    xmlOutput.output(document, System.out);  

    xmlOutput.setFormat(Format.getPrettyFormat());  
    xmlOutput.output(document, new FileWriter("D:\\Test.xml",true));
	}
}
