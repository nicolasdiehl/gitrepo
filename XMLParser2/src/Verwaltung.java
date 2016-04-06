import java.util.Scanner;

import org.jdom2.JDOMException;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.io.IOException;

public class Verwaltung {
	public static void main(String[] args) throws JDOMException, IOException {

		Calendar cal = Calendar.getInstance();

		// Die Monate werden mit 0 (= Januar) beginnend gezaehlt!
		// (Die Tage im Monat beginnen dagegen mit 1)
		String testDatum = cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1) + "."
				+ cal.get(Calendar.YEAR);
		System.out.println(testDatum);
		System.out.println("Datum: " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1) + "."
				+ cal.get(Calendar.YEAR));

		System.out.println("Uhrzeit: " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE));

		Mitarbeiter tempMitarbeiter = einlesenMitarbeiter();
		ConnectXMLMitarbeiter.einfügenMitarbeiter(tempMitarbeiter.getNachname(), tempMitarbeiter.getVorname(),
				tempMitarbeiter.isFuehrerschein(), tempMitarbeiter.getPersNr());
		ConnectXMLMitarbeiter.readMitarbeiterListe();

		Fahrzeug tempFahrzeug = einlesenFahrzeug();
		ConnectXMLFahrzeug.einfügenFahrzeug(tempFahrzeug.getFahrzeugtyp(), tempFahrzeug.isGeliehen(),
				tempFahrzeug.getZweck(), tempFahrzeug.getKennzeichen());
		ConnectXMLFahrzeug.readFahrzeugListe();

		Ausleihe tempAusleihe = einlesenAusleihvorgang();
		ConnectXMLAusleihe.einfügenAusleihvorgang(tempAusleihe.getKennzeichen(), tempAusleihe.getMitarbeiterID(),
				tempAusleihe.getLeihbeginn(), tempAusleihe.getLeihende());
		ConnectXMLAusleihe.readAusleihe();
	}

	public static Mitarbeiter einlesenMitarbeiter() {
		String nachname = einlesenText("Nachname: ");
		String vorname = einlesenText("Vorname: ");
		boolean fuehrerschein = einlesenBool("Führerschein: ");
		long persNr = einlesenLong("Personalnummer: ");
		Mitarbeiter neuerMitarbeiter = new Mitarbeiter(nachname, vorname, fuehrerschein, persNr);
		return neuerMitarbeiter;
	}

	public static Fahrzeug einlesenFahrzeug() {

		String fahrzeugtyp = einlesenText("Fahrzeugtyp: ");
		boolean geliehen = einlesenBool("Geliehen: ");
		String zweck = einlesenText("Zweck: ");
		String kennzeichen = einlesenText("Kennzeichen: ");
		Fahrzeug neuesFahrzeug = new Fahrzeug(fahrzeugtyp, geliehen, zweck, kennzeichen);
		return neuesFahrzeug;
	}

	public static Ausleihe einlesenAusleihvorgang() {
		System.out.println("______________");
		System.out.println("Hier starten Sie den Ausleihvorgang");
		String mitarbeiterID = einlesenText("MitarbeiterID: ");
		String kennzeichen = einlesenText("Kennzeichen: ");
		String leihbeginn = einlesenText("Leihbeginn: ");
		String leihende = einlesenText("Leihende: ");

		Ausleihe neueAusleihe = new Ausleihe(mitarbeiterID, kennzeichen, leihbeginn, leihende);
		return neueAusleihe;
	}

	public static String einlesenDatum(String eingabewert) {
		String inData;
		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert);
		inData = scan.nextLine();
		return inData;
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
					System.out.println("Ihre Eingabe war: " + inData + "Das ist eine ungültige Eingabe!");
					fehler = true;
				}
			}
		} while (fehler); // Wurde weder j noch n eingegeben

		System.out.println("Fehlerhafte Eingabe! Versuchen Sie es noch einmal!");
		return false;
	}
}