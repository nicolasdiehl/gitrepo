import java.util.Scanner;

import org.jdom2.JDOMException;
import java.util.Calendar;
import java.io.IOException;

public class Verwaltung {
	public static void main(String[] args) throws JDOMException, IOException {

		Calendar cal = Calendar.getInstance();

		// Die Monate werden mit 0 (= Januar) beginnend gezaehlt!
		// (Die Tage im Monat beginnen dagegen mit 1)
		System.out.println("Datum: " + cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.get(Calendar.MONTH) + 1) + "."
				+ cal.get(Calendar.YEAR));

		System.out.println("Uhrzeit: " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE));

		// ConnectXMLVehicle.sucheTyp();
		XMLMaker.createXML();
		Person tempPerson = einlesenPerson();
		ConnectXMLPerson.einfügenPerson(tempPerson.getNachname(), tempPerson.getVorname(),
				tempPerson.getFuehrerschein(), tempPerson.getPersNr());
		ConnectXMLPerson.readPersonListe();

		Vehicle tempVehicle = einlesenVehicle();
		ConnectXMLVehicle.einfügenVehicle(tempVehicle.getTyp(), tempVehicle.isGeliehen(),
				tempVehicle.getZweck(), tempVehicle.getKennzeichen());
		ConnectXMLVehicle.readVehicleListe();

		Ausleihe tempAusleihe = einlesenAusleihvorgang();
		ConnectXMLAusleihe.einfügenAusleihvorgang(tempAusleihe.getKennzeichen(), tempAusleihe.getPersonID(),
				tempAusleihe.getLeihzweck(), tempAusleihe.getLeihbeginn(), tempAusleihe.getLeihende());
		ConnectXMLAusleihe.readAusleihe();

		SuchBaumBSP.Suche();
	}

	public static Person einlesenPerson() {
		String vorname = einlesenText("Vorname: ");
		String nachname = einlesenText("Nachname: ");
		String fuehrerschein = einlesenText("Führerschein: ");
		String personalnummer = einlesenText("Personalnummer: ");
		Person neuerPerson = new Person(nachname, vorname, fuehrerschein, personalnummer);
		return neuerPerson;
	}

	public static Vehicle einlesenVehicle() {

		String typ = einlesenText("Typ: ");
		boolean geliehen = einlesenBool("Geliehen: ");
		String zweck = einlesenText("Zweck: ");
		String kennzeichen = einlesenText("Kennzeichen: ");
		Vehicle neuesVehicle = new Vehicle(typ, geliehen, zweck, kennzeichen);
		return neuesVehicle;
	}

	public static Ausleihe einlesenAusleihvorgang() {
		System.out.println("______________");
		System.out.println("Hier starten Sie den Ausleihvorgang");
		String personID = einlesenText("PersonID: ");
		String kennzeichen = einlesenText("Kennzeichen: ");
		String leihzweck = einlesenText("Leihzweck: ");
		String leihbeginn = einlesenText("Leihbeginn: ");
		String leihende = einlesenText("Leihende: ");

		Ausleihe neueAusleihe = new Ausleihe(personID, kennzeichen, leihzweck, leihbeginn, leihende);
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