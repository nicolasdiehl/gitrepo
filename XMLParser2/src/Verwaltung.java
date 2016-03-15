import java.util.Scanner;

import org.jdom2.JDOMException;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.IOException;

abstract public class Verwaltung {
	public static void main(String[] args) throws JDOMException, IOException {
		System.out.println("Hello World");

		ConnectXMLAusleihe.einfügenAusleihvorgang("999", "9099");
		ConnectXMLAusleihe.einfügenAusleihvorgang("11111111", "9099");
		ConnectXMLFahrzeug.einfügenFahrzeug("Jaguar", "E-Type", "minimal");

		String temp = ConnectXMLAusleihe.createUniqueID("Fuhrpark.xml");
		System.out.println(temp);
		ConnectXMLFahrzeug.readFahrzeugListe();
		ConnectXMLMitarbeiter.readMitarbeiterListe();
		ConnectXMLAusleihe.readAusleihe();

		Ausleihe Ausleihetemp = Verwaltung.einlesenAusleihvorgang();
		ConnectXMLAusleihe.einfügenAusleihvorgang(Ausleihetemp.getMitarbeiterID(), Ausleihetemp.getFahrzeugID());
		ConnectXMLAusleihe.einfügenAusleihvorgang("999", "000");
		System.out.println("Ausgabe nach Bestellvorgang");
		ConnectXMLAusleihe.readAusleihe();

		Calendar cal = Calendar.getInstance();
		cal.set(1990, 03, 14); // set(int year, int month, int date)
		Mitarbeiter einMitarbeiter = new Mitarbeiter("Schmidt", "Maria", "Berlin", "B", cal);

		// erzeugen von Objekten der Klasse Fahrzeug bzw. Pkw
		Fahrzeug meinFahrzeug = einlesenFahrzeug();
		// Ausgabebeispiel
	}
	public static Fahrzeug einlesenFahrzeug(){
		
		return einlesenFahrzeug();
	}
	public static Ausleihe einlesenAusleihvorgang() {
		System.out.println("______________");
		System.out.println("Hier starten Sie den Ausleihvorgang");
		String MitarbeiterID = einlesenText("MitarbeiterID:");
		String FahrzeugID = einlesenText("FahrzeugID: ");
		Ausleihe ausleihe = new Ausleihe(MitarbeiterID, FahrzeugID);
		return ausleihe;
	}

	public static String einlesenText(String eingabewert) {
		String inData;
		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert);
		inData = scan.nextLine();
		return inData;
	}

	public static int einlesenZahl(String eingabewert) {
		String inData;
		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert);
		inData = scan.nextLine();
		return Integer.parseInt(inData);
	}

	public static Boolean einlesenBool(String eingabewert) {
		String inData;
		Boolean fehler = false;

		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert + "(j für ja, n für nein) ");

		do {
			inData = scan.nextLine();

			if (inData.equals("j")) {
				fehler = false;
				return true;
			} else {
				if (inData.equals("n")) {
					fehler = false;
					return false;
				} else {
					System.out.println("Ihre Eingabe war: " + inData + "Dies ist keine gültige Eingabe!");
					fehler = true;
				}
			}
		} while (fehler); // Wurde weder j noch n eingegeben

		System.out.println("Fehlerhafte Eingabe, kommt das Programm hier hin, ist richtig was schief gelaufen!!!");
		return false;
	}
}