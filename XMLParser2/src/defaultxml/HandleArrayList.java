package defaultxml;

import ch.makery.address.model.Person;
import ch.makery.address.model.Vehicle;
import ch.makery.address.model.Buchen;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class HandleArrayList {

	public static void printArrayList(ArrayList<Object> arrayList) {
		if (arrayList.isEmpty()) {
			System.out.println("Diese Liste ist leer!");
		}
		for (int i = 0; i < arrayList.size(); i++) {
			printObject(arrayList.get(i));
		}
	}

	public static void printObject(Object object) {
		if (object instanceof Person) {
			String nn = ((Person) object).getNachname();
			String vn = ((Person) object).getVorname();
			String fu = ((Person) object).getFuehrerschein();
			String pe = ((Person) object).getPersonalnummer();
			System.out.printf("Name: %s, Vorname: %s, Führerschein: %s, Personalnummer: %s\n", nn, vn, fu, pe);
		} else if (object instanceof Vehicle) {
			String ty = ((Vehicle) object).getTyp();
			String ke = ((Vehicle) object).getKennzeichen();
			System.out.printf("Typ: %s, Kennzeichen: %s\n", ty, ke);
		} else if (object instanceof Buchen) {
			String nn = ((Buchen) object).getNachname();
			String vn = ((Buchen) object).getVorname();
			String pe = ((Buchen) object).getPersonalnummer();
			String fu = ((Buchen) object).getFuehrerschein();
			String ke = ((Buchen) object).getKennzeichen();
			String ty = ((Buchen) object).getTyp();
			String zw = ((Buchen) object).getZweck();
			String von = ((Buchen) object).getVon();
			String bis = ((Buchen) object).getBis();
			String dauer = ((Buchen) object).getDauer();
			System.out.printf(
					"Name: %s, Vorname: %s, Personalnummer: %s, Führerschein %S, Kennzeichen: %s, Typ: %s, Zweck: %s, Von: %s, Bis %s, Dauer: %s\n",
					nn, vn, pe, fu, ke, ty, zw, von, bis, dauer);
		}
	}

	public static Vehicle sucheFreiesAutoArrayList(ArrayList<Object> vehicles, ArrayList<Object> buchungen,
			Calendar neuVon, Calendar neuBis, String zweck) {
		Vehicle bingo = null;
		ArrayList<String> zuSuchen = new ArrayList<String>();
		switch (zweck) {
		case "Stadtfahrt":
			System.out.println("stadtfahrt, also city oder limo");
			zuSuchen.add("Cityflitzer");
			zuSuchen.add("Limousine");
			break;
		case "Langstreckenfahrt":
			System.out.println("langfahrt, also limo oder city");
			zuSuchen.add("Limousine");
			zuSuchen.add("Cityflitzer");
			break;
		case "Transport":
			System.out.println("transport, also lkw oder transp");
			zuSuchen.add("Lkw");
			zuSuchen.add("Transporter");
		}
		System.out.println("mache ein array aus vehicle objekten die vom typ sind der gesucht wird");
		if (!zuSuchen.isEmpty()) {
			for (int i = 0; i < zuSuchen.size(); i++) {
				for (int j = 0; j < vehicles.size(); j++) {
					Vehicle veh = ((Vehicle) vehicles.get(j));
					if (veh.getTyp().equals(zuSuchen.get(i))) {
						String ken = veh.getKennzeichen();
						if (buchungen.isEmpty()) {
							// bingo weil keine buchungen vorhanden
							bingo = veh;
						}
						for (int k = 0; k < buchungen.size(); k++) {
							Buchen buc = ((Buchen) buchungen.get(k));
							if (buc.getKennzeichen().equals(ken)) {
								Calendar von = Verwaltung.umrechnenZeit(buc.getVon());
								Calendar bis = Verwaltung.umrechnenZeit(buc.getBis());
								// neuVon>=von &&neuBis<=bis ||
								if ((neuVon.compareTo(von) >= 0) && neuBis.compareTo(bis) <= 0 ||
								// neuVon>=von &&neuVon<=bis ||
										(neuVon.compareTo(von) >= 0) && (neuVon.compareTo(bis) <= 0) ||
										// neuBis<=bis &&neuBis>=von
										(neuBis.compareTo(bis) <= 0) && (neuBis.compareTo(von) >= 0)) {
									// Überschneidung, abbruch für dieses
									// vehicle
									break;
								}
							}
							if (k == buchungen.size() - 1) {
								// hat es bis hierher geschafft, ohne konflikt
								// bingo
								bingo = veh;
							}
						}
					}
				}
			}
		}
		return bingo;
	}

	public static ArrayList<Object> ArrayListUndNameUndWerteLineareSucheZuArrayList(ArrayList<Object> arrayList,
			String zuSuchenderAttributName, ArrayList<String> gesuchteWerte) {
		ArrayList<Object> gefundeneObjekte = new ArrayList<Object>();
		if (!arrayList.isEmpty() || gesuchteWerte.isEmpty()) {
			if (arrayList.get(0) instanceof Person) {
				for (int i = 0; i < arrayList.size(); i++) {
					Person p = (Person) arrayList.get(i);
					switch (zuSuchenderAttributName) {
					case "Nachname":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (p.getNachname().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(p);
							}
						}
						break;
					case "Vorname":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (p.getVorname().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(p);
							}
						}
						break;
					case "Fuehrerschein":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (p.getFuehrerschein().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(p);
							}
						}
						break;
					case "Personalnummer":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (p.getPersonalnummer().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(p);
							}
						}
						break;
					}
				}
			} else if (arrayList.get(0) instanceof Vehicle) {
				for (int i = 0; i < arrayList.size(); i++) {
					Vehicle v = (Vehicle) arrayList.get(i);
					switch (zuSuchenderAttributName) {
					case "Typ":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (v.getTyp().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(v);
							}
						}
						break;
					case "Geliehen":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (v.getGeliehen().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(v);
							}
						}
						break;
					case "Kennzeichen":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (v.getKennzeichen().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(v);
							}
						}
						break;
					}
				}
			} else {
				for (int i = 0; i < arrayList.size(); i++) {
					Buchen b = (Buchen) arrayList.get(i);
					switch (zuSuchenderAttributName) {
					case "Nachname":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (b.getNachname().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(b);
							}
						}
						break;
					case "Vorname":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (b.getVorname().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(b);
							}
						}
						break;
					case "Personalnummer":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (b.getPersonalnummer().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(b);
							}
						}
						break;
					case "Fuehrerschein":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (b.getFuehrerschein().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(b);
							}
						}
						break;
					case "Kennzeichen":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (b.getKennzeichen().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(b);
							}
						}
						break;
					case "Typ":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (b.getTyp().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(b);
							}
						}
						break;
					case "Zweck":
						for (int j = 0; j < gesuchteWerte.size(); j++) {
							if (b.getZweck().equals(gesuchteWerte.get(j))) {
								gefundeneObjekte.add(b);
							}
						}
						break;
					}
				}
			}
		}
		return gefundeneObjekte;
	}

	public static ArrayList<Object> getAmOeftestenGelieheneVehicle(ArrayList<Object> arrayListBuchen,
			ArrayList<Object> arrayListVehicle) {
		ArrayList<Object> gefunden = new ArrayList<Object>(); // Hier werden
																// Ergebnisse
																// sortiert
		for (int i = 0; i < arrayListVehicle.size(); i++) { // Array wird von
															// arrayListVehicle
															// kopiert
			gefunden.add(i, arrayListVehicle.get(i));
		}
		ArrayList<Integer> tempA = new ArrayList<Integer>();
		for (int i = 0; i < arrayListVehicle.size(); i++) {
			String kennzeichen = ((Vehicle) arrayListVehicle.get(i)).getKennzeichen();
			ArrayList<String> kennzeichenAsListElement = new ArrayList<String>(Arrays.asList(kennzeichen));
			Integer anzahl = ArrayListUndNameUndWerteLineareSucheZuArrayList(arrayListBuchen, "Kennzeichen",
					kennzeichenAsListElement).size();
			tempA.add(i, anzahl);
		}
		Integer highestValue = 0;
		for (int i = 0; i < tempA.size(); i++) {
			Object objectAtCounterI = gefunden.get(i);
			int highestPosition = i;
			for (int j = i + 1; j < gefunden.size(); j++) {
				highestValue = tempA.get(j);
				if (highestValue > tempA.get(highestPosition)) {
					highestPosition = j;
				}
			}
			tempA.set(highestPosition, tempA.get(i));
			gefunden.set(i, gefunden.get(highestPosition));
			tempA.set(i, highestValue);
			gefunden.set(highestPosition, objectAtCounterI);
		}
		return gefunden;
	}

	public static ArrayList<String> getDurchschnittlichGenutzt(ArrayList<Object> arrayListVehicle,
			ArrayList<Object> arrayListBuchen) {
		ArrayList<String> gefunden = new ArrayList<String>();
		for (int i = 0; i < arrayListVehicle.size(); i++) {
			String durchschnittInMinuten;
			Vehicle vehicle = (Vehicle) arrayListVehicle.get(i);
			String kennzeichen = vehicle.getKennzeichen();
			BigInteger zeitBigInt = new BigInteger("0");
			BigInteger zeit = new BigInteger("0");
			BigInteger minuten = new BigInteger("0");
			BigInteger counter = new BigInteger("0");
			BigInteger eins = new BigInteger("1");
			boolean hatBuchung = false;
			for(int j = 0; j < arrayListBuchen.size(); j++){
				Buchen buchen = (Buchen) arrayListBuchen.get(j);
				if(buchen.getKennzeichen().equals(kennzeichen)){
					String dauer = buchen.getDauer();
					BigInteger dauerProBuchung = new BigInteger(Verwaltung.zeitZuMinuten(dauer));
					minuten = minuten.add(dauerProBuchung);
					counter=counter.add(eins);
					hatBuchung = true;
				}
			}
			if (hatBuchung) {
				BigInteger alleMinutenProFahrzeug = zeit.add(minuten);
				zeitBigInt = (alleMinutenProFahrzeug).divide(new BigInteger(counter.toString()));
				durchschnittInMinuten = zeitBigInt.toString();
				gefunden.add(Verwaltung.minutenZuZeit(durchschnittInMinuten));
			
			} else {
				gefunden.add("keine Buchungen bisher.");
			}
		}
		return gefunden;
	}
			
