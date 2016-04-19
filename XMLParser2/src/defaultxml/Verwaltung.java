package defaultxml;
import java.util.Scanner;

import org.jdom2.JDOMException;

import ch.makery.address.model.Booking;
import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.IOException;

public class Verwaltung {
	public static void main(String[] args) throws JDOMException, IOException {
		// Monate beginnen mit 0
		// Tage im Monat beginnen mit 1
		
		Scanner scan = new Scanner(System.in);
		String eingabe;
		File personFile = new File("PersonListe.xml");
		File vehicleFile = new File("VehicleListe.xml");
		File bookingFile = new File("BookingListe.xml");
		//XMLMaker.createXML();
		System.out.println("Was darfs denn heute sein?");
		while (true) {
			System.out.println("a: Ausleihvorgang");
			System.out.println("0,1,2: Alle Personen,Wägen,Buchungen ausgeben");
			System.out.println("3,4,5: Neue Person,Wagen,Buchung anlegen");
			System.out.println("6,7,8: Person,Wagen,Buchung verändern");
			System.out.println("s: Person,Wagen,Buchung suchen");
			System.out.println("x,y,z: Person,Wagen,Buchung XML speichern");
			eingabe = scan.nextLine();
			switch (eingabe) {
			case "a":
				einlesenBooking();
				break;
			case "0":
				HandleXML.xmlZuArrayList(personFile);
				break;
			case "1":
				HandleXML.xmlZuArrayList(vehicleFile);
				break;
			case "2":
				HandleXML.xmlZuArrayList(bookingFile);
				break;
			case "3":
				HandleXML.inXmlAnhängen(personFile, einlesenPerson());
				break;
			case "4":
				HandleXML.inXmlAnhängen(vehicleFile, einlesenVehicle());
				break;
			case "5":
				HandleXML.inXmlAnhängen(bookingFile, einlesenBooking());
				break;
			case "6":
				HandleXML.
				break;
			case "7":
				
				break;
			case "8":
				
				break;
			case "s":
				
				break;
			case "x":
				
				break;
			case "y":
				
				break;
			case "z":
				
				break;
			default:
				System.out.println("ciao.");
			}
		}
		
		
		//Person tempPerson = einlesenPerson();
		//HandleXML.inXmlAnhängen(personFile, tempPerson);
		//HandleXML.xmlZuArrayList(personFile);

		/*Vehicle tempVehicle = einlesenVehicle();
		ConnectXMLVehicle.einfügenVehicle(tempVehicle.getTyp(),tempVehicle.getGeliehen(), tempVehicle.getKennzeichen());
		ConnectXMLVehicle.readVehicleListe();

		Ausleihe tempAusleihe = einlesenAusleihvorgang();
		ConnectXMLAusleihe.einfügenAusleihvorgang(tempAusleihe.getKennzeichen(), tempAusleihe.getPersonID(),
				tempAusleihe.getLeihzweck(), tempAusleihe.getLeihbeginn(), tempAusleihe.getLeihende());
		ConnectXMLAusleihe.readAusleihe();

<<<<<<< HEAD
=======
		SuchBaumBSP.Suche();*/
		
>>>>>>> refs/remotes/origin/ohne_fxml_jdom2versuch
	}

