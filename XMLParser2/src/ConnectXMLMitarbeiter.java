import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Calendar;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

public class ConnectXMLMitarbeiter extends ConnectXML{
 
	   public static List<Element> readMitarbeiterListe(){
		   List<Element> mitarbeiterList = null;   
		   try {
		         File inputFile = new File("MitarbeiterListe.xml");
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);
		         Element rootNode = document.getRootElement();

		         mitarbeiterList = rootNode.getChildren();

		         for (int i = 0; i < mitarbeiterList.size(); i++) {    
		            Element mitarbeiter = mitarbeiterList.get(i);
		            Attribute attribute =  mitarbeiter.getAttribute("MitarbeiterID");
		         }
		      }catch(JDOMException e){
		         e.printStackTrace();
		      }catch(IOException ioe){
		         ioe.printStackTrace();
		      }
			return mitarbeiterList;
		   }
	   
	   public static void einfügenMitarbeiter(String nname, String vname, boolean fschein, long persNr) throws JDOMException{
		   try {
		         File inputFile = new File("MitarbeiterListe.xml");	//Zugriff auf XML Datei
		         SAXBuilder saxBuilder = new SAXBuilder();
		         Document document = saxBuilder.build(inputFile);
		         
		         String tempID = createUniqueID("MitarbeiterListe.xml");
		         Element nMitarbeiter= new Element ("Mitarbeiter");
		         nMitarbeiter.setAttribute(new Attribute ("MitarbeiterID", tempID));
		         Element mNachname= new Element ("Nachname");
		         mNachname.setText(nname);
		         Element mVorname= new Element ("Vorname");
		         mVorname.setText(vname);
		         Element mFuehrerschein= new Element ("Fuehrerschein");
		         mFuehrerschein.setText(Objects.toString(fschein));
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
		         xmlOutput.output(document, new FileWriter(inputFile));  
		      }catch(IOException e){
		         e.printStackTrace();
		      }	
		   }	
}