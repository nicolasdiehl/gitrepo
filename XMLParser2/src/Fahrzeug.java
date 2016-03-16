public class Fahrzeug {

	public static String fahrzeugtyp;
	public static boolean geliehen;
	public static String zweck;
	public static String kennzeichen;

	protected Fahrzeug(String fahrzeugtyp, boolean geliehen, String zweck, String kennzeichen){ //Konstruktor Fahrzeug
		this.fahrzeugtyp = fahrzeugtyp;
		this.geliehen = geliehen;
		this.zweck = zweck;
		this.kennzeichen = kennzeichen;
	}

	public static String getFahrzeugtyp() {
		return fahrzeugtyp;
	}

	public static void setFahrzeugtyp(String fahrzeugtyp) {
		Fahrzeug.fahrzeugtyp = fahrzeugtyp;
	}

	public static boolean isGeliehen() {
		return geliehen;
	}

	public static void setGeliehen(boolean geliehen) {
		Fahrzeug.geliehen = geliehen;
	}

	public static String getZweck() {
		return zweck;
	}

	public static void setZweck(String zweck) {
		Fahrzeug.zweck = zweck;
	}
	
	public static String getKennzeichen() {
		return kennzeichen;
	}

	public static void setKennzeichen(String kennzeichen) {
		Fahrzeug.kennzeichen = kennzeichen;
	}
}