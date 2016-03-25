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

public class Verwaltung {
	public static void main(String[] args) throws JDOMException, IOException {
		// Calendar cal = Calendar.getInstance();
		// cal.set(1990, 03, 14); // set(int year, int month, int date)
		System.out.println("Hello World");

		Mitarbeiter tempMitarbeiter = einlesenMitarbeiter();
		ConnectXMLMitarbeiter.einf�genMitarbeiter(tempMitarbeiter.getNachname(), tempMitarbeiter.getVorname(),
				tempMitarbeiter.isFuehrerschein(), tempMitarbeiter.getPersNr());
	}

	public static Mitarbeiter einlesenMitarbeiter() {
		String nachname = einlesenText("Nachname: ");
		String vorname = einlesenText("Vorname: ");
		boolean fuehrerschein = einlesenBool("F�hrerschein: ");
		long persNr = einlesenLong("Personalnummer: ");
		Mitarbeiter neuerMitarbeiter = new Mitarbeiter(nachname, vorname, fuehrerschein, persNr);
		return neuerMitarbeiter;
	}

	public static Fahrzeug einlesenFahrzeug() {
		Fahrzeug neuesFahrzeug = new Fahrzeug("Cityflitzer", false, "Stadtfahrt", "X-XXXX");
		// eingabe fehlt
		String fahrzeugtyp = einlesenText("Fahrzeugtyp: ");
		boolean ausgeliehen = false;
		String zweck = einlesenText("Zweck: ");
		String kennzeichen = einlesenText("Kennzeichen: ");
		return neuesFahrzeug;
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

	public static int einlesenInt(String eingabewert) {
		String inData;
		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert);
		inData = scan.nextLine();
		return Integer.parseInt(inData);
	}

	public static long einlesenLong(String eingabewert) {
		String inData;
		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert);
		inData = scan.nextLine();
		return Long.parseLong(inData);
	}

	public static Boolean einlesenBool(String eingabewert) {
		String inData;
		Boolean fehler = false;

		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert + "(j f�r ja, n f�r nein) ");

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
					System.out.println("Ihre Eingabe war: " + inData + "Dies ist keine g�ltige Eingabe!");
					fehler = true;
				}
			}
		} while (fehler); // Wurde weder j noch n eingegeben

		System.out.println("Fehlerhafte Eingabe, kommt das Programm hier hin, ist richtig was schief gelaufen!!!");
		return false;
	}
}