/*
			// dann füge tempzeit zur zeit hinzu
			x = zeit;
			gefunden.add(x);
		}
		ArrayList<Object> ausgeliehen = getAmOeftestenGelieheneVehicle(arrayListBuchen, arrayListVehicle);
		
		  for (int i=0; i<ausgeliehen.size();i++) { Vehicle vehicle =
		  (Vehicle)ausgeliehen.get(i); String kennzeichen =
		  vehicle.getKennzeichen(); ArrayList<String> kennzeichenAsListElement
		  = new ArrayList<String>(Arrays.asList(kennzeichen));
		  ArrayListUndNameUndWerteLineareSucheZuArrayList(ausgeliehen, zuSuchenderAttributName, gesuchteWerte);
		  }
		 

		return gefunden;
	}*/

	public static ArrayList<Object> inArrayListAnhaengen(ArrayList<Object> arrayList, Object objekt) {
		if (!arrayList.isEmpty() && (objekt instanceof Person && arrayList.get(0) instanceof Person))
			arrayList.add(objekt);
		else if (!arrayList.isEmpty() && (objekt instanceof Vehicle && arrayList.get(0) instanceof Vehicle))
			arrayList.add(objekt);
		else if (!arrayList.isEmpty() && (objekt instanceof Buchen && arrayList.get(0) instanceof Buchen))
			arrayList.add(objekt);
		else
			System.out.println("Kann Objekt nicht in ArrayList einfügen, Objekt ist nicht vom selben Typ!");
		return arrayList;
	}

	public static ArrayList<Object> arrayListInArrayListAnhaengen(ArrayList<Object> arrayListA,
			ArrayList<Object> arrayListB) {
		for (int i = 0; i < arrayListA.size(); i++) {
			arrayListB = inArrayListAnhaengen(arrayListB, arrayListA.get(i));
		}
		return arrayListB;
	}

	public static ArrayList<Object> personArrayListSelectionSortPNAufsteigend(ArrayList<Object> arrayList) {
		Object tempObject = null;
		ArrayList<Object> tempArray = new ArrayList<Object>();
		for (int i = 0; i < arrayList.size(); i++) {
			tempArray.add(i, arrayList.get(i));
		}
		for (int i = 0; i < tempArray.size(); i++) {
			tempObject = tempArray.get(i);
			Object lowestObject = tempArray.get(i);
			int lowestObjectPlace = i;
			for (int j = i + 1; j < tempArray.size(); j++) {
				Person a = (Person) tempArray.get(j);
				Person b = (Person) lowestObject;
				if (a.compareTo(b) < 0) {
					lowestObject = tempArray.get(j);
					lowestObjectPlace = j;
				}
			}
			tempArray.set(lowestObjectPlace, tempObject);
			tempArray.set(i, lowestObject);
		}
		return tempArray;
	}

	public static ArrayList<Object> vehicleArrayListSelectionSortKZAufsteigend(ArrayList<Object> arrayList) {
		Object tempObject = null;
		ArrayList<Object> tempArray = new ArrayList<Object>();
		for (int i = 0; i < arrayList.size(); i++) {
			tempArray.add(i, arrayList.get(i));
		}
		for (int i = 0; i < tempArray.size(); i++) {
			tempObject = tempArray.get(i);
			Object lowestObject = tempArray.get(i);
			int lowestObjectPlace = i;
			for (int j = i + 1; j < tempArray.size(); j++) {
				String a = ((Vehicle) tempArray.get(j)).getKennzeichen();
				String b = ((Vehicle) lowestObject).getKennzeichen();
				if (a.compareTo(b) < 0) {
					lowestObject = tempArray.get(j);
					lowestObjectPlace = j;
				}
			}
			tempArray.set(lowestObjectPlace, tempObject);
			tempArray.set(i, lowestObject);
		}
		return tempArray;
	}
}