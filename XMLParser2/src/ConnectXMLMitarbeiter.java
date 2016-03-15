import java.io.File;
import java.io.IOException;
import java.util.List;
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
		         File inputFile = new File("Mitarbeiter.xml");
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
		            System.out.println("Nachname :         " + mitarbeiter.getChild("Nachname").getText());
		            System.out.println("Vorname:           " + mitarbeiter.getChild("Vorname").getText());
		            System.out.println("Standort:          " + mitarbeiter.getChild("Standort").getText());
		            System.out.println("Fuehrerschein:     " + mitarbeiter.getChild("Fuehrerschein").getText());	   
		            System.out.println("Geburtstag:        " + mitarbeiter.getChild("Geburtstag").getText());
		         }
		      }catch(JDOMException e){
		         e.printStackTrace();
		      }catch(IOException ioe){
		         ioe.printStackTrace();
		      }
		   }
	   
	   public static void einfügenMitarbeiter(String nname, String vname, String standort, String fschein, Calendar geburtstag) throws JDOMException{
		   try {
		         File inputFile = new File("Mitarbeiter.xml");	//Zugriff auf XML Datei
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);
		         String tempID= createUniqueID("Mitarbeiter.xml");
		         Element nMitarbeiter= new Element ("Mitarbeiter"); //Neues Mitarbeiterelement
		         nMitarbeiter.setAttribute(new Attribute ("MitarbeiterID", tempID));
		         Element mNachname= new Element ("Nachname");
		         mNachname.setText(nname);
		         Element mVorname= new Element ("Vorname");
		         mVorname.setText(vname);
		         Element mStandort= new Element ("Standort");
		         mStandort.setText(standort);
		         Element mFuehrerschein= new Element ("Fuehrerschein");
		         mFuehrerschein.setText(fschein);
		         String tempgeburtstag= geburtstag.toString();
		         Element mGeburtstag= new Element ("Geburtstag");
		         mGeburtstag.setText(tempgeburtstag);
		            
		         nMitarbeiter.addContent(mNachname);
		         nMitarbeiter.addContent(mVorname);
		         nMitarbeiter.addContent(mStandort);
		         nMitarbeiter.addContent(mFuehrerschein);
		         nMitarbeiter.addContent(mGeburtstag);

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