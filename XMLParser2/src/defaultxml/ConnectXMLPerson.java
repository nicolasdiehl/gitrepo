package defaultxml;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import java.util.Scanner;

public class ConnectXMLPerson extends ConnectXML {

	public static List<Element> readPersonListe() {
		List<Element> personList = null;
		try {
			File inputFile = new File("PersonListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			System.out.println("Root element :" + document.getRootElement().getName());
			Element classElement = document.getRootElement();

			personList = classElement.getChildren();
			System.out.println("----------------------------");

			for (int temp = 0; temp < personList.size(); temp++) {
				Element person = personList.get(temp);
				System.out.println("\n Current Element :" + person.getName());
				Attribute attribute = person.getAttribute("ID");
				System.out.println("Zeile Nummer:\t" + attribute.getValue());
				System.out.println("Nachname:\t" + person.getChild("Nachname").getText());
				System.out.println("Vorname:\t" + person.getChild("Vorname").getText());
				System.out.println("Fuehrerschein:\t" + person.getChild("Fuehrerschein").getText());
				System.out.println("PersNr:\t" + person.getChild("Personalnummer").getText());
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return personList;
	}

	public static void einfügenPerson(String vname, String nname, String fschein, String personalnummer)
			throws JDOMException {
		try {
			File inputFile = new File("PersonListe.xml"); // Zugriff auf
																// XML Datei
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);

			String tempID = createUniqueID(new File("PersonListe.xml"));
			Element nPerson = new Element("Person");
			nPerson.setAttribute(new Attribute("ID", tempID));
			Element mNachname = new Element("Nachname");
			mNachname.setText(nname);
			Element mVorname = new Element("Vorname");
			mVorname.setText(vname);
			Element mFuehrerschein = new Element("Fuehrerschein");
			mFuehrerschein.setText(fschein);
			Element mPersNr = new Element("PersNr");
			mPersNr.setText(Objects.toString(personalnummer, null));

			nPerson.addContent(mNachname);
			nPerson.addContent(mVorname);
			nPerson.addContent(mFuehrerschein);
			nPerson.addContent(mPersNr);

			document.getRootElement().addContent(nPerson);

			XMLOutputter xmlOutput = new XMLOutputter();

			// display xml
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(inputFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void lineareSuche() {
		int suchzahl = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welche Personalnummer suchen Sie?");
		suchzahl = scan.nextInt();
		List<Element> personList1 = null;
		try {
			File inputFile = new File("PersonListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			Element classElement = document.getRootElement();
			personList1 = classElement.getChildren();
			for (int temp = 0; temp < personList1.size(); temp++) {
				Element person1 = personList1.get(temp);
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