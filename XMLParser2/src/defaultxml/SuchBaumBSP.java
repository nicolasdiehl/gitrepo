package defaultxml;
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
	static int personalnummer = 0;
	static SuchBaum baum;

	public static void Suche() throws DataConversionException
	{
		Scanner sc = new Scanner(System.in);
		baum = new SuchBaum(personalnummer);
		List<Element> personList = null;
		try
		{
			File inputFile = new File("PersonListe.xml");
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(inputFile);
			Element classElement = document.getRootElement();
			personList = classElement.getChildren();
			for (int temp = 0; temp < personList.size(); temp++)
			{
				Element person = personList.get(temp);
				Element attribute = person.getChild("PersNr");
				personalnummer = Integer.parseInt(attribute.getValue());
				baum.fuegeEin(personalnummer);
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
			List<Element> personList1 = null;
			try
			{
				File inputFile = new File("PersonListe.xml");
				SAXBuilder saxBuilder = new SAXBuilder();
				Document document = saxBuilder.build(inputFile);
				Element classElement = document.getRootElement();
				personList1 = classElement.getChildren();
				for (int temp = 0; temp < personList1.size(); temp++)
				{
					Element person1 = personList1.get(temp);
					Element attribute1 = person1.getChild("PersNr");
					personalnummer = Integer.parseInt(attribute1.getValue());
					if(personalnummer == suchzahl)
					{
						System.out.println("\nElement:	   " + person1.getName());	
						System.out.println("Nachname:          " + person1.getChild("Nachname").getText());
						System.out.println("Vorname:           " + person1.getChild("Vorname").getText());
						System.out.println("Fuehrerschein:     " + person1.getChild("Fuehrerschein").getText());
						System.out.println("PersNr:            " + person1.getChild("PersNr").getText());
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