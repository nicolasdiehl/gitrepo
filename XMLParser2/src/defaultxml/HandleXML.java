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
	
	public static ArrayList<Object> xmlZuArrayList(File file) {
		ArrayList<Object> fArray = new ArrayList<Object>();
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			Element rootElement = document.getRootElement();
			List<Element> listeDerElemente = rootElement.getChildren();
			for (int i = 0; i < listeDerElemente.size(); i++) {
				Element einzelnesElement = listeDerElemente.get(i);
				List<Element> listeDerBodenElemente = einzelnesElement.getChildren();
				ArrayList<String> tempWerte = new ArrayList<String>();
				Attribute einzelnesElementId = einzelnesElement.getAttribute("ID");
				String einzelnesElementIdWert = einzelnesElementId.getValue();
				System.out.println("ID: " + einzelnesElementIdWert);
				for (int j = 0; j < listeDerBodenElemente.size(); j++) {
					Element einzelnesBodenElement = listeDerBodenElemente.get(j);
					String einzelnesBodenElementName = einzelnesBodenElement.getName();
					String einzelnesBodenElementWert = einzelnesBodenElement.getText();
					System.out.println(einzelnesBodenElementName + ":\t" + einzelnesBodenElementWert);
					tempWerte.add(einzelnesBodenElementWert);
				}
				System.out.println("");
				if (rootElement.getName().equals("PersonListe")) fArray.add(new Person(tempWerte.get(0),tempWerte.get(0),tempWerte.get(1),tempWerte.get(2), tempWerte.get(3)));
				else if (rootElement.getName().equals("VehicleListe")) fArray.add(new Vehicle(tempWerte.get(0),tempWerte.get(1),tempWerte.get(2)));
				else fArray.add(new Booking(tempWerte.get(0), tempWerte.get(1), tempWerte.get(2), tempWerte.get(3), tempWerte.get(4)));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return fArray;
	}

	public static Object xmlUndIdZuObjekt(File file, String id) {
	Object newItem = null;
	try {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(file);
		Element rootElement = document.getRootElement();
		List<Element> listeDerElemente = rootElement.getChildren();
		for (int i = 0; i < listeDerElemente.size(); i++) {
			Element einzelnesElement = listeDerElemente.get(i);
			Attribute einzelnesElementId = einzelnesElement.getAttribute("ID");
			String einzelnesElementIdWert = einzelnesElementId.getValue();
			if (einzelnesElementIdWert.equals(id)) {
				System.out.println("ID: " + einzelnesElementIdWert);
				ArrayList<String> tempWerte = new ArrayList<String>();
				List<Element> listeDerBodenelemente = einzelnesElement.getChildren();
				for (int j = 0; j < listeDerBodenelemente.size(); j++) {
					Element einzelnesBodenelement = listeDerBodenelemente.get(j);
					String einzelnesBodenelementName = einzelnesBodenelement.getName(); // Fuehrerschein
					String einzelnesBodenelementWert = einzelnesBodenelement.getText(); // B
					System.out.println(einzelnesBodenelementName + ":\t" + einzelnesBodenelementWert);
					tempWerte.add(einzelnesBodenelementWert);
				}
				System.out.println("");
				if (rootElement.getName().equals("PersonListe")) newItem = new Person(tempWerte.get(0),tempWerte.get(0),tempWerte.get(1),tempWerte.get(2), tempWerte.get(3));
				else if (rootElement.getName().equals("VehicleListe")) newItem = new Vehicle(tempWerte.get(0),tempWerte.get(1),tempWerte.get(2));
				else  newItem = new Booking(tempWerte.get(0), tempWerte.get(1), tempWerte.get(2), tempWerte.get(3), tempWerte.get(4));
			}
		}
	} catch (JDOMException e) {
		e.printStackTrace();
	} catch (IOException ioe) {
		ioe.printStackTrace();
	}
	return newItem;
}

	public static Object xmlUndZeileZuObjekt(File file, String zeilenNummer) {
	Object newItem = null;
	try {
		List<Element> listeDerElemente = null;
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(file);
		Element rootElement = document.getRootElement();
		listeDerElemente = rootElement.getChildren();
		Element einzelnesElement = listeDerElemente.get(Integer.parseInt(zeilenNummer));
		Attribute einzelnesElementId = einzelnesElement.getAttribute("ID");
		String einzelnesElementIdWert = einzelnesElementId.getValue();
		System.out.println("ID: " + einzelnesElementIdWert);
		ArrayList<String> tempWerte = new ArrayList<String>();
		List<Element> listeDerBodenelemente = einzelnesElement.getChildren();
		for (int j = 0; j < listeDerBodenelemente.size(); j++) {
			Element einzelnesBodenelement = listeDerBodenelemente.get(j);
			String einzelnesBodenelementName = einzelnesBodenelement.getName(); // Fuehrerschein
			String einzelnesBodenelementWert = einzelnesBodenelement.getText(); // B
			System.out.println(einzelnesBodenelementName + ":\t" + einzelnesBodenelementWert);
			tempWerte.add(einzelnesBodenelementWert);
		}
		System.out.println("");
		if (rootElement.getName().equals("PersonListe")) newItem = new Person(tempWerte.get(0),tempWerte.get(0),tempWerte.get(1),tempWerte.get(2), tempWerte.get(3));
		else if (rootElement.getName().equals("VehicleListe")) newItem = new Vehicle(tempWerte.get(0),tempWerte.get(1),tempWerte.get(2));
		else  newItem = new Booking(tempWerte.get(0), tempWerte.get(1), tempWerte.get(2), tempWerte.get(3), tempWerte.get(4));
	} catch (JDOMException e) {
		e.printStackTrace();
	} catch (IOException ioe) {
		ioe.printStackTrace();
	}
	return newItem;
}

	public static void inXmlAnhängen(File file, String typ, String[] attributNamen, String[] werte)
			throws JDOMException {
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			String newID = createUniqueID(file);
			Element einzelnesElement = new Element(typ);
			einzelnesElement.setAttribute(new Attribute("ID", newID));
			for (int i = 0; i < attributNamen.length; i++) {
				Element einzelnesBodenelement = new Element(attributNamen[i]);
				einzelnesBodenelement.setText(werte[i]);
				einzelnesElement.addContent(einzelnesBodenelement);
			}
			document.getRootElement().addContent(einzelnesElement);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void inXmlAnhängen(File file, Object objekt)
			throws JDOMException {
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			Element rootElement = document.getRootElement();
			String rootElementName = rootElement.getName(); // PersonListe
			String newID = createUniqueID(file);
			Element einzelnesElement = null;
			String newValue;
			Element einzelnesBodenelement;
			if (rootElement.getName().equals("PersonListe")) {
				einzelnesElement = new Element("Person");
				einzelnesElement.setAttribute(new Attribute("ID", newID));
				einzelnesBodenelement = new Element("Nachname");
				newValue = ((Person)objekt).getNachname();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
				einzelnesBodenelement = new Element("Vorname");
				newValue = ((Person)objekt).getVorname();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
				einzelnesBodenelement = new Element("Fuehrerschein");
				newValue = ((Person)objekt).getFuehrerschein();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
				einzelnesBodenelement = new Element("Personalnummer");
				newValue = ((Person)objekt).getPersonalnummer();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
			}
			else if (rootElement.getName().equals("VehicleListe")) {
				einzelnesElement = new Element("Vehicle");
				einzelnesElement.setAttribute(new Attribute("ID", newID));
				einzelnesBodenelement = new Element("Typ");
				newValue = ((Vehicle)objekt).getTyp();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
				einzelnesBodenelement = new Element("Geliehen");
				newValue = ((Vehicle)objekt).getGeliehen();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
				einzelnesBodenelement = new Element("Kennzeichen");
				newValue = ((Vehicle)objekt).getKennzeichen();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
			}
			else {
				einzelnesElement = new Element("Booking");
				einzelnesElement.setAttribute(new Attribute("ID", newID));
				einzelnesBodenelement = new Element("Von");
				newValue = ((Booking)objekt).getVon();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
				einzelnesBodenelement = new Element("Bis");
				newValue = ((Booking)objekt).getBis();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
				einzelnesBodenelement = new Element("Personalnummer");
				newValue = ((Booking)objekt).getPersonalnummer();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
				einzelnesBodenelement = new Element("Kennzeichen");
				newValue = ((Booking)objekt).getKennzeichen();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
				einzelnesBodenelement = new Element("Zweck");
				newValue = ((Booking)objekt).getZweck();
				einzelnesBodenelement.setText(newValue);
				einzelnesElement.addContent(einzelnesBodenelement);
			}
			document.getRootElement().addContent(einzelnesElement);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void inXmlMitIdBearbeiten(File file, String id, String[] attributNamen, String[] wert) {
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			Element rootElement = document.getRootElement();
			List<Element> listeDerElemente = rootElement.getChildren();
			for (int i = 0; i < listeDerElemente.size(); i++) {
				Element einzelnesElement = listeDerElemente.get(i);
				List<Element> listeDerBodenElemente = einzelnesElement.getChildren();
				Attribute einzelnesElementId = einzelnesElement.getAttribute("ID");
				String einzelnesElementIdWert = einzelnesElementId.getValue();
				if (einzelnesElementIdWert.equals(id)) {
					for (int j = 0; j < attributNamen.length; j++) {
						for (int k = 0; k < listeDerBodenElemente.size(); k++) {
							Element einzelnesBodenelement = listeDerBodenElemente.get(k);
							String einzelnesBodenelementName = einzelnesBodenelement.getName();
							if (einzelnesBodenelementName.equals(attributNamen[j])) {
								einzelnesBodenelement.getParent().removeContent(einzelnesBodenelement);
								Element newElement = new Element(attributNamen[j]);
								newElement.setText(wert[j]);
								einzelnesElement.addContent(newElement);
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

	public static void inXmlMitZeilebearbeiten(File file, String zeilenNummer, String[] attributNamen, String[] wert) {
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			Element rootElement = document.getRootElement();
			List<Element> listeDerElemente = rootElement.getChildren();
			Element einzelnesElement = listeDerElemente.get(Integer.parseInt(zeilenNummer));
			List<Element> listeDerBodenElemente = einzelnesElement.getChildren();
			for (int j = 0; j < attributNamen.length; j++) {
				for (int k = 0; k < listeDerBodenElemente.size(); k++) {
					Element einzelnesBodenelement = listeDerBodenElemente.get(k);
					String einzelnesBodenelementName = einzelnesBodenelement.getName();
					if (einzelnesBodenelementName.equals(attributNamen[j])) {
						einzelnesBodenelement.getParent().removeContent(einzelnesBodenelement);
						Element newElement = new Element(attributNamen[j]);
						newElement.setText(wert[j]);
						einzelnesElement.addContent(newElement);
					}
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static String sucheFreiesAuto(String zweck) {
	String autoId="";
	if (zweck.equals("Transport")) {
	ArrayList<Object> typListe = xmlUndNameUndWertLineareSucheZuArrayList(new File("VehicleListe.xml"), "Typ", "Lastwagen");
	} else if (zweck.equals("Stadtfahrt")) {
		ArrayList<Object> typListe = xmlUndNameUndWertLineareSucheZuArrayList(new File("VehicleListe.xml"), "Typ", "Cityflitzer");
		ArrayList<Object> typListeVerfeinert = typListe
		if (typListe == null) {
			System.out.println("Leider ist kein Fahrzeug der Klasse Cityflitzer mehr verfügbar.");
			typListe = xmlUndNameUndWertLineareSucheZuArrayList(new File("VehicleListe.xml"), "Typ", "Limousine");
		}
	} else if (zweck.equals("Langstreckenfahrt")) {
		ArrayList<Object> typListe = xmlUndNameUndWertLineareSucheZuArrayList(new File("VehicleListe.xml"), "Typ", "Limousine");
	}
	return autoId;
	}
	
	public static ArrayList<Object> xmlUndNameUndWertLineareSucheZuArrayList(File file, String zuSuchenderAttributName, String gesuchterWert) {
		ArrayList<String> listeGefundenerZeilen = new ArrayList<String>();
		ArrayList<Object> listeGefundenerObjekte = new ArrayList<Object>();
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			Element rootElement = document.getRootElement();
			List<Element> listeDerElemente = rootElement.getChildren();
			for (int i = 0; i < listeDerElemente.size(); i++) {
				Element einzelnesElement = listeDerElemente.get(i);
				List<Element> listeDerBodenElemente = einzelnesElement.getChildren();
				for (int j = 0; j < listeDerBodenElemente.size(); j++) {
					Element einzelnesBodenElement = listeDerBodenElemente.get(j);
					String einzelnesBodenElementName = einzelnesBodenElement.getName();
					String einzelnesBodenElementWert = einzelnesBodenElement.getText();
					if (einzelnesBodenElementName.equals(zuSuchenderAttributName) && einzelnesBodenElementWert.equals(gesuchterWert)) {
						listeGefundenerZeilen.add(Integer.toString(i));
						break;
					}
				}
				for (int j = 0; j < listeGefundenerZeilen.size(); j++) {
					listeGefundenerObjekte.add(xmlUndZeileZuObjekt(file, listeGefundenerZeilen.get(j)));
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return listeGefundenerObjekte;
	}

	public static String bitteWertEingeben() {
		Scanner scan = new Scanner(System.in);
		String antwort;
		System.out.println("Nach welchem Wert suchen Sie?");
		antwort = scan.nextLine();
		return antwort;
	}
	
	public static String bitteWertEingebenBool() {
		Scanner scan = new Scanner(System.in);
		String antwort;
		System.out.println("ja(j), oder nein(n)?");
		antwort = scan.nextLine();
		return antwort;
	}
	
	public static ArrayList<Object> xmlUndUserInputLineareSucheZuArrayList() {
		ArrayList<Object> listeGefundenerObjekte = null;
		Scanner scan = new Scanner(System.in);
		String inputA;
		String inputB;
		File file;
		System.out.println("Was genau suchen Sie? Eine Person(p), einen Leihwagen(l), oder eine Buchung?(b)");
		inputA = scan.nextLine();
		switch (inputA) {
		case "p":
			file = new File("PersonListe.xml");
			System.out.println("Suchen Sie den Vornamen(v), Nachnamen(n), Personalnummer(p), oder den Führerscheintyp(f)?");
			inputB = scan.nextLine();
			switch (inputB) {
			case "v":
				listeGefundenerObjekte = xmlUndNameUndWertLineareSucheZuArrayList(file, "Vorname", bitteWertEingeben());
				break;
			case "n":
				listeGefundenerObjekte = xmlUndNameUndWertLineareSucheZuArrayList(file, "Nachname", bitteWertEingeben());
				break;
			case "p":
				listeGefundenerObjekte = xmlUndNameUndWertLineareSucheZuArrayList(file, "Personalnummer", bitteWertEingeben());
				break;
			case "f":
				listeGefundenerObjekte = xmlUndNameUndWertLineareSucheZuArrayList(file, "Fuehrerschein", bitteWertEingebenBool());
				break;
			default:
				System.out.println("falsche Eingabe!");
			}
			break;
		case "f":
			file = new File("VehicleListe.xml");
			System.out.println("Suchen Sie den Fahrzeugtyp(f), das Kennzeichen(k), oder den Leihstatus(l)?");
			inputB = scan.nextLine();
			switch (inputB) {
			case "f":
				listeGefundenerObjekte = xmlUndNameUndWertLineareSucheZuArrayList(file, "Typ", bitteWertEingeben());
				break;
			case "k":
				listeGefundenerObjekte = xmlUndNameUndWertLineareSucheZuArrayList(file, "Kennzeichen", bitteWertEingeben());
				break;
			case "l":
				listeGefundenerObjekte = xmlUndNameUndWertLineareSucheZuArrayList(file, "Geliehen", bitteWertEingebenBool());
				break;
			default:
				System.out.println("falsche Eingabe!");
			}
			break;
		case "b":
			file = new File("BookingListe.xml");
			System.out.println("Suchen Sie eine Personalnummer(f) oder das Kennzeichen(k)?");
			inputB = scan.nextLine();
			switch (inputB) {
			case "p":
				listeGefundenerObjekte = xmlUndNameUndWertLineareSucheZuArrayList(file, "Personalnummer", bitteWertEingeben());
				break;
			case "k":
				listeGefundenerObjekte = xmlUndNameUndWertLineareSucheZuArrayList(file, "Kennzeichen", bitteWertEingeben());
				break;
			default:
				System.out.println("falsche Eingabe!");
			}
			break;
		default:
			System.out.println("falsche Eingabe!");
		}
		return listeGefundenerObjekte;
	}

	/*public static void lineareSuche() {
		String suchzahl = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welche Personalnummer suchen Sie?");
		suchzahl = scan.nextLine();
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
	}*/
}