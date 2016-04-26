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
import java.nio.file.Files;

import static java.util.Arrays.asList;

public class Verwaltung {
	public static void main(String[] args) throws JDOMException, IOException {
		File file = null;
		System.out.println("Lese Standard-Dateien \"PersonListe.xml\", \"VehicleListe.xml\" und \"BuchenListe.xml\"");
		File personFile = new File("PersonListe.xml");
		File vehicleFile = new File("VehicleListe.xml");
		File buchenFile = new File("BuchenListe.xml");
		ArrayList<Object> arrayListPerson = HandleXML.xmlZuArrayList(personFile, false);
		ArrayList<Object> arrayListVehicle = HandleXML.xmlZuArrayList(vehicleFile, false);
		ArrayList<Object> arrayListBuchen = HandleXML.xmlZuArrayList(buchenFile, false);		
		Scanner scan = new Scanner(System.in);
		System.out.println("Was darfs denn heute sein?");
		String eingabe;
		while (true) {
			System.out.println("0,1,2: Personen, Fahrzeuge oder Buchungen ausgeben");
			System.out.println("3,4,5: Neue Person, Fahrzeug oder Buchung anlegen");
			System.out.println("6,7,8: Eine Person, Fahrzeug oder Buchung verändern");
			System.out.println("s: Etwas suchen");
			System.out.println("k,l,m: Person,Wagen,Buchen XML laden");
			System.out.println("x,y,z: Person,Wagen,Buchen XML speichern");
			System.out.println("r: Alle Listen leeren.");
			System.out.println("b: Beliebteste Fahrzeuge ausgeben.");
			eingabe = scan.nextLine();
			switch (eingabe) {
			case "0":
				HandleArrayList.printArrayList(arrayListPerson);
				break;
			case "1":
				HandleArrayList.printArrayList(arrayListVehicle);
				break;
			case "2":
				HandleArrayList.printArrayList(arrayListBuchen);
				break;
			case "3":
				HandleArrayList.inArrayListAnhaengen(arrayListPerson,einlesenPerson());
				break;
			case "4":
				HandleArrayList.inArrayListAnhaengen(arrayListVehicle,einlesenVehicle());
				break;
			case "5":
				HandleArrayList.inArrayListAnhaengen(arrayListBuchen,einlesenBuchen(arrayListPerson, arrayListVehicle, arrayListBuchen));
				break;
			case "6":
				//HandleXML.
				break;
			case "7":
				
				break;
			case "8":
				
				break;
			case "b":
				HandleXML.getBeliebtestesFahrzeug(); // noch nicht optimal, noch nicht in gui
				break;
			case "s":
				ArrayList<Object> gefunden = new ArrayList<Object>();
				gefunden = suchenDialog(arrayListPerson, arrayListVehicle, arrayListBuchen);
				System.out.println("Liste der gefundenen Objekte:");
				HandleArrayList.printArrayList(gefunden);
				System.out.println("Soll die Liste in ein XML gespeichert werden? j n");
				eingabe = scan.nextLine();
				if (eingabe.equals("j")) {
					System.out.println("Welche Datei soll verwendet werden? Leerlassen um in Standard-Datei zu schreiben.");
					file = new File(scan.nextLine());
					if (!gefunden.isEmpty() && gefunden.get(0) instanceof Person) HandleXML.arrayListZuXml(gefunden, file, "Person");
					else if (!gefunden.isEmpty() && gefunden.get(0) instanceof Vehicle) HandleXML.arrayListZuXml(gefunden, file, "Vehicle");
					else if (!gefunden.isEmpty() && gefunden.get(0) instanceof Buchen) HandleXML.arrayListZuXml(gefunden, file, "Buchen");
					else System.out.println("Liste ist leer, wurde nicht gespeichert!");
				}
				break;
			case "k":
				System.out.println("Welche Datei soll verwendet werden?");
				file = new File(scan.nextLine());
				arrayListPerson=HandleXML.xmlZuArrayList(file, false);
				break;
			case "l":
				System.out.println("Welche Datei soll verwendet werden?");
				file = new File(scan.nextLine());
				arrayListVehicle=HandleXML.xmlZuArrayList(file, false);
				break;
			case "m":
				System.out.println("Welche Datei soll verwendet werden?");
				file = new File(scan.nextLine());
				arrayListBuchen=HandleXML.xmlZuArrayList(file, false);
				break;
			case "x":
				System.out.println("Welche Datei soll verwendet werden? Leerlassen um in Standard-Datei zu schreiben.");
				file = new File(scan.nextLine());
				HandleXML.arrayListZuXml(arrayListPerson,file,"Person");
				break;
			case "y":
				System.out.println("Welche Datei soll verwendet werden? Leerlassen um in Standard-Datei zu schreiben.");
				file = new File(scan.nextLine());
				HandleXML.arrayListZuXml(arrayListVehicle,file,"Vehicle");				
				break;
			case "z":
				System.out.println("Welche Datei soll verwendet werden? Leerlassen um in Standard-Datei zu schreiben.");
				file = new File(scan.nextLine());
				HandleXML.arrayListZuXml(arrayListBuchen,file,"Buchen");
				break;
			case "r":
				arrayListPerson.clear();
				arrayListVehicle.clear();
				arrayListBuchen.clear();
				System.out.println("Alle internen Listen wurden gelöscht. Die XML Daten sind noch vorhanden.");
				break;
			default:
				System.out.println("falsche eingabe.. nochmal");
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
		String geliehen="";
		String kennzeichen="";
		try {
			File file = new File("VehicleListe.xml");
			idtemp = ConnectXML.createUniqueID(file);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = idtemp;
		String typ = einlesenText("Typ: (Cityflitzer, Lkw. Limousine oder Transporter)");
		if (!typ.equals("Cityflitzer")&&!typ.equals("Lkw")&&!typ.equals("Limousine")&&!typ.equals("Transporter")) {
			geliehen = "nein";
			kennzeichen = einlesenText("Kennzeichen: ");
			
		}
		return new Vehicle(id, typ, geliehen, kennzeichen);
	}

	public static Buchen einlesenBuchen(ArrayList<Object> perA, ArrayList<Object> vehA, ArrayList<Object> bucA) throws JDOMException, IOException {
		Buchen newBuchen=null;
		String zweck = "";
		String personalnummer = einlesenText("Personalnummer: ");
		//System.out.println("personalnummer wird in xml gesucht und die person zurückgegeben");
		ArrayList<Object> temp = HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(perA,"Personalnummer",new ArrayList<>(Arrays.asList(personalnummer)));
				if (temp.isEmpty()) {
			System.out.println("Personalnummer nicht vorhanden!");
		} else {
			Person person = (Person)(temp).get(temp.size()-1); // nimm den letzten hinzugefügten
			HandleArrayList.printObject(person);
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
					System.out.println("freies auto wird gesucht");
					Vehicle vehicle = HandleArrayList.sucheFreiesAutoArrayList(vehA, bucA, calVon, calBis, zweck);
					if (vehicle==null) {
						System.out.println("kein passendes Fahrzeug gefunden, Fahrrad?");
					} else {
						// auto gefunden!
						System.out.println("ein freies auto wurde gefunden");
						System.out.println("nachn= "+person.getNachname());
						newBuchen = new Buchen(person.getNachname(), person.getVorname(), person.getPersonalnummer(), "fs", vehicle.getKennzeichen(), vehicle.getTyp(), zweck, umrechnenZeit(calVon), umrechnenZeit(calBis), vergleichenZeit(calVon,calBis));
					}
				}
			}
		}
		return newBuchen;
	}

	public static ArrayList<Object> suchenDialog(ArrayList<Object> arrayListPerson, ArrayList<Object> arrayListVehicle, ArrayList<Object> arrayListBuchen) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Was genau suchen Sie? Eine Person(p), einen Leihwagen(l), oder eine Buchung?(b)");
		String input = scan.nextLine();
		ArrayList<String> zuSuchen = new ArrayList<String>();
		ArrayList<Object> gefundeneObjekte = new ArrayList<Object>(); 
		switch (input) {
		case "p":
			System.out.println("Suchen Sie den Vornamen(v), Nachnamen(n), Personalnummer(p), oder den Führerscheintyp(f)?");
			input = scan.nextLine();
			switch (input) {
			case "v":
				System.out.println("Welcher Wert soll gesucht werden? (Leere Eingabe im die Suche zu starten)");
				do {
					input = scan.nextLine();
					if (!input.equals("")) {
						zuSuchen.add(input);
					}
				} while (!input.equals(""));
				gefundeneObjekte=HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayListPerson, "Vorname", zuSuchen);
				break;
			case "n":
				System.out.println("Welcher Wert soll gesucht werden? (Leere Eingabe im die Suche zu starten)");
				do {
					input = scan.nextLine();
					if (!input.equals("")) {
						zuSuchen.add(input);
					}
				} while (!input.equals(""));
				gefundeneObjekte=HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayListPerson, "Nachname", zuSuchen);
				break;
			case "p":
				System.out.println("Welcher Wert soll gesucht werden? (Leere Eingabe im die Suche zu starten)");
				do {
					input = scan.nextLine();
					if (!input.equals("")) {
						zuSuchen.add(input);
					}
				} while (!input.equals(""));
				gefundeneObjekte=HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayListPerson, "Personalnummer", zuSuchen);
				break;
			case "f":
				System.out.println("Welcher Wert soll gesucht werden? (Leere Eingabe im die Suche zu starten)");
				do {
					input = scan.nextLine();
					if (!input.equals("")) {
						zuSuchen.add(input);
					}
				} while (!input.equals(""));
				gefundeneObjekte=HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayListPerson, "Fuehrerschein", zuSuchen);
				break;
			default:
				System.out.println("falsche Eingabe!");
			}
			break;
		case "f":
			System.out.println("Suchen Sie den Fahrzeugtyp(f), das Kennzeichen(k), oder den Leihstatus(l)?");
			input = scan.nextLine();
			switch (input) {
			case "f":
				do {
					input = scan.nextLine();
					if (!input.equals("")) {
						zuSuchen.add(input);
					}
				} while (!input.equals(""));
				gefundeneObjekte=HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayListVehicle, "Typ", zuSuchen);
				break;
			case "k":
				do {
					input = scan.nextLine();
					if (!input.equals("")) {
						zuSuchen.add(input);
					}
				} while (!input.equals(""));
				gefundeneObjekte=HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayListVehicle, "Kennzeichen", zuSuchen);
				break;
			case "l":

			default:
				System.out.println("falsche Eingabe!");
			}
			break;
		case "b":
			System.out.println("Suchen Sie eine Personalnummer(f) oder ein Kennzeichen(k)?");
			input = scan.nextLine();
			switch (input) {
			case "p":
				do {
					input = scan.nextLine();
					if (!input.equals("")) {
						zuSuchen.add(input);
					}
				} while (!input.equals(""));
				gefundeneObjekte=HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayListBuchen, "Personalnummer", zuSuchen);
				break;
			case "k":
				do {
					input = scan.nextLine();
					if (!input.equals("")) {
						zuSuchen.add(input);
					}
				} while (!input.equals(""));
				gefundeneObjekte=HandleArrayList.ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayListBuchen, "Kennzeichen", zuSuchen);
				break;
			default:
				System.out.println("falsche Eingabe!");
			}
			break;
		default:
			System.out.println("falsche Eingabe!");
		}
		return gefundeneObjekte;
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
	}//12345678901234567
	// 01234567891123456
	// 13:48 11.06.2018
	public static Calendar umrechnenZeit(String string) {
		System.out.println("umrechnenZeitString: "+string);
		Calendar calendarObject = Calendar.getInstance();
		if (string.length()!=16) {
			System.out.printf("Fehler bei umrechnenZeit(String string): falsches Format!");
		} else {
			int stunde = Integer.parseInt(""+string.charAt(0)+""+string.charAt(1));
			int minute = Integer.parseInt(""+string.charAt(3)+""+string.charAt(4));
			int tag = Integer.parseInt(""+string.charAt(6)+""+string.charAt(7));
			int monat = Integer.parseInt(""+string.charAt(9)+""+string.charAt(10));
			int jahr = Integer.parseInt(""+string.charAt(12)+""+string.charAt(13)+""+string.charAt(14)+""+string.charAt(15));
			calendarObject.set(Calendar.YEAR, jahr);
			calendarObject.set(Calendar.MONTH, monat-1);
			calendarObject.set(Calendar.DAY_OF_MONTH, tag);
			calendarObject.set(Calendar.HOUR_OF_DAY, stunde);
			calendarObject.set(Calendar.MINUTE, minute);
			//Date dateRepresentation = calendarObject.getTime();
		}
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
	
	public static int elapsed(Calendar before, Calendar after, int field) {
	    Calendar clone = (Calendar) before.clone(); // Otherwise changes are been reflected.
	    int elapsed = -1;
	    while (!clone.after(after)) {
	        clone.add(field, 1);
	        elapsed++;
	    }
	    return elapsed;
	}
	
	public static String vergleichenZeit(Calendar start, Calendar end) {
		
		String output;
		Integer[] elapsed = new Integer[6];
		Calendar clone = (Calendar) start.clone(); // Otherwise changes are been reflected.
		elapsed[0] = elapsed(clone, end, Calendar.YEAR);
		clone.add(Calendar.YEAR, elapsed[0]);
		elapsed[1] = elapsed(clone, end, Calendar.MONTH);
		clone.add(Calendar.MONTH, elapsed[1]);
		elapsed[2] = elapsed(clone, end, Calendar.DATE);
		clone.add(Calendar.DATE, elapsed[2]);
		elapsed[3] = (int) (end.getTimeInMillis() - clone.getTimeInMillis()) / 3600000;
		clone.add(Calendar.HOUR, elapsed[3]);
		elapsed[4] = (int) (end.getTimeInMillis() - clone.getTimeInMillis()) / 60000;
		clone.add(Calendar.MINUTE, elapsed[4]);
		elapsed[5] = (int) (end.getTimeInMillis() - clone.getTimeInMillis()) / 1000;
		output = zeitFormat(elapsed);
		return output;
	}
	
	public static String zeitFormat(Integer[] elapsed) {
		String output="";
		if (elapsed[0]>0) output=output+elapsed[0]+"J ";
		if (elapsed[1]>0) output=output+elapsed[1]+"M ";
		if (elapsed[2]>0) output=output+elapsed[2]+"T ";
		if (elapsed[3]>0) output=output+elapsed[3]+"S ";
		if (elapsed[4]>0) output=output+elapsed[4]+"M ";
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