import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.input.SAXBuilder;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

class SuchBaumBSP
{
	static int persNr = 0;
	static SuchBaum baum;

	public static void Suche() throws DataConversionException
	{
		Scanner sc = new Scanner(System.in);
		baum = new SuchBaum(persNr);
		List<Element> mitarbeiterList = null;
		try
		{
			File inputFile = new File("MitarbeiterListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			Element classElement = document.getRootElement();
			mitarbeiterList = classElement.getChildren();
			for (int temp = 0; temp < mitarbeiterList.size(); temp++)
			{
				Element mitarbeiter = mitarbeiterList.get(temp);
				Element attribute = mitarbeiter.getChild("PersNr");
				persNr = Integer.parseInt(attribute.getValue());
				baum.fuegeEin(persNr);
			}
		}
		catch (JDOMException e)
		{
			e.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		baum.laufeDurch();
		System.out.print("Nach welcher Personalnummer soll gesucht werden?\n");
		int suchzahl = sc.nextInt();
		System.out.print("Die Personalnummer " + suchzahl + " wurde");
		if (baum.gefunden(suchzahl))
		{
			System.out.println(" gefunden!\n");
			List<Element> mitarbeiterList1 = null;
			try
			{
				File inputFile = new File("MitarbeiterListe.xml");
				SAXBuilder saxBuilder = new SAXBuilder();
				Document document = saxBuilder.build(inputFile);
				Element classElement = document.getRootElement();
				mitarbeiterList1 = classElement.getChildren();
				for (int temp = 0; temp < mitarbeiterList1.size(); temp++)
				{
					Element mitarbeiter1 = mitarbeiterList1.get(temp);
					Element attribute1 = mitarbeiter1.getChild("PersNr");
					persNr = Integer.parseInt(attribute1.getValue());
					if(persNr == suchzahl)
					{
						System.out.println("\nElement:	   " + mitarbeiter1.getName());	
						System.out.println("Nachname:          " + mitarbeiter1.getChild("Nachname").getText());
						System.out.println("Vorname:           " + mitarbeiter1.getChild("Vorname").getText());
						System.out.println("Fuehrerschein:     " + mitarbeiter1.getChild("Fuehrerschein").getText());
						System.out.println("PersNr:            " + mitarbeiter1.getChild("PersNr").getText());
					}
				}
			}
			catch (JDOMException e)
			{
				e.printStackTrace();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		else
		{
			System.out.println(" nicht gefunden.");
		}
		sc.close();
	}
}