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

public class ConnectXMLMitarbeiter extends ConnectXML {

	public static List<Element> readMitarbeiterListe() {
		List<Element> mitarbeiterList = null;
		try {
			File inputFile = new File("MitarbeiterListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			System.out.println("Root element :" + document.getRootElement().getName());
			Element classElement = document.getRootElement();

			mitarbeiterList = classElement.getChildren();
			System.out.println("----------------------------");

			for (int temp = 0; temp < mitarbeiterList.size(); temp++) {
				Element mitarbeiter = mitarbeiterList.get(temp);
				System.out.println("\n Current Element :" + mitarbeiter.getName());
				Attribute attribute = mitarbeiter.getAttribute("ID");
				System.out.println("Zeile Nummer:\t" + attribute.getValue());
				System.out.println("Nachname:\t" + mitarbeiter.getChild("Nachname").getText());
				System.out.println("Vorname:\t" + mitarbeiter.getChild("Vorname").getText());
				System.out.println("Fuehrerschein:\t" + mitarbeiter.getChild("Fuehrerschein").getText());
				System.out.println("PersNr:\t" + mitarbeiter.getChild("PersNr").getText());
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return mitarbeiterList;
	}

	public static void einfügenMitarbeiter(String nname, String vname, boolean fschein, long persNr)
			throws JDOMException {
		try {
			File inputFile = new File("MitarbeiterListe.xml"); // Zugriff auf
																// XML Datei
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);

			String tempID = createUniqueID("MitarbeiterListe.xml");
			Element nMitarbeiter = new Element("Mitarbeiter");
			nMitarbeiter.setAttribute(new Attribute("ID", tempID));
			Element mNachname = new Element("Nachname");
			mNachname.setText(nname);
			Element mVorname = new Element("Vorname");
			mVorname.setText(vname);
			Element mFuehrerschein = new Element("Fuehrerschein");
			mFuehrerschein.setText(Objects.toString(fschein));
			Element mPersNr = new Element("PersNr");
			mPersNr.setText(Objects.toString(persNr, null));

			nMitarbeiter.addContent(mNachname);
			nMitarbeiter.addContent(mVorname);
			nMitarbeiter.addContent(mFuehrerschein);
			nMitarbeiter.addContent(mPersNr);

			document.getRootElement().addContent(nMitarbeiter);

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
		List<Element> mitarbeiterList1 = null;
		try {
			File inputFile = new File("MitarbeiterListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			Element classElement = document.getRootElement();
			mitarbeiterList1 = classElement.getChildren();
			for (int temp = 0; temp < mitarbeiterList1.size(); temp++) {
				Element mitarbeiter1 = mitarbeiterList1.get(temp);
				Element attribute1 = mitarbeiter1.getChild("PersNr");
				int persNr = Integer.parseInt(attribute1.getValue());
				if (persNr == suchzahl) {
					System.out.println("\nElement:	   " + mitarbeiter1.getName());
					System.out.println("Nachname:          " + mitarbeiter1.getChild("Nachname").getText());
					System.out.println("Vorname:           " + mitarbeiter1.getChild("Vorname").getText());
					System.out.println("Fuehrerschein:     " + mitarbeiter1.getChild("Fuehrerschein").getText());
					System.out.println("PersNr:            " + mitarbeiter1.getChild("PersNr").getText());
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}