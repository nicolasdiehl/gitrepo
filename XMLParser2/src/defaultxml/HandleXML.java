package defaultxml;
import static java.util.Arrays.asList;

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
import ch.makery.address.model.Buchen;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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
				else if (rootElement.getName().equals("VehicleListe")) fArray.add(new Vehicle(einzelnesElementIdWert, tempWerte.get(0),tempWerte.get(1),tempWerte.get(2)));
				else fArray.add(new Buchen(tempWerte.get(0), tempWerte.get(1), tempWerte.get(2), tempWerte.get(3), tempWerte.get(4), tempWerte.get(5), tempWerte.get(6), tempWerte.get(7), tempWerte.get(8), tempWerte.get(9)));
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
				else if (rootElement.getName().equals("VehicleListe")) newItem = new Vehicle(id, tempWerte.get(0),tempWerte.get(1),tempWerte.get(2));
				else  newItem = new Buchen(tempWerte.get(0), tempWerte.get(1), tempWerte.get(2), tempWerte.get(3), tempWerte.get(4), tempWerte.get(5), tempWerte.get(6), tempWerte.get(7), tempWerte.get(8), tempWerte.get(9));
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
		if (rootElement.getName().equals("PersonListe")) newItem = new Person(einzelnesElementIdWert,tempWerte.get(0),tempWerte.get(1),tempWerte.get(2), tempWerte.get(3));
		else if (rootElement.getName().equals("VehicleListe")) newItem = new Vehicle(einzelnesElementIdWert, tempWerte.get(0),tempWerte.get(1),tempWerte.get(2));
		else  {
			newItem = new Buchen(tempWerte.get(0), tempWerte.get(1), tempWerte.get(2), tempWerte.get(3), tempWerte.get(4), tempWerte.get(5), tempWerte.get(6), tempWerte.get(7), tempWerte.get(8), tempWerte.get(9));
			System.out.println("zeile 123: einzelnesElementIdWert+tempWerte.get(0)+tempWerte.get(1)+tempWerte.get(2)+tempWerte.get(3)+tempWerte.get(4)="+einzelnesElementIdWert+tempWerte.get(0)+tempWerte.get(1)+tempWerte.get(2)+tempWerte.get(3)+tempWerte.get(4));
			}
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
		if (objekt==null) {
			System.out.println("objekt ist null!");
		} else {
			try {
				SAXBuilder saxBuilder = new SAXBuilder();
				Document document = saxBuilder.build(file);
				Element rootElement = document.getRootElement();
				//String rootElementName = rootElement.getName(); // PersonListe
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
					System.out.println("testo!");
					einzelnesElement = new Element("Buchen");
					einzelnesElement.setAttribute(new Attribute("ID", newID));
					einzelnesBodenelement = new Element("Von");
					newValue = ((Buchen)objekt).getVon();
					einzelnesBodenelement.setText(newValue);
					einzelnesElement.addContent(einzelnesBodenelement);
					einzelnesBodenelement = new Element("Bis");
					newValue = ((Buchen)objekt).getBis();
					einzelnesBodenelement.setText(newValue);
					einzelnesElement.addContent(einzelnesBodenelement);
					einzelnesBodenelement = new Element("Personalnummer");
					newValue = ((Buchen)objekt).getPersonalnummer();
					einzelnesBodenelement.setText(newValue);
					einzelnesElement.addContent(einzelnesBodenelement);
					einzelnesBodenelement = new Element("Kennzeichen");
					newValue = ((Buchen)objekt).getKennzeichen();
					einzelnesBodenelement.setText(newValue);
					einzelnesElement.addContent(einzelnesBodenelement);
					einzelnesBodenelement = new Element("Zweck");
					newValue = ((Buchen)objekt).getZweck();
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

	public static String sucheFreiesAuto(String zweck, Calendar neuVon, Calendar neuBis) {
		String kennzeichen="";
		ArrayList<String> zuSuchen = new ArrayList<String>();
		switch (zweck) {
		case "Stadtfahrt":
			zuSuchen.add("Cityflitzer");
			zuSuchen.add("Limousine");
			break;
		case "Langstreckenfahrt":
			zuSuchen.add("Limousine");
			zuSuchen.add("Cityflitzer");
			break;
		case "Transport":
			zuSuchen.add("Lkw");
			zuSuchen.add("Transporter");
		}
		//if (zweck.equals("Stadtfahrt")) { // wenn zweck stadtfahrt ist
			// mache ein array aus vehikel objekten das typliste heißt und die vom typ cityflitzer/Limousine sind
			ArrayList<Object> typListe = xmlUndNameUndWerteLineareSucheZuArrayList(new File("VehicleListe.xml"), "Typ", zuSuchen); //				
			// für jedes element der gefundenen vehikeln
			for (int i=0;i < typListe.size(); i++) {
				// erstell ein array aus buchen objekten, das tempA heißt und kennzeichen wie ein element der liste fahrzeuge hat 
				ArrayList<Object> tempA = xmlUndNameUndWerteLineareSucheZuArrayList(new File("BuchenListe.xml"), "Kennzeichen", new ArrayList<>(asList(((Vehicle) typListe.get(i)).getKennzeichen())));
				if (tempA.isEmpty()) {
					// gehe direkt über los und ziehe 4000 € ein
					// gib die id des autos zurück
					kennzeichen=((Vehicle) typListe.get(i)).getKennzeichen();
					System.out.println("zeile 326: kennzeichen="+kennzeichen);
					break;
				} else {
					for (int j=0; j<tempA.size();j++) {
						Calendar von = Verwaltung.umrechnenZeit(((Buchen)tempA.get(j)).getVon());
						Calendar bis = Verwaltung.umrechnenZeit(((Buchen)tempA.get(j)).getBis());
						  // neuVon>=von              &&neuBis<=bis || 
						if ((neuVon.compareTo(von)>=0)&&neuBis.compareTo(bis)<=0 ||
						  // neuVon>=von              &&neuVon<=bis || 
							(neuVon.compareTo(von)>=0)&&(neuVon.compareTo(bis)<=0)||
							// neuBis<=bis            &&neuBis>=von
							(neuBis.compareTo(bis)<=0)&&(neuBis.compareTo(von)>=0))
							{ 
							// Wenn sich auszuleihende Zeit mit schon bestehender Leihzeit überschneiden
							// Abbruch
							break;
						} else {
							// gib am Ende die id des Autos zurück
							if (j==tempA.size()-1) kennzeichen=((Vehicle) typListe.get(i)).getKennzeichen();
							System.out.println("zeile 345: kennzeichen="+kennzeichen);
						}
					}
				}
			}
		//}
	return kennzeichen;
	}
	
	public static ArrayList<Object> xmlUndNameUndWerteLineareSucheZuArrayList(File file, String zuSuchenderAttributName, ArrayList<String> gesuchteWerte) {
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
					if (einzelnesBodenElementName.equals(zuSuchenderAttributName)) {
						for (int k=0;k<gesuchteWerte.size(); k++) {
							if (einzelnesBodenElementWert.equals(gesuchteWerte.get(k))) {
								listeGefundenerZeilen.add(Integer.toString(i));
								break;
							}
						}
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
				listeGefundenerObjekte = xmlUndNameUndWerteLineareSucheZuArrayList(file, "Vorname", new ArrayList<String>(Arrays.asList(Verwaltung.einlesenText("Vorname:"))));
				break;
			case "n":
				listeGefundenerObjekte = xmlUndNameUndWerteLineareSucheZuArrayList(file, "Nachname", new ArrayList<String>(Arrays.asList(Verwaltung.einlesenText("Nachname:"))));
				break;
			case "p":
				listeGefundenerObjekte = xmlUndNameUndWerteLineareSucheZuArrayList(file, "Personalnummer", new ArrayList<String>(Arrays.asList(Verwaltung.einlesenText("Personalnummer:"))));
				break;
			case "f":
				listeGefundenerObjekte = xmlUndNameUndWerteLineareSucheZuArrayList(file, "Fuehrerschein", new ArrayList<String>(Arrays.asList(Verwaltung.einlesenText("FÜhrerschein:"))));
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
				listeGefundenerObjekte = xmlUndNameUndWerteLineareSucheZuArrayList(file, "Typ", new ArrayList<String>(Arrays.asList(Verwaltung.einlesenText("Fahrzeugtyp:"))));
				break;
			case "k":
				listeGefundenerObjekte = xmlUndNameUndWerteLineareSucheZuArrayList(file, "Kennzeichen", new ArrayList<String>(Arrays.asList(Verwaltung.einlesenText("Kennzeichen:"))));
				break;
			case "l":
				listeGefundenerObjekte = xmlUndNameUndWerteLineareSucheZuArrayList(file, "Geliehen", new ArrayList<String>(Arrays.asList(Verwaltung.einlesenJaNein("Geliehen (j)a oder (n)ein?"))));
				break;
			default:
				System.out.println("falsche Eingabe!");
			}
			break;
		case "b":
			file = new File("BuchenListe.xml");
			System.out.println("Suchen Sie eine Personalnummer(f) oder das Kennzeichen(k)?");
			inputB = scan.nextLine();
			switch (inputB) {
			case "p":
				listeGefundenerObjekte = xmlUndNameUndWerteLineareSucheZuArrayList(file, "Personalnummer", new ArrayList<String>(Arrays.asList(Verwaltung.einlesenText("Personalnummer:"))));
				break;
			case "k":
				listeGefundenerObjekte = xmlUndNameUndWerteLineareSucheZuArrayList(file, "Kennzeichen", new ArrayList<String>(Arrays.asList(Verwaltung.einlesenText("Kennzeichen:")))); 
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