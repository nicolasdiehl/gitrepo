import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Calendar;

import org.jdom2.*;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ConnectXMLMitarbeiter extends ConnectXML{
//	   public static void main(String[] args) throws JDOMException{
//	   }
 
	   public static void readMitarbeiterListe(){
		      try {
		         File inputFile = new File("MitarbeiterListe.xml");
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);

		         System.out.println("Root element :" + document.getRootElement().getName());
		         Element classElement = document.getRootElement();

		         List<Element> mitarbeiterList = classElement.getChildren();
		         System.out.println("----------------------------");

		         for (int temp = 0; temp < mitarbeiterList.size(); temp++) {    
		            Element mitarbeiter = mitarbeiterList.get(temp);
		            System.out.println("\n Current Element :" 
		               + mitarbeiter.getName());
		            Attribute attribute =  mitarbeiter.getAttribute("MitarbeiterID");
		            System.out.println("Fuhrpark roll no : " 
		               + attribute.getValue() );
		            System.out.println("Nachname:         " + mitarbeiter.getChild("Nachname").getText());
		            System.out.println("Vorname:           " + mitarbeiter.getChild("Vorname").getText());
		            System.out.println("Fuehrerschein:     " + mitarbeiter.getChild("Fuehrerschein").getText());	   
		            System.out.println("PersNr:        " + mitarbeiter.getChild("PersNr").getText());
		         }
		      }catch(JDOMException e){
		         e.printStackTrace();
		      }catch(IOException ioe){
		         ioe.printStackTrace();
		      }
		   }
	   
	   public static void einfügenMitarbeiter(String nname, String vname, String fschein, long persNr) throws JDOMException{
		   try {
		         File inputFile = new File("MitarbeiterListe.xml");	//Zugriff auf XML Datei
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);
		         String tempID= createUniqueID("MitarbeiterListe.xml");
		         Element nMitarbeiter= new Element ("Mitarbeiter"); //Neues Mitarbeiterelement
		         nMitarbeiter.setAttribute(new Attribute ("MitarbeiterID", tempID));
		         Element mNachname= new Element ("Nachname");
		         mNachname.setText(nname);
		         Element mVorname= new Element ("Vorname");
		         mVorname.setText(vname);
		         Element mFuehrerschein= new Element ("Fuehrerschein");
		         mFuehrerschein.setText(fschein);
		         Element mPersNr= new Element ("PersNr");
		         mPersNr.setText(Objects.toString(persNr, null));
		            
		         nMitarbeiter.addContent(mNachname);
		         nMitarbeiter.addContent(mVorname);
		         nMitarbeiter.addContent(mFuehrerschein);
		         nMitarbeiter.addContent(mPersNr);

		         document.getRootElement().addContent(nMitarbeiter);

		         XMLOutputter xmlOutput = new XMLOutputter();

		         // display xml
		         xmlOutput.setFormat(Format.getPrettyFormat());
		         xmlOutput.output(document, System.out); 
		      }catch(IOException e){
		         e.printStackTrace();
		      }	
		   }//schließt Methode	
}