package defaultxml;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import ch.makery.address.model.Booking;

public class ConnectXMLBooking extends ConnectXML{

	public static List<Element> readBookingListe(){
		List<Element> bookingList = null;
		try {
			File inputFile = new File("BookingListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			System.out.println("Root element :" + document.getRootElement().getName());
			Element classElement = document.getRootElement();

			bookingList = classElement.getChildren();
			System.out.println("----------------------------");

			for (int temp = 0; temp < bookingList.size(); temp++) {    
				Element booking = bookingList.get(temp);
				System.out.println("\n Current Element :" + booking.getName());
				Attribute attribute =  booking.getAttribute("ID");
				System.out.println("Nummer :\t" + attribute.getValue() );
				System.out.println("Von:\t"+ booking.getChild("Von").getText());
				System.out.println("Bis:\t"+ booking.getChild("Bis").getText());
				System.out.println("Personalnummer:\t"+ booking.getChild("Personalnummer").getText());	            
				System.out.println("Kennzeichen:\t"+ booking.getChild("Kennzeichen").getText());
				System.out.println("Zweck:\t"+ booking.getChild("Zweck").getText());
			}
		}catch(JDOMException e){
			e.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return bookingList;
	}

	public static void einfügenBooking(String von, String bis, String personalnummer, String kennzeichen, String zweck)
			throws JDOMException{
		try {
			File inputFile = new File("BookingListe.xml"); // Zugriff auf
			// XML Datei
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);

			String tempID= createUniqueID("BookingListe.xml");
			Element neuBooking= new Element ("Booking");
			neuBooking.setAttribute(new Attribute ("ID", tempID));
			Element neuVon= new Element ("Von");
			neuVon.setText(von);
			Element neuBis= new Element ("Bis");
			neuBis.setText(bis);
			Element neuPersonalnummer= new Element ("Personalnummer");
			neuPersonalnummer.setText(personalnummer);
			Element neuKennzeichen= new Element ("Kennzeichen");
			neuKennzeichen.setText(kennzeichen);
			Element neuZweck= new Element ("Zweck");
			neuZweck.setText(zweck);

			neuBooking.addContent(neuVon);
			neuBooking.addContent(neuBis);
			neuBooking.addContent(neuPersonalnummer);	
			neuBooking.addContent(neuKennzeichen);	
			neuBooking.addContent(neuZweck);

			document.getRootElement().addContent(neuBooking);

			XMLOutputter xmlOutput = new XMLOutputter();

			// display ml
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(inputFile)); 
		}catch(IOException e){
			e.printStackTrace();
		}	
	}  

	public static void einfügenBooking(Booking booking)
			throws JDOMException{
		try {
			File inputFile = new File("BookingListe.xml"); // Zugriff auf
			// XML Datei
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);

			String tempID= createUniqueID("BookingListe.xml");
			Element neuBooking= new Element ("Booking");
			neuBooking.setAttribute(new Attribute ("ID", tempID));
			Element neuVon= new Element ("Von");
			neuVon.setText(booking.getVon());
			Element neuBis= new Element ("Bis");
			neuBis.setText(booking.getBis());
			Element neuPersonalnummer= new Element ("Personalnummer");
			neuPersonalnummer.setText(booking.getPersonalnummer());
			Element neuKennzeichen= new Element ("Kennzeichen");
			neuKennzeichen.setText(booking.getKennzeichen());
			Element neuZweck= new Element ("Zweck");
			neuZweck.setText(booking.getZweck());

			neuBooking.addContent(neuVon);
			neuBooking.addContent(neuBis);
			neuBooking.addContent(neuPersonalnummer);
			neuBooking.addContent(neuKennzeichen);		 
			neuBooking.addContent(neuZweck);

			document.getRootElement().addContent(neuBooking);

			XMLOutputter xmlOutput = new XMLOutputter();

			// display ml
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(document, new FileWriter(inputFile)); 
		}catch(IOException e){
			e.printStackTrace();
		}	
	}  
	
	public static void sucheTyp() {
		String suchtyp = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("Welchen Typ suchen Sie?");
		suchtyp = scan.next();
		List<Element> bookingList1 = null;
		try {
			File inputFile = new File("BookingListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			Element classElement = document.getRootElement();
			bookingList1 = classElement.getChildren();
			for (int temp = 0; temp < bookingList1.size(); temp++) {
				Element person1 = bookingList1.get(temp);
				Element attribute1 = person1.getChild("Typ");
				String typ = attribute1.getValue();
				if (typ.equals(suchtyp)) {
					System.out.println("\nElement:\t" + person1.getName());
					System.out.println("Typ:\t" + person1.getChild("Typ").getText());
					System.out.println("Geliehen:\t" + person1.getChild("Geliehen").getText());
					System.out.println("Zweck:\t\t" + person1.getChild("Zweck").getText());
					System.out.println("Kennzeichen:\t" + person1.getChild("Kennzeichen").getText());
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}