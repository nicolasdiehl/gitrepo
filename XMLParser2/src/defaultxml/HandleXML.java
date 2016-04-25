package defaultxml;
import static java.util.Arrays.asList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import javafx.beans.property.SimpleStringProperty;
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
	
	public static ArrayList<Object> xmlZuArrayList(File file, boolean output) {
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
				if (output) System.out.println("ID: " + einzelnesElementIdWert);
				for (int j = 0; j < listeDerBodenElemente.size(); j++) {
					Element einzelnesBodenElement = listeDerBodenElemente.get(j);
					String einzelnesBodenElementName = einzelnesBodenElement.getName();
					String einzelnesBodenElementWert = einzelnesBodenElement.getText();
					if (output) System.out.println(einzelnesBodenElementName + ":\t" + einzelnesBodenElementWert);
					tempWerte.add(einzelnesBodenElementWert);
				}
				if (output) System.out.println("");
				if (rootElement.getName().equals("PersonListe")) fArray.add(new Person(einzelnesElementIdWert, tempWerte.get(0),tempWerte.get(1),tempWerte.get(2), tempWerte.get(3)));
				else if (rootElement.getName().equals("VehicleListe")) fArray.add(new Vehicle(einzelnesElementIdWert, tempWerte.get(0),tempWerte.get(1),tempWerte.get(2)));
				else fArray.add(new Buchen(einzelnesElementIdWert, tempWerte.get(0), tempWerte.get(1), tempWerte.get(2), tempWerte.get(3), tempWerte.get(4), tempWerte.get(5), tempWerte.get(6), tempWerte.get(7), tempWerte.get(8), tempWerte.get(9)));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return fArray;
	}

	public static void arrayListZuXml(ArrayList<Object> arrayList, File file, String typ) {
		if (file.toString().equals("")) { // leer, standard-filename nehmen
			if (typ.equals("Person")) {
				System.out.println("PersonListe.xml wird verwendet");
				file = new File("PersonListe.xml");
			} else if (typ.equals("Vehicle")) {
				System.out.println("VehicleListe.xml wird verwendet");
				file = new File("VehicleListe.xml");  
			} else {
				System.out.println("BuchenListe.xml wird verwendet");
				file = new File("BuchenListe.xml");   
			}
		}
		if (true/*!file.isFile()*/) { // wenn file nicht existiert
			Element rootElement;
			if (typ.equals("Person")) {
				rootElement = new Element("PersonListe");  
			} else if (typ.equals("Vehicle")) {
				rootElement = new Element("VehicleListe"); 
			} else {
				rootElement = new Element("BuchenListe"); 
			}
			Document document = new Document(rootElement);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			try {
				Files.deleteIfExists(file.toPath());
			} catch (IOException e) {
				System.out.println("delete failed");
				e.printStackTrace();
			}
			try {
				xmlOutput.output(document, new FileWriter(file.toString()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i=0;i<arrayList.size();i++) {
			try {
				inXmlAnhängen(file, arrayList.get(i));
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				String newID = createUniqueID(file);
				Element einzelnesElement = null;
				String newValue;
				Element einzelnesBodenelement;
				List<String> elements;
				List<String> values;
				if (objekt instanceof Person) {
					einzelnesElement = new Element("Person");
					elements = new ArrayList<>(Arrays.asList("Nachname", "Vorname", "Fuehrerschein", "Personalnummer"));
					values = new ArrayList<>(Arrays.asList(((Person)objekt).getNachname(),((Person)objekt).getVorname(),((Person)objekt).getFuehrerschein(),((Person)objekt).getPersonalnummer()));
				}
				else if (objekt instanceof Vehicle) {
					einzelnesElement = new Element("Vehicle");
					elements = new ArrayList<>(Arrays.asList("Typ", "Geliehen"));
					values = new ArrayList<>(Arrays.asList(((Vehicle)objekt).getTyp(),((Vehicle)objekt).getGeliehen()));
					
				}
				else {
					einzelnesElement = new Element("Buchen");
					elements = new ArrayList<>(Arrays.asList("Nachname", "Vorname","Personalnummer","Fuehrerschein","Kennzeichen","Typ","Zweck","Von","Bis", "Dauer"));
					values = new ArrayList<>(Arrays.asList(((Buchen)objekt).getNachname(),((Buchen)objekt).getVorname(),((Buchen)objekt).getPersonalnummer(),((Buchen)objekt).getFuehrerschein(),((Buchen)objekt).getKennzeichen(),((Buchen)objekt).getTyp(),((Buchen)objekt).getZweck(),((Buchen)objekt).getVon(),((Buchen)objekt).getBis(),((Buchen)objekt).getDauer()));
				}
				einzelnesElement.setAttribute(new Attribute("ID", newID));
				for (int i=0; i<elements.size();i++) {
					einzelnesBodenelement = new Element(elements.get(i));
					einzelnesBodenelement.setText(values.get(i));
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

	public static Vehicle sucheFreiesAutoXml(String zweck, Calendar neuVon, Calendar neuBis) {
		Vehicle vehicle=null;
		ArrayList<String> zuSuchen = new ArrayList<String>();
		switch (zweck) {
		case "Stadtfahrt":
			System.out.println("stadtfahrt, also city oder limo");
			zuSuchen.add("Cityflitzer");
			zuSuchen.add("Limousine");
			break;
		case "Langstreckenfahrt":
			System.out.println("langfahrt, also limo oder city");
			zuSuchen.add("Limousine");
			zuSuchen.add("Cityflitzer");
			break;
		case "Transport":
			System.out.println("transport, also lkw oder transp");
			zuSuchen.add("Lkw");
			zuSuchen.add("Transporter");
		}
		System.out.println("mache ein array aus vehikel objekten die vom typ sind der gesucht wird");
			ArrayList<Object> typListe = xmlUndNameUndWerteLineareSucheZuArrayList(new File("VehicleListe.xml"), "Typ", zuSuchen); //				
			// für jedes element der gefundenen vehikeln
			System.out.println("für jedes gefundene vehicle/objekt mit dem richtigen typ");
			for (int i=0;i < typListe.size(); i++) {
				System.out.println("mache ein array aus buchen objekten, die das selbe kennzeichen haben wie das objekt");
				//Vehicle a = (Vehicle) typListe.get(i);
				//String ke = a.getKennzeichen();
				System.out.println("nämlich"+new ArrayList<>(asList(((Vehicle) typListe.get(i)).getKennzeichen())));
				System.out.println("weiter zu xmlUndNameUndWerteLineareSucheZuArrayList");
				ArrayList<Object> tempA = xmlUndNameUndWerteLineareSucheZuArrayList(new File("BuchenListe.xml"), "Kennzeichen", new ArrayList<>(asList(((Vehicle) typListe.get(i)).getKennzeichen())));
				System.out.println("arraylist erfolgreich gespeichert");
				if (tempA.isEmpty()) {
					// gehe direkt über los und ziehe 4000 € ein
					// gib die id des autos zurück
					vehicle=(Vehicle) typListe.get(i);
					System.out.println("zeile 326: vehicleid="+vehicle.getId());
					break;
				} else {
					for (int j=0; j<tempA.size();j++) {
						System.out.println("für jedes buchen element fürs kennzeichen, runde "+j);
						Calendar von = Verwaltung.umrechnenZeit(((Buchen)tempA.get(j)).getVon());
						Calendar bis = Verwaltung.umrechnenZeit(((Buchen)tempA.get(j)).getBis());
						  // neuVon>=von              &&neuBis<=bis || 
						if ((neuVon.compareTo(von)>=0)&&neuBis.compareTo(bis)<=0 ||
						  // neuVon>=von              &&neuVon<=bis || 
							(neuVon.compareTo(von)>=0)&&(neuVon.compareTo(bis)<=0)||
							// neuBis<=bis            &&neuBis>=von
							(neuBis.compareTo(bis)<=0)&&(neuBis.compareTo(von)>=0))
							{ 
							System.out.println("Wenn sich auszuleihende Zeit mit schon bestehender Leihzeit überschneiden: Abbruch");
							break;
						} else {
							// gib am Ende die id des Autos zurück
							if (j==tempA.size()-1) vehicle=(Vehicle) typListe.get(i);
							System.out.println("zeile 397: gib vehicle zurück, id="+vehicle.getId());
						}
					}
				}
			}
		//}
			System.out.println("ende sucheauto");
	return vehicle;
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
				if (rootElement.getName().equals("PersonListe")) newItem = new Person(einzelnesElementIdWert,tempWerte.get(0),tempWerte.get(1),tempWerte.get(2), tempWerte.get(3));
				else if (rootElement.getName().equals("VehicleListe")) newItem = new Vehicle(einzelnesElementIdWert, tempWerte.get(0),tempWerte.get(1),tempWerte.get(2));
				else  newItem = new Buchen(einzelnesElementIdWert,tempWerte.get(0), tempWerte.get(1), tempWerte.get(2), tempWerte.get(3), tempWerte.get(4), tempWerte.get(5), tempWerte.get(6), tempWerte.get(7), tempWerte.get(8), tempWerte.get(9));
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
		System.out.println("zeilennummer zum hinzufügen: "+zeilenNummer);
		System.out.println("xmlUndZeileZuObjekt, in file:"+file);
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
		else if (rootElement.getName().equals("BuchenListe")) {
			System.out.printf("handlexml zeile 124: id %s\n",einzelnesElementIdWert);
			for (int i=0;i<tempWerte.size();i++) {
				System.out.print(tempWerte.get(i)+" ");
			}
			System.out.printf("\n");
			newItem = new Buchen(einzelnesElementIdWert, tempWerte.get(0), tempWerte.get(1), tempWerte.get(2), tempWerte.get(3), tempWerte.get(4), tempWerte.get(5), tempWerte.get(6), tempWerte.get(7), tempWerte.get(8), tempWerte.get(9));
			}
		else {
			System.out.println("Fehler, Liste nicht richtig.");
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


	
	public static ArrayList<Object> xmlUndNameUndWerteLineareSucheZuArrayList(File file, String zuSuchenderAttributName, ArrayList<String> gesuchteWerte) {
		ArrayList<String> listeGefundenerZeilen = new ArrayList<String>();
		ArrayList<Object> listeGefundenerObjekte = new ArrayList<Object>();
		System.out.println("xmlUndNameUndWerteLineareSucheZuArrayList, gesuchte werte:");
		for (int i = 0;i<gesuchteWerte.size();i++) {
			System.out.println(gesuchteWerte.get(i));
		}
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(file);
			Element rootElement = document.getRootElement();
			List<Element> listeDerElemente = rootElement.getChildren();
			for (int i = 0; i < listeDerElemente.size(); i++) { // liste aller personen/vehicle usw
				Element einzelnesElement = listeDerElemente.get(i);
				List<Element> listeDerBodenElemente = einzelnesElement.getChildren();
				for (int j = 0; j < listeDerBodenElemente.size(); j++) {
					//System.out.println("schleifenaufruf mit j="+j);
					Element einzelnesBodenElement = listeDerBodenElemente.get(j);
					String einzelnesBodenElementName = einzelnesBodenElement.getName();
					String einzelnesBodenElementWert = einzelnesBodenElement.getText();
					if (einzelnesBodenElementName.equals(zuSuchenderAttributName)) {
						//System.out.println("einzelnesBodenElementName: "+einzelnesBodenElementName+" ist gleich zu zuSuchenderAttributName: "+zuSuchenderAttributName);
						for (int k=0;k<gesuchteWerte.size(); k++) {
							if (einzelnesBodenElementWert.equals(gesuchteWerte.get(k))) {
								listeGefundenerZeilen.add(Integer.toString(i));
								//System.out.println("also gefundene zeile:"+Integer.toString(i)+"wird hinzugefügt");
								break;
							}
						}
						break;
					}
				}
			}
			for (int j = 0; j < listeGefundenerZeilen.size(); j++) {
				System.out.println("ein gefundenes Objekt wird hinzugefügt, listenplatz:"+j+"zeileinfile:"+listeGefundenerZeilen.get(j));
				listeGefundenerObjekte.add(xmlUndZeileZuObjekt(file, listeGefundenerZeilen.get(j)));
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		System.out.println("liste wird zurückgegeben");
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
}