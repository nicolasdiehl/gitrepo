import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ConnectXMLVehicle extends ConnectXML{

	public static List<Element> readVehicleListe(){
		List<Element> vehicleList = null;
		try {
			File inputFile = new File("VehicleListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			System.out.println("Root element :" + document.getRootElement().getName());
			Element classElement = document.getRootElement();

			vehicleList = classElement.getChildren();
			System.out.println("----------------------------");

			for (int temp = 0; temp < vehicleList.size(); temp++) {    
				Element vehicle = vehicleList.get(temp);
				System.out.println("\n Current Element :" + vehicle.getName());
				Attribute attribute =  vehicle.getAttribute("ID");
				System.out.println("Zeile Nummer :\t" + attribute.getValue() );
				System.out.println("Typ:\t"+ vehicle.getChild("Typ").getText());
				System.out.println("Geliehen:\t"+ vehicle.getChild("Geliehen").getText());
				System.out.println("Zweck:\t"+ vehicle.getChild("Zweck").getText());	            
				System.out.println("Kennzeichen:\t"+ vehicle.getChild("Kennzeichen").getText());
			}
		}catch(JDOMException e){
			e.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return vehicleList;
	}

	public static void einfügenVehicle(String typ, boolean geliehen, String zweck, String kennzeichen)
			throws JDOMException{
		try {
			File inputFile = new File("VehicleListe.xml"); // Zugriff auf
			// XML Datei
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);

			String tempID= createUniqueID("VehicleListe.xml");
			Element neuesVehicle= new Element ("Vehicle");
			neuesVehicle.setAttribute(new Attribute ("ID", tempID));
			Element neuerTyp= new Element ("Typ");
			neuerTyp.setText(typ);
			Element neuesGeliehen= new Element ("Geliehen");
			neuesGeliehen.setText(Objects.toString(geliehen));
			Element neuerZweck= new Element ("Zweck");
			neuerZweck.setText(zweck);
			Element neuesKennzeichen= new Element ("Kennzeichen");
			neuesKennzeichen.setText(kennzeichen);

			neuesVehicle.addContent(neuerTyp);
			neuesVehicle.addContent(neuesGeliehen);
			neuesVehicle.addContent(neuerZweck);
			neuesVehicle.addContent(neuesKennzeichen);		         

			document.getRootElement().addContent(neuesVehicle);

			XMLOutputter xmlOutput = new XMLOutputter();

			// display ml
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(inputFile)); 
		}catch(IOException e){
			e.printStackTrace();
		}	
	}  

	public static void sucheTyp() {
		String suchtyp = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("Welchen Typ suchen Sie?");
		suchtyp = scan.next();
		List<Element> vehicleList1 = null;
		try {
			File inputFile = new File("VehicleListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			Element classElement = document.getRootElement();
			vehicleList1 = classElement.getChildren();
			for (int temp = 0; temp < vehicleList1.size(); temp++) {
				Element person1 = vehicleList1.get(temp);
				Element attribute1 = person1.getChild("Typ");
				String typ = attribute1.getValue();
				if (typ.equals(suchtyp)) {
					System.out.println("\nElement:\t" + person1.getName());
					System.out.println("Typ:\t" + person1.getChild("Typ").getText());
					System.out.println("Geliehen:\t" + person1.getChild("Geliehen").getText());
					System.out.println("Zweck:\t\t" + person1.getChild("Zweck").getText());
					System.out.println("Kennzeichen:\t" + person1.getChild("Kennzeichen").getText());
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}