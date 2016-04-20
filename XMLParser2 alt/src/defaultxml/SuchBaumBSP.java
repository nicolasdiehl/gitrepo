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

public class SuchBaumBSP
{
	static int personalnummer;
	static SuchBaum baum;
	String tempNachname;
	String tempVorname;
	String tempFuehrerschein;
	String tempPersonalnummer;
	
	public void Suche(int suchzahl)
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
				Element persnr = person.getChild("Personalnummer");
				personalnummer = Integer.parseInt(persnr.getValue());
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
					Element persnr1 = person1.getChild("Personalnummer");
					personalnummer = Integer.parseInt(persnr1.getValue());
					if(personalnummer == suchzahl)
					{
						System.out.println("\nElement:	   " + person1.getName());	
						System.out.println("Nachname:          " + person1.getChild("Nachname").getText());
						System.out.println("Vorname:           " + person1.getChild("Vorname").getText());
						System.out.println("Fuehrerschein:     " + person1.getChild("Fuehrerschein").getText());
<<<<<<< HEAD
						System.out.println("Personalnummer:    " + person1.getChild("Personalnummer").getText());
						tempNachname = person1.getChild("Nachname").getText();
						tempVorname = person1.getChild("Vorname").getText();
						tempFuehrerschein = person1.getChild("Fuehrerschein").getText();
						tempPersonalnummer = person1.getChild("Personalnummer").getText();
=======
						System.out.println("PersNr:            " + person1.getChild("Personalnummer").getText());
>>>>>>> branch 'ohne_fxml' of https://github.com/nicolasdiehl/gitrepo.git
						
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

	public String getTempNachname() {
		return tempNachname;
	}

	public void setTempNachname(String tempNachname) {
		this.tempNachname = tempNachname;
	}

	public String getTempVorname() {
		return tempVorname;
	}

	public void setTempVorname(String tempVorname) {
		this.tempVorname = tempVorname;
	}

	public String getTempFuehrerschein() {
		return tempFuehrerschein;
	}

	public void setTempFuehrerschein(String tempFuehrerschein) {
		this.tempFuehrerschein = tempFuehrerschein;
	}

	public String getTempPersonalnummer() {
		return tempPersonalnummer;
	}

	public void setTempPersonalnummer(String tempPersonalnummer) {
		this.tempPersonalnummer = tempPersonalnummer;
	}
}