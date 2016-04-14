package defaultxml;
import java.util.Scanner;

import org.jdom2.JDOMException;

import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;

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
		ConnectXMLPerson.einf�genPerson(tempPerson.getNachname(), tempPerson.getVorname(),
				tempPerson.getFuehrerschein(), tempPerson.getPersonalnummer());
		ConnectXMLPerson.readPersonListe();

		Vehicle tempVehicle = einlesenVehicle();
		ConnectXMLVehicle.einf�genVehicle(tempVehicle.getTyp(),tempVehicle.getGeliehen(), tempVehicle.getKennzeichen());
		ConnectXMLVehicle.readVehicleListe();

		Ausleihe tempAusleihe = einlesenAusleihvorgang();
		ConnectXMLAusleihe.einf�genAusleihvorgang(tempAusleihe.getKennzeichen(), tempAusleihe.getPersonID(),
				tempAusleihe.getLeihzweck(), tempAusleihe.getLeihbeginn(), tempAusleihe.getLeihende());
		ConnectXMLAusleihe.readAusleihe();

		SuchBaumBSP.Suche();
	}

	public static Person einlesenPerson() {
		String idtemp=null;
		try {
			idtemp = ConnectXML.createUniqueID("PersonListe.xml");
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = idtemp;
		String vorname = einlesenText("Vorname: ");
		String nachname = einlesenText("Nachname: ");
		String fuehrerschein = einlesenText("F�hrerschein: ");
		String personalnummer = einlesenText("Personalnummer: ");
		Person neuPerson = new Person(id, nachname, vorname, fuehrerschein, personalnummer);
		return neuPerson;
	}

	public static Vehicle einlesenVehicle() {

		String typ = einlesenText("Typ: ");
		String geliehen = einlesenText("Geliehen: ");
		String zweck = einlesenText("Zweck: ");
		String kennzeichen = einlesenText("Kennzeichen: ");
		Vehicle neuesVehicle = new Vehicle(typ, geliehen, kennzeichen);
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
					System.out.println("Ihre Eingabe war: " + inData + "Das ist eine ung�ltige Eingabe!");
					fehler = true;
				}
			}
		} while (fehler); // Wurde weder j noch n eingegeben

		System.out.println("Fehlerhafte Eingabe! Versuchen Sie es noch einmal!");
		return false;
	}
}