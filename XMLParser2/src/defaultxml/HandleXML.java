package defaultxml;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import ch.makery.address.model.Booking;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import java.util.Scanner;
import java.util.ArrayList;

public class HandleXML extends ConnectXML {

	public static ArrayList<Object> auslesen(File file) {
		ArrayList<Object> fArray = new ArrayList<Object>();
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			Element classElement = document.getRootElement();
			List<Element> liste = classElement.getChildren();
			for (int i = 0; i < liste.size(); i++) {
				Element item = liste.get(i);
				List<Element> itemProperties = item.getChildren();
				ArrayList<String> tempValues = new ArrayList<String>();
				System.out.println("\n");
				Attribute attribute = item.getAttribute("ID");
				for (int j = 0; j < itemProperties.size(); j++) {
					String tempB = item.getText();
					System.out.println(itemProperties.get(j).getText()+"\t" + tempB);
					tempValues.add(tempB);
				}
				if (classElement.getText().equals("Person")) fArray.add(new Person(tempValues.get(0),tempValues.get(0),tempValues.get(1),tempValues.get(2), tempValues.get(3)));
				else if (classElement.getText().equals("Vehicle")) fArray.add(new Vehicle(tempValues.get(0),tempValues.get(1),tempValues.get(2)));
				else if (classElement.getText().equals("Booking")) fArray.add(new Booking(tempValues.get(0), tempValues.get(0), tempValues.get(1), tempValues.get(2), tempValues.get(3)));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return fArray;
	}

	public static Object auslesen(File file, String id) {
	Object newItem = null;
	try {
		List<Element> liste = null;
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(file);
		Element classElement = document.getRootElement();
		liste = classElement.getChildren();
		for (int i = 0; i < liste.size(); i++) {
			Element item = liste.get(i);
			List<Element> itemProperties = item.getChildren();
			ArrayList<String> tempValues = new ArrayList<String>();
			Attribute attribute = item.getAttribute("ID");
			if (attribute.getText().equals(id)) {
				
				for (int t = 0; t < itemProperties.size(); t++) {
					Element typElement = itemProperties.get(t);
					String tempB = item.getText();
					System.out.println(itemProperties.get(j).getText()+"\t" + tempB);
					tempValues.add(tempB);
					System.out.println(typElement.getName()+"\t"+typElement.getText());
					
				}
				if (classElement.getText().equals("Person")) newItem = new Person(tempValues.get(0),tempValues.get(0),tempValues.get(1),tempValues.get(2), tempValues.get(3));
				else if (classElement.getText().equals("Vehicle")) newItem = new Vehicle(tempValues.get(0),tempValues.get(1),tempValues.get(2));
				else if (classElement.getText().equals("Booking")) newItem = new Booking(tempValues.get(0), tempValues.get(0), tempValues.get(1), tempValues.get(2), tempValues.get(3));
			}
		}
	} catch (JDOMException e) {
		e.printStackTrace();
	} catch (IOException ioe) {
		ioe.printStackTrace();
	}
	return newItem;
}
	
	public static void einfügen(File file, String typ, String[] attribute, String[] werte)
			throws JDOMException {
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			String newID = createUniqueID(file);
			Element item = new Element(typ);
			item.setAttribute(new Attribute("ID", newID));
			for (int i = 0; i < attribute.length; i++) {
				Element newElement = new Element(attribute[i]);
				newElement.setText(werte[i]);
				item.addContent(newElement);
			}
			document.getRootElement().addContent(item);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void bearbeiten(File file, String id, String[] typ, String[] wert) {
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			Element classElement = document.getRootElement();
			List<Element> liste = classElement.getChildren();
			for (int i = 0; i < liste.size(); i++) {
				Element item = liste.get(i);
				List<Element> itemProperties = item.getChildren();
				Attribute attribute = item.getAttribute("ID");
				if (attribute.equals(id)) {
					for (int t = 0; t < typ.length; t++) {
						for (int p = 0; p < itemProperties.size(); p++) {
							Element typElement=itemProperties.get(p);
							if (typElement.equals(typ[t])) {
								itemProperties.get(p).getParent().removeContent(itemProperties.get(p));
								Element newElement = new Element(typ[p]);
								newElement.setText(wert[p]);
								item.addContent(newElement);
							}
						}
					}
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static ArrayList<Object> lineareSuche(File file, String typ, String wert) {
		ArrayList<String> fList = new ArrayList<String>();
		ArrayList<Object> fArray = new ArrayList<Object>();
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			Element classElement = document.getRootElement();
			List<Element> listeItems = classElement.getChildren();
			for (int i = 0; i < listeItems.size(); i++) {
				Element Einitem = listeItems.get(i);
				List<Element> itemProperties = Einitem.getChildren();
				System.out.println("\n");
				Attribute attribute = Einitem.getAttribute("ID");
				String id = attribute.getValue();
				for (int j = 0; j < itemProperties.size(); j++) {
					if (itemProperties.get(j).equals(typ)&&id.equals(wert)) {
						fList.add(id);
						break;
					}
				}
				for (int j = 0; j < fList.size(); j++) {
					fArray.add(auslesen(file, fList.get(j)));
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return fArray;
	}
	
	public static void lineareSuche() {
		int suchzahl = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welche Personalnummer suchen Sie?");
		suchzahl = scan.nextInt();
		List<Element> slist = null;
		try {
			File inputFile = new File("PersonListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			Element classElement = document.getRootElement();
			slist = classElement.getChildren();
			for (int temp = 0; temp < slist.size(); temp++) {
				Element person1 = slist.get(temp);
				Element attribute1 = person1.getChild("PersNr");
				int personalnummer = Integer.parseInt(attribute1.getValue());
				if (personalnummer == suchzahl) {
					System.out.println("\nElement:	   " + person1.getName());
					System.out.println("Nachname:          " + person1.getChild("Nachname").getText());
					System.out.println("Vorname:           " + person1.getChild("Vorname").getText());
					System.out.println("Fuehrerschein:     " + person1.getChild("Fuehrerschein").getText());
					System.out.println("PersNr:            " + person1.getChild("PersNr").getText());
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}