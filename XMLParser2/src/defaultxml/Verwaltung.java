package defaultxml;
import java.util.Scanner;

import org.jdom2.JDOMException;

import ch.makery.address.model.Buchen;
import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import static java.util.Arrays.asList;

public class Verwaltung {
	public static void main(String[] args) throws JDOMException, IOException {
		// Monate beginnen mit 0
		// Tage im Monat beginnen mit 1
		String str = "08:01 06.07.2016";
		System.out.println("zeile 24: str="+str);
		Calendar cal = umrechnenZeit(str);
		str = umrechnenZeit(cal);
		System.out.println("zeile 27: str="+str);
		Scanner scan = new Scanner(System.in);
		String eingabe;
		File personFile = new File("PersonListe.xml");
		File vehicleFile = new File("VehicleListe.xml");
		File buchenFile = new File("Buchen.xml");
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
				HandleXML.inXmlAnhängen(new File("Buchen.xml"), einlesenBuchen());
				break;
			case "0":
				HandleXML.xmlZuArrayList(personFile);
				break;
			case "1":
				HandleXML.xmlZuArrayList(vehicleFile);
				break;
			case "2":
				HandleXML.xmlZuArrayList(buchenFile);
				break;
			case "3":
				HandleXML.inXmlAnhängen(personFile, einlesenPerson());
				break;
			case "4":
				HandleXML.inXmlAnhängen(vehicleFile, einlesenVehicle());
				break;
			case "5":
				HandleXML.inXmlAnhängen(buchenFile, einlesenBuchen());
				break;
			case "6":
				//HandleXML.
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
		SuchBaumBSP.Suche();*/
		
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
		String idtemp=null;
		try {
			File file = new File("VehicleListe.xml");
			idtemp = ConnectXML.createUniqueID(file);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = idtemp;
		String typ = einlesenText("Typ: ");
		String geliehen = "nein";
		String kennzeichen = einlesenText("Kennzeichen: ");
		Vehicle neuesVehicle = new Vehicle(id, typ, geliehen, kennzeichen);
		return neuesVehicle;
	}

	public static Buchen einlesenBuchen() {
		Buchen newBuchen=null;
		String zweck = "";
		String personalnummer = einlesenText("Personalnummer: ");
		if (HandleXML.xmlUndNameUndWerteLineareSucheZuArrayList(new File("PersonListe.xml"), "Personalnummer", new ArrayList<>(asList(personalnummer))).isEmpty()) {
			System.out.println("Personalnummer nicht vorhanden!");
		} else {
			zweck = einlesenText("Zweck: ((t)ransport, (s)tadtfahrt, (l)angstreckenfahrt)");
			switch (zweck) {
				case "t": zweck= "Transport";
				break;
				case "s": zweck= "Stadtfahrt";
				break;
				case "l": zweck = "Langstreckenfahrt";
			}
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
					String kennzeichen = HandleXML.sucheFreiesAuto(zweck, calVon, calBis);
					if (kennzeichen.equals("")) {
						System.out.println("kein passendes Fahrzeug gefunden, Fahrrad?");
					} else {
						// auto gefunden!
						newBuchen = new Buchen("vn", "nn", personalnummer, "fs", kennzeichen, "tp", zweck, umrechnenZeit(calVon), umrechnenZeit(calBis), "dr");
					}
				}
			}
		}
		return newBuchen;
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
	// 01234567891123456
	// 13:48 11.06.2018
	public static Calendar umrechnenZeit(String string) {
		System.out.println("umrechnenZeitString: "+string);
		int stunde = Integer.parseInt(""+string.charAt(0)+""+string.charAt(1));
		int minute = Integer.parseInt(""+string.charAt(3)+""+string.charAt(4));
		int tag = Integer.parseInt(""+string.charAt(6)+""+string.charAt(7));
		int monat = Integer.parseInt(""+string.charAt(9)+""+string.charAt(10));
		int jahr = Integer.parseInt(""+string.charAt(12)+""+string.charAt(13)+""+string.charAt(14)+""+string.charAt(15));
		Calendar calendarObject = Calendar.getInstance();
		calendarObject.set(Calendar.YEAR, jahr);
		calendarObject.set(Calendar.MONTH, monat-1);
		calendarObject.set(Calendar.DAY_OF_MONTH, tag);
		calendarObject.set(Calendar.HOUR_OF_DAY, stunde);
		calendarObject.set(Calendar.MINUTE, minute);
		//Date dateRepresentation = calendarObject.getTime();
		return calendarObject; 
	}
	
	public static String umrechnenZeit(Calendar calendar) {
		String output;
		String jahr = String.valueOf(calendar.get(Calendar.YEAR));
		String monat = String.valueOf(calendar.get(Calendar.MONTH)+1);
		String tag = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String stunde = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(calendar.get(Calendar.MINUTE));
		if (Integer.parseInt(monat)<10) monat = "0"+monat;
		if (Integer.parseInt(tag)<10) tag = "0"+tag;
		if (Integer.parseInt(stunde)<10) stunde = "0"+stunde;
		if (Integer.parseInt(minute)<10) minute = "0"+minute;
		output = stunde+":"+minute+" "+tag+"."+monat+"."+jahr;
		System.out.println("String umrechnenZeitCalendar "+output);
		return output;
	}
	
	public static boolean hatFahrerlaubnis(String personalnummer, String zweck) {
		boolean ok = false;
		ArrayList<Object> list = HandleXML.xmlUndNameUndWerteLineareSucheZuArrayList(new File("PersonListe.xml"), "Personalnummer", new ArrayList<>(asList(personalnummer)));
		if (list.isEmpty()) {
			System.out.println("list is empty");
		}
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