public class Vehicle {

	public static String typ;
	public static boolean geliehen;
	public static String zweck;
	public static String kennzeichen;
	// Konstruktor
	protected Vehicle(String typ, boolean geliehen, String zweck, String kennzeichen){ //Konstruktor Vehicle
		this.typ = typ;
		this.geliehen = geliehen;
		this.zweck = zweck;
		this.kennzeichen = kennzeichen;
	}
	// Getter und setter
	public static String getTyp() {
		return typ;
	}

	public static void setTyp(String typ) {
		Vehicle.typ = typ;
	}

	public static boolean isGeliehen() {
		return geliehen;
	}

	public static void setGeliehen(boolean geliehen) {
		Vehicle.geliehen = geliehen;
	}

	public static String getZweck() {
		return zweck;
	}

	public static void setZweck(String zweck) {
		Vehicle.zweck = zweck;
	}
	
	public static String getKennzeichen() {
		return kennzeichen;
	}

	public static void setKennzeichen(String kennzeichen) {
		Vehicle.kennzeichen = kennzeichen;
	}
}