	public static Person einlesenPerson() {
		String idtemp=null;
		try {
			File file = new File("PersonListe.xml");
			idtemp = ConnectXML.createUniqueID(file);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = idtemp;
		String vorname = einlesenText("Vorname: ");
		String nachname = einlesenText("Nachname: ");
		String fuehrerschein = einlesenText("Führerschein: ");
		String personalnummer = einlesenText("Personalnummer: ");
		Person neuPerson = new Person(id, vorname, nachname, fuehrerschein, personalnummer);
		return neuPerson;
	}

	public static Vehicle einlesenVehicle() {

		String typ = einlesenText("Typ: ");
		String geliehen = "nein";
		String kennzeichen = einlesenText("Kennzeichen: ");
		Vehicle neuesVehicle = new Vehicle(typ, geliehen, kennzeichen);
		return neuesVehicle;
	}

	public static Booking einlesenBooking() {
		String personalnummer = einlesenText("Personalnummer: ");
		if (HandleXML.xmlUndNameUndWertLineareSucheZuArrayList(new File("PersonList.xml"), "Personalnummer", personalnummer) == null) {
			System.out.println("Personalnummer nicht vorhanden!");
		} else {
			String zweck = einlesenText("Zweck: ((t)ransport, (s)tadtfahrt, (l)angstreckenfahrt)");
			if (!hatFahrerlaubnis(personalnummer, zweck)) {
				System.out.println("Abgelehnt!");
			} else {
				String strVon = einlesenText("Von wann? ss:mm tt.mm.jjjj");
				String strBis = einlesenText("Bis wann? ss:mm tt.mm.jjjj");
				Calendar calVon = umrechnenZeit(strVon);
				Calendar calBis = umrechnenZeit(strBis);
				if (calVon.compareTo(Calendar.getInstance())<0) { // anfangs vor jetzt
					System.out.println("Falsche Eingabe!");
				} else if (calBis.compareTo(calVon)<0){
					System.out.println("Falsche Eingabe!");
				} else {
					ArrayList<Vehicle> auswahl = HandleXML.
				}
					// checken ob es ein freies auto gibt
					// checken ob das auto den richtigen typ hat
					// calendar in string umwandeln
				}
				
			}
		}
		

		String kennzeichen = einlesenText("Kennzeichen: ");

		Booking neueAusleihe = new Booking(von, bis, personalnummer, kennzeichen, zweck);
		return neueAusleihe;
	}

	/*public static String einlesenDatum(String eingabewert) {
		String inData;
		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert);
		inData = scan.nextLine();
		return inData;
	}*/

	public static String einlesenText(String eingabewert) {
		String inData;
		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert);
		inData = scan.nextLine();
		return inData;
	}

	/*public static int einlesenInt(String eingabewert) {
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
	}*/

	public static String einlesenJaNein(String eingabewert) {
		String eingabe;
		String rueck = "nein";

		Scanner scan = new Scanner(System.in);
		System.out.println(eingabewert + "(j für ja, n für nein) ");
		eingabe = scan.nextLine();
		if (eingabe.equals("j")) {
				rueck = "ja";
		}
		return rueck;
	}
	
	public static Calendar umrechnenZeit(String string) {
		int stunde = string.charAt(0)+string.charAt(1);
		int minute = string.charAt(3)+string.charAt(4);
		int tag = string.charAt(6)+string.charAt(7);
		int monat = string.charAt(9)+string.charAt(10);
		int jahr = string.charAt(12)+string.charAt(13)+string.charAt(14)+string.charAt(15);
		Calendar calendarObject = Calendar.getInstance();
		calendarObject.set(Calendar.YEAR, jahr);
		calendarObject.set(Calendar.MONTH, monat-1);
		calendarObject.set(Calendar.DAY_OF_MONTH, tag);
		calendarObject.set(Calendar.HOUR, stunde);
		calendarObject.set(Calendar.MINUTE, minute);
		//Date dateRepresentation = calendarObject.getTime();
		return calendarObject; 
	}
	
	public static String umrechnenZeit(Calendar calendar) {
		String output;
		int jahr = calendar.get(Calendar.YEAR);
		int monat = calendar.get(Calendar.MONTH)+1;
		int tag = calendar.get(Calendar.DAY_OF_MONTH);
		int stunde = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		output = stunde+":"+minute+" "+tag+"."+monat+"."+jahr;
		return output; 
	}
	
	public static boolean hatFahrerlaubnis(String personalnummer, String zweck) {
		boolean ok = false;
		ArrayList<Object> list = HandleXML.xmlUndNameUndWertLineareSucheZuArrayList(new File("PersonListe.xml"), "Personalnummer", personalnummer);
		Person person = (Person) list.get(0);
		String fs= person.getFuehrerschein();
		if (!fs.equals("B")&&!fs.equals("C")) {
			System.out.println("Kein Führerschein!");
		} else if (zweck.equals("Transport")&&!fs.equals("C")) {
			System.out.println("Kein Lasterführerschein!");
		} else {
			ok = true;
		}
		return ok;
	}
}